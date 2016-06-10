package oth.presentation.controller.utilisateur;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;
import oth.presentation.validator.ConnexionValidator;

/**
 * Controller pour changer l'état d'un utilisateur
 * 
 * @author Phil9175
 *
 */
@Controller
@RequestMapping("/changeEtatUtilisateur")
public class ChangeEtatUtilisateurController {
	@Autowired
	public IServiceUtilisateur serviceUtilisateur;

	@Autowired
	private ConnexionValidator connexionValidator;

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String modifierEtatUtilisateur(final Integer idUtilisateur, final Model model, final HttpSession session,
			final SessionStatus sessionStatus) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_LISTE_UTILISATEUR,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		// On récupère l'id depuis le service
		ServiceResponse serviceResponse = serviceUtilisateur.findById(idUtilisateur);

		UtilisateurDto utilisateurDto = (UtilisateurDto) serviceResponse.getDataResult();
		boolean nouvelleEtat = !utilisateurDto.isActif();

		ServiceResponse serviceResponse2 = serviceUtilisateur.changeEtatUtilisateur(utilisateurDto, nouvelleEtat);

		// If no service error
		if (!serviceResponse.isError()) {
			session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse2.getMessage());
			return ("redirect:/" + AbstractGlobals.PAGE_LISTE_UTILISATEUR);
		}
		session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse2.getMessage());
		return ("redirect:/" + AbstractGlobals.PAGE_LISTE_UTILISATEUR);
	}

}
