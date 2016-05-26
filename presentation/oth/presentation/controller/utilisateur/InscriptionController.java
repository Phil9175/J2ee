package oth.presentation.controller.utilisateur;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.dto.utilisateur.ConnexionDto;
import oth.presentation.dto.utilisateur.InscriptionDto;
import oth.presentation.validator.InscriptionValidator;

/**
 * Controlleur pour l'écran d'inscription.
 * 
 * @author badan
 * 
 */

@Controller
@RequestMapping("/inscription")
public class InscriptionController {
	@Autowired
	public IServiceUtilisateur serviceUtilisateur;

	@Autowired
	private InscriptionValidator inscriptionValidator;

	/**
	 * Affiche la page d'inscription.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showInscription(final Model model, final HttpSession session) {
		if (session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME) != null) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		model.addAttribute("inscriptionDto", new InscriptionDto());
		return AbstractGlobals.PAGE_INSCRIPTION;
	}

	/**
	 * Vérifie l'inscription.
	 * 
	 * @param inscriptionDto
	 *            DTO de l'inscription.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView validerInscription(final @ModelAttribute("inscriptionDto") InscriptionDto inscriptionDto,
			final BindingResult result, final HttpSession session, final SessionStatus sessionStatus) {
		if (session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME) != null) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return new ModelAndView("redirect:/" + AbstractGlobals.PAGE_ACCUEIL);
		}

		inscriptionValidator.validate(inscriptionDto, result);
		final ConnexionDto connexionDto = new ConnexionDto();
		final ModelAndView model = new ModelAndView();
		if (!result.hasErrors()) {
			final ServiceResponse serviceResponse = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
			connexionDto.setLogin(inscriptionDto.getLogin());
			if (!serviceResponse.isError()) {
				session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse.getMessage());
				model.setViewName("redirect:/" + AbstractGlobals.PAGE_CONNEXION);
				return model;
			}
			model.addObject("inscriptionDto", inscriptionDto);
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
		}
		model.setViewName(AbstractGlobals.PAGE_INSCRIPTION);
		return model;
	}

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
