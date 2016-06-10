package test.metier.mapper.utilisateur;

import java.text.ParseException;

import org.hibernate.MappingException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import oth.metier.mapper.utilisateur.MapperUtilisateur;
import oth.metier.util.Utility;
import oth.persistance.bean.utilisateur.ProfilDo;
import oth.persistance.bean.utilisateur.UtilisateurDo;
import oth.presentation.dto.utilisateur.EditInfoPersoByAdminDto;
import oth.presentation.dto.utilisateur.EditInfoPersoDto;
import oth.presentation.dto.utilisateur.InscriptionDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;



/**
 * Classe de test pour le mapperUtilisateur
 * 
 * @author Phil9175
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMapperUtilisateur {

	/**
	 * Cette fonction teste la création d'un utilisateurDto à partir d'un objet
	 * UtilisateurDo
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_01_utilisateurDo_to_utilisateurDto_correct() throws ParseException {
		final UtilisateurDo utilisateurDo = new UtilisateurDo();
		utilisateurDo.setActive(false);
		utilisateurDo.setAdresse("5 rue de l'université");

		utilisateurDo.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		utilisateurDo.setId(1);
		utilisateurDo.setLogin("bast");
		utilisateurDo.setMail("delattre.bastien@gmail.com");
		utilisateurDo.setNom("Delattre");
		utilisateurDo.setPrenom("Bastien");
		utilisateurDo.setProfil(new ProfilDo("bast"));

		final UtilisateurDto utilisateurDto;
		utilisateurDto = MapperUtilisateur.createUtilisateurDto(utilisateurDo);
		Assert.assertEquals(utilisateurDto.getNom(), utilisateurDo.getNom());
		Assert.assertEquals(utilisateurDto.getPrenom(), utilisateurDo.getPrenom());
		Assert.assertEquals(utilisateurDto.getAdresse(), utilisateurDo.getAdresse());
		Assert.assertEquals(utilisateurDto.getNomProfil(), utilisateurDo.getProfil().getNom());
		Assert.assertEquals(utilisateurDto.getMail(), utilisateurDo.getMail());
	}

	/**
	 * Cette fonction teste la création d'un utilisateurDto à partir d'un objet
	 * UtilisateurDo null
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_02_utilisateurDo_to_utilisateurDto_null() throws ParseException {
		UtilisateurDto utilisateurDto = MapperUtilisateur.createUtilisateurDto(null);
		Assert.assertEquals(utilisateurDto, null);
	}

	/**
	 * Cette fonction teste le bon fonctionnement de la fonction
	 * createUtilisateurDo de la classe MapperUtilisateur
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_03_utilisateurDto_to_utilisateurDo_correct() throws ParseException {
		final UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setActif(false);
		utilisateurDto.setAdresse("5 rue de l'université");
		utilisateurDto.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		utilisateurDto.setId(1);
		utilisateurDto.setLogin("bast");
		utilisateurDto.setMail("delattre.bastien@gmail.com");
		utilisateurDto.setNom("Delattre");
		utilisateurDto.setPrenom("Bastien");

		final UtilisateurDo utilisateurDo;
		utilisateurDo = MapperUtilisateur.createUtilisateurDo(utilisateurDto);
		Assert.assertEquals(utilisateurDto.getNom(), utilisateurDo.getNom());
		Assert.assertEquals(utilisateurDto.getPrenom(), utilisateurDo.getPrenom());
		Assert.assertEquals(utilisateurDto.getAdresse(), utilisateurDo.getAdresse());
		Assert.assertEquals(utilisateurDto.getMail(), utilisateurDo.getMail());
		Assert.assertNull(utilisateurDto.getNomProfil());
	}

	/**
	 * La fonction Teste
	 * 
	 * @throws MappingException
	 * @throws ParseException
	 * @throws Exception 
	 */
	@Test
	public void test_07_inscriptionDto_to_utilisateurDo_correct() throws MappingException, ParseException, Exception {
		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setPrenom("bastien");
		inscriptionDto.setAdresse("null");
		inscriptionDto.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		inscriptionDto.setLogin("null");
		inscriptionDto.setMail("delattre.bastien@gmail.com");
		inscriptionDto.setNom(null);
		final UtilisateurDo utilisateurDo = MapperUtilisateur.createUtilisateurDo(inscriptionDto);
		Assert.assertEquals(utilisateurDo.getNom(), inscriptionDto.getNom());
		Assert.assertEquals(utilisateurDo.getPrenom(), inscriptionDto.getPrenom());
		Assert.assertEquals(utilisateurDo.getAdresse(), inscriptionDto.getAdresse());
		Assert.assertEquals(utilisateurDo.getMail(), inscriptionDto.getMail());
		Assert.assertEquals(utilisateurDo.getLogin(), inscriptionDto.getLogin());
		Assert.assertEquals(utilisateurDo.getMotdepasse(), inscriptionDto.getMdp());
	}

	/**
	 * Cette fonction vérifie qu'une exception est bien déclenchée lorsqu'un
	 * utilisateur a une confirmation différente de son mot de passe
	 * 
	 * @throws MappingException
	 * @throws ParseException
	 * @throws Exception 
	 */
	@Test(expected = MappingException.class)
	public void test_09_inscriptiondto_to_utilisateurdo_exception() throws MappingException, ParseException, Exception {

		final InscriptionDto inscriptionDto = new InscriptionDto();
		inscriptionDto.setPrenom("bastien");
		inscriptionDto.setAdresse("5 rue de l'université");
		inscriptionDto.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		inscriptionDto.setLogin("bastDel");
		inscriptionDto.setMail("delattre.bastien@gmail.com");
		inscriptionDto.setNom("Delattre");
		inscriptionDto.setMdp("12");
		inscriptionDto.setConfMotDePasse("21");

		MapperUtilisateur.createUtilisateurDo(inscriptionDto);

	}

	/**
	 * Cette fonction permet de tester la fonction createEditInfoPersoByAdminDto
	 * et vérifie donc que l'utilisateurDo est correctement transformé en
	 * editInfoPersoByAdminDto
	 */
	@Test
	public void test_11_utilisateurdo_to_editinfopersobyadmindto_accept() {
		final UtilisateurDo utilisateurDo = new UtilisateurDo();
		utilisateurDo.setActive(false);
		utilisateurDo.setAdresse("5 rue de l'université");
		utilisateurDo.setDateNaissance(null);
		utilisateurDo.setLogin("bast");
		utilisateurDo.setMail(null);
		utilisateurDo.setNom("Delattre");
		utilisateurDo.setPrenom("Bastien");
		utilisateurDo.setProfil(new ProfilDo(ProfilDo.PROFIL_NAME_USER));

		final EditInfoPersoByAdminDto editInfo;
		editInfo = MapperUtilisateur.createEditInfoPersoByAdminDto(utilisateurDo);
		Assert.assertEquals(editInfo.getNom(), utilisateurDo.getNom());
		Assert.assertEquals(editInfo.getPrenom(), utilisateurDo.getPrenom());
		Assert.assertEquals(editInfo.getMail(), utilisateurDo.getMail());
		Assert.assertEquals(editInfo.getLogin(), utilisateurDo.getLogin());
		Assert.assertEquals(editInfo.getNomProfil(), utilisateurDo.getProfil().getNom());
		Assert.assertEquals(editInfo.getDateNaissance(), utilisateurDo.getDateNaissance());

	}

	/**
	 * Cette fonction permet de transformer un objet EditInfoPersoByAdminDto en
	 * utilisateurDo
	 * 
	 * @throws MappingException
	 * @throws ParseException
	 * @throws Exception 
	 */
	@Test
	public void test_13_editinfopersobyadmindto_to_utilisateurdo_accept() throws MappingException, ParseException, Exception {
		final EditInfoPersoByAdminDto editInfo = new EditInfoPersoByAdminDto();
		final UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setActif(false);
		utilisateurDto.setAdresse("5 rue de l'université");
		utilisateurDto.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		utilisateurDto.setId(1);
		utilisateurDto.setLogin("bast");
		utilisateurDto.setMail("delattre.bastien@gmail.com");
		utilisateurDto.setPrenom("Bastien");
		editInfo.setNouveauMdpConfirmation("1234");
		editInfo.setNouveauMdp("1234");

		final UtilisateurDo utilisateurDo = MapperUtilisateur.createUtilisateurDo(editInfo);

		Assert.assertEquals(editInfo.getNom(), utilisateurDo.getNom());
		Assert.assertEquals(editInfo.getPrenom(), utilisateurDo.getPrenom());
		Assert.assertEquals(editInfo.getMail(), utilisateurDo.getMail());
		Assert.assertEquals(editInfo.getLogin(), utilisateurDo.getLogin());
		Assert.assertEquals(editInfo.getDateNaissance(), utilisateurDo.getDateNaissance());

	}

	/**
	 * Cette fonction permet de tester la fonction createEditInfoPersoByAdminDto
	 * et vérifie son comportement si l'objet en entrée est nul
	 */
	@Test
	public void test_14_utilisateurdo_to_editinfopersobyadmindto_null() {
		final EditInfoPersoByAdminDto editInfo;
		editInfo = MapperUtilisateur.createEditInfoPersoByAdminDto(null);
		Assert.assertEquals(editInfo, null);

	}

	/**
	 * Cette fonction teste le bon fonctionnement de la fonction
	 * createUtilisateurDo de la classe MapperUtilisateur L'appel à la fonction
	 * doit retourner une MappingException car les deux champs de mot de passe
	 * ne sont pas identiques
	 * 
	 * @throws MappingException
	 * @throws ParseException
	 * @throws Exception 
	 */
	@Test(expected = MappingException.class)
	public void test_15_editinfopersobyadmindto_to_utilisateurdo_exception() throws MappingException, ParseException, Exception {
		final EditInfoPersoByAdminDto editInfo = new EditInfoPersoByAdminDto();
		final UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setActif(false);
		utilisateurDto.setAdresse("5 rue de l'université");
		utilisateurDto.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		utilisateurDto.setLogin("bast");
		utilisateurDto.setMail("delattre.bastien@gmail.com");
		utilisateurDto.setPrenom("Bastien");
		editInfo.setNouveauMdpConfirmation("2234");
		editInfo.setNouveauMdp("1234");

		MapperUtilisateur.createUtilisateurDo(editInfo);
	}

	/**
	 * Cette fonction teste la conversion d'un editInfoPersoDto en UtilisateurDo
	 * 
	 * @throws ParseException
	 */
	@Test
	public void test_16_editinfopersodto_to_utilisateurdo_accept() throws ParseException {
		final EditInfoPersoDto editInfo = new EditInfoPersoDto();
		editInfo.setAdresse("5 rue de l'université");
		editInfo.setDateNaissance(Utility.parseStringToDate("27/07/1992"));
		editInfo.setLogin("bast");
		editInfo.setMail("delattre.bastien@gmail.com");
		editInfo.setNomProfil(ProfilDo.PROFIL_NAME_USER);
		editInfo.setNom("Delattre");
		editInfo.setPrenom("Bastien");
		editInfo.setNouveauMdp("1234");
		editInfo.setNouveauMdpConfirmation("1234");

		final UtilisateurDo utilisateurDo = MapperUtilisateur.createUtilisateurDo(editInfo);
		Assert.assertEquals(editInfo.getAdresse(), utilisateurDo.getAdresse());
		Assert.assertEquals(editInfo.getNom(), utilisateurDo.getNom());
		Assert.assertEquals(editInfo.getPrenom(), utilisateurDo.getPrenom());
		Assert.assertEquals(editInfo.getLogin(), utilisateurDo.getLogin());
		Assert.assertEquals(editInfo.getNouveauMdp(), utilisateurDo.getMotdepasse());
	}

	/**
	 * Cette fonction teste la conversion d'un UtilisateurDo en
	 * editInfoPersoDto.
	 */
	@Test
	public void test_18_utilisateurdo_to_editinfopersodto_accept() {
		final UtilisateurDo utilisateurDo = new UtilisateurDo();
		utilisateurDo.setActive(true);
		utilisateurDo.setAdresse("5 rue de l'université");
		utilisateurDo.setMotdepasse("1234");
		utilisateurDo.setNom("Zidane");
		utilisateurDo.setPrenom("Zinedine");
		utilisateurDo.setProfil(new ProfilDo(ProfilDo.PROFIL_NAME_ADMIN));

		final EditInfoPersoDto editInfoPerso = MapperUtilisateur.createEditInfoPersoDto(utilisateurDo);
		Assert.assertEquals(editInfoPerso.getAdresse(), utilisateurDo.getAdresse());
		Assert.assertEquals(editInfoPerso.getNom(), utilisateurDo.getNom());
		Assert.assertEquals(editInfoPerso.getPrenom(), utilisateurDo.getPrenom());
		Assert.assertEquals(editInfoPerso.getLogin(), utilisateurDo.getLogin());
		Assert.assertEquals(editInfoPerso.getAncienMdp(), utilisateurDo.getMotdepasse());

	}

	/**
	 * Cette fonction teste la fonction createEditInfoPersoDto en ayant un objet
	 * null en entrée
	 */
	@Test
	public void test_19_utilisateurdo_to_editinfopersodto_null() {
		final EditInfoPersoDto editInfoPerso = MapperUtilisateur.createEditInfoPersoDto(null);
		Assert.assertNull(editInfoPerso);
	}
}
