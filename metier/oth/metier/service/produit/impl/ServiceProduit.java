package oth.metier.service.produit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import oth.metier.mapper.produit.MapperProduit;
import oth.metier.service.AbstractService;
import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.persistance.bean.produit.ProduitDo;
import oth.persistance.dao.produit.IProduitDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.produit.GestionProduitDto;
import oth.presentation.dto.produit.ListeProduitDto;
import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.dto.tri.SortByType;

/**
 * Implémentation du service métier produit.
 * 
 * @author badane
 *
 */
@Service("serviceProduit")
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceProduit extends AbstractService implements IServiceProduit {

	@Autowired
	private IProduitDao produitDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.produit.IServiceProduit#ajouterProduit(oth.
	 * presentation.dto.produit.bean.ProduitDto)
	 */
	@Override
	public ServiceResponse ajouterProduit(final ProduitDto produitDto) {
		final ProduitDo produitDo = MapperProduit.createProduitDo(produitDto);

		try {
			produitDao.create(produitDo);
		} catch (final DaoException daoException) {
			// On rollback la transaction
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.ajouterProduit.msg.error");
		}

		// On reconverti l'objet créé en dto
		final ProduitDto dtoResultat = MapperProduit.createProduitDto(produitDo);
		return this.buildResponse("service.produit.ajouterProduit.msg.success", dtoResultat);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.produit.IServiceProduit#modifierProduit(oth.
	 * presentation.dto.produit.bean.ProduitDto)
	 */
	@Override
	public ServiceResponse modifierProduit(final ProduitDto produitDto) {

		// Test si modification reference produit
		final ProduitDo currentProduitDo;
		try {
			currentProduitDo = produitDao.findById(produitDto.getId());
		} catch (final DaoException daoException) {
			daoException.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.modifierProduit.msg.error");
		}
		if (!currentProduitDo.getReference().equals(produitDto.getReference())) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.modifierProduit.msg.error");
		}

		final ProduitDo resultProduitDo = MapperProduit.createProduitDo(produitDto);

		try {
			produitDao.update(resultProduitDo);
		} catch (final DaoException daoException) {
			// On rollback la transaction
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.modifierProduit.msg.error");
		}

		// On reconverti l'objet créé en dto
		final ProduitDto dtoResultat = MapperProduit.createProduitDto(resultProduitDo);
		return this.buildResponse("service.produit.modifierProduit.msg.success", dtoResultat);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.produit.IServiceProduit#supprimerProduit(oth.
	 * presentation.dto.produit.bean.ProduitDto)
	 */
	@Override
	public ServiceResponse supprimerProduit(final int id) {
		try {
			produitDao.remove(id);
		} catch (final DaoException daoException) {
			// On rollback la transaction
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.supprimerProduit.msg.error");
		}

		return this.buildResponse("service.produit.supprimerProduit.msg.success", null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.produit.IServiceProduit#getProduitByReference(java.
	 * lang.String)
	 */
	@Override
	public ServiceResponse findByReference(final String referenceProduit) {

		final ProduitDo produitDo;
		try {
			produitDo = produitDao.findByReference(referenceProduit);
		} catch (final DaoException daoException) {
			// On rollback la transaction
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.findByReference.msg.error");
		}

		// On reconverti l'objet récupéré en dto
		final ProduitDto dtoResultat = MapperProduit.createProduitDto(produitDo);
		return this.buildResponse("service.produit.findByReference.msg.success", dtoResultat);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.produit.IServiceProduit#getListeProduit(oth.
	 * presentation.dto.tri.ETriAlphabetique, java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public ServiceResponse getListeProduit(final SortByType tri, final String reference, final Integer page) {
		// Check reference
		final String effectiveReference = (reference == null) ? "" : reference;

		final List<ProduitDo> listeProduitBean;
		try {
			listeProduitBean = produitDao.searchProduitWithReference(effectiveReference, tri, true);
		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.findByReference.msg.error");
		}

		final List<ProduitDo> subListeProduitBean;
		if (page * 20 > listeProduitBean.size()) {
			// Pas de résultats car index trop grand
			subListeProduitBean = null;
		} else {
			final Integer taille = ((page * 20) + 20 >= listeProduitBean.size()) ? listeProduitBean.size()
					: (page * 20) + 20;
			subListeProduitBean = listeProduitBean.subList(page * 20, taille);
		}

		final ListeProduitDto dtoResultat = MapperProduit.createListeProduitDto(subListeProduitBean);
		dtoResultat.setTri(tri);
		dtoResultat.setSearchByReference(reference);
		dtoResultat.setPage(page);
		return this.buildResponse("service.produit.findByReference.msg.success", dtoResultat);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.produit.IServiceProduit#getListeProduitGestion(java.
	 * lang.String)
	 */
	@Override
	public ServiceResponse getListeProduitGestion(final SortByType tri, final String reference) {
		final List<ProduitDo> listeProduitBean;
		try {
			listeProduitBean = produitDao.searchProduitWithReference(reference, tri, false);
		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.findByReference.msg.error");
		}

		final GestionProduitDto dtoResultat = MapperProduit.createGestionProduitDto(listeProduitBean);
		return this.buildResponse("service.produit.findByReference.msg.success", dtoResultat);
	}

}
