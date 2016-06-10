package oth.presentation.dto.utilisateur;

import java.util.Date;

/**
 * DTO de l'inscription.
 * 
 * @author Badan
 * 
 */
public class InscriptionDto {
	private String login;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String adresse;
	private String mail;
	private String mdp;
	private String confMotDePasse;

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
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp
	 *            the mdp to set
	 */
	public void setMdp(final String mdp) {
		this.mdp = mdp;
	}

	/**
	 * @return the confMotDePasse
	 */
	public String getConfMotDePasse() {
		return confMotDePasse;
	}

	/**
	 * @param confMotDePasse
	 *            the confMotDePasse to set
	 */
	public void setConfMotDePasse(final String confMotDePasse) {
		this.confMotDePasse = confMotDePasse;
	}

}
