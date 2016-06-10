package oth.metier.mapper.commande;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import oth.metier.mapper.panier.MapperPanier;
import oth.metier.mapper.utilisateur.MapperUtilisateur;
import oth.persistance.bean.commande.CommandeDo;
import oth.persistance.bean.commande.CommandeProduitDo;
import oth.presentation.dto.commande.ListeCommandeDto;
import oth.presentation.dto.commande.ValiderCommandeDto;
import oth.presentation.dto.commande.bean.CommandeDto;
import oth.presentation.dto.commande.bean.CommandeProduitDto;

/**
 * Mapper des DTO liés aux commandes.
 * 
 * @author Phil9175
 *
 */

public class MapperCommande {

	/**
	 * Créé le DO commande produit associé à un DTO liste commande.
	 * 
	 * @param commandeDo
	 *            Le DTO commande à mapper.
	 * @return Le DTO liste commande mappé.
	 */
	public static ListeCommandeDto createListeCommandeDto(final List<CommandeDo> commandeDo) {

		if (commandeDo == null) {
			return null;
		}
		final ListeCommandeDto listeCommandeDto = new ListeCommandeDto();
		final List<CommandeDto> liste = new ArrayList<CommandeDto>();
		for (CommandeDo commande : commandeDo) {
			liste.add(createCommandeDto(commande));
		}
		listeCommandeDto.setListeCommande(liste);
		return listeCommandeDto;

	}

	/**
	 * Crée la commandeDto associée à la commandeDo passée en paramètre.
	 * 
	 * @param commandeDo
	 *            la commande contenue en base de données à mapper.
	 * @return le dto mappé.
	 */

	public static CommandeDto createCommandeDto(final CommandeDo commandeDo) {

		if (commandeDo == null) {
			return null;
		}
		double prixTotal = 0;
		Integer quantiteTotale = 0;
		final List<CommandeProduitDto> liste = new ArrayList<CommandeProduitDto>();
		final CommandeDto commande = new CommandeDto();

		for (CommandeProduitDo com : commandeDo.getCommandeProduit()) {

			quantiteTotale += com.getQuantite();
			prixTotal += (com.getQuantite() * com.getPrixUnitaire());
			
			
			// On reduit à deux décimal le résultat
			double pow = Math.pow(10, 2);
			prixTotal = (Math.floor(prixTotal * pow)) / pow;

			CommandeProduitDto commandeProduitDto = new CommandeProduitDto();
			commandeProduitDto.setDescription(com.getDescriptionProduit());
			commandeProduitDto.setId(com.getId());
			commandeProduitDto.setPhoto(com.getPhotoProduit());
			commandeProduitDto.setPrixUnitaire(1.0 * com.getPrixUnitaire());
			commandeProduitDto.setQuantite(com.getQuantite());
			commandeProduitDto.setReferenceProduit(com.getReferenceProduit());
			liste.add(commandeProduitDto);
		}

		commande.setId(commandeDo.getId());
		commande.setRemise(commandeDo.getRemise());
		commande.setUtilisateur(MapperUtilisateur.createUtilisateurDto(commandeDo.getUtilisateur()));
		commande.setDate(commandeDo.getDateCommande());
		commande.setListCommandeProduit(liste);
		commande.setQuantiteTotale(quantiteTotale);
		commande.setPrixTotal(prixTotal);

		return commande;

	}

	/**
	 * Créé le DO commande associé à un DTO valider commande.
	 * 
	 * @param validerCommandeDto
	 *            Le DTO valider commande à mapper.
	 * @return Le DO commande mappé.
	 */
	public static CommandeDo createCommandeDo(final ValiderCommandeDto validerCommandeDto) {
		if (validerCommandeDto == null) {
			return null;
		}

		final CommandeDo commande = new CommandeDo();
		commande.setAdresseFacturation(validerCommandeDto.getAdresseFacturation());
		commande.setAdresseLivraison(validerCommandeDto.getAdresseLivraison());
		commande.setRemise(validerCommandeDto.getPanier().getRemise());
		final List<CommandeProduitDo> liste = MapperPanier.createListeCommandeProduitDo(validerCommandeDto.getPanier());

		final List<CommandeProduitDo> finalListe = new ArrayList<>();

		// Pour chaque element de la liste, on set la commande
		for (CommandeProduitDo cpd : liste) {
			cpd.setCommande(commande);
			finalListe.add(cpd);
		}

		commande.setCommandeProduit(new HashSet<CommandeProduitDo>(finalListe));
		commande.setUtilisateur(MapperUtilisateur.createUtilisateurDo(validerCommandeDto.getUtilisateurDto()));
		commande.setDateCommande(new Date());
		return commande;

	}
}
