package oth.metier.service.commande.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import oth.metier.mapper.commande.MapperCommande;
import oth.metier.service.AbstractService;
import oth.metier.service.ServiceResponse;
import oth.metier.service.commande.IServiceCommande;
import oth.persistance.bean.commande.CommandeDo;
import oth.persistance.bean.utilisateur.ProfilDo;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.persistance.dao.commande.ICommandeDao;
import oth.persistance.dao.utilisateur.IUtilisateurDao;
import oth.persistance.exception.DaoException;
import oth.presentation.dto.commande.ListeCommandeDto;
import oth.presentation.dto.commande.ValiderCommandeDto;
import oth.presentation.dto.commande.bean.CommandeDto;

/**
 * Implémentation du service métier commande.
 * 
 * @author Phil9175
 * 
 */
@Service("serviceCommande")
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceCommande extends AbstractService implements IServiceCommande {

	@Autowired
	private ICommandeDao commandeDao;

	@Autowired
	private IUtilisateurDao utilisateurDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see oth.metier.service.commande.IServiceCommande#validerCommande(oth.
	 * presentation.dto.commande.ValiderCommandeDto)
	 */
	@Override
	public ServiceResponse validerCommande(final ValiderCommandeDto validerCommandeDto) {
		CommandeDo commandeDo;
		CommandeDto commandeDto;
		if (validerCommandeDto.getAdresseFacturation() == null || validerCommandeDto.getAdresseLivraison() == null
				|| validerCommandeDto.getPanier() == null) {
			return this.buildErrorResponse("service.commande.valider.msg.error");
		}
		commandeDo = MapperCommande.createCommandeDo(validerCommandeDto);

		try {
			// On set le profil de l'utilisateur
			final ProfilDo profilDo = utilisateurDao
					.findProfilByNom(validerCommandeDto.getUtilisateurDto().getNomProfil());

			UtilisateurDo utilisateurDo = commandeDo.getUtilisateur();
			utilisateurDo.setProfil(profilDo);
			commandeDo.setUtilisateur(utilisateurDo);

			commandeDao.create(commandeDo);
		} catch (DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			daoException.printStackTrace();
			return this.buildErrorResponse("service.commande.valider.msg.error");
		}
		commandeDto = MapperCommande.createCommandeDto(commandeDo);
		return this.buildResponse("service.commande.valider.msg.success", commandeDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.commande.IServiceCommande#getListeCommande(java.lang.
	 * Integer)
	 */
	@Override
	public ServiceResponse getListeCommande(final Integer idUtilisateur) {
		final List<CommandeDo> listeCommandeDo;

		try {
			listeCommandeDo = commandeDao.findByUtilisateurId(idUtilisateur);
		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			daoException.printStackTrace();
			return this.buildErrorResponse("service.commande.getListeCommandeById.msg.error");
		}

		ListeCommandeDto listeCommandeDto = MapperCommande.createListeCommandeDto(listeCommandeDo);

		return this.buildResponse("service.commande.getListeCommandeById.msg.success", listeCommandeDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oth.metier.service.commande.IServiceCommande#getCommandeById(java.lang.
	 * Integer)
	 */
	@Override
	public ServiceResponse getCommandeById(final Integer idCommande) {
		CommandeDo commandeDo;
		try {
			commandeDo = commandeDao.findById(idCommande);

		} catch (final DaoException daoException) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			daoException.printStackTrace();
			return this.buildErrorResponse("service.commande.getCommandeById.msg.error");
		}
		final CommandeDto commandeDto = MapperCommande.createCommandeDto(commandeDo);
		return this.buildResponse("service.commande.getCommandeById.msg.success", commandeDto);

	}

}
