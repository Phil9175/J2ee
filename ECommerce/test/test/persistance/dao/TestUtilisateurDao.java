package test.persistance.dao;

import java.text.ParseException;
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

import oth.metier.util.CryptPassword;
import oth.metier.util.Utility;
import oth.persistance.bean.utilisateur.ProfilDo;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.persistance.dao.commande.ICommandeDao;
import oth.persistance.dao.utilisateur.IUtilisateurDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;



/**
 * Class de test pour le UtilisateurDao
 * 
 * @author Phil9175
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testing/testingContext.xml" })
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TestUtilisateurDao {

	@PersistenceContext(unitName = "puEboutique")
	protected EntityManager entityManager;

	@Autowired
	private IUtilisateurDao utilisateurDao;

	@Autowired
	private ICommandeDao commandeDao;

	/**
	 * Test de recherche d'un utilisateur par id.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_01_findById() throws DaoException {
		// On recupère le premier Utilisateur de la base dont l'id est 1
		final UtilisateurDo paul = utilisateurDao.findById(1);
		Assert.assertNotNull(paul);
		Assert.assertEquals((Integer) 1, paul.getId());
		Assert.assertEquals("42 rue de la rose 62000 Arras", paul.getAdresse());
		Assert.assertEquals("08/03/1960", Utility.parseDate(paul.getDateNaissance()));
		Assert.assertEquals(true, paul.isActive());
		Assert.assertEquals("paul298", paul.getLogin());
		Assert.assertEquals("paul29@wanadoo.fr", paul.getMail());
		Assert.assertEquals(CryptPassword.crypt("azerty"), paul.getMotdepasse());
		Assert.assertEquals("Dubois", paul.getNom());
		Assert.assertEquals("Paul", paul.getPrenom());
		Assert.assertEquals("admin", paul.getProfil().getNom());

		// On recupère le deuxieme Utilisateur de la base dont l'id est 2
		final UtilisateurDo consommateur = utilisateurDao.findById(2);
		Assert.assertNotNull(consommateur);
		Assert.assertEquals((Integer) 2, consommateur.getId());
		Assert.assertEquals("123 rue verte 45234 Orléans", consommateur.getAdresse());
		Assert.assertEquals("22/06/1972", Utility.parseDate(consommateur.getDateNaissance()));
		Assert.assertEquals(true, consommateur.isActive());
		Assert.assertEquals("consommateur", consommateur.getLogin());
		Assert.assertEquals("jeanpierre.b@gmail.com", consommateur.getMail());
		Assert.assertEquals(CryptPassword.crypt("azerty"), consommateur.getMotdepasse());
		Assert.assertEquals("Papin", consommateur.getNom());
		Assert.assertEquals("Jean-pierre", consommateur.getPrenom());
		Assert.assertEquals("user", consommateur.getProfil().getNom());

	}

	/**
	 * Test de la recherche d'un utilisateur par id introuvable.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_02_findById_unkown_id() throws DaoException {
		utilisateurDao.findById(-1);
	}

	/**
	 * Test de la recheche d'un utilisateur par login.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_03_findByLogin() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("paul298");

		Assert.assertEquals((Integer) 1, utilisateurDo.getId());
		Assert.assertEquals("42 rue de la rose 62000 Arras", utilisateurDo.getAdresse());
		Assert.assertEquals("08/03/1960", Utility.parseDate(utilisateurDo.getDateNaissance()));
		Assert.assertEquals(true, utilisateurDo.isActive());
		Assert.assertEquals("paul298", utilisateurDo.getLogin());
		Assert.assertEquals("paul29@wanadoo.fr", utilisateurDo.getMail());
		Assert.assertEquals(CryptPassword.crypt("azerty"), utilisateurDo.getMotdepasse());
		Assert.assertEquals("Dubois", utilisateurDo.getNom());
		Assert.assertEquals("Paul", utilisateurDo.getPrenom());
		Assert.assertEquals("admin", utilisateurDo.getProfil().getNom());
	}

	/**
	 * Test de la recheche d'un Utilisateur par login inexistant.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_04_findByLogin_unkown_login() throws DaoException {
		utilisateurDao.findByLogin("Jean");
	}

	/**
	 * Test de la recheche d'un Utilisateur par login null.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_05_findByLogin_null() throws DaoException {
		utilisateurDao.findByLogin(null);
	}

	/**
	 * Test de la recheche d'un Utilisateur par login et mot de passe.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_06_findByLoginAndPassword() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLoginAndPassword("paul298",
				CryptPassword.crypt("azerty"));

		Assert.assertEquals((Integer) 1, utilisateurDo.getId());
		Assert.assertEquals("42 rue de la rose 62000 Arras", utilisateurDo.getAdresse());
		Assert.assertEquals("08/03/1960", Utility.parseDate(utilisateurDo.getDateNaissance()));
		Assert.assertEquals(true, utilisateurDo.isActive());
		Assert.assertEquals("paul298", utilisateurDo.getLogin());
		Assert.assertEquals("paul29@wanadoo.fr", utilisateurDo.getMail());
		Assert.assertEquals(CryptPassword.crypt("azerty"), utilisateurDo.getMotdepasse());
		Assert.assertEquals("Dubois", utilisateurDo.getNom());
		Assert.assertEquals("Paul", utilisateurDo.getPrenom());
		Assert.assertEquals("admin", utilisateurDo.getProfil().getNom());
	}

	/**
	 * Test de la recherche d'un utiliseur par login et password inexistant.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_07_findByLoginAndPassword_unkown_match() throws DaoException {
		utilisateurDao.findByLoginAndPassword("paul298", "aze");
	}

	/**
	 * Test de la recherche d'un utiliseur par login null et password.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_08_findByLoginAndPassword_null_login() throws DaoException {
		utilisateurDao.findByLoginAndPassword(null, CryptPassword.crypt("azerty"));
	}

	/**
	 * Test de la recherche d'un utiliseur par login et password null.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_09_findByLoginAndPassword_null_password() throws DaoException {
		utilisateurDao.findByLoginAndPassword("paul298", null);
	}

	/**
	 * Test de la recherche d'un profil par nom.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_10_findProfilByNom() throws DaoException {
		final ProfilDo admin = utilisateurDao.findProfilByNom("admin");
		Assert.assertNotNull(admin);
		Assert.assertEquals((Integer) 2, admin.getId());
		Assert.assertEquals("admin", admin.getNom());

		final ProfilDo user = utilisateurDao.findProfilByNom("user");
		Assert.assertNotNull(user);
		Assert.assertEquals((Integer) 1, user.getId());
		Assert.assertEquals("user", user.getNom());
	}

	/**
	 * Test de la recherche d'un profil par nom inexistant.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_11_findProfilByNom_unkown() throws DaoException {
		utilisateurDao.findProfilByNom("administrateur");
	}

	/**
	 * Test de la recherche d'un profil par nom null.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_12_findProfilByNom_unkown() throws DaoException {
		utilisateurDao.findProfilByNom(null);
	}

	/**
	 * Test le findAll.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_13_findAll() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("paul298");
		final UtilisateurDo utilisateurDo2 = utilisateurDao.findByLogin("consommateur");

		final List<UtilisateurDo> listeUtilisateurDo = utilisateurDao.findAll();
		Assert.assertEquals(13, listeUtilisateurDo.size());

		// Verification du contenu (quelques éléments)
		Assert.assertTrue(listeUtilisateurDo.contains(utilisateurDo));
		Assert.assertTrue(listeUtilisateurDo.contains(utilisateurDo2));
	}

	/**
	 * Test d'ajout d'un utilisateur en BD. En deux partie car le transactionnal
	 * de la classe force le résultat dans une même transaction. Ici, il faut
	 * tester que l'utilisateur est bien persisté.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test
	@Commit
	public void test_14_01_createUtilisateur() throws DaoException, ParseException {
		final UtilisateurDo client = new UtilisateurDo();
		client.setAdresse("7 rue bleu");
		client.setDateNaissance(Utility.parseStringToDate("12/01/1998"));
		client.setActive(false);
		client.setLogin("client123456");
		client.setMail("client@gmail.com");
		client.setMotdepasse(CryptPassword.crypt("azerty"));
		client.setNom("nom");
		client.setPrenom("prenom");
		client.setProfil(utilisateurDao.findProfilByNom("admin"));

		utilisateurDao.create(client);
	}

	/**
	 * Deuxième partie du test de création d'utilisateur qui vérifie que
	 * celui-ci a bien été persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_14_02_verif_createUtilisateur() throws DaoException {
		final UtilisateurDo clientRecup = utilisateurDao.findByLogin("client123456");
		Assert.assertEquals("7 rue bleu", clientRecup.getAdresse());
		Assert.assertEquals("12/01/1998", Utility.parseDate(clientRecup.getDateNaissance()));
		Assert.assertEquals(false, clientRecup.isActive());
		Assert.assertEquals("client123456", clientRecup.getLogin());
		Assert.assertEquals("client@gmail.com", clientRecup.getMail());
		Assert.assertEquals(CryptPassword.crypt("azerty"), clientRecup.getMotdepasse());
		Assert.assertEquals("nom", clientRecup.getNom());
		Assert.assertEquals("prenom", clientRecup.getPrenom());
		Assert.assertEquals((Integer) 2, clientRecup.getProfil().getId());
		Assert.assertEquals("admin", clientRecup.getProfil().getNom());
	}

	/**
	 * Test d'ajout d'un utilisateur avec un login existant.
	 * 
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_15_createUtilisateur_login_exist() throws DaoException, ParseException {
		final UtilisateurDo client = new UtilisateurDo();
		client.setAdresse("72 rue bleu");
		client.setDateNaissance(Utility.parseStringToDate("12/01/1992"));
		client.setLogin("paul298");
		client.setActive(false);
		client.setMail("client@gmail.com");
		client.setMotdepasse("client");
		client.setNom("client");
		client.setPrenom("client");
		client.setProfil(utilisateurDao.findProfilByNom("admin"));

		utilisateurDao.create(client);
	}

	/**
	 * Test d'ajout d'utilisateur avec un login null.
	 *
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	public void test_16_createUtilisateur_null_login() throws DaoException, ParseException {
		final UtilisateurDo client = new UtilisateurDo();
		client.setAdresse("7 rue bleu");
		client.setDateNaissance(Utility.parseStringToDate("12/01/1998"));
		client.setActive(false);
		client.setLogin(null);
		client.setMail("client23@gmail.com");
		client.setMotdepasse("clientxx");
		client.setNom("client23");
		client.setPrenom("client23");
		client.setProfil(utilisateurDao.findProfilByNom("admin"));

		utilisateurDao.create(client);
	}

	/**
	 * Test d'ajout d'utilisateur avec un password null.
	 *
	 * @throws DaoException
	 * @throws ParseException
	 */
	@Test(expected = DaoException.class)
	public void test_17_createUtilisateur_null_password() throws DaoException, ParseException {
		final UtilisateurDo client = new UtilisateurDo();
		client.setAdresse("7 rue bleu");
		client.setDateNaissance(Utility.parseStringToDate("12/01/1998"));
		client.setActive(false);
		client.setLogin("totototo");
		client.setMail("client23@gmail.com");
		client.setMotdepasse(null);
		client.setNom("client23");
		client.setPrenom("client23");
		client.setProfil(utilisateurDao.findProfilByNom("admin"));

		utilisateurDao.create(client);
	}

	/**
	 * Test de suppression d'un utilisateur en BD. En trois partie car le
	 * transactionnal de la classe force le résultat dans une même transaction.
	 * Ici, il faut tester que l'utilisateur est bien persisté et que les
	 * commandes sont supprimées.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_18_01_removeUtilisateur() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("poupou25");
		Assert.assertNotNull(utilisateurDo);
		Assert.assertEquals(4, commandeDao.findAll().size());
		Assert.assertEquals(2, commandeDao.findByUtilisateurId(utilisateurDo.getId()).size());

		utilisateurDao.remove(utilisateurDo.getId());
	}

	/**
	 * Deuxième partie du test de création de produit qui vérifie que les
	 * commandes ont bien été supprimés.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_18_02_removeUtilisateur() throws DaoException {
		Assert.assertEquals(2, commandeDao.findAll().size());
		Assert.assertEquals(0, commandeDao.findByUtilisateurId(6).size());
	}

	/**
	 * Troisième partie du test de suppression d'utilisateur qui vérifie que
	 * celui-ci a bien été supprimé.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_18_03_removeUtilisateur() throws DaoException {
		utilisateurDao.findByLogin("poupou25");
	}

	/**
	 * Test de supprimer un utilisateur inconnu.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	public void test_19_removeUtilisateur_unknown() throws DaoException {
		utilisateurDao.remove(-1);
	}

	/**
	 * Test d'une mise à jour d'un utilisateur en BD. En deux partie car le
	 * transactionnal de la classe force le résultat dans une même transaction.
	 * Ici, il faut tester que l'utilisateur est bien persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	@Commit
	public void test_20_01_updateUtilisateur() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("consommateur");
		Assert.assertNotNull(utilisateurDo);
		Assert.assertEquals("123 rue verte 45234 Orléans", utilisateurDo.getAdresse());

		// On détach pour merge
		entityManager.detach(utilisateurDo);

		utilisateurDo.setMail("client2@gmail.com");
		utilisateurDo.setMotdepasse(CryptPassword.crypt("clientx"));
		utilisateurDo.setNom("client3Nom");
		utilisateurDo.setPrenom("client3Prenom");

		utilisateurDao.update(utilisateurDo);
	}

	/**
	 * Deuxième partie du test de modification d'utilisateur qui vérifie que
	 * celui-ci a bien été persisté.
	 * 
	 * @throws DaoException
	 */
	@Test
	public void test_20_02_updateUtilisateur() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("consommateur");
		Assert.assertNotNull(utilisateurDo);
		Assert.assertEquals("123 rue verte 45234 Orléans", utilisateurDo.getAdresse());
		Assert.assertEquals("client2@gmail.com", utilisateurDo.getMail());
		Assert.assertEquals(CryptPassword.crypt("clientx"), utilisateurDo.getMotdepasse());
		Assert.assertEquals("client3Nom", utilisateurDo.getNom());
		Assert.assertEquals("client3Prenom", utilisateurDo.getPrenom());
	}

	/**
	 * Test la modification du login d'un utilisateur.
	 * 
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	public void test_21_updateUtilisateur_change_login() throws DaoException {
		final UtilisateurDo client = utilisateurDao.findByLogin("consommateur");
		Assert.assertNotNull(client);

		// On détache le client
		entityManager.detach(client);

		client.setLogin("lothfy");
		utilisateurDao.update(client);
	}

	/**
	 * Test une modification de login null.
	 *
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	public void test_22_updateUtilisateur_null_login() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("paul298");

		// Détache l'objet
		entityManager.detach(utilisateurDo);
		utilisateurDo.setLogin(null);

		utilisateurDao.update(utilisateurDo);
	}

	/**
	 * Test une modification de mot de passe null.
	 *
	 * @throws DaoException
	 */
	@Test(expected = DaoException.class)
	@Rollback
	public void test_23_updateUtilisateur_null_mdp() throws DaoException {
		final UtilisateurDo utilisateurDo = utilisateurDao.findByLogin("paul298");

		// Détache l'objet
		entityManager.detach(utilisateurDo);
		utilisateurDo.setMotdepasse(null);

		utilisateurDao.update(utilisateurDo);
	}

	/**
	 * Test la recherche d'utilisateur par login croissant.
	 * 
	 * @throws DaoException
	 */
	@Test
	public void test_24_searchUtilisateur_by_login_ascendant() throws DaoException {
		final List<UtilisateurDo> listeUtilisateurDo;
		listeUtilisateurDo = utilisateurDao.searchUtilisateur(new SortByField(SortByField.FIELD_LOGIN),
				new SortByType(SortByType.ORDER_ASC));

		// On.assertEquals("client", listeUtilisateurDo.get(0).getLogin());
		Assert.assertEquals("client123456", listeUtilisateurDo.get(1).getLogin());

	}

	/**
	 * Test la recherche d'utilisateur par login décroissant.
	 * 
	 * @throws DaoException
	 */
	@Test
	public void test_25_searchUtilisateur_by_login_descendant() throws DaoException {
		final List<UtilisateurDo> listeUtilisateurDo;
		listeUtilisateurDo = utilisateurDao.searchUtilisateur(new SortByField(SortByField.FIELD_LOGIN),
				new SortByType(SortByType.ORDER_DESC));

		// On test juste que les 2 première valeur sont bien rangés
		Assert.assertEquals("totolefootballeur", listeUtilisateurDo.get(0).getLogin());
		Assert.assertEquals("thibolepasbeau", listeUtilisateurDo.get(1).getLogin());

	}

	/**
	 * Test la recherche d'utilisateur par nom croissant.
	 * 
	 * @throws DaoException
	 */
	@Test
	public void test_26_searchUtilisateur_by_nom_ascendant() throws DaoException {
		final List<UtilisateurDo> listeUtilisateurDo;
		listeUtilisateurDo = utilisateurDao.searchUtilisateur(new SortByField(SortByField.FIELD_NOM),
				new SortByType(SortByType.ORDER_ASC));

		// On test juste que les 2 première valeur sont bien rangés
		Assert.assertEquals("Canard", listeUtilisateurDo.get(0).getNom());
		Assert.assertEquals("client", listeUtilisateurDo.get(1).getNom());

	}

	/**
	 * Test la recherche d'utilisateur par nom décroissant.
	 * 
	 * @throws DaoException
	 */
	@Test
	public void test_27_searchUtilisateur_by_nom_descendant() throws DaoException {
		final List<UtilisateurDo> listeUtilisateurDo;
		listeUtilisateurDo = utilisateurDao.searchUtilisateur(new SortByField(SortByField.FIELD_NOM),
				new SortByType(SortByType.ORDER_DESC));

		// On test juste que les 2 première valeur sont bien rangés
		Assert.assertEquals("Thomas", listeUtilisateurDo.get(0).getNom());
		Assert.assertEquals("nom", listeUtilisateurDo.get(1).getNom());

	}

}
