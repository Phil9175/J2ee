package oth.presentation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import oth.presentation.dto.produit.bean.ProduitDto;

/**
 * Validateur du formulaire Produit
 * 
 * @author badane
 *
 */
@Component
public class ProduitValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ProduitDto.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final ProduitDto produit = (ProduitDto) target;
		if (produit.getReference() == null || produit.getReference().isEmpty()) {
			errors.rejectValue("reference", "validator.reference.required");
		}
		if (produit.getReference() != null && produit.getReference().length() > 255) {
			errors.rejectValue("reference", "validator.field.toolong");
		}
		if (produit.getDescription() == null || produit.getDescription().isEmpty()) {
			errors.rejectValue("description", "validator.description.required");
		}
		if (produit.getPhoto() == null) {
			errors.rejectValue("photo", "validator.photo.required");
		}
		if (produit.getPrix() != null && produit.getPrix() < 0.01) {
			errors.rejectValue("prix", "validator.prix.positive");
		}
		if (produit.getPrix() == null) {
			errors.rejectValue("prix", "validator.prix.required");
		}
	}
}
