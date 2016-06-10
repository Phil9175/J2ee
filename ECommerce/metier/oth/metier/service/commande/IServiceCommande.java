package oth.metier.service.commande;

import oth.metier.service.ServiceResponse;
import oth.presentation.dto.commande.ValiderCommandeDto;

/**
 * Interface du service métier commande.
 * 
 * @author Phil9175
 *
 */
public interface IServiceCommande {
	/**
	 * Créé une commande.
	 * 
	 * @param validerCommandeDto
	 *            Le DTO de la commande à valider.
	 * @return La réponse associée.
	 */
	ServiceResponse validerCommande(final ValiderCommandeDto validerCommandeDto);

	/**
	 * Récupère la liste des commandes d'un utilisateur.
	 * 
	 * @param idUtilisateur
	 *            L'id de l'utilisateur à qui récupérer les commandes.
	 * @return La réponse associée.
	 */
	ServiceResponse getListeCommande(final Integer idUtilisateur);

	/**
	 * Récupère une commande par identifiant.
	 * 
	 * @param idCommande
	 *            L'identifiant de la commande.
	 * @return La réponse associée.
	 */
	ServiceResponse getCommandeById(final Integer idCommande);

}
