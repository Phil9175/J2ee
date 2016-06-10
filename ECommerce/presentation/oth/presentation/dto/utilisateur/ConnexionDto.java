package oth.presentation.dto.utilisateur;

/**
 * DTO pour la connexion.
 * 
 * @author badane
 *
 */
public class ConnexionDto {
	private String login;
	private String motDePasse;

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
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse
	 *            the motDePasse to set
	 */
	public void setMotDePasse(final String motDePasse) {
		this.motDePasse = motDePasse;
	}

}
