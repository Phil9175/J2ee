package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import oth.presentation.dto.panier.AjoutProduitPanierDto;
import oth.presentation.dto.utilisateur.ConnexionDto;

/**
 * Validateur du formulaire Connexion
 * 
 * @author Phil9175
 *
 */
@Component
public class PanierValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ConnexionDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final AjoutProduitPanierDto ajoutProduitDto = (AjoutProduitPanierDto) target;
		if (ajoutProduitDto.getQuantite() <= 0) {
			errors.rejectValue("quantite", "validator.quantite.positive");
		}

	}
}
