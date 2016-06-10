package oth.persistance.bean.utilisateur;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DO associé à la table profil.
 * 
 * @author Phil9175
 *
 */
@Entity(name = "ProfilDo")
@Table(name = "profil", uniqueConstraints = @UniqueConstraint(columnNames = { "profil_nom" }))
public class ProfilDo implements Serializable {

	private static final long serialVersionUID = 1356009920143949006L;

	/**
	 * Nom du profil utilisateur.
	 */
	public static final String PROFIL_NAME_USER = "user";
	/**
	 * Nom du profil administrateur.
	 */
	public static final String PROFIL_NAME_ADMIN = "admin";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profil_id")
	private Integer id;

	@NotNull
	@Size(min = 0, max = 255)
	@Column(name = "profil_nom")
	private String nom;

	/**
	 * Constructeur par défaut
	 */
	public ProfilDo() {
		super();
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public ProfilDo(final String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

}
