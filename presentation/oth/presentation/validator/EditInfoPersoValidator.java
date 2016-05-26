package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.utilisateur.EditInfoPersoDto;

/**
 * Validateur du formulaire EditInfoPerso
 * 
 * @author badane
 *
 */
@Component
public class EditInfoPersoValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return EditInfoPersoDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final EditInfoPersoDto editInfoPerso = (EditInfoPersoDto) target;

		if (editInfoPerso.getDateNaissance() != null
				&& !ValidatorPattern.validateDate(editInfoPerso.getDateNaissance())) {
			errors.rejectValue("dateNaissance", "validator.dateNaissance.invalid");
		}

		// Verification du mail
		if (editInfoPerso.getMail() != null && !ValidatorPattern.validateEmail(editInfoPerso.getMail())) {
			errors.rejectValue("mail", "validator.mail.invalid");
		}
		checkPassword(editInfoPerso, errors);
		checkTooLong(editInfoPerso, errors);
	}

	/**
	 * Vérifie les champs mot de passe et confirmation
	 * 
	 * @param InscriptionDto
	 *            le Dto à valider
	 * @param Errors
	 *            les erreurs
	 */
	private void checkPassword(final EditInfoPersoDto editInfoPerso, final Errors errors) {
		// Le champ nouveauMdp est remplie alors que le champ
		// nouveauMdpConfirmation est vide
		if (((editInfoPerso.getNouveauMdp() != null) && (!editInfoPerso.getNouveauMdp().isEmpty()))
				&& (editInfoPerso.getNouveauMdpConfirmation() == null
						|| (editInfoPerso.getNouveauMdpConfirmation().isEmpty()))) {
			errors.rejectValue("nouveauMdpConfirmation", "validator.confirm.required");
		}

		// Si les 2 champs de mot de passe sont remplis, il faut vérifier qu'ils
		// ont les même valeurs
		if (editInfoPerso.getNouveauMdp() != null && editInfoPerso.getNouveauMdpConfirmation() != null) {

			if (!editInfoPerso.getNouveauMdp().equals(editInfoPerso.getNouveauMdpConfirmation())) {
				errors.rejectValue("nouveauMdp", "validator.password.different");
			}
		}
	}

	/**
	 * Vérifie si les champs ne sont pas trop long
	 * 
	 * @param InscriptionDto
	 *            le Dto à valider
	 * @param Errors
	 *            les erreurs
	 */
	private void checkTooLong(final EditInfoPersoDto editInfoPerso, final Errors errors) {
		if (editInfoPerso.getNom() != null && editInfoPerso.getNom().length() > 255) {
			errors.rejectValue("nom", "validator.field.toolong");
		}

		if (editInfoPerso.getPrenom() != null && editInfoPerso.getPrenom().length() > 255) {
			errors.rejectValue("prenom", "validator.field.toolong");
		}

		if (editInfoPerso.getMail() != null && editInfoPerso.getMail().length() > 255) {
			errors.rejectValue("mail", "validator.field.toolong");
		}

		if (editInfoPerso.getAdresse() != null && editInfoPerso.getAdresse().length() > 255) {
			errors.rejectValue("adresse", "validator.field.toolong");
		}
	}
}
