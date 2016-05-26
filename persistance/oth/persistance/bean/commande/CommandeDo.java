package oth.persistance.bean.commande;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import oth.persistance.bean.utilisateur.UtilisateurDo;

/**
 * DO associé à la table commande.
 * 
 * @author badane
 *
 */
@Entity(name = "CommandeDo")
@Table(name = "commande")
public class CommandeDo implements Serializable {

	private static final long serialVersionUID = 3169179101856791216L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commande_id")
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private UtilisateurDo utilisateur;

	@NotNull
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CommandeProduitDo> commandeProduit;

	@NotNull
	@Column(name = "commande_datecommande")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCommande;

	@NotNull
	@Column(columnDefinition = "double default 0")
	private Double remise;

	@NotNull
	@Column(name = "commande_adresselivraison")
	@Size(min = 0, max = 255)
	private String adresseLivraison;

	@NotNull
	@Column(name = "commande_adressefacturation")
	@Size(min = 0, max = 255)
	private String adresseFacturation;

	/**
	 * Constructeur par défaut.
	 */
	public CommandeDo() {
		super();
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
	 * @return the utilisateur
	 */
	public UtilisateurDo getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur
	 *            the utilisateur to set
	 */
	public void setUtilisateur(final UtilisateurDo utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the commandeProduit
	 */
	public Set<CommandeProduitDo> getCommandeProduit() {
		return commandeProduit;
	}

	/**
	 * @param commandeProduit
	 *            the commandeProduit to set
	 */
	public void setCommandeProduit(final Set<CommandeProduitDo> commandeProduit) {
		this.commandeProduit = commandeProduit;
	}

	/**
	 * @return the dateCommande
	 */
	public Date getDateCommande() {
		return dateCommande;
	}

	/**
	 * @param dateCommande
	 *            the dateCommande to set
	 */
	public void setDateCommande(final Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	/**
	 * @return the remise
	 */
	public Double getRemise() {
		return remise;
	}

	/**
	 * @param remise
	 *            the remise to set
	 */
	public void setRemise(final Double remise) {
		this.remise = remise;
	}

	/**
	 * @return the adresseLivraison
	 */
	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	/**
	 * @param adresseLivraison
	 *            the adresseLivraison to set
	 */
	public void setAdresseLivraison(final String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	/**
	 * @return the adresseFacturation
	 */
	public String getAdresseFacturation() {
		return adresseFacturation;
	}

	/**
	 * @param adresseFacturation
	 *            the adresseFacturation to set
	 */
	public void setAdresseFacturation(final String adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

}
