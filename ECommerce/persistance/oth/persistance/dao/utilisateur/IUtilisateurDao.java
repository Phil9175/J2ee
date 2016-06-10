package oth.persistance.dao.utilisateur;

import java.util.List;

import oth.persistance.bean.utilisateur.ProfilDo;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.persistance.dao.IGenericDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;

/**
 * Interface du Dao utilisateur.
 * 
 * @author Phil9175
 *
 */
public interface IUtilisateurDao extends IGenericDao<UtilisateurDo> {
	/**
	 * Cherche un utilisateur par login.
	 * 
	 * @param login
	 *            Le login à chercher.
	 * @return Le DO associé à l'utilisateur, null si pas trouvé.
	 * @throws DaoException
	 *             si erreur lors de la récupération.
	 */
	UtilisateurDo findByLogin(final String login) throws DaoException;

	/**
	 * Cherche un utilisateur par login et password.
	 * 
	 * @param login
	 *            Le login à chercher.
	 * @param password
	 *            Le mot de passe associé (crypté).
	 * @return Le DO associé à l'utilisateur, null si pas trouvé.
	 * @throws DaoException
	 *             si erreur lors de la récupération.
	 */
	UtilisateurDo findByLoginAndPassword(final String login, final String password) throws DaoException;

	/**
	 * Cherche un profil par son nom.
	 * 
	 * @param nom
	 *            Le nom du profil.
	 * @return Le DO profil associé.
	 */
	ProfilDo findProfilByNom(final String nom) throws DaoException;

	/**
	 * Retourne une liste triée d'utilisateurs
	 * 
	 * @param triUtilisateur
	 *            par utilisateur_login ou utilisateur_nom ,utilisateur_prenom
	 * @param triAlphabetique
	 *            croissant ou décroissant
	 * @return la liste de UtilisateurDo
	 */
	List<UtilisateurDo> searchUtilisateur(final SortByField triUtilisateur, final SortByType triAlphabetique)
			throws DaoException;

}
