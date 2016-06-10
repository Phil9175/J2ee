package oth.metier.service.produit;

import oth.metier.service.ServiceResponse;
import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.dto.tri.SortByType;

/**
 * Interface du service métier produit.
 * 
 * @author badane
 *
 */
public interface IServiceProduit {
	/**
	 * Ajoute un produit en base.
	 * 
	 * @param produitDto
	 *            Le DTO du produit à ajouter.
	 * @return La réponse associée.
	 */
	ServiceResponse ajouterProduit(final ProduitDto produitDto);

	/**
	 * Modifie un produit en base.
	 * 
	 * @param produitDto
	 *            Le DTO du produit à modifier.
	 * @return La réponse associée.
	 */
	ServiceResponse modifierProduit(final ProduitDto produitDto);

	/**
	 * Supprime un produit.
	 * 
	 * @param idProduit
	 *            L'identifiant du produit à supprimer.
	 * @return La réponse associée.
	 */
	ServiceResponse supprimerProduit(final int idProduit);

	/**
	 * Récupère un produit par référence.
	 * 
	 * @param referenceProduit
	 *            La référence du produit à chercher.
	 * @return La réponse associée.
	 */
	ServiceResponse findByReference(final String referenceProduit);

	/**
	 * Recupère une liste de produit.
	 * 
	 * @param tri
	 *            Le type de tri.
	 * @param reference
	 *            La référence du produit à chercher.
	 * @param page
	 *            Le numéro de la page à récupérer.
	 * @return La réponse associée.
	 */
	ServiceResponse getListeProduit(final SortByType tri, final String reference, final Integer page);

	/**
	 * Récupère la liste des produits à administrer.
	 * 
	 * @param typeTri
	 *            Le type de tri à appliquer (croissant / décroissant).
	 * @param reference
	 *            La référence à chercher.
	 * @return La réponse associée.
	 */
	ServiceResponse getListeProduitGestion(final SortByType typeTri, final String reference);
}
