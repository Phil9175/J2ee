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
			width="300px" height="200px" /><br /> <spring:message code="detailProduit.page.reference" />:
		${produitDto.reference} <br /> <spring:message code="detailProduit.page.prix" /> : ${produitDto.prix} <br />
		<spring:message code="detailProduit.page.description" /> : ${produitDto.description} <br />

		<form:form method="GET"
			action="${pageContext.servletContext.contextPath}/panier/addProduit"
			class="form-horizontal" modelAttribute="ajoutProduitPanierDto">

			<input id="referenceProduit" name="referenceProduit" type="hidden"
				value="${produitDto.reference}" />

					Quantite : <form:input path="quantite" />
			<input type="submit" value="<spring:message code="detailProduit.page.boutton" />" class="btn btn-info" />

		</form:form>
	</div>
</div>