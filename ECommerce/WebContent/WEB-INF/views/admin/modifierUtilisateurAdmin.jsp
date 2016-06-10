<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="modifierUtilisateurAdmin.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<form:form method="POST" modelAttribute="editInfoPersoByAdminDto"
			action="modifierUtilisateurAdmin" class="form-horizontal">

			<form:errors path="*" cssClass="errorblock" element="div" />


			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.login" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="login" />
					<form:input path="login" disabled="true" />
					<form:errors path="login" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.prenom" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="prenom" />
					<form:input path="prenom" disabled="true" />
					<form:errors path="prenom" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.nom" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="nom" />
					<form:input path="nom" disabled="true" />
					<form:errors path="nom" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.datedenaissance" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="dateNaissance" />
					<form:input path="dateNaissance" disabled="true" />
					<form:errors path="dateNaissance" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.adresse" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="adresse" />
					<form:textarea path="adresse" disabled="true" />
					<form:errors path="adresse" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.mail" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="mail" />
					<form:input path="mail" disabled="true" />
					<form:errors path="mail" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.motdepasse" /> :</label>
				<div class="col-sm-10">
					<form:password path="nouveauMdp" />
					<form:errors path="nouveauMdp" cssClass="error " />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateurAdmin.form.confirmermotdepasse" /> :</label>
				<div class="col-sm-10">
					<form:password path="nouveauMdpConfirmation" />
					<form:errors path="nouveauMdpConfirmation" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.profil" /> :</label>
				<div class="col-sm-10">

					<form:select path="nomProfil">
						<form:option value="user" label="Utilisateur" />
						<form:option value="admin" label="Administrateur" />
					</form:select>
					<form:errors path="nomProfil" cssClass="error" />
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">

				<input type="submit"
					value="<spring:message
                                code="modifierUtilisateurAdmin.form.submit" />"
					class="btn btn-info" />
			</div>
		</form:form>
	</div>
</div>