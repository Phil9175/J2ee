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

			<form:errors path="*" cssClass="alert alert-danger" element="div" />


			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="connexion.form.login" /></label>
				<div class="col-sm-10">
					<form:input path="login" class="form-control" />
					<form:errors path="login" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="connexion.form.motdepasse" /> :</label>
				<div class="col-sm-10">
					<form:password path="motDePasse" class="form-control" />
					<form:errors path="motDePasse" cssClass="error " />
				</div>
			</div>

			<div class="form-group">
				
				<div class="col-sm-12 col-md-4 col-md-offset-2">
					<input type="submit"
					value="<spring:message
								code="connexion.form.submit" />"
					class="btn btn-primary" />
				</div>
			</div>
		</form:form>
		
		<div class="col-sm-12 col-md-4 col-md-offset-2">
			Pas encore membre ?
			<p>
				<a href="${pageContext.request.contextPath}/inscription">S'inscrire !</a>
			</p>
		</div>
	</div>
</div>
