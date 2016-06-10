<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="listeUtilisateur.page.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.id" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.login" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.nom" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.prenom" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.adresse" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.dateNaissance" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.mail" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.actif" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.nomProfil" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.action" /></th>
					<th><spring:message
							code="listeUtilisateur.page.enteteTabelau.editer" /></th>
				</tr>
			</thead>
			<c:forEach items="${listeUtilisateurDto.listUtilisateur}"
				var="utilisateur">
				<tbody>
					<tr>
						<td>${utilisateur.id}</td>
						<td>${utilisateur.login}</td>
						<td>${utilisateur.nom}</td>
						<td>${utilisateur.prenom}</td>
						<td>${utilisateur.adresse}</td>
						<td>${utilisateur.dateNaissance}</td>
						<td>${utilisateur.mail}</td>
						<td>${utilisateur.actif}</td>
						<td>${utilisateur.nomProfil}</td>
						<td>
						<c:if test="${utilisateur.actif==true}">
							<a href="changeEtatUtilisateur?idUtilisateur=${utilisateur.id}">Désactiver</a>
						</c:if>
						<c:if test="${utilisateur.actif!=true}">
							<a href="changeEtatUtilisateur?idUtilisateur=${utilisateur.id}">Activer</a>
						</c:if>
						</td>
						<td><a
							href="modifierUtilisateurAdmin?idUtilisateur=${utilisateur.id}"><spring:message
									code="listeUtilisateur.page.enteteTabelau.editer" /></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>