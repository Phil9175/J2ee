package oth.presentation.controller.panier;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.panier.AjoutProduitPanierDto;
import oth.presentation.dto.panier.PanierDto;
import oth.presentation.dto.panier.bean.ElementPanierDto;
import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.validator.PanierValidator;

/**
 * Controlleur pour l'écran de gestion du panier.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/panier")
public class PanierController {

	@Autowired
	private IServiceProduit serviceProduit;

	

	/**
	 * Affiche la page du panier.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showPanier(final Model model, HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_PANIER,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		if (session.getAttribute(AbstractGlobals.SESSION_PANIER_NAME) == null) {
			session.setAttribute(AbstractGlobals.SESSION_PANIER_NAME, new PanierDto());
		}
		model.addAttribute(AbstractGlobals.SESSION_PANIER_NAME,
				session.getAttribute(AbstractGlobals.SESSION_PANIER_NAME));
		return AbstractGlobals.PAGE_PANIER;
	}

	/**
	 * Valide le vidage du panier.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(path = "/viderPanier", method = RequestMethod.GET)
	String viderPanier(final HttpServletRequest request, final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_PANIER,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		session.setAttribute(AbstractGlobals.SESSION_PANIER_NAME, new PanierDto());
		session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, "panier.vider.success");
		return "redirect:" + request.getHeader("referer");
	}

	/**
	 * Valide l'ajout d'un produit dans le panier.
	 * 
	 * @param elementPanierDto
	 *            Le DTO de l'élément du panier à ajouter.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	
	@RequestMapping(path = "/addProduit", method = RequestMethod.GET)
	String ajouterProduitPanier(
			final @ModelAttribute(value = "ajoutProduitPanierDto") AjoutProduitPanierDto ajoutProduitPanierDto, final BindingResult result,
			final HttpSession session, final HttpServletRequest request) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_PANIER,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_CONNEXION;
		}
		
		//SI on entre une chaine de caractere dans la quantite
		if(ajoutProduitPanierDto.getQuantite()==0){
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "panier.ajout.error");
			return "redirect:" + request.getHeader("referer");
		}
		PanierValidator panierValidator = new PanierValidator();
		panierValidator.validate(ajoutProduitPanierDto, result);

		if (result.hasErrors()) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "panier.ajout.error");
			return "redirect:" + request.getHeader("referer");
		}

		// Get le panier
		PanierDto panierDto = (PanierDto) session.getAttribute(AbstractGlobals.SESSION_PANIER_NAME);
		// Si pas existant, on en créé un
		if (panierDto == null) {
			panierDto = new PanierDto();
		}

		// On ajoute l'element
		final ElementPanierDto elementPanierDto = new ElementPanierDto();
		final ServiceResponse serviceResponse = serviceProduit
				.findByReference(ajoutProduitPanierDto.getReferenceProduit());
		if (serviceResponse.isError()) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "panier.ajout.error");
			return "redirect:" + request.getHeader("referer");
		}

		final ProduitDto produitDto = (ProduitDto) serviceResponse.getDataResult();

		elementPanierDto.setQuantite(ajoutProduitPanierDto.getQuantite());
		elementPanierDto.setPrixTotal(ajoutProduitPanierDto.getQuantite() * produitDto.getPrix());
		elementPanierDto.setProduit(produitDto);
		panierDto.addProduit(elementPanierDto);

		session.setAttribute(AbstractGlobals.SESSION_PANIER_NAME, panierDto);

		session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, "panier.ajout.success");
		return "redirect:" + request.getHeader("referer");
	}

	/**
	 * Valide la suppression du produit dans le panier.
	 * 
	 * @param dto
	 *            Le DTO du produit à supprimer.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(path = "/supprimerProduit", method = RequestMethod.GET)
	String supprimerProduitPanier(final @RequestParam(value = "referenceProduit") String referenceProduit,
			final HttpSession session, final HttpServletRequest request) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_PANIER,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		// Get le panier
		final PanierDto panierDto = (PanierDto) session.getAttribute(AbstractGlobals.SESSION_PANIER_NAME);
		// Si pas existant, on en créé un
		if (panierDto == null) {
			session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, "panier.remove.success");
			return "redirect:" + request.getHeader("referer");
		}

		// On supprime l'element
		panierDto.removeProduit(referenceProduit);
		session.setAttribute(AbstractGlobals.SESSION_PANIER_NAME, panierDto);
		session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, "panier.remove.success");
		return "redirect:" + request.getHeader("referer");
	}
	

}
