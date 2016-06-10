package oth.persistance.dao.produit;

import java.util.List;

import oth.persistance.bean.produit.ProduitDo;
import oth.persistance.dao.IGenericDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByType;

/**
 * Interface du Dao de commande.
 * 
 * @author Phil9175
 * 
 */
public interface IProduitDao extends IGenericDao<ProduitDo> {

	/**
	 * Récupère un produit par référence.
	 * 
	 * @param reference
	 *            La référence à chercher.
	 * @return Le DO associé au produit.
	 * 
	 * @throws DaoException
	 *             si erreur lors de la récupération.
	 */
	ProduitDo findByReference(final String reference) throws DaoException;

	/**
	 * Cherche des produit correspondant à une référence (type like SQL) et les
	 * tri par. Ne retourne pas les produits non disponibles à la vente.
	 * 
	 * @param reference
	 *            La référence à filtrer.
	 * @param typeTri
	 *            Le type de tri à appliquer.
	 * @param onlyEnVente
	 *            Défini si uniquement les produits vendu sont retournés (true)
	 *            ou non (false).
	 * @return La liste de DO associée aux produits trouvés.
	 * 
	 */
	List<ProduitDo> searchProduitWithReference(final String reference, final SortByType typeTri,
			final boolean onlyEnVente) throws DaoException;
}
