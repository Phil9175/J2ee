package oth.persistance.dao.produit.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oth.persistance.bean.produit.ProduitDo;
import oth.persistance.dao.AbstractGenericDao;
import oth.persistance.dao.produit.IProduitDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByType;

/**
 * Dao du produit.
 * 
 * @author Phil9175.
 * 
 */
@Repository("ProduitDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ProduitDao extends AbstractGenericDao<ProduitDo> implements IProduitDao {

	/**
	 * Constructeur par défaut.
	 */
	public ProduitDao() {
		super(ProduitDo.class);
	}

	@Override
	public void update(final ProduitDo produitDo) throws DaoException {
		// Test si la reference a changé
		final ProduitDo currentProduitDo = this.findById(produitDo.getId());

		if (!produitDo.getReference().equals(currentProduitDo.getReference())) {
			throw new DaoException("validator.referenceProduit.update");
		}

		try {
			entityManager.merge(produitDo);
		} catch (final PersistenceException | ConstraintViolationException | IllegalArgumentException exception) {
			exception.printStackTrace();
			throw new DaoException(exception.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.persistance.dao.produit.ICatalogueProduitDao#getProduitByReference(
	 * java.lang.String)
	 */
	@Override
	public ProduitDo findByReference(final String reference) throws DaoException {
		final TypedQuery<ProduitDo> query = entityManager
				.createQuery("Select c from ProduitDo c where c.reference=:ref", ProduitDo.class);
		query.setParameter("ref", reference);

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
	 * oth.persistance.dao.produit.IProduitDao#searchProduitByReference(java.
	 * lang.String, oth.presentation.dto.tri.ETriAlphabetique)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProduitDo> searchProduitWithReference(final String reference, final SortByType typeTri,
			final boolean onlyEnVente) throws DaoException {
		// Check null ref
		final String effectiveReference = (reference == null) ? "" : reference;
		final StringBuilder stringBuilder = new StringBuilder();

		// On construit la query
		stringBuilder.append("select c from ProduitDo c where ");
		if (onlyEnVente) {
			stringBuilder.append("c.enVente is true and ");
		}
		stringBuilder.append("c.reference like :ref order by c.prix ").append(typeTri.toString());

		final TypedQuery<ProduitDo> query = entityManager.createQuery(stringBuilder.toString(), ProduitDo.class);
		query.setParameter("ref", "%" + effectiveReference + "%");
		try {
			return query.getResultList();
		} catch (final NoResultException nre) {
			nre.printStackTrace();
			throw new DaoException(nre.getMessage());
		}
	}
}
