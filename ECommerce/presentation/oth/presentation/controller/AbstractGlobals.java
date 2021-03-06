package oth.presentation.controller;

/**
 * Contient l'ensemble des noms d'attributs stockés en session, noms de messages
 * (erreur / information) dans le model et des noms des pages tiles.
 * 
 * @author Phil9175
 *
 */
public abstract class AbstractGlobals {
	/**
	 * Nom de la variable de rôle en session.
	 */
	public static final String SESSION_ROLE_NAME = "role";

	/**
	 * Nom de la variable de nom en session.
	 */
	public static final String SESSION_USER_NAME = "user";

	/**
	 * Nom de la variable du panier en session.
	 */
	public static final String SESSION_PANIER_NAME = "panier";

	/**
	 * Nom de la varialbe de message d'erreur.
	 */
	public static final String GLOBAL_ERROR_MSG = "errorMsg";

	/**
	 * Nom de la variable de message d'information.
	 */
	public static final String GLOBAL_INFORMATION_MSG = "informationMsg";

	/**
	 * Nom de la page d'accueil.
	 */
	public static final String PAGE_ACCUEIL = "accueil";

	/**
	 * Nom de la page de détail de commande.
	 */
	public static final String PAGE_DETAIL_COMMANDE = "detailCommande";

	/**
	 * Nom de la page de listing des commandes.
	 */
	public static final String PAGE_LISTE_COMMANDE = "listeCommande";

	/**
	 * Nom de la page de validation des commandes.
	 */
	public static final String PAGE_VALIDATION_COMMANDE = "validerCommande";

	/**
	 * Nom de la page de consultation du panier.
	 */
	public static final String PAGE_PANIER = "panier";

	/**
	 * Nom de la page d'ajout de produit.
	 */
	public static final String PAGE_AJOUTER_PRODUIT = "ajouterProduit";

	/**
	 * Nom de la page de détail de produit.
	 */
	public static final String PAGE_DETAIL_PRODUIT = "detailProduit";

	/**
	 * Nom de la page de gestion des produits.
	 */
	public static final String PAGE_GESTION_PRODUIT = "gestionProduit";

	/**
	 * Nom de la page de modification de produit.
	 */
	public static final String PAGE_MODIFIER_PRODUIT = "modifierProduit";

	/**
	 * Nom de la page de connexion.
	 */
	public static final String PAGE_CONNEXION = "connexion";

	/**
	 * Nom de la page de déconnexion.
	 */
	public static final String PAGE_DECONNEXION = "deconnexion";

	/**
	 * Nom de la page d'inscription.
	 */
	public static final String PAGE_INSCRIPTION = "inscription";

	/**
	 * Nom de la page de listing des utilisateurs.
	 */
	public static final String PAGE_LISTE_UTILISATEUR = "listeUtilisateur";

	/**
	 * Nom de la page de modification d'utilisateur par l'admin.
	 */
	public static final String PAGE_MODIFIER_UTILISATEUR_ADMIN = "modifierUtilisateurAdmin";

	/**
	 * Nom de la page de modification d'utilisateur.
	 */
	public static final String PAGE_MODIFIER_UTILISATEUR = "modifierUtilisateur";

	/**
	 * Nom de la page de mon compte.
	 */
	public static final String PAGE_MON_COMPTE = "monCompte";

}
