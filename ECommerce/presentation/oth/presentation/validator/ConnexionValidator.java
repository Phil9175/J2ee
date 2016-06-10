package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.utilisateur.ConnexionDto;

/**
 * Validateur du formulaire Connexion
 * 
 * @author Phil9175
 *
 */
@Component
public class ConnexionValidator implements Validator {

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
		final ConnexionDto connexionDto = (ConnexionDto) target;

		if (connexionDto.getLogin() == null || connexionDto.getLogin().isEmpty()) {
			errors.rejectValue("login", "validator.username.required");
		}

		if (connexionDto.getMotDePasse() == null || connexionDto.getMotDePasse().isEmpty()) {
			errors.rejectValue("motDePasse", "validator.password.required");
		}
	}
}
