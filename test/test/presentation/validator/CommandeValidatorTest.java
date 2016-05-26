package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import grpb.presentation.dto.commande.ValiderCommandeDto;
import grpb.presentation.validator.CommandeValidator;

/**
 * Test de CommandeValidator
 * 
 * @author badane
 *
 */
public class CommandeValidatorTest {

	/**
	 * Test d'un formulaire correctement rempli
	 */
	@Test
	public void test_01_CommandeValidatorWithValidFields() {
		final CommandeValidator commandeValidator=new CommandeValidator();
		final ValiderCommandeDto validerDto=new ValiderCommandeDto();
		validerDto.setAdresseFacturation("facturation");
		validerDto.setAdresseLivraison("livraison");
		final Errors errors=new BeanPropertyBindingResult(validerDto, "validerDto");
		commandeValidator.validate(validerDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire sans adresse de livraison
	 */
	@Test
	public void test_02_CommandeValidatorWithNullLivraison() {
		final CommandeValidator commandeValidator=new CommandeValidator();
		final ValiderCommandeDto validerDto=new ValiderCommandeDto();
		validerDto.setAdresseFacturation("facturation");
		final Errors errors=new BeanPropertyBindingResult(validerDto, "validerDto");
		commandeValidator.validate(validerDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire sans adresse de facturation
	 */
	@Test
	public void test_03_CommandeValidatorWithNullFacturation() {
		final CommandeValidator commandeValidator=new CommandeValidator();
		final ValiderCommandeDto validerDto=new ValiderCommandeDto();
		validerDto.setAdresseLivraison("livraison");
		final Errors errors=new BeanPropertyBindingResult(validerDto, "validerDto");
		commandeValidator.validate(validerDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'un formulaire avec tous les champs vide
	 */
	@Test
	public void test_04_CommandeValidatorWithEmptyFields() {
		final CommandeValidator commandeValidator=new CommandeValidator();
		final ValiderCommandeDto validerDto=new ValiderCommandeDto();
		final Errors errors=new BeanPropertyBindingResult(validerDto, "validerDto");
		commandeValidator.validate(validerDto, errors);
		assertTrue(errors.hasErrors());
	}

}
