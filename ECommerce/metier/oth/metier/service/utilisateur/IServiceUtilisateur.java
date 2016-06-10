package oth.metier.service.utilisateur;

import oth.metier.service.ServiceResponse;
import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.ConnexionDto;
import oth.presentation.dto.utilisateur.EditInfoPersoByAdminDto;
import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.dto.utilisateur.InscriptionDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * Interface du service métier utilisateur.
 * 
 * @author badane
 *
 */
public interface IServiceUtilisateur {
	/**
	 * Récupère un utilisateur par id.
	 * 
	 * @param id
	 *            L'id de l'utilisateur à récupérer.
	 * @return La réponse associée.
	 */
	ServiceResponse findById(final int id);

	/**
	 * Récupère un EditInfoPersoByAdminDto par id utilisateur.
	 * 
	 * @param idUtilisateur
	 *            L'id de l'utilisateur.
	 * @return Le DTO editInfoPersoByAdmin associé.
	 */

	ServiceResponse findByIdToEditInfoPersoByAdmin(final int idUtilisateur);

	/**
	 * Récupère un EditInforPersoDto par id utilisateur.
	 * 
	 * @param idUtilisateur
	 *            L'id de l'utilisateur.
	 * @return Le DTO editInfoPerso associé.
	 */
	ServiceResponse findByIdToEditInfoPerso(final int idUtilisateur);

	/**
	 * Valide une connexion d'utilisateur à l'application.
	 * 
	 * @param connexionDto
	 *            Le DTO de connexion à analyser.
	 * @return La réponse associée.
	 */
	ServiceResponse connecterUtilisateur(final ConnexionDto connexionDto);

	/**
	 * Valide une inscription d'utilisateur.
	 * 
	 * @param inscriptionDto
	 *            Le DTO d'inscription à analyser.
	 * @return La réponse associée.
	 */
	ServiceResponse inscrireUtilisateur(final InscriptionDto inscriptionDto);

	/**
	 * Essaye de mettre à jour l'utilisateur (modifié par l'administrateur).
	 * 
	 * @param editInfoPersoByAdminDto
	 *            Le DTO de la modification.
	 * @return La réponse associée.
	 */
	ServiceResponse updateUtilisateurByAdmin(final EditInfoPersoByAdminDto editInfoPersoByAdminDto);

	/**
	 * Essaye de mettre à jour l'utilisateur.
	 * 
	 * @param editInfoPersoAdminDto
	 *            Le DTO de la modification.
	 * @return La réponse associée.
	 */
	ServiceResponse updateUtilisateur(final EditInfoPersoDto editInfoPersoAdminDto);

	/**
	 * Essaye de supprimer l'utilisateur.
	 * 
	 * @param utilisateurDto
	 *            Le DTO de l'utilisateur à supprimer.
	 * @return true si suppression réussie, false sinon.
	 */
	boolean supprimerUtilisateur(final UtilisateurDto utilisateurDto);

	/**
	 * Essaye de changer l'état (actif / désactivé) d'un utilisateur.
	 * 
	 * @param utilisateurDto
	 *            Le DTO de l'utilisateur à modifier.
	 * @param etat
	 *            L'état à appliquer (true=activer / false=désactiver).
	 * @return La réponse associée.
	 */
	ServiceResponse changeEtatUtilisateur(final UtilisateurDto utilisateurDto, final boolean etat);

	/**
	 * Récupère la liste des utilisateurs.
	 * 
	 * @param champTri
	 *            Le champ sur lequel trier.
	 * @param typeTri
	 *            Le type de tri à appliquer (croissant, décroissant).
	 * @return La réponse associée.
	 */
	ServiceResponse getListeUtilisateur(final SortByField champTri, final SortByType typeTri);
}
