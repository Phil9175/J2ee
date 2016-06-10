package oth.presentation.controller.produit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.dto.panier.AjoutProduitPanierDto;
import oth.presentation.dto.produit.ListeProduitDto;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.validator.ListeProduitValidator;

/**
 * Controlleur pour l'écran de listing des produits.
 * 
 * @author Phil9175
 * 
 */
@Controller
@RequestMapping("/")
public class ListeProduitController {

	@Autowired
	public IServiceProduit serviceProduit;

	@Autowired
	public ListeProduitValidator listeProduitValidator;

	/**
	 * Redirige vers l'accueil.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return La redirection vers l'accueil.
	 */
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String redirectAccueil(final Model model) {
		return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
	}

	/**
	 * Affiche la page de la liste des produits.
	 * 
	 * @param tri
	 *            Le type de tri.
	 * @param reference
	 *            La référence utilisateur à chercher.
	 * @param page
	 *            La page à afficher.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(path = "/accueil", method = RequestMethod.GET)
	public String showListeProduit(final @ModelAttribute(value = "listeProduitDto") ListeProduitDto listeProduitDto,
			final Model model, final HttpSession session, final BindingResult result) {

		final ServiceResponse serviceResponse = serviceProduit.getListeProduit(new SortByType(SortByType.ORDER_ASC),
				null, 0);

		model.addAttribute("listeProduitDto", (ListeProduitDto) serviceResponse.getDataResult());
		model.addAttribute("ajoutProduitPanierDto", new AjoutProduitPanierDto());
		return AbstractGlobals.PAGE_ACCUEIL;
	}

	/**
	 * Affiche la page de la liste des produits.
	 * 
	 * @param tri
	 *            Le type de tri.
	 * @param reference
	 *            La référence utilisateur à chercher.
	 * @param page
	 *            La page à afficher.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(path = "/accueil", method = RequestMethod.POST)
	public String search(final @ModelAttribute(value = "listeProduitDto") ListeProduitDto listeProduitDto,final BindingResult result
			,final Model model, final HttpSession session ) {
		listeProduitValidator.validate(listeProduitDto, result);

		if (result.hasErrors()) {
			return AbstractGlobals.PAGE_ACCUEIL;
		}

		final SortByType effectiveTri = (listeProduitDto.getTri() == null) ? new SortByType(SortByType.ORDER_ASC)
				: listeProduitDto.getTri();
		final Integer effectivePage = (listeProduitDto.getPage() == null) ? 0 : listeProduitDto.getPage();

		final ServiceResponse serviceResponse = serviceProduit.getListeProduit(effectiveTri,
				listeProduitDto.getSearchByReference(), effectivePage);

		model.addAttribute("listeProduitDto", (ListeProduitDto) serviceResponse.getDataResult());
		model.addAttribute("ajoutProduitPanierDto", new AjoutProduitPanierDto());
		return AbstractGlobals.PAGE_ACCUEIL;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {

	    binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));

	}
}
