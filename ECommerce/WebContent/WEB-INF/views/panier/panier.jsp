<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<spring:message code="panier.page.titre" />
	</div>
	<div class="panel-body">
		<spring:message code="panier.prixTotal" />
		:
		<fmt:formatNumber type="number" minFractionDigits="2"
			maxFractionDigits="2" value="${panier.prixTotal}" />
		€<br />
		<spring:message code="panier.quantiteTotale" />
		: ${panier.quantiteTotale}<br />
		<spring:message code="panier.remise" />
		:
		<fmt:formatNumber type="number" minFractionDigits="2"
			maxFractionDigits="2" value="${panier.remise}" />
		€<br /> <a
			href="${pageContext.servletContext.contextPath}/panier/viderPanier"><spring:message
				code="panier.vider" /></a> <br /> <a
			href="${pageContext.servletContext.contextPath}/validerCommande"><spring:message
				code="panier.passerCommande" /></a>
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
			<th><spring:message code="panier.enteteTableau.supprimer" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${panier.contenu}" var="element">
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
				<td><a
					href="${pageContext.servletContext.contextPath}/panier/supprimerProduit?referenceProduit=${element.produit.reference}"><spring:message
							code="panier.enteteTableau.supprimer" /></a></td>
			</tr>
		</c:forEach>
	</tbody>

</table>