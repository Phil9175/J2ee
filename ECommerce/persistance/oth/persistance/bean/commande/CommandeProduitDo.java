package oth.persistance.bean.commande;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import oth.persistance.bean.produit.ProduitDo;

/**
 * DO associé à la table CommandeProduit.
 * 
 * @author Phil9175
 *
 */
@Entity(name = "CommandeProduitDo")
@Table(name = "commandeproduit")
public class CommandeProduitDo implements Serializable {

	private static final long serialVersionUID = 6638115465385900136L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commandeproduit_id")
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commande_id")
	private CommandeDo commande;

	@NotNull
	@Size(min = 0, max = 255)
	@Column(name = "commandeproduit_referenceproduit")
	private String referenceProduit;

	@NotNull
	@Column(name = "commandeproduit_descriptionproduit")
	@Size(min = 0, max = 255)
	private String descriptionProduit;

	@NotNull
	@Lob
	@Column(name = "commandeproduit_photoproduit")
	private byte[] photoProduit;

	@NotNull
	@Column(name = "commandeproduit_quantite")
	private Integer quantite;

	@NotNull
	@Column(name = "commandeproduit_prixunitaire")
	private Double prixUnitaire;

	/**
	 * Constructeur par défaut.
	 */
	public CommandeProduitDo() {
		super();
	}

	public CommandeProduitDo(final ProduitDo produitDo) {
		super();
		this.referenceProduit = produitDo.getReference();
		this.descriptionProduit = produitDo.getDescription();
		this.photoProduit = produitDo.getPhoto();
		this.prixUnitaire = produitDo.getPrix();
	}

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
	 * @return the commande
	 */
	public CommandeDo getCommande() {
		return commande;
	}

	/**
	 * @param commande
	 *            the commande to set
	 */
	public void setCommande(final CommandeDo commande) {
		this.commande = commande;
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
	 * @return the descriptionProduit
	 */
	public String getDescriptionProduit() {
		return descriptionProduit;
	}

	/**
	 * @param descriptionProduit
	 *            the descriptionProduit to set
	 */
	public void setDescriptionProduit(final String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}

	/**
	 * @return the photoProduit
	 */
	public byte[] getPhotoProduit() {
		return photoProduit;
	}

	/**
	 * @param photoProduit
	 *            the photoProduit to set
	 */
	public void setPhotoProduit(final byte[] photoProduit) {
		this.photoProduit = photoProduit;
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

}
