package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import grpb.presentation.dto.utilisateur.InscriptionDto;
import grpb.presentation.validator.InscriptionValidator;

/**
 * Test de InscriptionValidator
 * 
 * @author badane
 *		
 */
public class InscriptionValidatorTest {
	
	/**
	 * Test d'un formulaire correctement rempli
	 */
	@Test
	public void test_01_InscriptionWithValidFields() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setLogin("a");
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom("c");
		inscriptionDto.setMail("abc@hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("PASSWORD");
		inscriptionDto.setDateNaissance(new Date());
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertFalse(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec les champs mot de passe et confirmation
	 * différents.
	 */
	@Test
	public void test_02_InscriptionWithDifferentPasswords() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setLogin("a");
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom("c");
		inscriptionDto.setMail("abc@hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("NOTPASSWORD");
		inscriptionDto.setDateNaissance(new Date());
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec un email invalide
	 */
	@Test
	public void test_03_InscriptionWithInvalidMail() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setLogin("a");
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom("c");
		inscriptionDto.setMail("abc_hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("PASSWORD");
		inscriptionDto.setDateNaissance(new Date());
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec une date invalide dans le future
	 */
	@Test
	public void test_04_InscriptionWithFutureDate() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setLogin("a");
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom("c");
		inscriptionDto.setMail("abc@hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("PASSWORD");
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateFuture=sdf.parse("2500-01-01");
			inscriptionDto.setDateNaissance(dateFuture);
		} catch (Exception e) {
			System.out.println(e);
		}
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec une date invalide dans le passé
	 */
	@Test
	public void test_05_InscriptionWithPastDate() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setLogin("a");
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom("c");
		inscriptionDto.setMail("abc@hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("PASSWORD");
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datePast=sdf.parse("1000-01-01");
			inscriptionDto.setDateNaissance(datePast);
		} catch (Exception e) {
			System.out.println(e);
		}
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec un champ vide (login)
	 */
	@Test
	public void test_06_InscriptionWithMissingLoginField() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom("c");
		inscriptionDto.setMail("abc@hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("PASSWORD");
		inscriptionDto.setDateNaissance(new Date());
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec tous les champs vide
	 */
	@Test
	public void test_07_InscriptionWithAllFieldsEmpty() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
	/**
	 * Test d'un formulaire avec une champ trop long (prenom)
	 */
	@Test
	public void test_08_InscriptionTooLong() {
		final InscriptionValidator inscriptionValidator=new InscriptionValidator();
		final InscriptionDto inscriptionDto=new InscriptionDto();
		inscriptionDto.setLogin("a");
		inscriptionDto.setNom("b");
		inscriptionDto.setPrenom(RandomStringUtils.random(260));
		inscriptionDto.setMail("abc@hotmail.com");
		inscriptionDto.setAdresse("adresse");
		inscriptionDto.setMdp("PASSWORD");
		inscriptionDto.setConfMotDePasse("PASSWORD");
		inscriptionDto.setDateNaissance(new Date());
		final Errors errors=new BeanPropertyBindingResult(inscriptionDto, "inscriptionDto");
		inscriptionValidator.validate(inscriptionDto, errors);
		assertTrue(errors.hasErrors());
	}
	
}
