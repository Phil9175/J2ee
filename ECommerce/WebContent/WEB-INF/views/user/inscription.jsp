<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="inscription.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<form:form method="POST" modelAttribute="inscriptionDto"
			action="${pageContext.servletContext.contextPath}/inscription" class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.login" /></label>
				<div class="col-sm-10">
					<form:input path="login" />
					<form:errors path="login" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.prenom" /> :</label>
				<div class="col-sm-10">
					<form:input path="prenom" />
					<form:errors path="prenom" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.nom" /> :</label>
				<div class="col-sm-10">
					<form:input path="nom" />
					<form:errors path="nom" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.datedenaissance" /></label>
				<div class="col-sm-10">
					<form:input path="dateNaissance" />
					<form:errors path="dateNaissance" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.adresse" /></label>
				<div class="col-sm-10">
					<form:textarea path="adresse" />
					<form:errors path="adresse" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.mail" /></label>
				<div class="col-sm-10">
					<form:input path="mail" />
					<form:errors path="mail" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.motdepasse" /></label>
				<div class="col-sm-10">
					<form:password path="mdp" />
					<form:errors path="mdp" cssClass="error " />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="inscription.form.confirmermotdepasse" /></label>
				<div class="col-sm-10">
					<form:password path="confMotDePasse" />
					<form:errors path="confMotDePasse" cssClass="error" />
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit"
					value="<spring:message
								code="inscription.form.validate" />"
					class="btn btn-info" />
			</div>
		</form:form>
	</div>
</div>
