package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.validator.ProduitValidator;

/**
 * Test de ProductValidator
 * 
 * @author Phil9175
 *
 */
public class ProduitValidatorTest {
	final byte[] placeholder_photo=javax.xml.bind.DatatypeConverter
			.parseHexBinary("aa4fd020da7a6910a2d8e8002333309d");

	/**
	 * Test d'un formulaire correctement rempli
	 */
	@Test
	public void test_01_ProduitWithValidFields() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setDescription("description");
		produitDto.setReference("reference");
		produitDto.setPrix(14.5);
		produitDto.setPhoto(placeholder_photo);
		final Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec une réference nulle
	 */
	@Test
	public void test_02_ProduitWithNullReference() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setDescription("description");
		produitDto.setPrix(14.5);
		produitDto.setPhoto(placeholder_photo);
		final Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec une réference trop longue
	 */
	@Test
	public void test_03_ProduitWithTooLongReference() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setDescription("description");
		produitDto.setPrix(14.5);
		produitDto.setReference(RandomStringUtils.randomAlphabetic(260));
		produitDto.setPhoto(placeholder_photo);
		final Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec une description nulle
	 */
	@Test
	public void test_04_ProduitWithNullDescription() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setReference("reference");
		produitDto.setPrix(14.5);
		produitDto.setPhoto(placeholder_photo);
		final Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec un prix à zero
	 */
	@Test
	public void test_05_ProduitWithZeroPrice() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setDescription("description");
		produitDto.setReference("reference");
		produitDto.setPrix(0.0);
		produitDto.setPhoto(placeholder_photo);
		Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire sans photo
	 */
	@Test
	public void test_06_ProduitWithNullPhoto() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		produitDto.setDescription("description");
		produitDto.setReference("reference");
		produitDto.setPrix(14.5);
		final Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec tous les champs vide
	 */
	@Test
	public void test_07_ProduitWithEmptyFields() {
		final ProduitValidator produitValidator=new ProduitValidator();
		final ProduitDto produitDto=new ProduitDto();
		final Errors errors=new BeanPropertyBindingResult(produitDto, "produitDto");
		produitValidator.validate(produitDto, errors);
		assertTrue(errors.hasErrors());
	}

}
