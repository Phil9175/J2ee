package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.utilisateur.InscriptionDto;

/**
 * Validateur du formulaire Inscription.
 * 
 * @author Phil9175
 *
 */
@Component
public class InscriptionValidator implements Validator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return InscriptionDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */

	@Override
	public void validate(final Object target, final Errors errors) {
		final InscriptionDto inscriptionDto = (InscriptionDto) target;

		if (inscriptionDto.getLogin() == null || inscriptionDto.getLogin().isEmpty()) {
			errors.rejectValue("login", "validator.username.required");
		}

		if (inscriptionDto.getNom() == null || inscriptionDto.getNom().isEmpty()) {
			errors.rejectValue("nom", "validator.nom.required");
		}

		if (inscriptionDto.getPrenom() == null || inscriptionDto.getPrenom().isEmpty()) {
			errors.rejectValue("prenom", "validator.prenom.required");
		}
		if (inscriptionDto.getMail() == null || inscriptionDto.getMail().isEmpty()) {
			errors.rejectValue("mail", "validator.mail.required");
		}

		if (inscriptionDto.getDateNaissance() == null) {
			errors.rejectValue("dateNaissance", "validator.dateNaissance.required");
		}

		if (inscriptionDto.getDateNaissance() != null
				&& !ValidatorPattern.validateDate(inscriptionDto.getDateNaissance())) {
			errors.rejectValue("dateNaissance", "validator.dateNaissance.invalid");
		}
		// Validation du mail
		if (inscriptionDto.getMail() != null && !ValidatorPattern.validateEmail(inscriptionDto.getMail())) {
			errors.rejectValue("mail", "validator.mail.invalid");
		}
		checkPassword(inscriptionDto, errors);
		checkTooLong(inscriptionDto, errors);

	}

	/**
	 * Vérifie les champs mot de passe et confirmation
	 * 
	 * @param InscriptionDto
	 *            le Dto à valider
	 * @param Errors
	 *            les erreurs
	 */
	private void checkPassword(final InscriptionDto inscriptionDto, final Errors errors) {
		if (inscriptionDto.getMdp() == null || inscriptionDto.getMdp().isEmpty()) {
			errors.rejectValue("mdp", "validator.password.required");
		}

		if (inscriptionDto.getConfMotDePasse() == null || inscriptionDto.getConfMotDePasse().isEmpty()) {
			errors.rejectValue("confMotDePasse", "validator.confirm.required");
		}

		// Si le champ mdp et le champ ConfMotDePasse sont remplis, il faut que
		// leur valeur soient les mêmes.

		if (inscriptionDto.getConfMotDePasse() != null & inscriptionDto.getMdp() != null) {
			if (!inscriptionDto.getConfMotDePasse().equals(inscriptionDto.getMdp())) {
				errors.rejectValue("mdp", "validator.password.different");
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
	private void checkTooLong(final InscriptionDto inscriptionDto, final Errors errors) {
		if (inscriptionDto.getPrenom() != null && inscriptionDto.getPrenom().length() > 255) {
			errors.rejectValue("prenom", "validator.field.toolong");
		}

		if (inscriptionDto.getAdresse() == null || inscriptionDto.getAdresse().isEmpty()) {
			errors.rejectValue("adresse", "validator.address.required");
		}

		if (inscriptionDto.getAdresse() != null && inscriptionDto.getAdresse().length() > 255) {
			errors.rejectValue("adresse", "validator.field.toolong");
		}

		if (inscriptionDto.getMail() != null && inscriptionDto.getMail().length() > 255) {
			errors.rejectValue("mail", "validator.field.toolong");
		}

		if (inscriptionDto.getNom() != null && inscriptionDto.getNom().length() > 255) {
			errors.rejectValue("nom", "validator.field.toolong");
		}

		if (inscriptionDto.getLogin() != null && inscriptionDto.getLogin().length() > 255) {
			errors.rejectValue("login", "validator.field.toolong");
		}

	}

}
