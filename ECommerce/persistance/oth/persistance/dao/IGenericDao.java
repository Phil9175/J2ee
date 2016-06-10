package oth.persistance.dao;

import java.util.List;

import oth.persistance.exception.DaoException;

/**
 * Interface générique pour les DAO.
 * 
 * @author Phil9175
 *
 * @param <T>
 *            Le DO associé.
 */
public interface IGenericDao<T> {
	/**
	 * Ajoute un objet en base.
	 * 
	 * @param obj
	 *            L'objet à ajouter.
	 * 
	 * @throws DaoException
	 *             si erreur à la création.
	 */
	void create(final T obj) throws DaoException;

	/**
	 * Supprime un objet en base.
	 * 
	 * @param id
	 *            L'identifiant de l'objet à supprimer.
	 * 
	 * @throws DaoException
	 *             si erreur à la suppression.
	 */
	void remove(final int id) throws DaoException;

	/**
	 * Met à jour l'objet en base.
	 * 
	 * @param obj
	 *            L'objet à mettre à jour.
	 * 
	 * @throws DaoException
	 *             si erreur à la modification.
	 */
	void update(final T obj) throws DaoException;

	/**
	 * Cherche un produit via un identifiant en base.
	 * 
	 * @param id
	 *            L'id de l'objet.
	 * @return L'objet trouvé.
	 * @throws DaoException 
	 */
	T findById(final int id) throws DaoException;

	/**
	 * Récupère tous les objets de la base.
	 * 
	 * @return La liste des objets trouvés.
	 */
	List<T> findAll();
}
