package oth.presentation.controller.utilisateur;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.dto.panier.PanierDto;
import oth.presentation.dto.utilisateur.ConnexionDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;
import oth.presentation.validator.ConnexionValidator;

/**
 * Controlleur pour l'écran de connexion.
 * 
 * @author Phil9175
 * 
 */
@Controller
@RequestMapping("/connexion")
public class ConnexionController {
	@Autowired
	public IServiceUtilisateur serviceUtilisateur;

	@Autowired
	private ConnexionValidator connexionValidator;

	/**
	 * Affiche la page de connexion.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showConnexion(final Model model, final HttpSession session) {
		if (session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME) != null) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		model.addAttribute("connexionDto", new ConnexionDto());
		return AbstractGlobals.PAGE_CONNEXION;
	}

	/**
	 * Vérifie la connexion à l'application.
	 * 
	 * @param connexionDto
	 *            Le DTO associé.
	 * @param model
	 *            Le modèle associé.
	 * @param result
	 *            Le résultat du binding.
	 * @param session
	 *            La session.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String validerConnexion(final @ModelAttribute("connexionDto") ConnexionDto connexionDto, final Model model,
			final BindingResult result, final HttpSession session) {

		if (session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME) != null) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		connexionValidator.validate(connexionDto, result);

		if (!result.hasErrors()) {
			ServiceResponse serviceResponse = serviceUtilisateur.connecterUtilisateur(connexionDto);
			if (!serviceResponse.isError()) {
				// On stocke l'information d'état de l'appel
				session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse.getMessage());
				// On stock en session les informations utilisateur + un nouveau
				// panier
				session.setAttribute(AbstractGlobals.SESSION_ROLE_NAME,
						((UtilisateurDto) serviceResponse.getDataResult()).getNomProfil());
				session.setAttribute(AbstractGlobals.SESSION_USER_NAME,
						((UtilisateurDto) serviceResponse.getDataResult()));
				session.setAttribute(AbstractGlobals.SESSION_PANIER_NAME, new PanierDto());

				return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
			}
			// Erreur, stock l'information et réinitialise le mdp du formulaire
			connexionDto.setMotDePasse(null);
			model.addAttribute("connexionDto", connexionDto);
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
		}
		return AbstractGlobals.PAGE_CONNEXION;
	}
}
