
package oth.persistance.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oth.persistance.exception.DaoException;

/**
 * Classe abstraite d'un dao générique pour le CRUD.
 * 
 * @author badane
 *
 * @param <T>
 *            La classe de l'objet à persister.
 */
@Transactional(propagation = Propagation.MANDATORY)
public abstract class AbstractGenericDao<T> implements IGenericDao<T> {

	@PersistenceContext(unitName = "puEboutique")
	protected EntityManager entityManager;

	private Class<T> templateClass;

	/**
	 * Constructeur du dao générique.
	 * 
	 * @param templateClass
	 *            La classe à persister.
	 */
	protected AbstractGenericDao(final Class<T> templateClass) {
		this.templateClass = templateClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#create(java.lang.Object)
	 */
	@Override
	public void create(final T obj) throws DaoException {
		try {
			entityManager.persist(obj);
		} catch (final PersistenceException | ConstraintViolationException | IllegalArgumentException exception) {
			exception.printStackTrace();
			throw new DaoException(exception.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#remove(int)
	 */
	@Override
	public void remove(final int id) throws DaoException {
		try {
			entityManager.remove(entityManager.getReference(this.templateClass, id));
		} catch (final PersistenceException | ConstraintViolationException | IllegalArgumentException exception) {
			exception.printStackTrace();
			throw new DaoException(exception.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#update(java.lang.Object)
	 */
	@Override
	public void update(final T obj) throws DaoException {
		try {
			entityManager.merge(obj);
		} catch (final PersistenceException | ConstraintViolationException | IllegalArgumentException exception) {
			exception.printStackTrace();
			throw new DaoException(exception.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#findById(int)
	 */
	@Override
	public T findById(final int id) throws DaoException {
		final T obj = entityManager.find(this.templateClass, id);
		if (obj == null) {
			throw new DaoException("dao.message.idNotFound");
		}
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#findAll()
	 */
	@Override
	public List<T> findAll() {
		// Query
		final CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(this.templateClass);
		final Root<T> rootQuery = criteriaQuery.from(this.templateClass);
		criteriaQuery.select(rootQuery);
		final TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
