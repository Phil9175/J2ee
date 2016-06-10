package oth.persistance.dao.commande;

import java.util.List;

import oth.persistance.bean.commande.CommandeDo;
import oth.persistance.dao.IGenericDao;
import oth.persistance.exception.DaoException;

/**
 * Interface du Dao de commande.
 * 
 * @author Phil9175
 *
 */
public interface ICommandeDao extends IGenericDao<CommandeDo> {
	/**
	 * Récupère la liste des commandes pour un utilisateur donné.
	 * 
	 * @param idUtilisateur
	 *            L'identifiant de l'utilisateur.
	 * @return La liste de CommandeDo passés par l'utilisateur.
	 * @throws DaoException 
	 */
	List<CommandeDo> findByUtilisateurId(final int idUtilisateur) throws DaoException;
}
