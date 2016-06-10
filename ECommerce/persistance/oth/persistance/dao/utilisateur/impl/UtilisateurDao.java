package oth.persistance.dao.utilisateur.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oth.persistance.bean.utilisateur.ProfilDo;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.persistance.dao.AbstractGenericDao;
import oth.persistance.dao.utilisateur.IUtilisateurDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;

/**
 * Dao pour l'utilisateur.
 * 
 * @author badane
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UtilisateurDao extends AbstractGenericDao<UtilisateurDo> implements IUtilisateurDao {

	@PersistenceContext(unitName = "puEboutique")
	private EntityManager entityManager;

	/**
	 * Constructeur par défaut.
	 */
	public UtilisateurDao() {
		super(UtilisateurDo.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#create(java.lang.Object)
	 */
	@Override
	public void update(final UtilisateurDo utilisateurDo) throws DaoException {
		// Verifie que l'on ne change pas le login
		final UtilisateurDo currentUtilisateurDo = this.findById(utilisateurDo.getId());
		if (!currentUtilisateurDo.getLogin().equals(utilisateurDo.getLogin())) {
			throw new DaoException("validator.login.update");
		}

		// Check mdp
		if (utilisateurDo.getMotdepasse() == null) {
			throw new DaoException("validator.password.required");
		}

		try {
			entityManager.merge(utilisateurDo);
		} catch (final PersistenceException | ConstraintViolationException | IllegalArgumentException exception) {
			exception.printStackTrace();
			throw new DaoException(exception.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.persistance.dao.utilisateur.IUtilisateurDao#getUtilisateurByLogin(
	 * java.lang.String)
	 */
	@Override
	public UtilisateurDo findByLogin(final String login) throws DaoException {
		final TypedQuery<UtilisateurDo> query = entityManager
				.createQuery("Select c from UtilisateurDo c where c.login=:log", UtilisateurDo.class);
		query.setParameter("log", login);

		try {
			return query.getSingleResult();
		} catch (final NoResultException nre) {
			nre.printStackTrace();
			throw new DaoException(nre.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.persistance.dao.utilisateur.IUtilisateurDao#
	 * getUtilisateurByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public UtilisateurDo findByLoginAndPassword(final String login, final String password) throws DaoException {
		final TypedQuery<UtilisateurDo> query = entityManager.createQuery(
				"Select c from UtilisateurDo c where c.login=:log and c.motdepasse=:mdp and c.isActive is true",
				UtilisateurDo.class);
		query.setParameter("log", login);
		query.setParameter("mdp", password);

		try {
			return query.getSingleResult();
		} catch (final NoResultException nre) {
			nre.printStackTrace();
			throw new DaoException(nre.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.persistance.dao.utilisateur.IUtilisateurDao#findProfilByNom(java.
	 * lang.String)
	 */
	@Override
	public ProfilDo findProfilByNom(String nom) throws DaoException {
		final TypedQuery<ProfilDo> query = entityManager.createQuery("Select c from ProfilDo c where c.nom=:profil",
				ProfilDo.class);
		query.setParameter("profil", nom);

		try {
			return query.getSingleResult();
		} catch (final NoResultException nre) {
			nre.printStackTrace();
			throw new DaoException(nre.getMessage());
		}
	}

	// SupressWarning pour le cast de la query qui ne sait pas résoudre
	@SuppressWarnings("unchecked")
	@Override
	public List<UtilisateurDo> searchUtilisateur(final SortByField triUtilisateur, final SortByType triAlphabetique)
			throws DaoException {

		final String queryString = "select c from UtilisateurDo c order by " + triUtilisateur.toString() + " "
				+ triAlphabetique.toString();
		final TypedQuery<UtilisateurDo> query = entityManager.createQuery(queryString, UtilisateurDo.class);
		try {
			return query.getResultList();
		} catch (final NoResultException nre) {
			nre.printStackTrace();
			throw new DaoException(nre.getMessage());
		}
	}
}
