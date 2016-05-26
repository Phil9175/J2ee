package oth.presentation.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validateur pour les champs plus complexes
 * 
 * @author badane
 *
 */
public abstract class ValidatorPattern {

	private static final String MIN_DATE = "1900-01-01";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Vérifie si un email est valide par rapport à la chaîne EMAIL_PATTERN
	 * 
	 * @param String
	 *            l'email à valider
	 * @return true si le mail est valide, false sinon
	 */
	public static boolean validateEmail(final String hex) {
		final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		final Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	/**
	 * Vérifie si une date est valide.
	 * 
	 * @param Date
	 *            La date de naissance à valider
	 * @return true si la date est valide, false sinon
	 */
	public static boolean validateDate(final Date date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// La date ne naissance ne peut pas être avant 1900
		try {
			final Date datePast = sdf.parse(MIN_DATE);
			if (date != null && date.before(datePast)) {
				return false;
			}
		} catch (Exception e) {
			System.out.print("Erreur inconnue sur validation date");
			return false;
		}
		// La date de naissance ne peut pas être dans le future
		if (date != null && date.after(new Date())) {
			return false;
		}
		return true;
	}

}
