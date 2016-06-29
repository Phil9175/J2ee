<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4><spring:message code="detailProduit.page.titre" /></h4>
	</div>
	<div class="panel-body">
		<img
			src="${pageContext.servletContext.contextPath}/photoProd?reference=${produitDto.reference}"
			width="300px" height="200px" /><br /> <strong><spring:message code="detailProduit.page.reference" />:</strong>
		${produitDto.reference} <br /> <strong><spring:message code="detailProduit.page.prix" /> :</strong> ${produitDto.prix} euros<br />
		<strong><spring:message code="detailProduit.page.description" /> :</strong> ${produitDto.description} <br />

		<form:form method="GET"
			action="${pageContext.servletContext.contextPath}/panier/addProduit"
			class="form-horizontal" modelAttribute="ajoutProduitPanierDto">

			<input id="referenceProduit" name="referenceProduit" type="hidden"
				value="${produitDto.reference}" />
			
			<div class="form-group col-md-2">
				<strong>Quantite :</strong>
				<form:input path="quantite" type="number" min="0" max="30" class="form-control" />
				<input type="submit" value="<spring:message code="detailProduit.page.boutton" />" class="btn btn-info" style="float: left;"/>
			</div>
			
		</form:form>
	</div>
</div>