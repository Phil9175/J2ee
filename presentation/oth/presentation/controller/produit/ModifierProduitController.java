package oth.presentation.controller.produit;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.produit.GestionProduitDto;
import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.validator.ProduitValidator;

/**
 * Controlleur pour l'écran de modification d'un produit.
 * 
 * @author badan
 * 
 */
@Controller
@RequestMapping("/modifierProduit")
public class ModifierProduitController {
	@Autowired
	public IServiceProduit serviceProduit;

	@Autowired
	private ProduitValidator produitValidator;

	/**
	 * Affiche la page d'édition d'information d'un produit.
	 * 
	 * @param referenceProduit
	 *            La référence du produit à afficher.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showEditInfoProduit(final String referenceProduit, final Model model, final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_MODIFIER_PRODUIT,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		ServiceResponse serviceResponse = serviceProduit.findByReference(referenceProduit);
		ProduitDto produitDto = (ProduitDto) serviceResponse.getDataResult();
		model.addAttribute("produitDto", produitDto);
		return AbstractGlobals.PAGE_MODIFIER_PRODUIT;
	}

	/**
	 * Valide l'édition d'information d'un produit.
	 * 
	 * @param produitDto
	 *            Le DTO du produit à modifier.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView validerEditInfoProduit(final @ModelAttribute("produitDto") ProduitDto produitDto,
			final BindingResult result, final HttpSession session, final SessionStatus sessionStatus,
			final MultipartFile file) throws IOException {

		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_AJOUTER_PRODUIT,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return new ModelAndView("redirect:/" + AbstractGlobals.PAGE_ACCUEIL);
		}

		ServiceResponse serviceResponse;
		// Set photo
		if (!file.isEmpty()) {
			produitDto.setPhoto(file.getBytes());
		} else {
			final String referenceProduit = produitDto.getReference();
			serviceResponse = serviceProduit.findByReference(referenceProduit);
			final ProduitDto produitDto2 = (ProduitDto) serviceResponse.getDataResult();
			produitDto.setPhoto(produitDto2.getPhoto());
		}
		// Validate product
		produitValidator.validate(produitDto, result);
		serviceResponse = serviceProduit.getListeProduitGestion(new SortByType(SortByType.ORDER_ASC), "");
		GestionProduitDto gestionProduitDto = (GestionProduitDto) serviceResponse.getDataResult();
		final ModelAndView model = new ModelAndView();
		// If no validation errors
		if (!result.hasErrors()) {
			serviceResponse = serviceProduit.modifierProduit(produitDto);
			// If no service error
			if (!serviceResponse.isError()) {
				session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse.getMessage());
				model.addObject("gestionProduitDto", gestionProduitDto);
				model.setViewName("redirect:/" + AbstractGlobals.PAGE_GESTION_PRODUIT);
				return model;
			}
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
		}
		model.addObject("produitDto", produitDto);
		model.setViewName(AbstractGlobals.PAGE_MODIFIER_PRODUIT);
		return model;
	}

}
