<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="listeCommande.page.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><spring:message
							code="listeCommande.page.enteteTabelau.commande" /></th>
					<th><spring:message
							code="listeCommande.page.enteteTabelau.quantite" /></th>
					<th><spring:message
							code="listeCommande.page.enteteTabelau.dateCommande" /></th>
					<th><spring:message
							code="listeCommande.page.enteteTabelau.prixTotal" /></th>
					<th><spring:message
							code="listeCommande.page.enteteTabelau.remise" /></th>
				</tr>
			</thead>
			<c:forEach items="${listeCommandeDto.listeCommande}" var="commmande">
				<tbody>
					<tr>
						<td><a href="detailCommande?idCommande=${commmande.id}">Commande
								n° ${commmande.id}</a></td>
						<td>${commmande.quantiteTotale}</td>
						<td>${commmande.date}</td>
						<td>${commmande.prixTotal}</td>
						<td>${commmande.remise}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>