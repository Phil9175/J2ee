<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="ajouterProduit.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<form:form method="POST" modelAttribute="produitDto"
			action="ajouterProduit" class="form-horizontal"
			enctype="multipart/form-data">

			<form:errors path="*" cssClass="errorblock" element="div" />
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="ajouterProduit.form.reference" /></label>
				<div class="col-sm-10">
					<form:input path="reference" />
					<form:errors path="reference" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="ajouterProduit.form.description" /></label>
				<div class="col-sm-10">
					<form:textarea path="description" />
					<form:errors path="description" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="ajouterProduit.form.photo" /></label>
				<div class="col-sm-10">
					<input type="file" name="file" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="ajouterProduit.form.envente" /></label>
				<div class="col-sm-10">
					<form:checkbox path="enVente" />
					<form:errors path="enVente" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="ajouterProduit.form.prix" /></label>
				<div class="col-sm-10">
					<form:input path="prix" />
					<form:errors path="prix" cssClass="error" />
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit"
					value="<spring:message
								code="ajouterProduit.form.submit" />"
					class="btn btn-info" />
			</div>
		</form:form>
	</div>
</div>

