package oth.presentation.dto.panier;

import java.util.ArrayList;
import java.util.List;

import oth.presentation.dto.panier.bean.ElementPanierDto;

/**
 * DTO d'un panier.
 * 
 * @author badane 
 *
 */
public class PanierDto {
	private List<ElementPanierDto> contenu;
	private Double prixTotal;
	private Integer quantiteTotale;
	private Double remise;

	public PanierDto() {
		this.prixTotal = 0d;
		this.quantiteTotale = 0;
		this.remise = 0d;
		this.contenu = new ArrayList<ElementPanierDto>();
	}

	/**
	 * @return the contenu
	 */
	public List<ElementPanierDto> getContenu() {
		return contenu;
	}

	/**
	 * @param contenu
	 *            the contenu to set
	 */
	public void setContenu(final List<ElementPanierDto> contenu) {
		this.contenu = contenu;
	}

	/**
	 * Ajoute un produit dans le panier.
	 * 
	 * @param elementPanierDto
	 *            L'element à ajouter.
	 */
	public void addProduit(final ElementPanierDto elementPanierDto) {
		boolean find = false;
		for (ElementPanierDto epd : this.contenu) {
			if (epd.getProduit().getId() == elementPanierDto.getProduit().getId()) {
				epd.setPrixTotal(epd.getPrixTotal() + elementPanierDto.getPrixTotal());
				epd.setQuantite(epd.getQuantite() + elementPanierDto.getQuantite());
				this.prixTotal += elementPanierDto.getPrixTotal();
				this.quantiteTotale += elementPanierDto.getQuantite();
				find = true;
				break;
			}
		}
		if (!find) {
			this.contenu.add(elementPanierDto);
			this.prixTotal += elementPanierDto.getPrixTotal();
			this.quantiteTotale += elementPanierDto.getQuantite();
		}

	}

	/**
	 * Supprime un produit du panier.
	 * 
	 * @param referenceProduit
	 *            La reference à supprimer.
	 */
	public void removeProduit(final String referenceProduit) {
		// FIXME SOA Mieux à faire...
		int index = 0;
		for (ElementPanierDto epd : this.contenu) {
			if (epd.getProduit().getReference().equals(referenceProduit)) {
				this.prixTotal -= epd.getPrixTotal();
				this.quantiteTotale -= epd.getQuantite();
				this.contenu.remove(index);
				return;
			}
			index++;
		}
	}

	/**
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal
	 *            the prixTotal to set
	 */
	public void setPrixTotal(final Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	/**
	 * @return the quantiteTotale
	 */
	public Integer getQuantiteTotale() {
		return quantiteTotale;
	}

	/**
	 * @param quantiteTotale
	 *            the quantiteTotale to set
	 */
	public void setQuantiteTotale(final Integer quantiteTotale) {
		this.quantiteTotale = quantiteTotale;
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

}
