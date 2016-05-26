package oth.presentation.controller.produit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;

/**
 * Controlleur pour supprimer un produit selon sa référence d'un produit.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/supprimerProduit")
public class SupprimerProduitController {
	@Autowired
	public IServiceProduit serviceProduit;

	/**
	 * Suppression d'un produit.
	 * 
	 * @param Identifiant
	 *            Produit
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String supprimerProduit(final Integer idProduit, final Model model, final HttpSession session,
			final SessionStatus sessionStatus) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_GESTION_PRODUIT,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		ServiceResponse serviceResponse = serviceProduit.supprimerProduit(idProduit);
		// If no service error
		if (!serviceResponse.isError()) {
			session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse.getMessage());
			return ("redirect:/" + AbstractGlobals.PAGE_GESTION_PRODUIT);
		}
		session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
		return ("redirect:/" + AbstractGlobals.PAGE_GESTION_PRODUIT);
	}

}
