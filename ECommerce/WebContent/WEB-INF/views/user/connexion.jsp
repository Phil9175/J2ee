<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<style>
.pp {
	color: #ff0000;
}
</style>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="connexion.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<form:form method="POST" modelAttribute="connexionDto"
			action="${pageContext.request.contextPath}/connexion" class="form-horizontal">

			<form:errors path="*" cssClass="errorblock" element="div" />


			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="connexion.form.login" /></label>
				<div class="col-sm-10">
					<form:input path="login" />
					<form:errors path="login" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="connexion.form.motdepasse" /> :</label>
				<div class="col-sm-10">
					<form:password path="motDePasse" />
					<form:errors path="motDePasse" cssClass="error " />
				</div>
			</div>

			<div class="col-sm-offset-2 col-sm-10">

				<input type="submit"
					value="<spring:message
								code="connexion.form.submit" />"
					class="btn btn-info" />
			</div>
		</form:form>
	</div>
	<div>
		<div class="col-sm-offset-2 col-sm-10">
			Pas encore membre ?
			<p>
				<a href="${pageContext.request.contextPath}/inscription">Inscrivez-vous
					maintenant !</a>
			<p>
		</div>
		<div class="col-sm-offset-2 col-sm-10">
			Mot de pass oublié ?
			<p class="pp">Veuillez contacter l'administrateur du site à
				l'adresse suivante admin@vlt.com ?</p>

		</div>
	</div>
</div>
