package oth.presentation.controller.produit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.produit.GestionProduitDto;
import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.dto.tri.SortByType;

/**
 * Controlleur pour l'écran de gestion des produits.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/gestionProduit")
public class GestionProduitController {

	// FIXME SOA Javadoc

	@Autowired
	public IServiceProduit serviceProduit;

	/**
	 * Affiche la page de gestion de produit.
	 * 
	 * @param referenceProduit
	 *            La référence produit à rechercher.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showGestionProduit(final @ModelAttribute("gestionProduitDto") ProduitDto produitDto, final Model model,
			final HttpSession session, final String reference, final SortByType tri) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_GESTION_PRODUIT,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		// TODO AFFECT Passage reference + tri
		final ServiceResponse serviceResponse = serviceProduit
				.getListeProduitGestion(new SortByType(SortByType.ORDER_ASC), "");
		final GestionProduitDto gestionProduitDto = (GestionProduitDto) serviceResponse.getDataResult();
		model.addAttribute("gestionProduitDto", gestionProduitDto.getListProduit());
		return AbstractGlobals.PAGE_GESTION_PRODUIT;
	}

}
