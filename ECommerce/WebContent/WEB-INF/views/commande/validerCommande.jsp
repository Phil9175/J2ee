<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<spring:message code="panier.page.titre" />
	</div>
	<div class="panel-body">
		<spring:message code="panier.prixTotal" />
		:
		<fmt:formatNumber type="number" minFractionDigits="2"
			maxFractionDigits="2" value="${validerCommandeDto.panier.prixTotal}" />
		€<br />
		<spring:message code="panier.quantiteTotale" />
		: ${validerCommandeDto.panier.quantiteTotale}<br />
		<spring:message code="panier.remise" />
		:
		<fmt:formatNumber type="number" minFractionDigits="2"
			maxFractionDigits="2" value="${validerCommandeDto.panier.remise}" />
		€<br />
	</div>
</div>

<table class="table table-striped">
	<thead>
		<tr>
			<th><spring:message code="panier.enteteTableau.reference" /></th>
			<th><spring:message code="panier.enteteTableau.description" />n</th>
			<th><spring:message code="panier.enteteTableau.quantite" /></th>
			<th><spring:message code="panier.enteteTableau.prixunitaire" /></th>
			<th><spring:message code="panier.enteteTableau.prixTotal" /></th>
			<th><spring:message code="panier.enteteTableau.reference.photo" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${validerCommandeDto.panier.contenu}" var="element">
			<tr>
				<td>${element.produit.reference}</td>
				<td>${element.produit.description}</td>
				<td>${element.quantite}</td>
				<td><fmt:formatNumber type="number" minFractionDigits="2"
						maxFractionDigits="2" value="${element.produit.prix}" /> €</td>
				<td><fmt:formatNumber type="number" minFractionDigits="2"
						maxFractionDigits="2" value="${element.prixTotal}" /> €</td>
				<td><img
					src="${pageContext.servletContext.contextPath}/photoProd?reference=${element.produit.reference}"
					style="width: 80px; height: 50px;"></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<form:form method="POST" modelAttribute="validerCommandeDto"
	action="validerCommande" class="form-horizontal">

	<div class="form-group">
		<label class="control-label col-sm-2"><spring:message
				code="validerCommande.form.adresseFacturation" /></label>
		<div class="col-sm-10">
			<form:input path="adresseFacturation" />
			<form:errors path="adresseFacturation" cssClass="error" />
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-sm-2"><spring:message
				code="validerCommande.form.adresseLivraison" /></label>
		<div class="col-sm-10">
			<form:input path="adresseLivraison" />
			<form:errors path="adresseLivraison" cssClass="error" />
		</div>
	</div>

	<div class="col-sm-offset-2 col-sm-10">
		<input type="submit"
			value="<spring:message code="validerCommande.form.button" />"
			class="btn btn-info" />
	</div>
</form:form>