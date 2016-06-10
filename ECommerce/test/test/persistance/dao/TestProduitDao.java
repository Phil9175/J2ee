package test.persistance.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import oth.persistance.bean.produit.ProduitDo;
import oth.persistance.dao.produit.IProduitDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByType;



/**
 * Class de test pour le ProduitDao
 * 
 * @author Phil9175
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testing/testingContext.xml" })
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TestProduitDao {

	@PersistenceContext(unitName = "puEboutique")
	protected EntityManager entityManager;

	@Autowired
	private IProduitDao produitDao;

	/**
	 * Test la récupération d'un produit par identifiant.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_01_findById() throws DaoException {
		// Test des attributs d'un produit récupéré en base (id = 1)
		final ProduitDo macbook = produitDao.findById(1);
		Assert.assertNotNull(macbook);
		Assert.assertEquals((Integer) 1, macbook.getId());
		Assert.assertEquals("mc32", macbook.getReference());
		Assert.assertEquals((Double) 500.0, macbook.getPrix());
		Assert.assertEquals("macbook", macbook.getDescription());
		Assert.assertTrue(macbook.isEnVente());

		// Test des attributs d'un produit récupéré en base (id = 2)
		final ProduitDo macbookPro = produitDao.findById(2);
		Assert.assertNotNull(macbookPro);
		Assert.assertEquals((Integer) 2, macbookPro.getId());
		Assert.assertEquals("mc362", macbookPro.getReference());
		Assert.assertEquals((Double) 1050.0, macbookPro.getPrix());
		Assert.assertEquals("macbook PRO", macbookPro.getDescription());
		Assert.assertTrue(macbookPro.isEnVente());

		// Test des attributs d'un produit récupéré en base (id = 3)
		final ProduitDo macbookAir = produitDao.findById(3);
		Assert.assertNotNull(macbookAir);
		Assert.assertEquals((Integer) 3, macbookAir.getId());
		Assert.assertEquals("mc34", macbookAir.getReference());
		Assert.assertEquals((Double) 1500.0, macbookAir.getPrix());
		Assert.assertEquals("macbook AIR", macbookAir.getDescription());
		Assert.assertFalse(macbookAir.isEnVente());

	}

	/**
	 * Test la récupération d'un produit par identifiant inexistant.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Commit
	public void test_02_findById_unknow_id() throws DaoException {
		produitDao.findById(9999);
	}

	/**
	 * Test la récupération d'un produit par référence.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_03_findByReference() throws DaoException {
		final ProduitDo macbookAir = produitDao.findByReference("mc34");
		Assert.assertNotNull(macbookAir);
		Assert.assertEquals((Integer) 3, macbookAir.getId());
		Assert.assertEquals("mc34", macbookAir.getReference());
		Assert.assertEquals((Double) 1500.0, macbookAir.getPrix());
		Assert.assertEquals("macbook AIR", macbookAir.getDescription());
		Assert.assertFalse(macbookAir.isEnVente());
	}

	/**
	 * Test la récupération d'un produit par référence inconnue.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Commit
	public void test_04_findByReference_unkown() throws DaoException {
		produitDao.findByReference("azerty");
	}

	/**
	 * Test la récupération d'un produit par référence null.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Commit
	public void test_05_findByReference_null() throws DaoException {
		produitDao.findByReference(null);
	}

	/**
	 * Test d'ajout d'un Produit en BD. En deux partie car le transactionnal de
	 * la classe force le résultat dans une même transaction. Ici, il faut
	 * tester que le produit est bien persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_06_01_createProduit() throws DaoException {
		final ProduitDo tablette = new ProduitDo();
		tablette.setDescription("une tablette de chocolat");
		tablette.setEnVente(true);
		tablette.setReference("tab56");
		tablette.setPrix(15.5);
		tablette.setPhoto("Test".getBytes());
		produitDao.create(tablette);
	}

	/**
	 * Deuxième partie du test de création de produit qui vérifie que celui-ci a
	 * bien été persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_06_02_createProduit() throws DaoException {
		final ProduitDo tabletteRecup = produitDao.findByReference("tab56");
		Assert.assertNotNull(tabletteRecup.getId());
		Assert.assertEquals("tab56", tabletteRecup.getReference());
		Assert.assertEquals((Double) 15.5, tabletteRecup.getPrix());
		Assert.assertEquals("une tablette de chocolat", tabletteRecup.getDescription());
		Assert.assertEquals(true, tabletteRecup.isEnVente());

	}

	/**
	 * Test d'ajout d'un Produit avec une réference existante en BD.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_07_createProduit_reference_exist() throws DaoException {
		final ProduitDo tablette = new ProduitDo();
		tablette.setDescription("une autre tablette de chocolat");
		tablette.setEnVente(true);
		tablette.setPrix(15.9);
		tablette.setReference("tab56");
		tablette.setPhoto("Test".getBytes());

		produitDao.create(tablette);
	}

	/**
	 * Test du respect de l'integrité de la description lors de la création.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_08_createProduit_null_description() throws DaoException {
		final ProduitDo iphone = new ProduitDo();
		iphone.setDescription(null);
		iphone.setEnVente(true);
		iphone.setReference("iph5687");
		iphone.setPhoto("Test".getBytes());
		iphone.setPrix(14000.25785);
		produitDao.create(iphone);
	}

	/**
	 * Test du respect de l'integrité de la référence lors de la création.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_09_createProduit_null_reference() throws DaoException {
		final ProduitDo iphone = new ProduitDo();
		iphone.setDescription("apple");
		iphone.setEnVente(true);
		iphone.setReference(null);
		iphone.setPhoto("Test".getBytes());
		iphone.setPrix(14000.25785);
		produitDao.create(iphone);
	}

	/**
	 * Test du respect de l'integrité de la photo lors de la création.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_10_createProduit_null_photo() throws DaoException {
		final ProduitDo iphone = new ProduitDo();
		iphone.setDescription("apple");
		iphone.setEnVente(true);
		iphone.setReference("iph5");
		iphone.setPhoto(null);
		iphone.setPrix(14000.25785);
		produitDao.create(iphone);
	}

	/**
	 * Test du respect de l'integrité du prix lors de la création.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_11_createProduit_null_prix() throws DaoException {
		final ProduitDo iphone = new ProduitDo();
		iphone.setDescription("apple");
		iphone.setEnVente(true);
		iphone.setReference("iph5632");
		iphone.setPhoto("Test".getBytes());
		iphone.setPrix(null);
		produitDao.create(iphone);
	}

	/**
	 * Test que tous les produits insérés sont retournées dans une liste.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_12_findAll() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("mc32");
		final ProduitDo produitDo2 = produitDao.findByReference("mc362");
		final ProduitDo produitDo3 = produitDao.findByReference("mc34");
		final ProduitDo produitDo4 = produitDao.findByReference("ma3624");
		final ProduitDo produitDo5 = produitDao.findByReference("tab56");

		final List<ProduitDo> listeProduitDo = produitDao.findAll();
		Assert.assertEquals(5, listeProduitDo.size());

		Assert.assertTrue(listeProduitDo.contains(produitDo));
		Assert.assertTrue(listeProduitDo.contains(produitDo2));
		Assert.assertTrue(listeProduitDo.contains(produitDo3));
		Assert.assertTrue(listeProduitDo.contains(produitDo4));
		Assert.assertTrue(listeProduitDo.contains(produitDo5));

	}

	/**
	 * Test la modification d'un Produit en BD. En deux partie car le
	 * transactionnal de la classe force le résultat dans une même transaction.
	 * Ici, il faut tester que le produit est bien persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_13_01_updateProduit() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("tab56");
		produitDo.setDescription("Toshiba");
		produitDo.setPrix(1500.0);

		produitDao.update(produitDo);
	}

	/**
	 * Deuxième partie du test de modification de produit qui vérifie que
	 * celui-ci a bien été modifié.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_13_02_updateProduit() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("tab56");

		Assert.assertNotNull(produitDo.getId());
		Assert.assertEquals("Toshiba", produitDo.getDescription());
		Assert.assertEquals((Double) 1500.0, produitDo.getPrix());
		Assert.assertEquals(true, produitDo.isEnVente());
	}

	/**
	 * Test de la mise à jour d'un Produit lors de la modification.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_14_updateProduit_change_reference() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("tab56");

		// On détache l'objet pour essayer de merge
		entityManager.detach(produitDo);

		produitDo.setReference("Toshiba");
		produitDao.update(produitDo);
	}

	/**
	 * Test du respect de l'integrité de la description lors de la modification.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_15_updateProduit_null_description() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("mc34");

		// On détache l'objet pour essayer de le modifier
		entityManager.detach(produitDo);

		produitDo.setDescription(null);
		produitDo.setEnVente(true);
		produitDo.setReference("iph5687");
		produitDo.setPhoto("Test".getBytes());
		produitDo.setPrix(14000.25785);
		produitDao.update(produitDo);
	}

	/**
	 * Test du respect de l'integrité de la référence lors de la modification.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_16_updateProduit_null_reference() throws DaoException {
		final ProduitDo iphone = new ProduitDo();

		// On détache l'objet pour essayer de le modifier
		entityManager.detach(iphone);

		iphone.setDescription("apple");
		iphone.setEnVente(true);
		iphone.setReference(null);
		iphone.setPhoto("Test".getBytes());
		iphone.setPrix(14000.25785);
		produitDao.create(iphone);
	}

	/**
	 * Test du respect de l'integrité de la photo lors de la modification.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_17_updateProduit_null_photo() throws DaoException {
		final ProduitDo iphone = new ProduitDo();

		// On détache l'objet pour essayer de le modifier
		entityManager.detach(iphone);

		iphone.setDescription("apple");
		iphone.setEnVente(true);
		iphone.setReference("iph5");
		iphone.setPhoto(null);
		iphone.setPrix(14000.25785);
		produitDao.create(iphone);
	}

	/**
	 * Testdu respect de l'integrité du prix lors de la modification.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_18_updateProduit_null_prix() throws DaoException {
		final ProduitDo iphone = new ProduitDo();

		// On détache l'objet pour essayer de le modifier
		entityManager.detach(iphone);

		iphone.setDescription("apple");
		iphone.setEnVente(true);
		iphone.setReference("iph5632");
		iphone.setPhoto("Test".getBytes());
		iphone.setPrix(null);
		produitDao.create(iphone);
	}

	/**
	 * Test la suppression d'un Produit en BD. En deux partie car le
	 * transactionnal de la classe force le résultat dans une même transaction.
	 * Ici, il faut tester que le produit est bien persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_19_01_removeProduit() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("tab56");
		Assert.assertNotNull(produitDo);
		Assert.assertNotNull(produitDo.getId());

		produitDao.remove(produitDo.getId());
	}

	/**
	 * Deuxième partie du test de suppression de produit qui vérifie que
	 * celui-ci a bien été supprimé.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_19_02_removeProduit() throws DaoException {
		produitDao.findByReference("tab56");
	}

	/**
	 * Test la suppression d'un Produit avec un id inconnu.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_20_removeProduit_unkown() throws DaoException {
		produitDao.remove(-1);
	}

	/**
	 * Test de la recherche d'un produit par référence trié de manière
	 * croissante.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_21_searchProduitWithReference_ascendant() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("mc32");
		final ProduitDo produitDo2 = produitDao.findByReference("mc362");
		final List<ProduitDo> listProduit = produitDao.searchProduitWithReference("mc",
				new SortByType(SortByType.ORDER_ASC), true);
		Assert.assertNotNull(listProduit);
		Assert.assertEquals(2, listProduit.size());

		// Test que la liste est bien triée par prix croissant
		Assert.assertEquals("mc32", listProduit.get(0).getReference());
		Assert.assertEquals("mc362", listProduit.get(1).getReference());

		Assert.assertTrue(listProduit.contains(produitDo));
		Assert.assertTrue(listProduit.contains(produitDo2));
	}

	/**
	 * Test de la recherche d'un produit par référence trié de manière
	 * decroissante.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_22_searchProduitWithReference_descendant() throws DaoException {
		final ProduitDo produitDo = produitDao.findByReference("mc32");
		final ProduitDo produitDo2 = produitDao.findByReference("mc362");
		final List<ProduitDo> listProduit = produitDao.searchProduitWithReference("mc",
				new SortByType(SortByType.ORDER_DESC), true);
		Assert.assertNotNull(listProduit);
		Assert.assertEquals(2, listProduit.size());

		// Test que la liste est bien triée par prix decroissant
		Assert.assertEquals("mc362", listProduit.get(0).getReference());
		Assert.assertEquals("mc32", listProduit.get(1).getReference());

		Assert.assertTrue(listProduit.contains(produitDo));
		Assert.assertTrue(listProduit.contains(produitDo2));
	}

	/**
	 * Test de la recherche d'un produit par référence via une référence vide.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_23_searchProduitWithReference_empty_reference() throws DaoException {

		List<ProduitDo> listProduit = produitDao.searchProduitWithReference("", new SortByType(SortByType.ORDER_DESC),
				true);
		Assert.assertNotNull(listProduit);
		Assert.assertEquals(3, listProduit.size());
	}

	/**
	 * Test de la recherche d'un produit par référence null.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_24_searchProduitWithReference_null_reference() throws DaoException {

		List<ProduitDo> listProduit = produitDao.searchProduitWithReference(null, new SortByType(SortByType.ORDER_DESC),
				true);
		Assert.assertNotNull(listProduit);
		Assert.assertEquals(3, listProduit.size());
	}

	/**
	 * Test de la recherche d'un produit par référence via une référence
	 * introuvable.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_25_searchProduitWithReference_no_result() throws DaoException {

		List<ProduitDo> listProduit = produitDao.searchProduitWithReference("toto",
				new SortByType(SortByType.ORDER_DESC), true);
		Assert.assertNotNull(listProduit);
		Assert.assertEquals(0, listProduit.size());
	}

}
