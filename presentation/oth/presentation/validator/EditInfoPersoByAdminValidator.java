package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.utilisateur.EditInfoPersoByAdminDto;

/**
 * Validateur du formulaire EditInfoPersoByAdmin
 * 
 * @author badane
 * 
 */
@Component
public class EditInfoPersoByAdminValidator implements Validator {
	// FIXME SOA La validation se déclenche pour le changement de password, même
	// si on veut changer que le profil, faite en sorte que la validation du
	// profil soit indépendante du changement password
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return EditInfoPersoByAdminValidator.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final EditInfoPersoByAdminDto editInfoPerso = (EditInfoPersoByAdminDto) target;

		// Si l'on n'a pas spécifié de mot de passe, on n'a rien à vérifier
		if (editInfoPerso.getNouveauMdp() != null && !editInfoPerso.getNouveauMdp().isEmpty()) {

			// Le champ confirmation ne peut pas être vide
			if (editInfoPerso.getNouveauMdpConfirmation() == null
					|| editInfoPerso.getNouveauMdpConfirmation().isEmpty()) {
				errors.rejectValue("nouveauMdpConfirmation", "validator.password.required");
			}

			// Si les 2 champs de mot de passe sont remplis, il faut vérifier
			// qu'ils
			// ont les même valeurs
			if (editInfoPerso.getNouveauMdp() != null && editInfoPerso.getNouveauMdpConfirmation() != null) {
				if (!editInfoPerso.getNouveauMdp().equals(editInfoPerso.getNouveauMdpConfirmation())) {
					errors.rejectValue("nouveauMdp", "validator.password.different");
				}
			}
		}
	}
}
