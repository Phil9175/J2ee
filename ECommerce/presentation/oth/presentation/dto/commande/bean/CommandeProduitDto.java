package oth.presentation.dto.commande.bean;

/**
 * DTO d'un CommandeProduit.
 * 
 * @author badane
 *
 */
public class CommandeProduitDto {
	private Integer id;
	private String referenceProduit;
	private String description;
	private byte[] photo;
	private Double prixUnitaire;
	private Integer quantite;

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
	 * @return the referenceProduit
	 */
	public String getReferenceProduit() {
		return referenceProduit;
	}

	/**
	 * @param referenceProduit
	 *            the referenceProduit to set
	 */
	public void setReferenceProduit(final String referenceProduit) {
		this.referenceProduit = referenceProduit;
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

	/**
	 * @return the prixUnitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire
	 *            the prixUnitaire to set
	 */
	public void setPrixUnitaire(final Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
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

}
