package test.metier.mapper.panier;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import oth.metier.mapper.panier.MapperPanier;
import oth.persistance.bean.commande.CommandeProduitDo;
import oth.presentation.dto.panier.PanierDto;
import oth.presentation.dto.panier.bean.ElementPanierDto;
import oth.presentation.dto.produit.bean.ProduitDto;



/**
 * Cette classe teste les fonctions de la classe MapperPanier
 * 
 * @author badane
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMapperPanier {

	/**
	 * Cette fonction teste la transformation d'un Objet PanierDto en commandeProduitDo .
	 */
	@Test
	public void test_01_panierdto_to_list_commandeproduitdo() {
		final PanierDto panierDto = new PanierDto();
		final ProduitDto produitDto = new ProduitDto();
		final ProduitDto produitDto2 = new ProduitDto();
		final List<ElementPanierDto> liste = new ArrayList<ElementPanierDto>();

		final ElementPanierDto elem1 = new ElementPanierDto();
			
		produitDto.setReference("ae2727");
		produitDto.setEnVente(true);
		produitDto.setId(1);
		produitDto.setPrix(125.0);
		produitDto.setDescription("aspirateur 2000w");
		elem1.setQuantite(5);
		elem1.setPrixTotal(produitDto.getPrix() * elem1.getQuantite());
		elem1.setProduit(produitDto);

		final ElementPanierDto elem2 = new ElementPanierDto();

		produitDto2.setReference("bc2458");
		produitDto2.setEnVente(true);
		produitDto2.setId(2);
		produitDto2.setPrix(12.0);
		produitDto2.setDescription("balai");

		elem2.setQuantite(5);
		elem2.setPrixTotal(produitDto2.getPrix() * elem2.getQuantite());
		elem2.setProduit(produitDto2);

		liste.add(elem1);
		liste.add(elem2);

		panierDto.setPrixTotal(2000.0);
		panierDto.setRemise(75.0);
		panierDto.setQuantiteTotale(12);
		panierDto.setContenu(liste);

		final List<CommandeProduitDo> listeCommandeProduitDo = MapperPanier.createListeCommandeProduitDo(panierDto);

		final CommandeProduitDo balai=listeCommandeProduitDo.get(0);
		final CommandeProduitDo aspirateur=listeCommandeProduitDo.get(1);

		
		Assert.assertEquals(balai.getReferenceProduit(),produitDto.getReference());
		Assert.assertEquals(balai.getPrixUnitaire(),produitDto.getPrix());
		Assert.assertEquals(balai.getDescriptionProduit(),produitDto.getDescription());
		
		Assert.assertEquals(aspirateur.getReferenceProduit(),produitDto2.getReference());
		Assert.assertEquals(aspirateur.getPrixUnitaire(),produitDto2.getPrix());
		Assert.assertEquals(aspirateur.getDescriptionProduit(),produitDto2.getDescription());
		
		

	}
	
	@Test
	public void test_02_panierdto_to_list_commandeproduitdo_null() {
		final List<CommandeProduitDo> listeCommandeProduitDo = MapperPanier.createListeCommandeProduitDo(null);
		Assert.assertTrue(listeCommandeProduitDo.isEmpty());
		
	}
	

	/**
	 * Cette fonction teste la transformation d'un objet ElementPanierDto en
	 * CommandeProduitDo
	 */
	@Test
	public void test_03_elementpanierdto_to_commandeproduitdo_accept() {
		final ProduitDto produitDto = new ProduitDto();

		final ElementPanierDto elem1 = new ElementPanierDto();

		produitDto.setReference("ae2727");
		produitDto.setEnVente(true);
		produitDto.setId(1);
		produitDto.setPrix(125.0);
		produitDto.setDescription("aspirateur 2000w");
		elem1.setQuantite(5);
		elem1.setPrixTotal(produitDto.getPrix() * elem1.getQuantite());
		elem1.setProduit(produitDto);
		

		final CommandeProduitDo commandeProduitDo = MapperPanier.createCommandeProduitDo(elem1);
		Assert.assertEquals(commandeProduitDo.getQuantite(), elem1.getQuantite());

		Assert.assertEquals(commandeProduitDo.getPhotoProduit(), elem1.getProduit().getPhoto());
		Assert.assertEquals(commandeProduitDo.getPrixUnitaire(), elem1.getProduit().getPrix());
		Assert.assertEquals(commandeProduitDo.getDescriptionProduit(), elem1.getProduit().getDescription());
		Assert.assertEquals(commandeProduitDo.getReferenceProduit(),produitDto.getReference());
	}
	@Test
	public void test_04_elementpanierdto_to_commandeproduitdo_null() {
		final ProduitDto produitDto = new ProduitDto();

		final ElementPanierDto elem1 = new ElementPanierDto();

		produitDto.setReference("ae2727");
		produitDto.setEnVente(true);
		produitDto.setId(1);
		produitDto.setPrix(125.0);
		produitDto.setDescription("aspirateur 2000w");
		elem1.setQuantite(5);
		elem1.setPrixTotal(produitDto.getPrix() * elem1.getQuantite());
		elem1.setProduit(produitDto);

		final CommandeProduitDo commandeProduitDo = MapperPanier.createCommandeProduitDo(elem1);
		Assert.assertEquals(commandeProduitDo.getQuantite(), elem1.getQuantite());

		Assert.assertEquals(commandeProduitDo.getPhotoProduit(), elem1.getProduit().getPhoto());
		Assert.assertEquals(commandeProduitDo.getPrixUnitaire(), elem1.getProduit().getPrix());
		Assert.assertEquals(commandeProduitDo.getDescriptionProduit(), elem1.getProduit().getDescription());
	}
}
