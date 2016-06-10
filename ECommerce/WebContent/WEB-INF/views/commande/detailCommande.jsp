<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="detailCommande.page.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><spring:message
							code="detailCommande.page.enteteTabelau.reference" /></th>
					<th><spring:message
							code="detailCommande.page.enteteTabelau.photo" /></th>
					<th><spring:message
							code="detailCommande.page.enteteTabelau.prixUnitaire" /></th>
					<th><spring:message
							code="detailCommande.page.enteteTabelau.quantite" /></th>
					<th><spring:message
							code="detailCommande.page.enteteTabelau.prixtotal" /></th>
				</tr>
			</thead>
			<c:forEach items="${commandeDto.listCommandeProduit}"
				var="CommandeProduit">
				<tbody>
					<tr>
						<td>${CommandeProduit.referenceProduit}</td>
						<td><img
							src="${pageContext.servletContext.contextPath}/photoProd?reference=${CommandeProduit.referenceProduit}"
							width="200px" height="100px" /></td>
						<td>${CommandeProduit.prixUnitaire}</td>
						<td>${CommandeProduit.quantite}</td>
						<td>${CommandeProduit.quantite*CommandeProduit.prixUnitaire}</td>
					</tr>
				</tbody>
			</c:forEach>

		</table>
		<hr>
		<div>
			<table class="tabl">
				<tr>
					<td class="bleu"><spring:message
							code="detailCommande.page.remise" />:</td>
					<td class="rouge">${commandeDto.remise}
					<td>
				</tr>
				<tr>
					<td class="bleu"><spring:message
							code="detailCommande.page.montanttotal" />:</td>
					<td class="rouge">${commandeDto.prixTotal}</td>
				</tr>
			</table>

		</div>
	</div>
</div>