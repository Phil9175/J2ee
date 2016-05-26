package oth.presentation.controller.commande;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.commande.IServiceCommande;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.commande.ListeCommandeDto;
import oth.presentation.dto.commande.bean.CommandeDto;

/**
 * Controlleur pour l'écran de détail de commande.
 * 
 * @author badan 
 * 
 */
@Controller
@RequestMapping("/detailCommande")
public class DetailCommandeController {

	@Autowired
	public IServiceCommande serviceCommande;

	/**
	 * Affiche la page de détail de commande.
	 * 
	 * @param commandeDto
	 *            Le DTO de la commande à afficher.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showDetailCommande(final Integer idCommande, final Model model, final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_DETAIL_COMMANDE,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		final ServiceResponse serviceResponse = serviceCommande.getCommandeById(idCommande);
		if (serviceResponse.isError()) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
			return "redirect:/" + AbstractGlobals.PAGE_LISTE_COMMANDE;
		}

		// **** Regarder le contenu

		final CommandeDto commandeDto = (CommandeDto) serviceResponse.getDataResult();
		model.addAttribute("commandeDto", commandeDto);
		return AbstractGlobals.PAGE_DETAIL_COMMANDE;
	}

}
