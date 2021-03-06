package oth.metier.mapper.produit;

import java.util.ArrayList;
import java.util.List;

import oth.persistance.bean.produit.ProduitDo;
import oth.presentation.dto.produit.GestionProduitDto;
import oth.presentation.dto.produit.ListeProduitDto;
import oth.presentation.dto.produit.bean.ProduitDto;

/**
 * Mapper des DTO liés au produit.
 * 
 * @author Phil9175
 *
 */

public abstract class MapperProduit {

	/**
	 * Créé le DTO produot associé au DO produit.
	 * 
	 * @param produitDo
	 *            Le DO produit à mapper.
	 * @return Le DTO produit mappé.
	 */
	public static ProduitDto createProduitDto(final ProduitDo produitDo) {
		// Vérification null
		if (produitDo == null) {
			return null;
		}

		// Mapping
		final ProduitDto produitDto = new ProduitDto();
		produitDto.setId(produitDo.getId());
		produitDto.setReference(produitDo.getReference());
		produitDto.setDescription(produitDo.getDescription());
		produitDto.setEnVente(produitDo.isEnVente());
		produitDto.setPrix(produitDo.getPrix());
		produitDto.setPhoto(produitDo.getPhoto());

		return produitDto;
	}

	/**
	 * Créé le DO produit associé à un DTO produit.
	 * 
	 * @param produitDto
	 *            Le DTO produit à mapper.
	 * @return Le DO produit mappé.
	 */
	public static ProduitDo createProduitDo(final ProduitDto produitDto) {
		if (produitDto == null) {
			return null;
		}
		// Mapping
		final ProduitDo produitDo = new ProduitDo();
		produitDo.setId(produitDto.getId());
		produitDo.setReference(produitDto.getReference());
		produitDo.setDescription(produitDto.getDescription());
		produitDo.setEnVente(produitDto.isEnVente());
		produitDo.setPrix(produitDto.getPrix());
		produitDo.setPhoto(produitDto.getPhoto());

		return produitDo;
	}

	/**
	 * Créé le DTO gestion produit associé à la liste de gestion des DO produit.
	 * 
	 * @param listeProduitBean
	 *            La liste de DO produit à mapper.
	 * @return Le DTO gestion produit mappé.
	 */
	public static GestionProduitDto createGestionProduitDto(final List<ProduitDo> listeProduitBean) {
		if (listeProduitBean == null) {
			return null;
		}

		final GestionProduitDto gestionProd = new GestionProduitDto();
		final List<ProduitDto> listeProduitDto = new ArrayList<ProduitDto>();

		for (ProduitDo prod : listeProduitBean) {
			listeProduitDto.add(createProduitDto(prod));
		}
		gestionProd.setListProduit(listeProduitDto);

		return gestionProd;

	}

	/**
	 * Créé le DTO liste de produit associé à la liste des DO produit.
	 * 
	 * @param listeProduitBean
	 *            La liste de DO produit à mapper.
	 * @return Le DTO liste produit mappé.
	 */
	public static ListeProduitDto createListeProduitDto(final List<ProduitDo> listeProduitBean) {

		if (listeProduitBean == null || listeProduitBean.isEmpty()) {
			return new ListeProduitDto();
		}

		final ListeProduitDto listeProduitDto = new ListeProduitDto();
		final List<ProduitDto> liste = new ArrayList<ProduitDto>();
		for (ProduitDo prod : listeProduitBean) {
			liste.add(createProduitDto(prod));
		}
		listeProduitDto.setListProduit(liste);
		return listeProduitDto;

	}
}
