package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.validator.EditInfoPersoValidator;




/**
 * Test de EditInfoPersoValidator
 * 
 * @author Phil9175
 *
 */
public class EditInfoPersoTest {

	/**
	 * Test d'un formulaire vide , cela est autorisé mais ne change rien
	 */
	@Test
	public void test_01_NoChanges() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire valide où l'on change le mot de passe
	 */
	@Test
	public void test_02_ChangePasswordValid() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setNouveauMdp("password");
		editDto.setNouveauMdpConfirmation("password");
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire sans confirmation mot de passe
	 */
	@Test
	public void test_03_NoConfirmationPassword() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setNouveauMdp("password");
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec les champs mot de passe et confirmation
	 * différents.
	 */
	@Test
	public void test_04_DifferentPasswords() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setNouveauMdp("password");
		editDto.setNouveauMdpConfirmation("passwrd");
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire valide où l'on change l'email
	 */
	@Test
	public void test_05_ChangeMailValid() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setMail("abc@hotmail.com");
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec un email invalide
	 */
	@Test
	public void test_06_ChangeMailInvalid() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setMail("email.email");
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire valide où l'on change le nom , le prénom et
	 * l'adresse
	 */
	@Test
	public void test_07_ChangeInfos() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setNom("abc");
		editDto.setPrenom("def");
		editDto.setAdresse("xde");
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire où l'on veut rentrer une adresse trop longue
	 */
	@Test
	public void test_08_ChangeInfosTooLong() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setNom("abc");
		editDto.setPrenom("def");
		editDto.setAdresse(RandomStringUtils.randomAlphabetic(260));
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire valide où l'on change la
	 */
	@Test
	public void test_09_ChangeDateValid() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		editDto.setDateNaissance(new Date());
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire où l'on change la date par une date invalide dans le
	 * passé
	 */
	@Test
	public void test_10_ChangeDatePast() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datePast=sdf.parse("1500-01-01");
			editDto.setDateNaissance(datePast);
		} catch (Exception e) {
			System.out.println(e);
		}
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire où l'on change la date par une date invalide dans le
	 * future
	 */
	@Test
	public void test_11_ChangeDateFuture() {
		final EditInfoPersoValidator editValidator=new EditInfoPersoValidator();
		final EditInfoPersoDto editDto=new EditInfoPersoDto();
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateFuture=sdf.parse("2500-01-01");
			editDto.setDateNaissance(dateFuture);
		} catch (Exception e) {
			System.out.println(e);
		}
		final Errors errors=new BeanPropertyBindingResult(editDto, "editDto");
		editValidator.validate(editDto, errors);
		assertTrue(errors.hasErrors());
	}
}
