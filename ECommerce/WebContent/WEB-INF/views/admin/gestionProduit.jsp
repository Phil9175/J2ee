<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="listeProduit.page.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.id" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.reference" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.description" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.enVente" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.prix" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.photo" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.edit" /></th>
					<th><spring:message
							code="gestionProduit.page.enteteTabelau.supprimer" /></th>
				</tr>
			</thead>
			<c:forEach items="${gestionProduitDto}" var="prod">
				<tbody>
					<tr>
						<td>${prod.id}</td>
						<td>${prod.reference}</td>
						<td>${prod.description}</td>
						<td>${prod.enVente}</td>
						<td>${prod.prix}</td>
						<td><img src="${pageContext.servletContext.contextPath}/photoProd?reference=${prod.reference}"
							style="width: 80px; height: 50px;"></td>
						<td><a
							href="${pageContext.servletContext.contextPath}/modifierProduit?referenceProduit=${prod.reference}"><spring:message
									code="gestionProduit.page.enteteTabelau.edit" /></a></td>
						<td><a href="${pageContext.servletContext.contextPath}/supprimerProduit?idProduit=${prod.id}"><spring:message
									code="gestionProduit.page.enteteTabelau.supprimer" /></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>