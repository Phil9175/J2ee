package test.persistance.dao;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import grpb.metier.util.Utility;
import grpb.persistance.bean.commande.CommandeDo;
import grpb.persistance.bean.commande.CommandeProduitDo;
import grpb.persistance.bean.produit.ProduitDo;
import grpb.persistance.bean.utilisateur.UtilisateurDo;
import grpb.persistance.dao.commande.ICommandeDao;
import grpb.persistance.dao.produit.IProduitDao;
import grpb.persistance.dao.utilisateur.IUtilisateurDao;
import grpb.persistance.exception.DaoException;

/**
 * Classe de test des commandes.
 * 
 * @author badane
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testing/testingContext.xml" })
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TestCommandeDao {
	@Autowired
	private ICommandeDao commandeDao;

	@Autowired
	private IUtilisateurDao utilisateurDao;

	@Autowired
	private IProduitDao produitDao;

	/**
	 * Test l'id de la commande insérée de test.
	 */
	public static int ID_COMMANDE_ADD;

	/**
	 * Test du findById avec un id reconnu.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_01_findById() throws DaoException {
		final CommandeDo commandeDo = commandeDao.findById(1);
		Assert.assertNotNull(commandeDo);
		Assert.assertNotNull(commandeDo.getCommandeProduit());
		Assert.assertEquals(0, commandeDo.getCommandeProduit().size());
	}

	/**
	 * Test du findById avec un id non reconnu.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_02_findById_unkown_id() throws DaoException {
		commandeDao.findById(-5);
	}

	/**
	 * Test du findByUtilisateurId.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_04_findByUtilisateurId() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("poupou25");
		final List<CommandeDo> listeCommandeDoOk = commandeDao.findByUtilisateurId(utilisateurDo.getId());
		Assert.assertNotNull(listeCommandeDoOk);
		Assert.assertTrue(listeCommandeDoOk.size() == 2);

		final List<CommandeDo> listeCommandeDoBad = commandeDao.findByUtilisateurId(-1);
		Assert.assertNotNull(listeCommandeDoBad);
		Assert.assertTrue(listeCommandeDoBad.size() == 0);
	}

	/**
	 * Test du findByUtilisateurId avec un id non reconnu.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_04_findByUtilisateurId_unkown_id() throws DaoException {
		final List<CommandeDo> listeCommandeDoOk = commandeDao.findByUtilisateurId(-1);
		Assert.assertNotNull(listeCommandeDoOk);
		Assert.assertTrue(listeCommandeDoOk.size() == 0);
	}

	/**
	 * Test d'ajout d'une commande avec un objet correct. Nous avons besoin de
	 * deux fonctions de test car il faut tester sur deux transactions.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test
	@Commit
	public void test_03_01_createCommande() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul298");
		Assert.assertNotNull(utilisateur);

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc32");
		Assert.assertNotNull(produit1);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();
		// Création des commande produit
		final CommandeProduitDo commandeProduitDo1 = new CommandeProduitDo(produit1);
		// On leut ajoute les attributs
		commandeProduitDo1.setQuantite(1);
		commandeProduitDo1.setCommande(commandeDo);

		// Ajout des commandeproduit à la liste
		listeProduitCommande1.add(commandeProduitDo1);

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation("8 rue des tournesols");
		commandeDo.setAdresseLivraison("5 rue du pivoine");
		commandeDo.setDateCommande(Utility.parseStringToDate("26/06/2015"));
		commandeDo.setRemise(5.5);

		// Ajout de la commande
		commandeDao.create(commandeDo);
		Assert.assertNotNull(commandeDo.getId());
		ID_COMMANDE_ADD = commandeDo.getId();
	}

	/**
	 * Deuxième partie du test d'ajout d'une commande avec un objet correct.
	 * Verifie que la commande a bien été persistée.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_03_02_createCommande_corect_object() throws DaoException {
		// On récupère les commandes de l'utilisateur
		final CommandeDo commande = commandeDao.findById(ID_COMMANDE_ADD);

		// Vérifie le contenu de la commande
		Assert.assertNotNull(commande.getId());
		Assert.assertEquals("8 rue des tournesols", commande.getAdresseFacturation());
		Assert.assertEquals("5 rue du pivoine", commande.getAdresseLivraison());
		Assert.assertEquals("26/06/2015", Utility.parseDate(commande.getDateCommande()));
		Assert.assertEquals(new Double(5.5), commande.getRemise());
		Assert.assertEquals("paul298", commande.getUtilisateur().getLogin());

		// Vérifie le contenu des produits de la commande
		final Set<CommandeProduitDo> listeCommandeProduitProduit = commande.getCommandeProduit();
		Assert.assertEquals(1, listeCommandeProduitProduit.size());

		for (CommandeProduitDo commandeProduitDo : listeCommandeProduitProduit) {
			Assert.assertEquals("mc32", commandeProduitDo.getReferenceProduit());
			Assert.assertEquals(new Double(500), commandeProduitDo.getPrixUnitaire());
			Assert.assertEquals("macbook", commandeProduitDo.getDescriptionProduit());
		}
	}

	/**
	 * Test l'ajout d'une commande avec une liste nulle de produits associés.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_04_createCommande_null_product_list() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul29");
		Assert.assertNotNull(utilisateur);

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc362");
		Assert.assertNotNull(produit1);

		// Set la commande
		commandeDo.setCommandeProduit(null);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation("87 rue de la république");
		commandeDo.setAdresseLivraison("87 rue de la république");
		commandeDo.setDateCommande(Utility.parseStringToDate("10/03/2016"));
		commandeDo.setRemise(0d);

		// Ajout de la commande
		commandeDao.create(commandeDo);
	}

	/**
	 * Test l'ajout d'une commande sans produits associés (liste vide).
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_05_createCommande_no_product() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul29");
		Assert.assertNotNull(utilisateur);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation("87 rue de la république");
		commandeDo.setAdresseLivraison("87 rue de la république");
		commandeDo.setDateCommande(Utility.parseStringToDate("10/03/2016"));
		commandeDo.setRemise(0d);

		// Ajout de la commande
		commandeDao.create(commandeDo);
	}

	/**
	 * Test l'ajout d'une commande avec une adresse de facturation null.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_06_createCommande_null_adressefact() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul298");
		Assert.assertNotNull(utilisateur);

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc32");
		Assert.assertNotNull(produit1);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();
		// Création des commande produit
		final CommandeProduitDo commandeProduitDo1 = new CommandeProduitDo(produit1);
		// On leut ajoute les attributs
		commandeProduitDo1.setQuantite(1);
		commandeProduitDo1.setCommande(commandeDo);

		// Ajout des commandeproduit à la liste
		listeProduitCommande1.add(commandeProduitDo1);

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation(null);
		commandeDo.setAdresseLivraison("5 rue du pivoine");
		commandeDo.setDateCommande(Utility.parseStringToDate("26/06/2015"));
		commandeDo.setRemise(5.5);

		// Ajout de la commande
		commandeDao.create(commandeDo);
		Assert.assertNotNull(commandeDo.getId());
		ID_COMMANDE_ADD = commandeDo.getId();
	}

	/**
	 * Test l'ajout d'une commande avec une adresse de facturation vide.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_07_createCommande_empty_adressefact() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul298");
		Assert.assertNotNull(utilisateur);

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc32");
		Assert.assertNotNull(produit1);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();
		// Création des commande produit
		final CommandeProduitDo commandeProduitDo1 = new CommandeProduitDo(produit1);
		// On leut ajoute les attributs
		commandeProduitDo1.setQuantite(1);
		commandeProduitDo1.setCommande(commandeDo);

		// Ajout des commandeproduit à la liste
		listeProduitCommande1.add(commandeProduitDo1);

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation("");
		commandeDo.setAdresseLivraison("5 rue du pivoine");
		commandeDo.setDateCommande(Utility.parseStringToDate("26/06/2015"));
		commandeDo.setRemise(5.5);

		// Ajout de la commande
		commandeDao.create(commandeDo);
	}

	/**
	 * Test l'ajout d'une commande avec une adresse de livraison null.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_08_createCommande_null_adresselivr() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul298");
		Assert.assertNotNull(utilisateur);

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc32");
		Assert.assertNotNull(produit1);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();
		// Création des commande produit
		final CommandeProduitDo commandeProduitDo1 = new CommandeProduitDo(produit1);
		// On leut ajoute les attributs
		commandeProduitDo1.setQuantite(1);
		commandeProduitDo1.setCommande(commandeDo);

		// Ajout des commandeproduit à la liste
		listeProduitCommande1.add(commandeProduitDo1);

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation("8 rue des tournesols");
		commandeDo.setAdresseLivraison(null);
		commandeDo.setDateCommande(Utility.parseStringToDate("26/06/2015"));
		commandeDo.setRemise(5.5);

		// Ajout de la commande
		commandeDao.create(commandeDo);
	}

	/**
	 * Test l'ajout d'une commande avec une adresse de livraison vide.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_09_createCommande_empty_adresselivr() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get le user
		final UtilisateurDo utilisateur = utilisateurDao.findByLogin("paul298");
		Assert.assertNotNull(utilisateur);

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc32");
		Assert.assertNotNull(produit1);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();
		// Création des commande produit
		final CommandeProduitDo commandeProduitDo1 = new CommandeProduitDo(produit1);
		// On leut ajoute les attributs
		commandeProduitDo1.setQuantite(1);
		commandeProduitDo1.setCommande(commandeDo);

		// Ajout des commandeproduit à la liste
		listeProduitCommande1.add(commandeProduitDo1);

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(utilisateur);
		commandeDo.setAdresseFacturation("8 rue des tournesols");
		commandeDo.setAdresseLivraison("");
		commandeDo.setDateCommande(Utility.parseStringToDate("26/06/2015"));
		commandeDo.setRemise(5.5);

		// Ajout de la commande
		commandeDao.create(commandeDo);
	}

	/**
	 * Test l'ajout d'une commande sans utilisateur associé.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_10_createCommande_null_user() throws DaoException, ParseException {
		// Créé la commande
		final CommandeDo commandeDo = new CommandeDo();

		// Get les produits
		final ProduitDo produit1 = produitDao.findByReference("mc362");
		Assert.assertNotNull(produit1);

		final HashSet<CommandeProduitDo> listeProduitCommande1 = new HashSet<>();
		// Création des commande produit
		final CommandeProduitDo commandeProduitDo1 = new CommandeProduitDo(produit1);
		// On leut ajoute les attributs
		commandeProduitDo1.setQuantite(20);
		commandeProduitDo1.setCommande(commandeDo);

		// Ajout des commandeproduit à la liste
		listeProduitCommande1.add(commandeProduitDo1);

		// Set la commande
		commandeDo.setCommandeProduit(listeProduitCommande1);
		commandeDo.setUtilisateur(null);
		commandeDo.setAdresseFacturation("87 rue de la république");
		commandeDo.setAdresseLivraison("87 rue de la république");
		commandeDo.setDateCommande(Utility.parseStringToDate("10/03/2016"));
		commandeDo.setRemise(200.99);

		// Ajout de la commande
		commandeDao.create(commandeDo);
	}

	/**
	 * Test de la modification d'une commande.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_11_modifyCommande() throws DaoException {
		final CommandeDo commandeDo = commandeDao.findById(ID_COMMANDE_ADD);
		commandeDo.setAdresseFacturation("bad adress");

		commandeDao.update(commandeDo);
	}

	/**
	 * Test de la supression d'une commande.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_12_removeCommande() throws DaoException {
		commandeDao.remove(ID_COMMANDE_ADD);
	}

}
