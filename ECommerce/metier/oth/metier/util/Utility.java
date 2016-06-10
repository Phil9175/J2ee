package oth.metier.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de fonction utilitaires.
 * 
 * @author Phil9175
 *
 */
public abstract class Utility {
	private static final String DATE_PATTERN="dd/MM/yyyy";

	/**
	 * Convertit la date en String dans le format dd/MM/yyyy.
	 * 
	 * @param date
	 *            La date à convertir.
	 * @return Le String converti.
	 */
	public static String parseDate(final Date date) {
		final DateFormat dateFormat=new SimpleDateFormat(DATE_PATTERN);
		return dateFormat.format(date.getTime());
	}

	/**
	 * Converti la chaîne en paramètre en date (format attendu dd/MM/yyyy).
	 * 
	 * @param str
	 *            Le String à parser.
	 * @return La date correspondant au String.
	 * @throws ParseException
	 *             si le format de date est incorrect.
	 */
	public static Date parseStringToDate(final String str) throws ParseException {
		final SimpleDateFormat fromUser=new SimpleDateFormat(DATE_PATTERN);
		return fromUser.parse(str);
	}
}
