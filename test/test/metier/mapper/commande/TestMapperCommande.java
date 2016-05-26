package test.metier.mapper.commande;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import grpb.metier.mapper.commande.MapperCommande;
import grpb.metier.util.Utility;
import grpb.persistance.bean.commande.CommandeDo;
import grpb.persistance.bean.commande.CommandeProduitDo;
import grpb.presentation.dto.commande.ListeCommandeDto;
import grpb.presentation.dto.commande.ValiderCommandeDto;
import grpb.presentation.dto.commande.bean.CommandeDto;
import grpb.presentation.dto.commande.bean.CommandeProduitDto;
import grpb.presentation.dto.panier.PanierDto;
import grpb.presentation.dto.panier.bean.ElementPanierDto;
import grpb.presentation.dto.produit.bean.ProduitDto;

/**
 * Cette classe permet de tester les fonctions implémentées dans la classe
 * MapperCommande
 * 
 * @author badane
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMapperCommande {
	/**
	 * Cette commande teste le bon déroulement de la fonction createCommandeDto
	 * de la classe MapperCommande
	 * 
	 * @throws ParseException
	 */

	@Test
	public void test_01__commandeDo_to_commandeDto_correct() throws ParseException {
		final CommandeDo commandeDo = new CommandeDo();
		final Set<CommandeProduitDo> listeCommandeProduitDo = new LinkedHashSet<CommandeProduitDo>();

		final CommandeProduitDo commandeProduitDo = new CommandeProduitDo();
		final CommandeProduitDo commandeProduitDo2 = new CommandeProduitDo();

		// commandeProduitDto.set
		// définition d'un premier objet commandeProduitDo
		commandeProduitDo.setQuantite(2);
		commandeProduitDo.setReferenceProduit("bc1458");
		commandeProduitDo.setDescriptionProduit("aspirateur");
		commandeProduitDo.setPrixUnitaire(120.0);
		commandeProduitDo.setCommande(commandeDo);

		// définition du deuxième commandeProduit
		commandeProduitDo2.setQuantite(2);
		commandeProduitDo2.setReferenceProduit("ba1458");
		commandeProduitDo2.setDescriptionProduit("balai");
		commandeProduitDo2.setPrixUnitaire(12.0);
		commandeProduitDo2.setCommande(commandeDo);

		listeCommandeProduitDo.add(commandeProduitDo);
		listeCommandeProduitDo.add(commandeProduitDo2);

		// On instancie les différents champs de l'objet CommandeDo

		commandeDo.setAdresseFacturation("rue de la mairie");
		commandeDo.setAdresseLivraison("rue de la mairie");
		commandeDo.setDateCommande(Utility.parseStringToDate("27/07/1992"));
		commandeDo.setId(1);
		commandeDo.setRemise(10.0);
		commandeDo.setCommandeProduit(listeCommandeProduitDo);

		final CommandeDto commandeDto = MapperCommande.createCommandeDto(commandeDo);
		Assert.assertEquals(commandeDo.getRemise(), commandeDto.getRemise());
		Assert.assertEquals(commandeDto.getListCommandeProduit().size(), 2);

		final CommandeProduitDto aspirateur = commandeDto.getListCommandeProduit().get(0);
		final CommandeProduitDto balai = commandeDto.getListCommandeProduit().get(1);

		// on teste que les produitDto correspondent bien aux produitDo ajoutés
		Assert.assertEquals(aspirateur.getQuantite(), commandeProduitDo.getQuantite());
		Assert.assertEquals(aspirateur.getReferenceProduit(), commandeProduitDo.getReferenceProduit());
		Assert.assertEquals(aspirateur.getPrixUnitaire(), commandeProduitDo.getPrixUnitaire());
		Assert.assertEquals(aspirateur.getQuantite(), commandeProduitDo.getQuantite());
		Assert.assertEquals(aspirateur.getPhoto(), commandeProduitDo.getPhotoProduit());

		Assert.assertEquals(balai.getQuantite(), commandeProduitDo2.getQuantite());
		Assert.assertEquals(balai.getReferenceProduit(), commandeProduitDo2.getReferenceProduit());
		Assert.assertEquals(balai.getPrixUnitaire(), commandeProduitDo2.getPrixUnitaire());
		Assert.assertEquals(balai.getQuantite(), commandeProduitDo2.getQuantite());
		Assert.assertEquals(balai.getPhoto(), commandeProduitDo2.getPhotoProduit());

	}

	@Test
	public void test_02__commandeDo_to_commandeDto_null() throws ParseException {

		final CommandeDto commandeDto = MapperCommande.createCommandeDto(null);
		Assert.assertNull(commandeDto);

	}

	@Test
	public void test_03_liste_commandeDo_to_listeCommandeDto_accept() throws ParseException {
		final CommandeDo commandeDo = new CommandeDo();
		final CommandeDo commandeDo2 = new CommandeDo();

		final Set<CommandeProduitDo> listeCommandeProduitDo = new LinkedHashSet<CommandeProduitDo>();
		final List<CommandeDo> listeCommandeDo = new ArrayList<CommandeDo>();

		final CommandeProduitDo commandeProduitDo = new CommandeProduitDo();
		final CommandeProduitDo commandeProduitDo2 = new CommandeProduitDo();

		// commandeProduitDto.set
		// définition d'un premier objet commandeProduitDo
		commandeProduitDo.setQuantite(2);
		commandeProduitDo.setReferenceProduit("bc1458");
		commandeProduitDo.setDescriptionProduit("aspirateur");
		commandeProduitDo.setPrixUnitaire(120.0);
		commandeProduitDo.setCommande(commandeDo);

		// définition du deuxième commandeProduit
		commandeProduitDo2.setQuantite(2);
		commandeProduitDo2.setReferenceProduit("ba1458");
		commandeProduitDo2.setDescriptionProduit("balai");
		commandeProduitDo2.setPrixUnitaire(12.0);
		commandeProduitDo2.setCommande(commandeDo);

		listeCommandeProduitDo.add(commandeProduitDo);
		listeCommandeProduitDo.add(commandeProduitDo2);

		// On instancie les différents champs de l'objet CommandeDo

		commandeDo.setAdresseFacturation("rue de la mairie");
		commandeDo.setAdresseLivraison("rue de la mairie");
		commandeDo.setDateCommande(Utility.parseStringToDate("27/07/1992"));
		commandeDo.setId(2);
		commandeDo.setRemise(20.0);
		commandeDo.setCommandeProduit(listeCommandeProduitDo);

		commandeDo2.setAdresseFacturation("rue de la mairie");
		commandeDo2.setAdresseLivraison("rue de la mairie");
		commandeDo2.setDateCommande(Utility.parseStringToDate("27/07/1992"));
		commandeDo2.setId(1);
		commandeDo2.setRemise(10.0);
		commandeDo2.setCommandeProduit(listeCommandeProduitDo);

		listeCommandeDo.add(commandeDo);
		listeCommandeDo.add(commandeDo2);

		final ListeCommandeDto listeCommandeDto = MapperCommande.createListeCommandeDto(listeCommandeDo);

		Assert.assertEquals(commandeDo.getRemise(), listeCommandeDto.getListeCommande().get(0).getRemise());
		Assert.assertEquals(commandeDo2.getRemise(), listeCommandeDto.getListeCommande().get(1).getRemise());

		final CommandeProduitDto aspirateur = listeCommandeDto.getListeCommande().get(0).getListCommandeProduit()
				.get(0);
		final CommandeProduitDto balai = listeCommandeDto.getListeCommande().get(0).getListCommandeProduit().get(1);

		// on teste que les produitDto correspondent bien aux produitDo ajoutés
		Assert.assertEquals(aspirateur.getQuantite(), commandeProduitDo.getQuantite());
		Assert.assertEquals(aspirateur.getReferenceProduit(), commandeProduitDo.getReferenceProduit());
		Assert.assertEquals(aspirateur.getPrixUnitaire(), commandeProduitDo.getPrixUnitaire());
		Assert.assertEquals(aspirateur.getQuantite(), commandeProduitDo.getQuantite());
		Assert.assertEquals(aspirateur.getPhoto(), commandeProduitDo.getPhotoProduit());

		Assert.assertEquals(balai.getQuantite(), commandeProduitDo2.getQuantite());
		Assert.assertEquals(balai.getReferenceProduit(), commandeProduitDo2.getReferenceProduit());
		Assert.assertEquals(balai.getPrixUnitaire(), commandeProduitDo2.getPrixUnitaire());
		Assert.assertEquals(balai.getQuantite(), commandeProduitDo2.getQuantite());
		Assert.assertEquals(balai.getPhoto(), commandeProduitDo2.getPhotoProduit());
		// on ne teste que sur une seule commandeDo de la liste

	}

	// public static CommandeDo createCommandeDo(final ValiderCommandeDto
	// validerCommandeDto) {
	//
	// }
	@Test
	public void test_04_validerCommandeDto_to_commandeDo() {
		
		final ValiderCommandeDto validerCommandeDto = new ValiderCommandeDto();
		validerCommandeDto.setAdresseFacturation("5 rue de la mairie");
		validerCommandeDto.setAdresseLivraison("5 rue de la mairie");
		
		final PanierDto panierDto = new PanierDto();
		
		
		final ProduitDto produitDto = new ProduitDto();
		final ProduitDto produitDto2 = new ProduitDto();

		/*On défininit le premier elementpanier */
		final ElementPanierDto elem1 = new ElementPanierDto();

		produitDto.setReference("ae2727");
		produitDto.setEnVente(true);
		produitDto.setId(1);
		produitDto.setPrix(125.0);
		produitDto.setDescription("aspirateur 2000w");
		elem1.setQuantite(5);
		elem1.setPrixTotal(produitDto.getPrix() * elem1.getQuantite());
		elem1.setProduit(produitDto);
		
		/*On défininit le deuxieme elementpanier */
		final ElementPanierDto elem2 = new ElementPanierDto();

		produitDto2.setReference("bc2458");
		produitDto2.setEnVente(true);
		produitDto2.setId(2);
		produitDto2.setPrix(12.0);
		produitDto2.setDescription("balai");
		elem2.setQuantite(5);
		elem2.setPrixTotal(produitDto2.getPrix() * elem2.getQuantite());
		elem2.setProduit(produitDto2);
		
		/*Définition de la liste d'élementPanierDto*/
		final List<ElementPanierDto> liste = new ArrayList<ElementPanierDto>();

		liste.add(elem1);
		liste.add(elem2);

		panierDto.setPrixTotal(2000.0);
		panierDto.setRemise(75.0);
		panierDto.setQuantiteTotale(12);
		panierDto.setContenu(liste);
		validerCommandeDto.setPanier(panierDto);

		
		CommandeDo commandeDo = MapperCommande.createCommandeDo(validerCommandeDto);
		
		Assert.assertEquals(commandeDo.getAdresseFacturation(), validerCommandeDto.getAdresseFacturation());
		Assert.assertEquals(commandeDo.getAdresseLivraison(), validerCommandeDto.getAdresseLivraison());

	
		Set<CommandeProduitDo> setCommandeProduitDo = commandeDo.getCommandeProduit();
		List<CommandeProduitDo> listeCommandeProduitDo=new ArrayList<CommandeProduitDo>(setCommandeProduitDo);
		
		CommandeProduitDo commandeProduitDo=listeCommandeProduitDo.get(0);
		Assert.assertEquals(commandeProduitDo.getReferenceProduit(), produitDto2.getReference());
		Assert.assertEquals(commandeProduitDo.getDescriptionProduit(),produitDto2.getDescription());
		Assert.assertEquals(commandeProduitDo.getPhotoProduit(),produitDto2.getPhoto());
		//Assert.assertEquals(commandeProduitDo.getPrixUnitaire()*commandeProduitDo.getQuantite(),produitDto.get);
		
		commandeProduitDo=listeCommandeProduitDo.get(1);
		Assert.assertEquals(commandeProduitDo.getReferenceProduit(), produitDto.getReference());
		Assert.assertEquals(commandeProduitDo.getDescriptionProduit(),produitDto.getDescription());
		Assert.assertEquals(commandeProduitDo.getPhotoProduit(),produitDto.getPhoto());
		

	}
}
