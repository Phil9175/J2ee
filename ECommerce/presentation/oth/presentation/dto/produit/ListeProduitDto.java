
package oth.presentation.dto.produit;

import java.util.List;

import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.dto.tri.SortByType;

/**
 * DTO pour la liste des produits.
 * 
 * @author badane 
 *
 */
public class ListeProduitDto {
	private List<ProduitDto> listProduit;
	private String searchByReference;
	private SortByType tri;
	private Integer page;

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

	/**
	 * @return the tri
	 */
	public SortByType getTri() {
		return tri;
	}

	/**
	 * @param tri
	 *            the tri to set
	 */
	public void setTri(final SortByType tri) {
		this.tri = tri;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(final Integer page) {
		this.page = page;
	}

}
