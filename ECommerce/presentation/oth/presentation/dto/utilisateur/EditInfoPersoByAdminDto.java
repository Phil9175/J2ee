package oth.presentation.dto.utilisateur;

import java.util.Date;

/**
 * DTO de l'édition d'information par l'administrateur.
 * 
 * @author Phil9175
 * 		
 */
public class EditInfoPersoByAdminDto {
	private String login;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String adresse;
	private String mail;
	private String nouveauMdp;
	private String nouveauMdpConfirmation;
	private String nomProfil;
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return the nouveauMdp
	 */
	public String getNouveauMdp() {
		return nouveauMdp;
	}
	
	/**
	 * @param nouveauMdp
	 *            the nouveauMdp to set
	 */
	public void setNouveauMdp(final String nouveauMdp) {
		this.nouveauMdp = nouveauMdp;
	}
	
	/**
	 * @return the nouveauMdpConfirmation
	 */
	public String getNouveauMdpConfirmation() {
		return nouveauMdpConfirmation;
	}
	
	/**
	 * @param nouveauMdpConfirmation
	 *            the nouveauMdpConfirmation to set
	 */
	public void setNouveauMdpConfirmation(final String nouveauMdpConfirmation) {
		this.nouveauMdpConfirmation = nouveauMdpConfirmation;
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
