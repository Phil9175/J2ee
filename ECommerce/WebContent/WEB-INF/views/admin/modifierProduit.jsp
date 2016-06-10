<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>


<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="modifierProduit.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<form:form method="POST" modelAttribute="produitDto"
			action="modifierProduit" enctype="multipart/form-data" class="form-horizontal">

			<form:errors path="*" cssClass="errorblock" element="div" />
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierProduit.form.reference" /> :</label>
				<div class="col-sm-10">
				    <form:hidden path="id" />
				    <form:hidden path="reference" />
					<form:input path="reference" disabled="true" />
					<form:errors path="reference" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierProduit.form.description" /> :</label>
				<div class="col-sm-10">
					<form:textarea path="description" />
					<form:errors path="description" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierProduit.form.photo" /> :</label>
				<div class="col-sm-10">
					<input type="file" name="file" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierProduit.form.ImageAModifier" /> :</label>
				<div class="col-sm-10">
					<img src="photoProd?reference=${produitDto.reference}"
						style="width: 80px; height: 50px;">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierProduit.form.envente" /> :</label>
				<div class="col-sm-10">
					<form:checkbox path="enVente" />
					<form:errors path="enVente" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><spring:message
						code="modifierProduit.form.prix" /> :</label>
				<div class="col-sm-10">
					<form:input path="prix" />
					<form:errors path="prix" cssClass="error" />
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">

				<input type="submit"
					value="<spring:message
								code="modifierProduit.form.submit" />"
					class="btn btn-info" />
			</div>
		</form:form>
	</div>
</div>