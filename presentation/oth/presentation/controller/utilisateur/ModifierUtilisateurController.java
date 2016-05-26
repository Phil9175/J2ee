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
import org.springframework.web.servlet.ModelAndView;

import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;
import oth.presentation.validator.EditInfoPersoValidator;

/**
 * Controlleur pour l'écran de modification d'utilisateur.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/modifierUtilisateur")
public class ModifierUtilisateurController {

	@Autowired
	public IServiceUtilisateur serviceUtilisateur;

	@Autowired
	public EditInfoPersoValidator editInfoPersoValidator;

	/**
	 * Affiche la page d'édition d'information personnelles.
	 * 
	 * @param idUtilisateur
	 *            L'id de l'utilisateur.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showEditInfoPerso(final Model model, final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_MODIFIER_UTILISATEUR,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		final UtilisateurDto utilisateurDto = (UtilisateurDto) session.getAttribute(AbstractGlobals.SESSION_USER_NAME);

		if (utilisateurDto == null) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:" + AbstractGlobals.PAGE_ACCUEIL;
		}

		final ServiceResponse serviceResponse = serviceUtilisateur.findByIdToEditInfoPerso(utilisateurDto.getId());
		if (serviceResponse.isError()) {
			// FIXME SOA msg
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
			return "redirect:" + AbstractGlobals.PAGE_ACCUEIL;
		}
		final EditInfoPersoDto editInfoPersoDto = (EditInfoPersoDto) serviceResponse.getDataResult();
		model.addAttribute("editInfoPersoDto", editInfoPersoDto);
		return AbstractGlobals.PAGE_MODIFIER_UTILISATEUR;
	}

	/**
	 * Valide la modification d'information personnelles.
	 * 
	 * @param editInfoPersoDto
	 *            DTO de l'édition d'information personnelles.
	 * @param result
	 *            Le binding associé.
	 * @param session
	 *            La session associée.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView validerEditInfoPerso(final @ModelAttribute("editInfoPersoDto") EditInfoPersoDto editInfoPersoDto,
			final BindingResult result, final HttpSession session) {

		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_MODIFIER_UTILISATEUR,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return new ModelAndView("redirect:/" + AbstractGlobals.PAGE_ACCUEIL);
		}

		// Validate utilisateur
		editInfoPersoValidator.validate(editInfoPersoDto, result);
		final ModelAndView model = new ModelAndView();
		// If no validation errors
		if (!result.hasErrors()) {
			final ServiceResponse serviceResponse = serviceUtilisateur.updateUtilisateur(editInfoPersoDto);
			// If no service error
			if (!serviceResponse.isError()) {
				session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse.getMessage());
				model.setViewName("redirect:/" + AbstractGlobals.PAGE_MODIFIER_UTILISATEUR);
				return model;
			}
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
		}
		model.addObject("editInfoPersoDto", editInfoPersoDto);
		model.setViewName(AbstractGlobals.PAGE_MODIFIER_UTILISATEUR);
		return model;
	}

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
