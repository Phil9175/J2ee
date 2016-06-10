package oth.presentation.dto.produit;

import java.util.List;

import oth.presentation.dto.produit.bean.ProduitDto;

/**
 * DTO pour la gestion des produits.
 * 
 * @author Phil9175
 *
 */
public class GestionProduitDto {
	private List<ProduitDto> listProduit;
	private String searchByReference;

	/**
	 * @return the listProduit
	 */
	public List<ProduitDto> getListProduit() {
		return listProduit;
	}

	/**
	 * @param listProduit
	 *            the listProduit to set
	 */
	public void setListProduit(final List<ProduitDto> listProduit) {
		this.listProduit = listProduit;
	}

	/**
	 * @return the searchByReference
	 */
	public String getSearchByReference() {
		return searchByReference;
	}

	/**
	 * @param searchByReference
	 *            the searchByReference to set
	 */
	public void setSearchByReference(final String searchByReference) {
		this.searchByReference = searchByReference;
	}

}
