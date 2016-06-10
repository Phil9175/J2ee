package oth.presentation.dto.utilisateur.bean;

import java.util.Date;

/**
 * DTO d'un utilisateur.
 * 
 * @author Phil9175
 *
 */
public class UtilisateurDto {
	private Integer id;
	private String login;
	private String nom;
	private String prenom;
	private String adresse;
	private Date dateNaissance;
	private String mail;
	private boolean actif;
	private String nomProfil;

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
		return dateNaissance;
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
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(final boolean actif) {
		this.actif = actif;
	}

	/**
	 * @return the nomProfil
	 */
	public String getNomProfil() {
		return nomProfil;
	}

	/**
	 * @param nomProfil
	 *            the nomProfil to set
	 */
	public void setNomProfil(final String nomProfil) {
		this.nomProfil = nomProfil;
	}

}
