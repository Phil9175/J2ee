package oth.presentation.dto.commande;

import oth.presentation.dto.panier.PanierDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * DTO de la validation de commande.
 * 
 * @author Phil9175
 *
 */
public class ValiderCommandeDto {
	private PanierDto panier;
	private String adresseFacturation;
	private String adresseLivraison;
	private UtilisateurDto utilisateurDto;

	/**
	 * @return the panier
	 */
	public PanierDto getPanier() {
		return panier;
	}

	/**
	 * @param panier
	 *            the panier to set
	 */
	public void setPanier(final PanierDto panier) {
		this.panier = panier;
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
	 * @return the utilisateurDto
	 */
	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	/**
	 * @param utilisateurDto
	 *            the utilisateurDto to set
	 */
	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

}
