package oth.persistance.bean.utilisateur;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import oth.persistance.bean.commande.CommandeDo;

/**
 * DO associé à la table utilisateur.
 * 
 * @author Phil9175
 *
 */
@Entity(name = "UtilisateurDo")
@Table(name = "utilisateur")
public class UtilisateurDo implements Serializable {

	private static final long serialVersionUID = -8433118076652654486L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utilisateur_id")
	private Integer id;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "utilisateur_login", unique = true)
	private String login;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "utilisateur_nom")
	private String nom;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "utilisateur_prenom")
	private String prenom;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "utilisateur_adresse")
	private String adresse;

	@Temporal(TemporalType.DATE)
	@Past
	@Column(name = "utilisateur_datenaissance")
	private Date dateNaissance;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "utilisateur_mail")
	private String mail;

	@NotNull
	@Column(name = "utilisateur_actif")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isActive;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "profil_id")
	private ProfilDo profil;

	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
	private Set<CommandeDo> listeCommande;

	@NotNull
	@Size(min = 0, max = 255)
	@Column(name = "utilisateur_motdepasse")
	private String motdepasse;

	/**
	 * Constructeur par défaut.
	 */
	public UtilisateurDo() {
		// Empty constructor
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	/**
	 * @param dateNaissance
	 *            the dateNaissance to set
	 */
	public void setDateNaissance(final Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(final String mail) {
		this.mail = mail;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(final boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the profil
	 */
	public ProfilDo getProfil() {
		return profil;
	}

	/**
	 * @param profil
	 *            the profil to set
	 */
	public void setProfil(final ProfilDo profil) {
		this.profil = profil;
	}

	/**
	 * @return the listeCommande
	 */
	public Set<CommandeDo> getListeCommande() {
		return listeCommande;
	}

	/**
	 * @param listeCommande
	 *            the listeCommande to set
	 */
	public void setListeCommande(final Set<CommandeDo> listeCommande) {
		this.listeCommande = listeCommande;
	}

	/**
	 * @return the motdepasse
	 */
	public String getMotdepasse() {
		return motdepasse;
	}

	/**
	 * @param motdepasse
	 *            the motdepasse to set
	 */
	public void setMotdepasse(final String motdepasse) {
		this.motdepasse = motdepasse;
	}

}
