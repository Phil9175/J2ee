package oth.metier.service.panier.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oth.metier.service.AbstractService;
import oth.metier.service.panier.IServicePanier;
import oth.presentation.dto.panier.PanierDto;

/**
 * Implémentation du service métier panier.
 * 
 * @author Phil9175
 *
 */
@Service("servicePanier")
@Transactional(propagation = Propagation.REQUIRED)
public class ServicePanier extends AbstractService implements IServicePanier {

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.panier.IServicePanier#getRemisePanier(oth.
	 * presentation.dto.panier.PanierDto)
	 */
	@Override
	public Double getRemisePanier(final PanierDto panierDto) {

		if (panierDto.getContenu().size() >= 5) {
			return panierDto.getPrixTotal() * 0.1;
		}
		return 0.0;
	}

}
