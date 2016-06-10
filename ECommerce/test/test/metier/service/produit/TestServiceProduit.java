package test.metier.service.produit;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.dto.produit.bean.ProduitDto;



/**
 * Class de test pour le service produit.
 * 
 * @author badane
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testing/testingContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServiceProduit {

	@Autowired
	public IServiceProduit serviceProduit;

	/**
	 * Teste l'appel au service getProduitByReference avec une référence
	 * correcte.
	 */
	@Test
	public void test_01_getProduitByReference_correct_reference() {
		// Récupère par référence le produit
		final ServiceResponse serviceResponse = serviceProduit.findByReference("mc32");

		// Vérifie la récupération
		Assert.assertFalse(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.findByReference.msg.success", serviceResponse.getMessage());
		final Object objRecup = serviceResponse.getDataResult();
		Assert.assertNotNull(objRecup);
		Assert.assertTrue(objRecup instanceof ProduitDto);

		// Vérifie l'objet récupéré
		final ProduitDto produitRecup = (ProduitDto) objRecup;
		Assert.assertEquals(new Integer(1), produitRecup.getId());
		Assert.assertEquals("mc32", produitRecup.getReference());
		Assert.assertEquals("macbook", produitRecup.getDescription());
		Assert.assertEquals(new Double(500), produitRecup.getPrix());

	}

	/**
	 * Teste l'appel au service getProduitByReference avec une référence null.
	 */
	@Test
	public void test_02_getProduitByReference_null_reference() {
		final ServiceResponse serviceResponse = serviceProduit.findByReference(null);
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.findByReference.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service getProduitByReference avec une référence non
	 * définie en base.
	 */
	@Test
	public void test_03_getProduitByReference_unkown_reference() {
		final ServiceResponse serviceResponse = serviceProduit.findByReference("willnotfindit");
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.findByReference.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service ajouterProduit avec un produit correct.
	 */
	@Test
	public void test_04_ajouterProduit_with_correct_object() {
		final ProduitDto produitDto = new ProduitDto();
		produitDto.setReference("pc test service");
		produitDto.setDescription("mon super pc");
		produitDto.setEnVente(true);
		produitDto.setPhoto("photo".getBytes());
		produitDto.setPrix(8745.99);

		// Test ajout par service
		final ServiceResponse serviceResponse = serviceProduit.ajouterProduit(produitDto);
		Assert.assertFalse(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.ajouterProduit.msg.success", serviceResponse.getMessage());

		// Test objet bien set
		final Object objRecup = serviceResponse.getDataResult();
		Assert.assertNotNull(objRecup);
		Assert.assertTrue(objRecup instanceof ProduitDto);

		// Test l'objet
		final ProduitDto produitRecup = (ProduitDto) objRecup;
		Assert.assertNotNull(produitRecup.getId());
		Assert.assertEquals(produitRecup.getReference(), produitDto.getReference());
		Assert.assertEquals(produitRecup.getDescription(), produitDto.getDescription());
		Assert.assertEquals(produitRecup.getPhoto(), produitDto.getPhoto());
		Assert.assertEquals(produitRecup.getPrix(), produitDto.getPrix());
	}

	/**
	 * Teste l'appel au service ajouterProduit avec un produit corrompu.
	 */
	@Test
	public void test_05_ajouterProduit_with_corrupt_object() {
		final ProduitDto produitDto = new ProduitDto();
		final ServiceResponse serviceResponse = serviceProduit.ajouterProduit(produitDto);
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.ajouterProduit.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service ajouterProduit avec un produit corrompu.
	 */
	@Test
	public void test_05_modifierProduit_with_null_id() {
		// On demande au service de le modifier
		final ProduitDto produitDto = new ProduitDto();
		final ServiceResponse serviceResponseModifierProduit = serviceProduit.modifierProduit(produitDto);
		Assert.assertTrue(serviceResponseModifierProduit.isError());
		Assert.assertNull(serviceResponseModifierProduit.getDataResult());
		Assert.assertNotNull(serviceResponseModifierProduit.getMessage());
		Assert.assertEquals("service.produit.modifierProduit.msg.error", serviceResponseModifierProduit.getMessage());
	}

	/**
	 * Teste l'appel au service ajouterProduit avec un produit correct.
	 */
	@Test
	public void test_06_modifierProduit_with_correct_object() {
		// Récupère un produit déja défini
		final ServiceResponse serviceResponseGetProduitByReference = serviceProduit.findByReference("mc362");
		Assert.assertFalse(serviceResponseGetProduitByReference.isError());
		Assert.assertNotNull(serviceResponseGetProduitByReference.getMessage());
		Assert.assertEquals("service.produit.findByReference.msg.success",
				serviceResponseGetProduitByReference.getMessage());

		final Object objRecup = serviceResponseGetProduitByReference.getDataResult();
		Assert.assertNotNull(objRecup);
		Assert.assertTrue(objRecup instanceof ProduitDto);

		// Modification de l'objet reçu
		final ProduitDto produitRecup = (ProduitDto) objRecup;
		Assert.assertNotNull(produitRecup.getId());
		Assert.assertNotNull(produitRecup.getPrix());
		final Double nouveauxPrix = produitRecup.getPrix() + 1;
		produitRecup.setPrix(nouveauxPrix);

		// On demande au service de le modifier
		final ServiceResponse serviceResponseModifierProduit = serviceProduit.modifierProduit(produitRecup);
		Assert.assertFalse(serviceResponseModifierProduit.isError());
		final Object objRecupModif = serviceResponseModifierProduit.getDataResult();
		Assert.assertNotNull(objRecupModif);
		Assert.assertTrue(objRecupModif instanceof ProduitDto);

		// Test l'objet récupéré
		final ProduitDto produitRecupVerif = (ProduitDto) objRecupModif;
		// Assert.assertNotNull(produitRecupVerif.getId());
		Assert.assertEquals(nouveauxPrix, produitRecupVerif.getPrix());
	}

	/**
	 * Teste l'appel au service modifierProduit avec un produit null.
	 */
	@Test
	public void test_07_modifierProduit_with_null_object() {
		final ServiceResponse serviceResponse = serviceProduit.modifierProduit(null);
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.modifierProduit.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service modifierProduit avec une modification de
	 * référence.
	 */
	@Test
	public void test_08_modifierProduit_with_new_reference() {
		// Récupère un produit déja défini
		final ServiceResponse serviceResponseGetByReference = serviceProduit.findByReference("mc34");
		Assert.assertFalse(serviceResponseGetByReference.isError());
		Assert.assertNotNull(serviceResponseGetByReference.getMessage());
		Assert.assertEquals("service.produit.findByReference.msg.success", serviceResponseGetByReference.getMessage());

		final Object objRecup = serviceResponseGetByReference.getDataResult();
		Assert.assertNotNull(objRecup);
		Assert.assertTrue(objRecup instanceof ProduitDto);

		// Modification de l'objet reçu
		final ProduitDto produitRecup = (ProduitDto) objRecup;
		Assert.assertNotNull(produitRecup.getId());
		Assert.assertNotEquals("nouvelle reference", produitRecup.getReference());
		produitRecup.setReference("nouvelle reference");

		// On demande au service de le modifier
		final ServiceResponse serviceResponseModifierProduit = serviceProduit.modifierProduit(produitRecup);
		Assert.assertTrue(serviceResponseModifierProduit.isError());
		Assert.assertNotNull(serviceResponseModifierProduit.getMessage());
		Assert.assertEquals("service.produit.modifierProduit.msg.error", serviceResponseModifierProduit.getMessage());
		Assert.assertNull(serviceResponseModifierProduit.getDataResult());

		// On vérifie que le produit n'a en effet pas été modifié
		final ServiceResponse serviceResponseGetByReferenceVerif = serviceProduit.findByReference("mc32");
		Assert.assertFalse(serviceResponseGetByReferenceVerif.isError());
		final Object objRecupVerif = serviceResponseGetByReferenceVerif.getDataResult();
		Assert.assertNotNull(objRecupVerif);
		Assert.assertTrue(objRecupVerif instanceof ProduitDto);

	}

	/**
	 * Teste l'appel au service modifierProduit avec une modification de
	 * référence.
	 * 
	 * Ce test se fait en deux étapes car il a besoin de deux transactions
	 * distinctes (une qui doit commit la suppression, une pour chercher un
	 * produit inexistant, quoi doit elle être rollback).
	 */
	@Test
	public void test_09_supprimerProduit_with_correct_id() {
		// Supression du produit
		final ServiceResponse serviceResponseModifierProduit = serviceProduit.supprimerProduit(1);
		Assert.assertFalse(serviceResponseModifierProduit.isError());
		Assert.assertNotNull(serviceResponseModifierProduit.getMessage());
		Assert.assertEquals("service.produit.supprimerProduit.msg.success",
				serviceResponseModifierProduit.getMessage());
		Assert.assertNull(serviceResponseModifierProduit.getDataResult());

		// On verifie que le produit n'existe plus
		final ServiceResponse serviceResponseGetDeletedProduit = serviceProduit.findByReference("mc32");
		Assert.assertTrue(serviceResponseGetDeletedProduit.isError());
		Assert.assertNull(serviceResponseGetDeletedProduit.getDataResult());
		Assert.assertNotNull(serviceResponseGetDeletedProduit.getMessage());
		Assert.assertEquals("service.produit.findByReference.msg.error", serviceResponseGetDeletedProduit.getMessage());
	}

	/**
	 * Teste l'appel au service modifierProduit avec un produit null.
	 */
	@Test
	public void test_10_supprimerProduit_with_unkown_id() {
		final ServiceResponse serviceResponse = serviceProduit.supprimerProduit(99999);
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.produit.supprimerProduit.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service getListeProduit.
	 */
	@Test
	public void test_11_getListeProduit() {
		// TODO CLS Le DAO de recherche de produit
	}

	/**
	 * Teste l'appel au service getListeProduitGestion.
	 */
	@Test
	public void test_12_getListeProduitGestion() {
		// TODO CLS Le DAO de recherche de produit
	}

}
