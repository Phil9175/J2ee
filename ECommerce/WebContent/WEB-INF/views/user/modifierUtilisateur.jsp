<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="modifierUtilisateur.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<form:form method="POST" modelAttribute="editInfoPersoDto"
			action="${pageContext.servletContext.contextPath}/modifierUtilisateur" class="form-horizontal">

			<form:errors path="*" cssClass="errorblock" element="div" />


			<div class="form-group">

				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.login" /></label>
				<div class="col-sm-10">
					<form:hidden path="login" />
					<form:input path="login" disabled="true" />
					<form:errors path="login" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.prenom" /> :</label>
				<div class="col-sm-10">
					<form:input path="prenom" />
					<form:errors path="prenom" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.nom" /> :</label>
				<div class="col-sm-10">
					<form:input path="nom" />
					<form:errors path="nom" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.datedenaissance" /> :</label>
				<div class="col-sm-10">
					<form:input path="dateNaissance" />
					<form:errors path="dateNaissance" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.adresse" /> :</label>
				<div class="col-sm-10">
					<form:textarea path="adresse" />
					<form:errors path="adresse" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.mail" /> :</label>
				<div class="col-sm-10">
					<form:input path="mail" />
					<form:errors path="mail" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.ancienMdp" /> :</label>
				<div class="col-sm-10">
					<form:password path="ancienMdp" />
					<form:errors path="ancienMdp" cssClass="error " />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.nouveauMdp" /> :</label>
				<div class="col-sm-10">
					<form:password path="nouveauMdp" />
					<form:errors path="nouveauMdp" cssClass="error " />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.nouveaumdpconfirmation" /> :</label>
				<div class="col-sm-10">
					<form:password path="nouveauMdpConfirmation" />
					<form:errors path="nouveauMdpConfirmation" cssClass="error " />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierUtilisateur.form.profil" /> :</label>
				<div class="col-sm-10">
					<form:hidden path="nomProfil" />
					<form:input path="nomProfil" disabled="true" />
					<form:errors path="nomProfil" cssClass="error" />
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">

				<input type="submit"
					value="<spring:message
								code="modifierUtilisateur.form.submit" />"
					class="btn btn-info" />
			</div>
		</form:form>
	</div>
</div>