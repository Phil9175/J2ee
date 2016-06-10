package oth.metier.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe pour le cryptage des mots de passe.
 * 
 * @author Phil9175
 *
 */
public abstract class CryptPassword {

	/**
	 * Crypte la chaîne passée en paramètre.
	 * 
	 * @param password
	 *            La chaîne à crypter.
	 * @return La chaîne cryptée.
	 */
	public static String crypt(final String password) {
		return (password == null) ? null : getSHASecurePassword(password);
	}

	/**
	 * Hash la chaîne passée en paramètre par SHA-256.
	 * 
	 * @param password
	 *            La chaîne à hash.
	 * @return La chaîne hashée.
	 */
	private static String getSHASecurePassword(final String passwordToHash) {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-256");
			final byte[] bytes = md.digest(passwordToHash.getBytes());
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
