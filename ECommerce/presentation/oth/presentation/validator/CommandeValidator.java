package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.commande.ValiderCommandeDto;

/**
 * Validateur du formulaire ValiderCommande.
 * 
 * @author badane
 *
 */
@Component
public class CommandeValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ValiderCommandeDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final ValiderCommandeDto validerCommande = (ValiderCommandeDto) target;

		if (validerCommande.getAdresseFacturation() == null || validerCommande.getAdresseFacturation().isEmpty()) {
			errors.rejectValue("adresseFacturation", "validator.facturation.required");
		}

		if (validerCommande.getAdresseLivraison() == null || validerCommande.getAdresseLivraison().isEmpty()) {
			errors.rejectValue("adresseLivraison", "validator.livraison.required");
		}
	}
}
