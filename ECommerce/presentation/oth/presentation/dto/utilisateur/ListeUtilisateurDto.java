package oth.presentation.dto.utilisateur;

import java.util.List;

import oth.presentation.dto.tri.SortByField;
import oth.presentation.dto.tri.SortByType;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * DTO de la liste des utilisateurs.
 * 
 * @author Phil9175 
 *
 */
public class ListeUtilisateurDto {
	private List<UtilisateurDto> listUtilisateur;
	private SortByField champTri;
	private SortByType typeTri;

	/**
	 * @return the listUtilisateur
	 */
	public List<UtilisateurDto> getListUtilisateur() {
		return listUtilisateur;
	}

	/**
	 * @param listUtilisateur
	 *            the listUtilisateur to set
	 */
	public void setListUtilisateur(final List<UtilisateurDto> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}

	/**
	 * @return the champTri
	 */
	public SortByField getChampTri() {
		return champTri;
	}

	/**
	 * @param champTri
	 *            the champTri to set
	 */
	public void setChampTri(final SortByField champTri) {
		this.champTri = champTri;
	}

	/**
	 * @return the typeTri
	 */
	public SortByType getTypeTri() {
		return typeTri;
	}

	/**
	 * @param typeTri
	 *            the typeTri to set
	 */
	public void setTypeTri(final SortByType typeTri) {
		this.typeTri = typeTri;
	}

}
