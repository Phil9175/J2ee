package oth.presentation.dto.commande.bean;

import java.util.Date;
import java.util.List;

import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * DTO d'une commande.
 * 
 * @author Phil9175
 *
 */
public class CommandeDto {
	private Integer id;
	private UtilisateurDto utilisateur;
	private List<CommandeProduitDto> listCommandeProduit;
	private Date date;
	private Integer quantiteTotale;
	private Double prixTotal;
	private Double remise;
	
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
	 * @return the utilisateur
	 */
	public UtilisateurDto getUtilisateur() {
		return utilisateur;
	}
	
	/**
	 * @param utilisateur
	 *            the utilisateur to set
	 */
	public void setUtilisateur(final UtilisateurDto utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	/**
	 * @return the listCommandeProduit
	 */
	public List<CommandeProduitDto> getListCommandeProduit() {
		return listCommandeProduit;
	}
	
	/**
	 * @param listCommandeProduit
	 *            the listCommandeProduit to set
	 */
	public void setListCommandeProduit(final List<CommandeProduitDto> listCommandeProduit) {
		this.listCommandeProduit = listCommandeProduit;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
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
