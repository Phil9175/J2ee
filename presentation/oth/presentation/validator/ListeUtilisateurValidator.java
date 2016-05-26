package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.ListeUtilisateurDto;

/**
 * Validateur pour l'affichage de la liste des utilisateurs
 * 
 * @author badane
 *
 */
@Component
public class ListeUtilisateurValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ListeUtilisateurDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final ListeUtilisateurDto listeUtilisateurDto = (ListeUtilisateurDto) target;

		if (!listeUtilisateurDto.getChampTri().toString().equals(SortByField.FIELD_LOGIN)
				&& (!listeUtilisateurDto.getChampTri().toString().equals(SortByField.FIELD_NOM))) {
			errors.rejectValue("champTri", "validator.listeUtilisateur.field");

		}

		if (!listeUtilisateurDto.getTypeTri().toString().equals(SortByType.ORDER_ASC)
				&& (!listeUtilisateurDto.getTypeTri().toString().equals(SortByType.ORDER_DESC))) {
			errors.rejectValue("typeTri", "validator.listeUtilisateur.type");

		}

	}
}
