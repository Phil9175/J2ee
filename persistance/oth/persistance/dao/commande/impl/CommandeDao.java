package oth.persistance.dao.commande.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oth.persistance.bean.commande.CommandeDo;
import oth.persistance.dao.AbstractGenericDao;
import oth.persistance.dao.commande.ICommandeDao;
import oth.persistance.exception.DaoException;

/**
 * Dao de la commande.
 * 
 * @author badane.
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CommandeDao extends AbstractGenericDao<CommandeDo> implements ICommandeDao {
	/**
	 * Constructeur par défaut.
	 */
	public CommandeDao() {
		super(CommandeDo.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grpb.persistance.dao.commande.ICommandeDao#findByUtilisateurId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommandeDo> findByUtilisateurId(int idUtilisateur) throws DaoException {
		final TypedQuery<CommandeDo> query = entityManager
				.createQuery("Select c from CommandeDo c where c.utilisateur.id=:idUser", CommandeDo.class);
		query.setParameter("idUser", idUtilisateur);
		try {
			return query.getResultList();
		} catch (final NoResultException exception) {
			exception.printStackTrace();
			throw new DaoException(exception.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#create(java.lang.Object)
	 */
	@Override
	public void create(final CommandeDo commandeDo) throws DaoException {
		// Test une liste de produit vide
		if (commandeDo.getCommandeProduit() != null && commandeDo.getCommandeProduit().size() < 1) {
			throw new DaoException("valiator.command.empty");
		}

		// Test adresse vide ou null
		if (commandeDo.getAdresseFacturation() == null || commandeDo.getAdresseFacturation().isEmpty()) {
			throw new DaoException("validator.facturation.required");
		}
		if (commandeDo.getAdresseLivraison() == null || commandeDo.getAdresseLivraison().isEmpty()) {
			throw new DaoException("validator.livraison.required");

		}

		try {
			entityManager.persist(commandeDo);

		} catch (final PersistenceException pe) {
			pe.printStackTrace();
			throw new DaoException(pe.getMessage());

		} catch (final ConstraintViolationException cve) {
			cve.printStackTrace();
			throw new DaoException(cve.getMessage());

		} catch (final IllegalArgumentException iae) {
			iae.printStackTrace();
			throw new DaoException(iae.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#create(java.lang.Object)
	 */
	@Override
	public void update(final CommandeDo commandeDo) throws DaoException {
		throw new DaoException("validator.command.update");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ili.jpa.dao.IGenericDao#create(java.lang.Object)
	 */
	@Override
	public void remove(final int id) throws DaoException {
		throw new DaoException("validator.command.remove");
	}

}
