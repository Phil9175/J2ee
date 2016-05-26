package oth.presentation.controller.utilisateur;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.ListeUtilisateurDto;

/**
 * Controlleur pour l'écran de liste des utilisateur.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/listeUtilisateur")
public class ListeUtilisateurController {

	@Autowired
	public IServiceUtilisateur serviceUtilisateur;

	/**
	 * Affiche la page de la liste des utilisateurs.
	 * 
	 * @param champ
	 *            Le champ de tri.
	 * @param typeTri
	 *            Le type de tri.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showListeUtilisateur(final SortByField champ, final SortByType typeTri, final Model model,
			final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_LISTE_UTILISATEUR,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		// TODO AFFECT Passage reference + tri
		final ServiceResponse serviceResponse = serviceUtilisateur
				.getListeUtilisateur(new SortByField(SortByField.FIELD_LOGIN), new SortByType(SortByType.ORDER_ASC));
		final ListeUtilisateurDto listeUtilisateurDto = (ListeUtilisateurDto) serviceResponse.getDataResult();
		model.addAttribute("listeUtilisateurDto", listeUtilisateurDto);
		System.out.println(listeUtilisateurDto.getListUtilisateur());
		return AbstractGlobals.PAGE_LISTE_UTILISATEUR;
	}

}
