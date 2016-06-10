package oth.presentation.dto.tri;

/**
 * Classe pour le type de tri.
 * 
 * @author Phil9175 
 *
 */
public class SortByType {
	/**
	 * Ordre croissant.
	 */
	public static final String ORDER_ASC = "ASC";
	
	/**
	 * Ordre décroissant.
	 */
	public static final String ORDER_DESC = "DESC";

	private final String type;

	/**
	 * Constructeur par défaut.
	 */
	public SortByType() {
		this.type = SortByType.ORDER_ASC;
	}

	/**
	 * Constructeur avec type.
	 * 
	 * @param type
	 *            Le type de tri.
	 */
	public SortByType(final String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.type;
	}
}
