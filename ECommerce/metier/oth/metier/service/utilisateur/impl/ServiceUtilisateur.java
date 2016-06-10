package oth.metier.service.utilisateur.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import oth.metier.exception.MappingException;
import oth.metier.mapper.utilisateur.MapperUtilisateur;
import oth.metier.service.AbstractService;
import oth.metier.service.ServiceResponse;
import oth.metier.service.utilisateur.IServiceUtilisateur;
import oth.metier.util.CryptPassword;
import oth.persistance.bean.utilisateur.ProfilDo;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.persistance.dao.utilisateur.IUtilisateurDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.ConnexionDto;
import oth.presentation.dto.utilisateur.EditInfoPersoByAdminDto;
import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.dto.utilisateur.InscriptionDto;
import oth.presentation.dto.utilisateur.ListeUtilisateurDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * Implémentation du service métier utilisateur.
 * 
 * @author badane
 * 
 */
@Service("serviceUtilisateur")
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceUtilisateur extends AbstractService implements IServiceUtilisateur {

	@Autowired
	private IUtilisateurDao utilisateurDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.utilisateur.IServiceUtilisateur#getUtilisateurById(
	 * java.lang.Integer)
	 */
	@Override
	public ServiceResponse findById(final int id) {
		final UtilisateurDo utilisateurDo;
		try {
			utilisateurDo = utilisateurDao.findById(id);

		} catch (final DaoException daoException) {
			// Rollback
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			daoException.printStackTrace();
			return this.buildErrorResponse("service.utilisateur.findById.msg.error");
		}

		final UtilisateurDto utilisateurDto = MapperUtilisateur.createUtilisateurDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.findById.msg.success", utilisateurDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.utilisateur.IServiceUtilisateur#
	 * findByIdToEditInfoPerso( java.lang.Integer)
	 */
	@Override
	public ServiceResponse findByIdToEditInfoPerso(final int idUtilisateur) {
		final UtilisateurDo utilisateurDo;
		try {
			utilisateurDo = utilisateurDao.findById(idUtilisateur);

		} catch (DaoException daoException) {
			// Rollback
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			daoException.printStackTrace();
			return this.buildErrorResponse("service.utilisateur.findById.msg.error");
		}

		final EditInfoPersoDto editInfoPersoDto = MapperUtilisateur.createEditInfoPersoDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.findById.msg.success", editInfoPersoDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.utilisateur.IServiceUtilisateur#
	 * findByIdToEditInfoPersoByAdmin( java.lang.Integer)
	 */
	@Override
	public ServiceResponse findByIdToEditInfoPersoByAdmin(final int idUtilisateur) {

		final UtilisateurDo utilisateurDo;
		try {
			utilisateurDo = utilisateurDao.findById(idUtilisateur);

		} catch (DaoException daoException) {
			// Rollback
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			daoException.printStackTrace();
			return this.buildErrorResponse("service.utilisateur.findById.msg.error");
		}

		final EditInfoPersoByAdminDto editInfoPersoByAdminDto = MapperUtilisateur
				.createEditInfoPersoByAdminDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.findById.msg.success", editInfoPersoByAdminDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.utilisateur.IServiceUtilisateur#connecterUtilisateur(
	 * oth.presentation.dto.utilisateur.ConnexionDto)
	 */
	@Override
	public ServiceResponse connecterUtilisateur(final ConnexionDto connexionDto) {
		final UtilisateurDo utilisateurDo;

		try {
			utilisateurDo = utilisateurDao.findByLoginAndPassword(connexionDto.getLogin(),
					CryptPassword.crypt(connexionDto.getMotDePasse()));
		} catch (final DaoException daoException) {
			// On rollback la transaction
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.utilisateur.connexion.msg.error");
		}
		final UtilisateurDto utilisateurDto = MapperUtilisateur.createUtilisateurDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.connexion.msg.success", utilisateurDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.utilisateur.IServiceUtilisateur#inscrireUtilisateur(
	 * oth.presentation.dto.utilisateur.InscriptionDto)
	 */
	@Override
	public ServiceResponse inscrireUtilisateur(final InscriptionDto inscriptionDto) {

		final UtilisateurDo utilisateurDo;

		try {
			utilisateurDo = MapperUtilisateur.createUtilisateurDo(inscriptionDto);

			// On set le profil
			utilisateurDo.setProfil(utilisateurDao.findProfilByNom(ProfilDo.PROFIL_NAME_USER));

			// On active
			utilisateurDo.setActive(true);

			// On crypte le mdp
			utilisateurDo.setMotdepasse(CryptPassword.crypt(inscriptionDto.getMdp()));

			// On persiste l'utilisateur
			utilisateurDao.create(utilisateurDo);
		} catch (final MappingException mappingException) {

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.utilisateur.inscription.msg.error");
		} catch (final DaoException daoException) {

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.utilisateur.inscription.msg.error");
		}
		final UtilisateurDto utilisateurDto = MapperUtilisateur.createUtilisateurDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.inscription.msg.success", utilisateurDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.utilisateur.IServiceUtilisateur#
	 * updateUtilisateurByAdmin(oth.presentation.dto.utilisateur.
	 * EditInfoPersoByAdminDto)
	 */
	@Override
	public ServiceResponse updateUtilisateurByAdmin(final EditInfoPersoByAdminDto editInfoPersoByAdminDto) {

		UtilisateurDo utilisateurDo = null;
		try {
			utilisateurDo = MapperUtilisateur.createUtilisateurDo(editInfoPersoByAdminDto);

			// On recupere son id
			utilisateurDo.setId(utilisateurDao.findByLogin(editInfoPersoByAdminDto.getLogin()).getId());

			// on lui recup son mot de passe
			utilisateurDo.setActive(utilisateurDao.findByLogin(editInfoPersoByAdminDto.getLogin()).isActive());

			// ON crypte le mdp

			if (editInfoPersoByAdminDto.getNouveauMdp() == null || editInfoPersoByAdminDto.getNouveauMdp().isEmpty()) {
				utilisateurDo
						.setMotdepasse(utilisateurDao.findByLogin(editInfoPersoByAdminDto.getLogin()).getMotdepasse());
			} else {

				// On crypte le mdp
				utilisateurDo.setMotdepasse(CryptPassword.crypt(editInfoPersoByAdminDto.getNouveauMdp()));
			}

			// On set le profil
			ProfilDo profilDo = utilisateurDao.findProfilByNom(editInfoPersoByAdminDto.getNomProfil());
			utilisateurDo.setProfil(profilDo);

			// On persiste l'utilisateur
			utilisateurDao.update(utilisateurDo);
		} catch (final MappingException e) {
			e.printStackTrace();

		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.utilisateur.modification.msg.error");
		}

		final EditInfoPersoByAdminDto utilisateurDto = MapperUtilisateur.createEditInfoPersoByAdminDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.modification.msg.success", utilisateurDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.utilisateur.IServiceUtilisateur#updateUtilisateur(
	 * oth.presentation.dto.utilisateur.EditInfoPersoDto)
	 */
	@Override
	public ServiceResponse updateUtilisateur(final EditInfoPersoDto editInfoPersoDto) {

		final UtilisateurDo utilisateurDo;

		try {
			// On recupere le user à modifier

			utilisateurDo = MapperUtilisateur.createUtilisateurDo(editInfoPersoDto);

			// On recupere son id
			utilisateurDo.setId(utilisateurDao.findByLogin(editInfoPersoDto.getLogin()).getId());
			// on recupere son état
			utilisateurDo.setActive(utilisateurDao.findByLogin(editInfoPersoDto.getLogin()).isActive());
			// On change ses informations
			utilisateurDo.setAdresse(editInfoPersoDto.getAdresse());
			utilisateurDo.setDateNaissance(editInfoPersoDto.getDateNaissance());
			utilisateurDo.setMail(editInfoPersoDto.getMail());
			utilisateurDo.setNom(editInfoPersoDto.getNom());
			utilisateurDo.setPrenom(editInfoPersoDto.getPrenom());

			ProfilDo profilDo = utilisateurDao.findProfilByNom(editInfoPersoDto.getNomProfil());
			utilisateurDo.setProfil(profilDo);

			// ON crypte le mdp

			if (editInfoPersoDto.getNouveauMdp() == null || editInfoPersoDto.getNouveauMdp().isEmpty()) {
				utilisateurDo.setMotdepasse(utilisateurDao.findByLogin(editInfoPersoDto.getLogin()).getMotdepasse());
			} else {

				// On crypte le mdp
				utilisateurDo.setMotdepasse(CryptPassword.crypt(editInfoPersoDto.getNouveauMdp()));
			}

			// On persiste l'utilisateur
			utilisateurDao.update(utilisateurDo);

		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.utilisateur.modification.msg.error");
		}
		final EditInfoPersoDto utilisateurDto = MapperUtilisateur.createEditInfoPersoDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.modification.msg.success", utilisateurDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.utilisateur.IServiceUtilisateur#supprimerUtilisateur(
	 * oth.presentation.dto.utilisateur.bean.UtilisateurDto)
	 */
	@Override
	public boolean supprimerUtilisateur(final UtilisateurDto utilisateurDto) {
		final UtilisateurDo utilisateurDo;

		try {
			utilisateurDo = utilisateurDao.findByLogin(utilisateurDto.getLogin());
			utilisateurDao.remove(utilisateurDo.getId());
			return true;

		} catch (final DaoException daoException) {
			daoException.printStackTrace();
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.utilisateur.IServiceUtilisateur#changeEtatUtilisateur
	 * (oth.presentation.dto.utilisateur.bean.UtilisateurDto, boolean)
	 */
	@Override
	public ServiceResponse changeEtatUtilisateur(final UtilisateurDto utilisateurDto, final boolean etat) {
		final UtilisateurDo utilisateurDo;
		try {
			// On recupere le user à modifier
			utilisateurDo = utilisateurDao.findByLogin(utilisateurDto.getLogin());

			utilisateurDo.setActive(etat);
			// On persiste l'utilisateur
			utilisateurDao.update(utilisateurDo);

		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.utilisateur.modification.msg.error");
		}
		final EditInfoPersoByAdminDto utilisateurDto1 = MapperUtilisateur.createEditInfoPersoByAdminDto(utilisateurDo);
		return this.buildResponse("service.utilisateur.modification.msg.success", utilisateurDto1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefindById
	 * oth.metier.service.utilisateur.IServiceUtilisateur#getListeUtilisateur(
	 * oth.presentation.dto.tri.ETriUtilisateur,
	 * oth.presentation.dto.tri.ETriAlphabetique)
	 */
	@Override
	public ServiceResponse getListeUtilisateur(final SortByField triUtilisateur, final SortByType triAlphabetique) {
		final List<UtilisateurDo> listeUserBean;
		try {
			listeUserBean = utilisateurDao.searchUtilisateur(triUtilisateur, triAlphabetique);
		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return this.buildErrorResponse("service.produit.findByReference.msg.error");
		}

		final ListeUtilisateurDto dtoResultat = MapperUtilisateur.createListeUtilisateurDto(listeUserBean);
		return this.buildResponse("service.produit.findByReference.msg.success", dtoResultat);
	}

}
