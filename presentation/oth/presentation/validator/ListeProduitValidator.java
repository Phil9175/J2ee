package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import oth.presentation.dto.produit.ListeProduitDto;
import oth.presentation.dto.tri.SortByType;

/**
 * Validateur pour l'affichage de la liste des produits
 * 
 * @author badane
 *
 */
@Component
public class ListeProduitValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ListeProduitDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final ListeProduitDto listeProduitDto = (ListeProduitDto) target;
		
		if (!listeProduitDto.getTri().toString().equals(SortByType.ORDER_ASC)
				&& (!listeProduitDto.getTri().toString().equals(SortByType.ORDER_DESC))) {
			errors.rejectValue("typeTri", "validator.listeProduit.type");

		}
		// Le numéro de page ne peut jamais être nul ou négatif
		// Le nombre de page est limité par la taille de la liste
		if (listeProduitDto.getPage() == null) {
			errors.rejectValue("page", "validator.listeProduit.page");
		} else if (listeProduitDto.getListProduit() != null) {
			if (listeProduitDto.getPage() < 1
					|| (listeProduitDto.getPage() - 1) * 20 > listeProduitDto.getListProduit().size()) {
				errors.rejectValue("page", "validator.listeProduit.page");

			}
		}

	}
	
	}
