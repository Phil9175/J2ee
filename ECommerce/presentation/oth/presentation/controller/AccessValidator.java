package oth.presentation.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant de vérifier si un rôle à le droit d'afficher une page
 * 
 * @author Phil9175
 * 
 */
public abstract class AccessValidator {

	/*
	 * Cette map à pour clés les pages et pour valeurs 0,1 ou 2 0 correspond à
	 * une invité 1 correspond à un utilisateur normal 2 correspond à un
	 * administrateur
	 */
	static Map<String, Integer> authorizations = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;

		{
			put(AbstractGlobals.PAGE_ACCUEIL, 0);
			put(AbstractGlobals.PAGE_CONNEXION, 0);
			put(AbstractGlobals.PAGE_INSCRIPTION, 0);
			put(AbstractGlobals.PAGE_DETAIL_PRODUIT, 0);
			put(AbstractGlobals.PAGE_DECONNEXION, 1);
			put(AbstractGlobals.PAGE_LISTE_COMMANDE, 1);
			put(AbstractGlobals.PAGE_PANIER, 1);
			put(AbstractGlobals.PAGE_VALIDATION_COMMANDE, 1);
			put(AbstractGlobals.PAGE_DETAIL_COMMANDE, 1);
			put(AbstractGlobals.PAGE_MODIFIER_UTILISATEUR, 1);
			put(AbstractGlobals.PAGE_MON_COMPTE, 1);
			put(AbstractGlobals.PAGE_MODIFIER_UTILISATEUR_ADMIN, 2);
			put(AbstractGlobals.PAGE_MODIFIER_PRODUIT, 2);
			put(AbstractGlobals.PAGE_LISTE_UTILISATEUR, 2);
			put(AbstractGlobals.PAGE_AJOUTER_PRODUIT, 2);
			put(AbstractGlobals.PAGE_GESTION_PRODUIT, 2);

		}
	};

	/**
	 * @return la map des autorisations
	 */
	public static Map<String, Integer> getAuthorizations() {
		return authorizations;
	}

	/**
	 * @param page
	 *            le nom de la page où se trouve l'utilisateur
	 * @param role
	 *            la chaine de caractère de la session associée à l'utilisateur
	 * @return true si l'utilisateur à le droit d'accèder à la page, false sinon
	 */
	public static boolean validateAccess(final String page, final String role) {
		return authorizations.get(page) <= getRoleNumber(role);
	}

	/**
	 * @param role
	 *            la chaine de caractère de la session associé à l'utilisateur
	 * @return la valeur associé au role en question
	 */
	public static int getRoleNumber(final String role) {
		if (role == null) {
			return 0;
		} else if (role.equals("admin")) {
			return 2;
		} else if (role.equals("user")) {
			return 1;
		}

		return 0;
	}

}
