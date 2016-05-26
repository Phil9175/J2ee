package oth.presentation.controller.produit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.dto.panier.AjoutProduitPanierDto;
import oth.presentation.dto.produit.bean.ProduitDto;

/**
 * Controlleur pour l'écran de détail produit.
 * 
 * @author badan
 * 
 */
@Controller
public class DetailProduitController {

	@Autowired
	IServiceProduit serviceProduit;

	/**
	 * Affiche la page de détail d'un produit.
	 * 
	 * @param referenceProduit
	 *            La référence produit à afficher.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(path = "/detailProduit/{referenceProduit}", method = RequestMethod.GET)
	public String showDetailProduit(final @PathVariable(value = "referenceProduit") String referenceProduit,
			final Model model, final HttpSession session) {
		final ServiceResponse serviceResponse = serviceProduit.findByReference(referenceProduit);
		if (serviceResponse.isError()) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		model.addAttribute("produitDto", (ProduitDto) serviceResponse.getDataResult());
		model.addAttribute("ajoutProduitPanierDto", new AjoutProduitPanierDto());
		return AbstractGlobals.PAGE_DETAIL_PRODUIT;
	}

}
