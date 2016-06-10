package test.metier.mapper.produit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import oth.metier.mapper.produit.MapperProduit;
import oth.persistance.bean.produit.ProduitDo;
import oth.presentation.dto.produit.GestionProduitDto;
import oth.presentation.dto.produit.bean.ProduitDto;



/**
 * Tests pour les fonctions de mapping DO / DTO liées au produit.
 * 
 * @author Phil9175
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMapperProduit {
	/**
	 * Test avec un DTO produit correct pour récupération d'un DO produit.
	 */
	@Test
	public void test_01_produitDto_to_produitDo_correct() {
		// Défini un DO de base
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setId(50);
		produitDto.setDescription("desc");
		produitDto.setEnVente(false);
		produitDto.setPrix(80.64);
		produitDto.setPhoto(null);

		// Mappe le DTO et teste les valeurs mappées

		final ProduitDo produitDo=MapperProduit.createProduitDo(produitDto);
		Assert.assertEquals(new Integer(50), produitDo.getId());
		Assert.assertNull(produitDo.getReference());
		Assert.assertEquals("desc", produitDo.getDescription());
		Assert.assertFalse(produitDo.isEnVente());
		Assert.assertEquals(new Double(80.64), produitDo.getPrix());
		Assert.assertNull(produitDo.getPhoto());
	}

	/**
	 * Test avec un DO produit correct pour récupération d'un DTO produit.
	 */
	@Test
	public void test_02_produitDo_to_produitDto_correct() {
		// Défini un DO de base
		final ProduitDo produitDo=new ProduitDo();
		produitDo.setId(2175);
		produitDo.setReference("aze");
		produitDo.setDescription("dtoDesc");
		produitDo.setEnVente(true);
		produitDo.setPrix(null);
		produitDo.setPhoto(null);

		// Mappe le DO et teste les valeurs mappées
		final ProduitDto produitDto=MapperProduit.createProduitDto(produitDo);
		Assert.assertEquals(new Integer(2175), produitDto.getId());
		Assert.assertEquals("aze", produitDto.getReference());
		Assert.assertEquals("dtoDesc", produitDto.getDescription());
		Assert.assertTrue(produitDto.isEnVente());
		Assert.assertNull(produitDto.getPrix());
		Assert.assertNull(produitDto.getPhoto());
	}

	/**
	 * Test avec un DTO produit null pour récupération d'un DO produit.
	 */
	@Test
	public void test_03_produitDto_to_produitDo_null() {
		final ProduitDo produitDo=MapperProduit.createProduitDo(null);
		Assert.assertNull(produitDo);
	}

	/**
	 * Test avec un DO produit null pour récupération d'un DTO produit.
	 */
	@Test
	public void test_04_produitDo_to_produitDto_null() {
		final ProduitDto produitDto=MapperProduit.createProduitDto(null);
		Assert.assertNull(produitDto);
	}

	/**
	 * Cette foncton teste la conversion d'une objet ListProduitDo en GestionProduitDto
	 */
	@Test
	public void test_05_ListProduitDo_to_GestionProduitDto_accept() {
		//Définition du premier produitDo
		final ProduitDo produitDo=new ProduitDo();
		produitDo.setDescription("aspirateur");
		produitDo.setId(1);
		produitDo.setPhoto(null);
		produitDo.setPrix(25.0);

		//Déclaration du second produitDo
		final ProduitDo produitDo2=new ProduitDo();
		produitDo2.setDescription("four");
		produitDo2.setId(2);
		produitDo2.setPhoto(null);
		produitDo2.setPrix(75.0);

		final List<ProduitDo> liste=new ArrayList<ProduitDo>();
		liste.add(produitDo);
		liste.add(produitDo2);

		//Transformation de la liste de produitDo en GestionProduitDto
		GestionProduitDto gestionProduitDto=MapperProduit.createGestionProduitDto(liste);
		Assert.assertTrue(gestionProduitDto.getListProduit().size() == 2);
		
		
		final ProduitDto aspirateur=gestionProduitDto.getListProduit().get(0);
		final ProduitDto four=gestionProduitDto.getListProduit().get(1);
		
		Assert.assertEquals(produitDo.getDescription(),aspirateur.getDescription());
		Assert.assertEquals(produitDo2.getDescription(),four.getDescription());
		
		Assert.assertEquals(produitDo.getId(),aspirateur.getId());
		Assert.assertEquals(produitDo2.getId(),four.getId());
		
		Assert.assertEquals(produitDo.getPhoto(),aspirateur.getPhoto());
		Assert.assertEquals(produitDo2.getPhoto(),four.getPhoto());
		
		Assert.assertEquals(produitDo.getPrix(),aspirateur.getPrix());
		Assert.assertEquals(produitDo2.getPrix(),four.getPrix());
		
		
	}
	@Test
	public void test_06_ListProduitDo_to_GestionProduitDto_null() {
		
		GestionProduitDto produitDto=MapperProduit.createGestionProduitDto(null);
		Assert.assertNull(produitDto);


	}

}
