package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import oth.presentation.dto.utilisateur.ConnexionDto;
import oth.presentation.validator.ConnexionValidator;



/**
 * Test de ConnexionValidator
 * 
 * @author badane
 *
 */
public class ConnexionValidatorTest {

	/**
	 * Test d'un formulaire correctement rempli
	 */
	@Test
	public void test_01_ConnexionWithValidFields() {
		final ConnexionValidator connexionValidator=new ConnexionValidator();
		final ConnexionDto connexionDto=new ConnexionDto();
		connexionDto.setLogin("test");
		connexionDto.setMotDePasse("mdp");
		final Errors errors=new BeanPropertyBindingResult(connexionDto, "connexionDto");
		connexionValidator.validate(connexionDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire sans login
	 */
	@Test
	public void test_02_ConnexionWithNullLogin() {
		final ConnexionValidator connexionValidator=new ConnexionValidator();
		final ConnexionDto connexionDto=new ConnexionDto();
		connexionDto.setMotDePasse("mdp");
		final Errors errors=new BeanPropertyBindingResult(connexionDto, "connexionDto");
		connexionValidator.validate(connexionDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire sans mot de passe
	 */
	@Test
	public void test_03_ConnexionWithNullPassword() {
		final ConnexionValidator connexionValidator=new ConnexionValidator();
		final ConnexionDto connexionDto=new ConnexionDto();
		connexionDto.setLogin("test");
		final Errors errors=new BeanPropertyBindingResult(connexionDto, "connexionDto");
		connexionValidator.validate(connexionDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec tous les champs vides
	 */
	@Test
	public void test_04_ConnexionWithEmptyFields() {
		final ConnexionValidator connexionValidator=new ConnexionValidator();
		final ConnexionDto connexionDto=new ConnexionDto();
		final Errors errors=new BeanPropertyBindingResult(connexionDto, "connexionDto");
		connexionValidator.validate(connexionDto, errors);
		assertTrue(errors.hasErrors());
	}

}
