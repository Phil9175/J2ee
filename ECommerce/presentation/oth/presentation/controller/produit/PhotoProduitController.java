package oth.presentation.controller.produit;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.dto.produit.bean.ProduitDto;

/**
 * 
 * @author badan
 *
 */
@Controller
@RequestMapping("/photoProd")
public class PhotoProduitController {
	@Autowired
	public IServiceProduit serviceProduit;

	/**
	 * Permet d'aller chercher la photo en base et l'afficher
	 * 
	 * @param reference
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProduit(String reference) throws IOException {
		ServiceResponse serviceResponse = serviceProduit.findByReference(reference);
		ProduitDto produitDto = (ProduitDto) serviceResponse.getDataResult();
		return IOUtils.toByteArray(new ByteArrayInputStream(produitDto.getPhoto()));
	}
}
