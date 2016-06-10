package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import oth.persistance.exception.DaoException;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;



/**
 * Classe de test pour AccessValidator
 * 
 * @author Phil9175
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testing/testingContext.xml" })
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccessTest {

	/**
	 * Test pour vérifier que la map s'est bien chargée
	 */
	@Test
	public void test_01_LoadMap() {
		assertFalse(AccessValidator.getAuthorizations().isEmpty());
	}

	/**
	 * Test pour vérifier les authorisations d'un invité
	 */
	@Test
	public void test_02_testGuest() throws DaoException {
		// Acces à une page publique
		assertTrue(AccessValidator.validateAccess(AbstractGlobals.PAGE_ACCUEIL, null));
		// Non acces à une page utilisateur
		assertFalse(AccessValidator.validateAccess(AbstractGlobals.PAGE_MON_COMPTE, null));
		// Non acces à une page admin
		assertFalse(AccessValidator.validateAccess(AbstractGlobals.PAGE_AJOUTER_PRODUIT, null));
	}

	/**
	 * Test pour vérifier les authorisations d'un utilisateur
	 */
	@Test
	public void test_03_testNormalUser() throws DaoException {
		// Acces à une page publique
		assertTrue(AccessValidator.validateAccess(AbstractGlobals.PAGE_ACCUEIL, "user"));
		// Acces à une page utilisateur
		assertTrue(AccessValidator.validateAccess(AbstractGlobals.PAGE_MON_COMPTE, "user"));
		// Non acces à une page admin
		assertFalse(AccessValidator.validateAccess(AbstractGlobals.PAGE_AJOUTER_PRODUIT, "user"));
	}

	/**
	 * Test pour vérifier les authorisations d'un administrateur
	 */
	@Test
	public void test_04_testAdmin() throws DaoException {
		// Acces à une page publique
		assertTrue(AccessValidator.validateAccess(AbstractGlobals.PAGE_ACCUEIL, "admin"));
		// Acces à une page utilisateur
		assertTrue(AccessValidator.validateAccess(AbstractGlobals.PAGE_MON_COMPTE, "admin"));
		// Acces à une page admin
		assertTrue(AccessValidator.validateAccess(AbstractGlobals.PAGE_AJOUTER_PRODUIT, "admin"));
	}

}

