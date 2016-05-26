package test.presentation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import grpb.presentation.dto.tri.SortByType;
import grpb.presentation.dto.produit.ListeProduitDto;
import grpb.presentation.validator.ListeProduitValidator;
import grpb.presentation.dto.produit.bean.ProduitDto;

/**
 * Test de ListeProduitValidator
 * 
 * @author badane
 *
 */
public class ListeProduitTest {

	/**
	 * Test d'une liste correcte vide
	 */
	@Test
	public void test_01_CorrectEmptyList() {
		final ListeProduitValidator listeProduitValidator = new ListeProduitValidator();
		final ListeProduitDto listeProduitDto = new ListeProduitDto();
		listeProduitDto.setTri(new SortByType(SortByType.ORDER_DESC));
		listeProduitDto.setSearchByReference("ref");
		listeProduitDto.setPage(1);
		final Errors errors = new BeanPropertyBindingResult(listeProduitDto, "listeProduitDto");
		listeProduitValidator.validate(listeProduitDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test correct avec une liste non vide
	 */
	@Test
	public void test_02_CorrectList() {
		final ListeProduitValidator listeProduitValidator = new ListeProduitValidator();
		final ListeProduitDto listeProduitDto = new ListeProduitDto();
		listeProduitDto.setTri(new SortByType(SortByType.ORDER_DESC));
		listeProduitDto.setSearchByReference("ref");
		List<ProduitDto> list = new ArrayList<ProduitDto>();
		list.add(new ProduitDto());
		list.add(new ProduitDto());
		listeProduitDto.setListProduit(list);
		listeProduitDto.setPage(1);
		final Errors errors = new BeanPropertyBindingResult(listeProduitDto, "listeProduitDto");
		listeProduitValidator.validate(listeProduitDto, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Test sans page
	 */
	@Test
	public void test_03_NoPage() {
		final ListeProduitValidator listeProduitValidator = new ListeProduitValidator();
		final ListeProduitDto listeProduitDto = new ListeProduitDto();
		listeProduitDto.setTri(new SortByType(SortByType.ORDER_DESC));
		listeProduitDto.setSearchByReference("ref");
		List<ProduitDto> list = new ArrayList<ProduitDto>();
		list.add(new ProduitDto());
		list.add(new ProduitDto());
		listeProduitDto.setListProduit(list);
		final Errors errors = new BeanPropertyBindingResult(listeProduitDto, "listeProduitDto");
		listeProduitValidator.validate(listeProduitDto, errors);
		assertTrue(errors.hasErrors());
	}

	/**
	 * Test avec un mauvais numéro de page
	 */
	@Test
	public void test_04_PageNumberTooBigForList() {
		final ListeProduitValidator listeProduitValidator = new ListeProduitValidator();
		final ListeProduitDto listeProduitDto = new ListeProduitDto();
		listeProduitDto.setTri(new SortByType(SortByType.ORDER_DESC));
		listeProduitDto.setSearchByReference("ref");
		List<ProduitDto> list = new ArrayList<ProduitDto>();
		list.add(new ProduitDto());
		list.add(new ProduitDto());
		listeProduitDto.setListProduit(list);
		listeProduitDto.setPage(100);
		final Errors errors = new BeanPropertyBindingResult(listeProduitDto, "listeProduitDto");
		listeProduitValidator.validate(listeProduitDto, errors);
		assertTrue(errors.hasErrors());
	}

}
