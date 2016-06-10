package oth.presentation.dto.commande;

import java.util.List;

import oth.presentation.dto.commande.bean.CommandeDto;

/**
 * DTO d'une liste de commande.
 * 
 * @author  Phil9175
 *
 */
public class ListeCommandeDto {
	private List<CommandeDto> listeCommande;

	/**
	 * @return the listeCommande
	 */
	public List<CommandeDto> getListeCommande() {
		return listeCommande;
	}

	/**
	 * @param listeCommande
	 *            the listeCommande to set
	 */
	public void setListeCommande(final List<CommandeDto> listeCommande) {
		this.listeCommande = listeCommande;
	}

}
