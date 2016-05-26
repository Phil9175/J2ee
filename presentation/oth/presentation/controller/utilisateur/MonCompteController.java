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
import oth.presentation.dto.commande.ListeCommandeDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * Controlleur pour l'écran d'affichage du compte.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/monCompte")
public class MonCompteController {
	@Autowired
	public IServiceUtilisateur serviceUtilisateur;
	
	/**
	 * Affiche la page de consultation du compte.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showMonCompte(final Model model, final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_MON_COMPTE, (String) session.getAttribute(
				AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		
		// TODO AFFECT
		UtilisateurDto utilisateurDto = (UtilisateurDto) session.getAttribute(AbstractGlobals.SESSION_USER_NAME);
		model.addAttribute("utilisateurDto", utilisateurDto);
		return AbstractGlobals.PAGE_MON_COMPTE;
	}
	
	/**
	 * Valide le changement d'état d'un utilisateur (actif / desactivé).
	 * 
	 * @param utilisateurDto
	 *            DTO de l'utilisateur à qui changer l'état.
	 * @param etat
	 *            La nouvelle valeur de l'état (true si activation, false si
	 *            désactivation)
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.POST)
	String validerChangerEtatUtilisateur(final UtilisateurDto utilisateurDto, final Boolean etat, final Model model,
			final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_LISTE_UTILISATEUR, (String) session.getAttribute(
				AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		
		// TODO AFFECT
		return null;
	}
	
	/**
	 * Valide la supression d'un utilisateur.
	 * 
	 * @param utilisateurDto
	 *            DTO de l'utilisateur à supprimer.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	String validerSupprimerUtilisateur(final Model model,
			final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_LISTE_UTILISATEUR, (String) session.getAttribute(
				AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		
		final UtilisateurDto utilisateurDto = (UtilisateurDto) session.getAttribute(AbstractGlobals.SESSION_USER_NAME);
		if (!serviceUtilisateur.supprimerUtilisateur(utilisateurDto)) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "service.utilisateur.suppression.msg.error");
			return "redirect:/" + AbstractGlobals.PAGE_MON_COMPTE;
		}
		return AbstractGlobals.PAGE_ACCUEIL;
	}
	
}
