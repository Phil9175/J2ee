package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.ListeUtilisateurDto;
import oth.presentation.validator.ListeUtilisateurValidator;



/**
 * Test de ListeUtilisateurValidator
 * 
 * @author badane
 *
 */
public class ListeUtilisateurTest {

	/**
	 * Test d'une liste correcte
	 */
	@Test
	public void test_01_CorrectList() {
		final ListeUtilisateurValidator listeUtilisateurValidator = new ListeUtilisateurValidator();
		final ListeUtilisateurDto listeUtilisateurDto = new ListeUtilisateurDto();
		listeUtilisateurDto.setTypeTri(new SortByType(SortByType.ORDER_DESC));
		listeUtilisateurDto.setChampTri(new SortByField(SortByField.FIELD_LOGIN));
		final Errors errors = new BeanPropertyBindingResult(listeUtilisateurDto, "listeUtilisateurDto");
		listeUtilisateurValidator.validate(listeUtilisateurDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test d'une liste avec un tri incorrect (field)
	 */
	@Test
	public void test_02_ListWithWrongField() {
		final ListeUtilisateurValidator listeUtilisateurValidator = new ListeUtilisateurValidator();
		final ListeUtilisateurDto listeUtilisateurDto = new ListeUtilisateurDto();
		listeUtilisateurDto.setTypeTri(new SortByType(SortByType.ORDER_DESC));
		listeUtilisateurDto.setChampTri(new SortByField("WRONG"));
		final Errors errors = new BeanPropertyBindingResult(listeUtilisateurDto, "listeUtilisateurDto");
		listeUtilisateurValidator.validate(listeUtilisateurDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test d'une liste avec un tri incorrect (type)
	 */
	@Test
	public void test_02_ListWithWrongType() {
		final ListeUtilisateurValidator listeUtilisateurValidator = new ListeUtilisateurValidator();
		final ListeUtilisateurDto listeUtilisateurDto = new ListeUtilisateurDto();
		listeUtilisateurDto.setTypeTri(new SortByType("WRONG"));
		listeUtilisateurDto.setChampTri(new SortByField(SortByField.FIELD_LOGIN));
		final Errors errors = new BeanPropertyBindingResult(listeUtilisateurDto, "listeUtilisateurDto");
		listeUtilisateurValidator.validate(listeUtilisateurDto, errors);
		assertTrue(errors.hasErrors());
	}

}
