package oth.presentation.dto.tri;

/**
 * Classe pour le type de tri.
 * 
 * @author Phil9175 
 *
 */
public class SortByField {
	/**
	 * Tri par login.
	 */
	public static final String FIELD_LOGIN = "utilisateur_login";

	/**
	 * Tri par nom.
	 */
	public static final String FIELD_NOM = "utilisateur_nom";

	private final String field;

	/**
	 * Constructeur par défaut.
	 */
	public SortByField() {
		this.field = null;
	}

	/**
	 * Constructeur avec champ.
	 * 
	 * @param type
	 *            Le champ à trier.
	 */
	public SortByField(final String field) {
		this.field = field;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.field;
	}
}
