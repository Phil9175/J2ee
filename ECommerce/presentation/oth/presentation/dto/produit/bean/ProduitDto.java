package oth.presentation.dto.produit.bean;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO d'un produit.
 * 
 * @author Phil9175 
 *
 */
public class ProduitDto {
	private Integer id;

	@Size(min = 1)
	private String reference;
	private String description;
	private boolean enVente;

	@NotNull
	@Min(value = 5)
	private Double prix;

	@Lob
	@NotNull
	private byte[] photo;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(final String reference) {
		this.reference = reference;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the enVente
	 */
	public boolean isEnVente() {
		return enVente;
	}

	/**
	 * @param enVente
	 *            the enVente to set
	 */
	public void setEnVente(final boolean enVente) {
		this.enVente = enVente;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(final Double prix) {
		this.prix = prix;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(final byte[] photo) {
		this.photo = photo;
	}

}
