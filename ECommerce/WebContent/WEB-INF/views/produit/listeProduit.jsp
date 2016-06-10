<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="listeProduit.info.titre" />
		</h4>
	</div>
	<div class="panel-body">

		<form:form method="POST"
			action="${pageContext.servletContext.contextPath}/accueil"
			class="form-horizontal" modelAttribute="listeProduitDto">

			<spring:message code="listeProduit.filtre.reference" />
			<form:input path="searchByReference" />

			<spring:message code="listeProduit.filtre.tri" />
			<form:select path="tri">
				<form:option value="ASC" label="Croissant" />
				<form:option value="DESC" label="Décroissant" />
			</form:select>

			<spring:message code="listeProduit.filtre.page" />
			<form:input path="page" />
			<form:errors path="page" cssClass="error" />

			<input type="submit"
				value='<spring:message	code="listeProduit.filtre.boutton" />'
				class="btn btn-info" />
		</form:form>

		${fn:length(listeProduitDto.listProduit)}
		<spring:message code="listeProduit.info.resultat" />
		<br />

		<c:forEach items="${listeProduitDto.listProduit}" var="produit">
			<div class="col-md-6">
				<img
					src="${pageContext.servletContext.contextPath}/photoProd?reference=${produit.reference}"
					width="300px" height="200px" /> <br /> <a
					href="${pageContext.servletContext.contextPath}/detailProduit/${produit.reference}">

					<spring:message code="listeProduit.produit.reference" /> :
					${produit.reference}

				</a> <br />


				<spring:message code="listeProduit.produit.prix" />
				:
				<fmt:formatNumber type="number" minFractionDigits="2"
					maxFractionDigits="2" value="${produit.prix}" />
				€<br />

				<form:form method="GET"
					action="${pageContext.servletContext.contextPath}/panier/addProduit"
					class="form-horizontal" modelAttribute="ajoutProduitPanierDto">

					<input id="referenceProduit" name="referenceProduit" type="hidden"
						value="${produit.reference}" />

					<spring:message code="listeProduit.panier.quantite" /> : 
					<form:input	path="quantite" />
					<input type="submit"
						value='<spring:message	code="listeProduit.panier.boutton" />'
						class="btn btn-info" />

				</form:form>
			</div>
		</c:forEach>
	</div>
</div>
