package oth.metier.service.panier;

import oth.presentation.dto.panier.PanierDto;

/**
 * Interface du service métier panier.
 * 
 * @author Phil9175
 *
 */
public interface IServicePanier {
	/**
	 * Calcule la remise applicable sur un panier.
	 * 
	 * @param panierDto
	 *            Le panier à analyser.
	 * @return La remise applicable.
	 */
	Double getRemisePanier(final PanierDto panierDto);
}
