package test.metier.service.utilisateur;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.metier.util.Utility;
import oth.persistance.bean.utilisateur.ProfilDo;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.ConnexionDto;
import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.dto.utilisateur.InscriptionDto;
import oth.presentation.dto.utilisateur.ListeUtilisateurDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;



/**
 * Class de test pour le service utilisateur.
 * 
 * @author Phil9175
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testing/testingContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServiceUtilisateur {
	@Autowired
	public IServiceUtilisateur serviceUtilisateur;

	/**
	 * Teste l'appel au service utilisateur findById avec un id correct.
	 * 
	 */
	@Test
	public void test_01_getUtilisateurById_correct() {
		final ServiceResponse serviceResponse = serviceUtilisateur.findById(1);

		// Vérifie la récupération
		Assert.assertFalse(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.findById.msg.success", serviceResponse.getMessage());

		// Vérifie le contenu de l'objet trouvé
		final Object objRecup = serviceResponse.getDataResult();
		Assert.assertNotNull(objRecup);
		Assert.assertTrue(objRecup instanceof UtilisateurDto);

		final UtilisateurDto utilisateurDto = (UtilisateurDto) objRecup;
		Assert.assertEquals(new Integer(1), utilisateurDto.getId());
		Assert.assertEquals("42 rue de la rose 62000 Arras", utilisateurDto.getAdresse());
		Assert.assertEquals("1960-03-08", utilisateurDto.getDateNaissance().toString());
		Assert.assertEquals("paul298", utilisateurDto.getLogin());
		Assert.assertEquals("paul29@wanadoo.fr", utilisateurDto.getMail());
		Assert.assertEquals("Dubois", utilisateurDto.getNom());
		Assert.assertEquals("Paul", utilisateurDto.getPrenom());
		Assert.assertTrue(utilisateurDto.isActif());
		Assert.assertEquals("user", ProfilDo.PROFIL_NAME_USER);

	}

	/**
	 * Teste l'appel au service utilisateur findById avec un id incorrect.
	 */
	@Test
	public void test_02_getUtilisateurById_unkown_id() {
		final ServiceResponse serviceResponse = serviceUtilisateur.findById(9999);
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.findById.msg.error", serviceResponse.getMessage());

		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur connecterUtilisateur avec un login
	 * mdp correct.
	 * 
	 */
	@Test
	public void test_03_connecterUtilisateur_correct() {
		final ConnexionDto connexionDto = new ConnexionDto();
		connexionDto.setLogin("paul298");
		connexionDto.setMotDePasse("azerty");

		final ServiceResponse serviceResponse = serviceUtilisateur.connecterUtilisateur(connexionDto);

		// Vérifie la connexion
		Assert.assertFalse(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.connexion.msg.success", serviceResponse.getMessage());

		// Vérifie le contenu de l'objet trouvé
		final Object dataResult = serviceResponse.getDataResult();
		Assert.assertNotNull(dataResult);
		Assert.assertTrue(dataResult instanceof UtilisateurDto);

		final UtilisateurDto utilisateur = (UtilisateurDto) dataResult;
		Assert.assertEquals((Integer) 1, utilisateur.getId());
		Assert.assertEquals("42 rue de la rose 62000 Arras", utilisateur.getAdresse());
		Assert.assertEquals("08/03/1960", Utility.parseDate(utilisateur.getDateNaissance()));
		Assert.assertEquals(true, utilisateur.isActif());
		Assert.assertEquals("paul298", utilisateur.getLogin());
		Assert.assertEquals("paul29@wanadoo.fr", utilisateur.getMail());
		Assert.assertEquals("Dubois", utilisateur.getNom());
		Assert.assertEquals("Paul", utilisateur.getPrenom());
		Assert.assertEquals(ProfilDo.PROFIL_NAME_ADMIN, utilisateur.getNomProfil());
	}

	/**
	 * Teste l'appel au service utilisateur connecterUtilisateur avec un mdp
	 * incorrect.
	 */
	@Test
	public void test_05_connecterUtilisateur_bad_password() {
		final ConnexionDto connexionDto = new ConnexionDto();
		connexionDto.setLogin("paul298");
		connexionDto.setMotDePasse("azertyaze");

		final ServiceResponse serviceResponse = serviceUtilisateur.connecterUtilisateur(connexionDto);
		// Vérifie la connexion
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.connexion.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur connecterUtilisateur avec un login
	 * incorrect.
	 */
	@Test
	public void test_05_connecterUtilisateur_bad_login() {
		final ConnexionDto connexionDto = new ConnexionDto();
		connexionDto.setLogin("paul298azd");
		connexionDto.setMotDePasse("azerty");

		final ServiceResponse serviceResponse = serviceUtilisateur.connecterUtilisateur(connexionDto);

		// Vérifie la connexion
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.connexion.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur connecterUtilisateur avec un login
	 * null.
	 */
	@Test
	public void test_05_connecterUtilisateur_null_login() {
		final ConnexionDto connexionDto = new ConnexionDto();
		connexionDto.setLogin(null);
		connexionDto.setMotDePasse("azerty");

		final ServiceResponse serviceResponse = serviceUtilisateur.connecterUtilisateur(connexionDto);

		// Vérifie la connexion
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.connexion.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());

	}

	/**
	 * Teste l'appel au service utilisateur connecterUtilisateur avec un mdp
	 * null.
	 */
	@Test
	public void test_06_connecterUtilisateur_null_password() {
		final ConnexionDto connexionDto = new ConnexionDto();
		connexionDto.setLogin("paul298");
		connexionDto.setMotDePasse(null);

		final ServiceResponse serviceResponse = serviceUtilisateur.connecterUtilisateur(connexionDto);

		// Vérifie la connexion
		Assert.assertTrue(serviceResponse.isError());
		Assert.assertNotNull(serviceResponse.getMessage());
		Assert.assertEquals("service.utilisateur.connexion.msg.error", serviceResponse.getMessage());
		Assert.assertNull(serviceResponse.getDataResult());

	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un objet
	 * correct.
	 */
	@Test
	public void test_07_inscrireUtilisateur_correct() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("ABC");
		inscriptionDto.setMail("bla@hotmail.fr");
		inscriptionDto.setAdresse("Adresse");
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());

		// On verifie l'inscription
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertFalse(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.success", serviceResponseInscription.getMessage());
		Assert.assertNotNull(serviceResponseInscription.getDataResult());
		Assert.assertTrue(serviceResponseInscription.getDataResult() instanceof UtilisateurDto);

		// On verifie l'objet récupéré
		final UtilisateurDto utilisateurDtoInscrit = (UtilisateurDto) serviceResponseInscription.getDataResult();
		Assert.assertEquals("ABC", utilisateurDtoInscrit.getLogin());
		Assert.assertEquals("Nom", utilisateurDtoInscrit.getNom());
		Assert.assertEquals("Prenom", utilisateurDtoInscrit.getPrenom());
		Assert.assertEquals("bla@hotmail.fr", utilisateurDtoInscrit.getMail());
		Assert.assertEquals("Adresse", utilisateurDtoInscrit.getAdresse());
		Assert.assertEquals("user", utilisateurDtoInscrit.getNomProfil());

		// // On teste que c'est ok par récupération login
		// final ServiceResponse serviceResponse =
		// serviceUtilisateur.findUtilisateurDtoByLogin("ABC");
		// Assert.assertFalse(serviceResponse.isError());
		// Assert.assertNotNull(serviceResponse.getMessage());
		// Assert.assertEquals("service.utilisateur.findByLogin.msg.success",
		// serviceResponse.getMessage());
		// Assert.assertNotNull(serviceResponse.getDataResult());
		// Assert.assertTrue(serviceResponse.getDataResult() instanceof
		// UtilisateurDto);
		// final Object objRecup = serviceResponse.getDataResult();
		// final UtilisateurDto utilisateurDto = (UtilisateurDto) objRecup;
		// Assert.assertEquals("Nom", utilisateurDto.getNom());
		// Assert.assertEquals("Prenom", utilisateurDto.getPrenom());
		// Assert.assertEquals("bla@hotmail.fr", utilisateurDto.getMail());
		// Assert.assertEquals("Adresse", utilisateurDto.getAdresse());
		// Assert.assertEquals("user", utilisateurDto.getNomProfil());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un login
	 * vide.
	 */
	@Test
	public void test_08_inscrireUtilisateur_empty_login() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("");
		inscriptionDto.setMail("bla@hotmail.fr");
		inscriptionDto.setAdresse("Adresse");
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un login
	 * null.
	 */
	@Test
	public void test_09_inscrireUtilisateur_null_login() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin(null);
		inscriptionDto.setMail("bla@hotmail.fr");
		inscriptionDto.setAdresse("Adresse");
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un mail
	 * vide.
	 */
	@Test
	public void test_10_inscrireUtilisateur_empty_mail() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("");
		inscriptionDto.setAdresse("Adresse");
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un mail
	 * null.
	 */
	@Test
	public void test_11_inscrireUtilisateur_null_mail() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail(null);
		inscriptionDto.setAdresse("Adresse");
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec une adresse
	 * vide.
	 */
	@Test
	public void test_12_inscrireUtilisateur_empty_adresse() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("");
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec une adresse
	 * null.
	 */
	@Test
	public void test_13_inscrireUtilisateur_null_adresse() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@aze.aze");
		inscriptionDto.setAdresse(null);
		inscriptionDto.setMdp("mdp1");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un mot de
	 * passe vide.
	 */
	@Test
	public void test_14_inscrireUtilisateur_empty_mdp() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("");
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un mot de
	 * passe null.
	 */
	@Test
	public void test_15_inscrireUtilisateur_null_mdp() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@aze.aze");
		inscriptionDto.setAdresse("aze");
		inscriptionDto.setMdp(null);
		inscriptionDto.setConfMotDePasse("mdp1");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec une
	 * confirmation de mot de passe vide.
	 */
	@Test
	public void test_16_inscrireUtilisateur_empty_confmdp() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec une
	 * confirmation de mot de passe null.
	 */
	@Test
	public void test_17_inscrireUtilisateur_null_confmdp() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@aze.aze");
		inscriptionDto.setAdresse("aze");
		inscriptionDto.setMdp(null);
		inscriptionDto.setConfMotDePasse(null);
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un mot de
	 * passe vide.
	 */
	@Test
	public void test_18_inscrireUtilisateur_mpd_et_confmdp_vide() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("");
		inscriptionDto.setConfMotDePasse("");
		inscriptionDto.setNom("Nom");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un nom
	 * vide.
	 */
	@Test
	public void test_16_inscrireUtilisateur_empty_nom() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("aze");
		inscriptionDto.setNom("");
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un nom
	 * null.
	 */
	@Test
	public void test_17_inscrireUtilisateur_null_nom() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@aze.aze");
		inscriptionDto.setAdresse("aze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("aze");
		inscriptionDto.setNom(null);
		inscriptionDto.setPrenom("Prenom");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un prenom
	 * vide.
	 */
	@Test
	public void test_18_inscrireUtilisateur_empty_prenom() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("aze");
		inscriptionDto.setNom("nom");
		inscriptionDto.setPrenom("");
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec un prenom
	 * null.
	 */
	@Test
	public void test_19_inscrireUtilisateur_null_prenom() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@aze.aze");
		inscriptionDto.setAdresse("aze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("aze");
		inscriptionDto.setNom("nom");
		inscriptionDto.setPrenom(null);
		inscriptionDto.setDateNaissance(new Date());
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec une date de
	 * naissance null.
	 */
	@Test
	public void test_20_inscrireUtilisateur_null_datenaissance() {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("aze");
		inscriptionDto.setNom("nom");
		inscriptionDto.setPrenom("prenom");
		inscriptionDto.setDateNaissance(null);
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * Teste l'appel au service utilisateur inscrireUtilisateur avec une date de
	 * naissance future.
	 */
	@Test
	public void test_21_inscrireUtilisateur_future_datenaissance() throws ParseException {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setLogin("aze");
		inscriptionDto.setMail("azeaze@azeaze.aze");
		inscriptionDto.setAdresse("azeaze");
		inscriptionDto.setMdp("aze");
		inscriptionDto.setConfMotDePasse("aze");
		inscriptionDto.setNom("nom");
		inscriptionDto.setPrenom("prenom");
		inscriptionDto.setDateNaissance(Utility.parseStringToDate("12/02/2100"));
		final ServiceResponse serviceResponseInscription = serviceUtilisateur.inscrireUtilisateur(inscriptionDto);
		Assert.assertTrue(serviceResponseInscription.isError());
		Assert.assertNotNull(serviceResponseInscription.getMessage());
		Assert.assertEquals("service.utilisateur.inscription.msg.error", serviceResponseInscription.getMessage());
		Assert.assertNull(serviceResponseInscription.getDataResult());
	}

	/**
	 * On test l'appel au Service Utilisateur : updateUtilisateur
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_22_updateUtilisateur() throws ParseException {
		final EditInfoPersoDto editInfoPersoDto = new EditInfoPersoDto();
		editInfoPersoDto.setLogin("consommateur");
		editInfoPersoDto.setMail("blamatoscope@hotmail.fr");
		editInfoPersoDto.setAdresse("Adresse");
		editInfoPersoDto.setAncienMdp("mdp34");
		editInfoPersoDto.setNouveauMdp("mdp3455");
		editInfoPersoDto.setNouveauMdpConfirmation("mdp3455");
		editInfoPersoDto.setNom("NouveauNom");
		editInfoPersoDto.setPrenom("NouveauPrenom");
		editInfoPersoDto.setNomProfil("user");
		editInfoPersoDto.setDateNaissance(Utility.parseStringToDate("12/02/2000"));

		// On verifie l'inscription
		final ServiceResponse serviceResponseUpdate = serviceUtilisateur.updateUtilisateur(editInfoPersoDto);
		Assert.assertFalse(serviceResponseUpdate.isError());
		Assert.assertNotNull(serviceResponseUpdate.getMessage());
		Assert.assertEquals("service.utilisateur.modification.msg.success", serviceResponseUpdate.getMessage());
		Assert.assertNotNull(serviceResponseUpdate.getDataResult());
		Assert.assertTrue(serviceResponseUpdate.getDataResult() instanceof EditInfoPersoDto);

		// On verifie l'objet récupéré
		final EditInfoPersoDto utilisateurDtoUpdate = (EditInfoPersoDto) serviceResponseUpdate.getDataResult();
		Assert.assertEquals("consommateur", utilisateurDtoUpdate.getLogin());
		Assert.assertEquals("NouveauNom", utilisateurDtoUpdate.getNom());
		Assert.assertEquals("NouveauPrenom", utilisateurDtoUpdate.getPrenom());
		Assert.assertEquals("blamatoscope@hotmail.fr", utilisateurDtoUpdate.getMail());
		Assert.assertEquals("Adresse", utilisateurDtoUpdate.getAdresse());
		Assert.assertEquals("user", utilisateurDtoUpdate.getNomProfil());

		// // On teste que c'est ok par récupération login
		// final ServiceResponse serviceResponse =
		// serviceUtilisateur.findUtilisateurDtoByLogin("consommateur");
		// Assert.assertFalse(serviceResponse.isError());
		// Assert.assertNotNull(serviceResponse.getMessage());
		// Assert.assertEquals("service.utilisateur.findByLogin.msg.success",
		// serviceResponse.getMessage());
		// Assert.assertNotNull(serviceResponse.getDataResult());
		// Assert.assertTrue(serviceResponse.getDataResult() instanceof
		// UtilisateurDto);
		// final Object objRecup = serviceResponse.getDataResult();
		// final UtilisateurDto utilisateurDto = (UtilisateurDto) objRecup;
		// Assert.assertEquals("consommateur", utilisateurDto.getLogin());
		// Assert.assertEquals("NouveauNom", utilisateurDto.getNom());
		// Assert.assertEquals("NouveauPrenom", utilisateurDto.getPrenom());
		// Assert.assertEquals("blamatoscope@hotmail.fr",
		// utilisateurDto.getMail());
		// Assert.assertEquals("Adresse", utilisateurDto.getAdresse());
		// Assert.assertEquals("user", utilisateurDto.getNomProfil());
	}

	/**
	 * On test l'appel au Service Utilisateur : supprimerUtilisateur avec un
	 * utilisateur qui existe en base
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_23_SuppessionUtilisateurExistant() {

		final ServiceResponse serviceResponse = serviceUtilisateur.findById(1);

		// On recupere l'utilisateur d'id 1
		final UtilisateurDto utilisateurDto = (UtilisateurDto) serviceResponse.getDataResult();

		// On verifie la suprresion est bien faite
		Assert.assertEquals(true, serviceUtilisateur.supprimerUtilisateur(utilisateurDto));

		final ServiceResponse serviceResponse2 = serviceUtilisateur.findById(1);
		Assert.assertEquals("service.utilisateur.findById.msg.error", serviceResponse2.getMessage());

	}

	/**
	 * On test l'appel au Service Utilisateur : updateUtilisateurByAdmmin
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_24_updateUtilisateurByAdmin() throws ParseException {

		// // On teste que c'est ok par récupération login
		// final ServiceResponse serviceResponseFind =
		// serviceUtilisateur.findUtilisateurDtoByLogin("consommateur");
		// Assert.assertFalse(serviceResponseFind.isError());
		// Assert.assertNotNull(serviceResponseFind.getMessage());
		// Assert.assertEquals("service.utilisateur.findByLogin.msg.success",
		// serviceResponseFind.getMessage());
		// Assert.assertNotNull(serviceResponseFind.getDataResult());
		// Assert.assertTrue(serviceResponseFind.getDataResult() instanceof
		// UtilisateurDto);
		// final UtilisateurDto utilisateurDtoRecup = (UtilisateurDto)
		// serviceResponseFind.getDataResult();
		//
		// // On change ses informations
		// final EditInfoPersoByAdminDto editInfoPersoByAdminDto = new
		// EditInfoPersoByAdminDto();
		//
		// editInfoPersoByAdminDto.setUtilisateur(utilisateurDtoRecup);
		// editInfoPersoByAdminDto.getUtilisateur().setLogin("consommateur");
		// editInfoPersoByAdminDto.setNouveauMdp("albert");
		// editInfoPersoByAdminDto.setNouveauMdpConfirmation("albert");
		// editInfoPersoByAdminDto.setProfil(ProfilDo.PROFIL_NAME_ADMIN);
		//
		// // On verifie la mise à jour
		// final ServiceResponse serviceResponseUpdate = serviceUtilisateur
		// .updateUtilisateurByAdmin(editInfoPersoByAdminDto);
		// Assert.assertFalse(serviceResponseUpdate.isError());
		// Assert.assertNotNull(serviceResponseUpdate.getMessage());
		// Assert.assertEquals("service.utilisateur.modification.msg.success",
		// serviceResponseUpdate.getMessage());
		// Assert.assertNotNull(serviceResponseUpdate.getDataResult());
		// Assert.assertTrue(serviceResponseUpdate.getDataResult() instanceof
		// EditInfoPersoByAdminDto);
		//
		// // On verifie l'objet récupéré
		// final EditInfoPersoByAdminDto utilisateurDtoUpdate =
		// (EditInfoPersoByAdminDto) serviceResponseUpdate
		// .getDataResult();
		// Assert.assertEquals(ProfilDo.PROFIL_NAME_ADMIN,
		// utilisateurDtoUpdate.getProfil());
		// Assert.assertEquals(CryptPassword.crypt("albert"),
		// utilisateurDtoUpdate.getNouveauMdp());
		//
		// // On teste que c'est ok par récupération login
		// final ServiceResponse serviceResponse =
		// serviceUtilisateur.findUtilisateurDtoByLogin("consommateur");
		// Assert.assertFalse(serviceResponse.isError());
		// Assert.assertNotNull(serviceResponse.getMessage());
		// Assert.assertEquals("service.utilisateur.findByLogin.msg.success",
		// serviceResponse.getMessage());
		// Assert.assertNotNull(serviceResponse.getDataResult());
		// Assert.assertTrue(serviceResponse.getDataResult() instanceof
		// UtilisateurDto);
		// final Object objRecup = serviceResponse.getDataResult();
		// final UtilisateurDto utilisateurDto = (UtilisateurDto) objRecup;
		// Assert.assertEquals("consommateur", utilisateurDto.getLogin());
		// Assert.assertEquals(ProfilDo.PROFIL_NAME_ADMIN,
		// utilisateurDtoUpdate.getProfil());
		// Assert.assertEquals(CryptPassword.crypt("albert"),
		// utilisateurDtoUpdate.getNouveauMdp());

	}

	/**
	 * On test l'appel au Service Utilisateur : getListeUtilisateur
	 * 
	 * 
	 */
	@Test
	public void test_25_RechercheUtilisateurParLoginASC() {

		// ON recupere notre liste de UtilisateurDto
		final ServiceResponse serviceResponse = serviceUtilisateur
				.getListeUtilisateur(new SortByField(SortByField.FIELD_LOGIN), new SortByType(SortByType.ORDER_ASC));

		Assert.assertFalse(serviceResponse.isError());
		Assert.assertEquals("service.produit.findByReference.msg.success", serviceResponse.getMessage());

		Assert.assertNotNull(serviceResponse.getDataResult());

		// On cast le resultat de ServiceReponse sachanat que c'est une
		// List<UtilisateurDto>

		final ListeUtilisateurDto listeUtilisateur = (ListeUtilisateurDto) serviceResponse.getDataResult();
		List<UtilisateurDto> listeUtilisateurDto = listeUtilisateur.getListUtilisateur();
		Assert.assertEquals("ABC", listeUtilisateurDto.get(0).getLogin());
		Assert.assertEquals("client", listeUtilisateurDto.get(1).getLogin());

	}

	/**
	 * On test l'appel au Service Utilisateur : getListeUtilisateur
	 * 
	 * On test une recherche d'utilisateur en triant le resultat par login
	 * decroissant croissant
	 */
	@Test
	public void test_26_RechercheUtilisateurParLoginDESC() {
		// ON recupere notre liste de UtilisateurDto
		final ServiceResponse serviceResponse = serviceUtilisateur
				.getListeUtilisateur(new SortByField(SortByField.FIELD_LOGIN), new SortByType(SortByType.ORDER_DESC));

		Assert.assertFalse(serviceResponse.isError());
		Assert.assertEquals("service.produit.findByReference.msg.success", serviceResponse.getMessage());

		Assert.assertNotNull(serviceResponse.getDataResult());

		// On cast le resultat de ServiceReponse sachanat que c'est une
		// List<UtilisateurDto>

		final ListeUtilisateurDto listeUtilisateur = (ListeUtilisateurDto) serviceResponse.getDataResult();
		List<UtilisateurDto> listeUtilisateurDto = listeUtilisateur.getListUtilisateur();
		Assert.assertEquals("totolefootballeur", listeUtilisateurDto.get(0).getLogin());
		Assert.assertEquals("thibolepasbeau", listeUtilisateurDto.get(1).getLogin());
	}

	/**
	 * On test une recherche d'utilisateur en triant le resultat par nom
	 * croissant croissant
	 */
	@Test
	public void test_27_RechercheUtilisateurParNomASC() {
		// ON recupere notre liste de UtilisateurDto
		final ServiceResponse serviceResponse = serviceUtilisateur
				.getListeUtilisateur(new SortByField(SortByField.FIELD_NOM), new SortByType(SortByType.ORDER_ASC));

		Assert.assertFalse(serviceResponse.isError());
		Assert.assertEquals("service.produit.findByReference.msg.success", serviceResponse.getMessage());

		Assert.assertNotNull(serviceResponse.getDataResult());

		// On cast le resultat de ServiceReponse sachanat que c'est une
		// List<UtilisateurDto>

		final ListeUtilisateurDto listeUtilisateur = (ListeUtilisateurDto) serviceResponse.getDataResult();
		List<UtilisateurDto> listeUtilisateurDto = listeUtilisateur.getListUtilisateur();
		Assert.assertEquals("thibolepasbeau", listeUtilisateurDto.get(0).getLogin());
		Assert.assertEquals("client", listeUtilisateurDto.get(1).getLogin());
	}

	/**
	 * On test une recherche d'utilisateur en triant le resultat par nom
	 * decroissant croissant
	 */
	@Test
	public void test_28_RechercheUtilisateurParNomDESC() {
		// ON recupere notre liste de UtilisateurDto
		final ServiceResponse serviceResponse = serviceUtilisateur
				.getListeUtilisateur(new SortByField(SortByField.FIELD_NOM), new SortByType(SortByType.ORDER_DESC));

		Assert.assertFalse(serviceResponse.isError());
		Assert.assertEquals("service.produit.findByReference.msg.success", serviceResponse.getMessage());

		Assert.assertNotNull(serviceResponse.getDataResult());

		// On cast le resultat de ServiceReponse sachanat que c'est une
		// List<UtilisateurDto>

		final ListeUtilisateurDto listeUtilisateur = (ListeUtilisateurDto) serviceResponse.getDataResult();
		List<UtilisateurDto> listeUtilisateurDto = listeUtilisateur.getListUtilisateur();
		Assert.assertEquals("totolefootballeur", listeUtilisateurDto.get(0).getLogin());
		Assert.assertEquals("consommateur", listeUtilisateurDto.get(1).getLogin());
	}

	/**
	 * On test l'appel au Service Utilisateur : changeEtatUtilisateur
	 * 
	 * 
	 */
	@Test
	public void test_29_ChangerEtatUtilisateur() {

		// On recupere l'utilisateur Mika
		final ServiceResponse serviceResponse = serviceUtilisateur.findById(8);
		Assert.assertFalse(serviceResponse.isError());
		Assert.assertEquals("service.utilisateur.findById.msg.success", serviceResponse.getMessage());

		final UtilisateurDto mikaBefore = (UtilisateurDto) serviceResponse.getDataResult();
		Assert.assertNotNull(mikaBefore);
		Assert.assertTrue(mikaBefore.isActif());

		final ServiceResponse serviceResponse2 = serviceUtilisateur.changeEtatUtilisateur(mikaBefore, false);
		Assert.assertFalse(serviceResponse2.isError());
		Assert.assertEquals("service.utilisateur.modification.msg.sucess", serviceResponse2.getMessage());
		
		final ServiceResponse serviceResponse3 = serviceUtilisateur.findById(8);
		Assert.assertFalse(serviceResponse3.isError());
		Assert.assertEquals("service.utilisateur.findById.msg.success", serviceResponse3.getMessage());

		final UtilisateurDto mikaAfter = (UtilisateurDto) serviceResponse3.getDataResult();
		Assert.assertNotNull(mikaAfter);
		Assert.assertEquals(false, mikaAfter.isActif());

	}
}
