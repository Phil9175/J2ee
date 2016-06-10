package oth.presentation.dto.panier.bean;

import oth.presentation.dto.produit.bean.ProduitDto;

/**
 * DTO d'un élément de panier.
 * 
 * @author badane
 *
 */
public class ElementPanierDto {
	private ProduitDto produit;
	private Integer quantite;
	private Double prixTotal;

	/**
	 * @return the produit
	 */
	public ProduitDto getProduit() {
		return produit;
	}

	/**
	 * @param produit
	 *            the produit to set
	 */
	public void setProduit(final ProduitDto produit) {
		this.produit = produit;
	}

	/**
	 * @return the quantite
	 */
	public Integer getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite
	 *            the quantite to set
	 */
	public void setQuantite(final Integer quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal
	 *            the prixTotal to set
	 */
	public void setPrixTotal(final Double prixTotal) {
		this.prixTotal = prixTotal;
	}

}
