<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!--${requestScope['javax.servlet.forward.request_uri']}  -->
<hr />

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="${pageContext.servletContext.contextPath}/accueil"
				class="navbar-brand">ECommerce</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.servletContext.contextPath}/accueil"><spring:message
							code="menu.accueil" /></a></li>
				<c:if test="${sessionScope.role == 'admin'}">
					<li><a
						href="${pageContext.servletContext.contextPath}/gestionProduit"><spring:message
								code="menu.gestionproduits" /></a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}/ajouterProduit"><spring:message
								code="menu.ajouterproduit" /></a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}/listeUtilisateur"><spring:message
								code="menu.listeutilisateurs" /></a></li>
				</c:if>
				<c:if
					test="${sessionScope.role == 'admin' or sessionScope.role == 'user'}">
					<li><a
						href="${pageContext.servletContext.contextPath}/listeCommande"><spring:message
								code="menu.mescommandes" /></a></li>
					<li><a href="${pageContext.servletContext.contextPath}/panier"><spring:message
								code="menu.panier" /></a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}/monCompte"><spring:message
								code="menu.moncompte" /></a></li>
					<li><a
						href="${pageContext.servletContext.contextPath}/deconnexion"><spring:message
								code="menu.deconnexion" /></a></li>
				</c:if>
				<c:if test="${sessionScope.role == null}">
					<li><a
						href="${pageContext.servletContext.contextPath}/connexion"><spring:message
								code="menu.connexion" /></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>

<c:if
	test="${sessionScope.errorMsg != null and sessionScope.errorMsg != ''}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<spring:message code="${sessionScope.errorMsg}" />
	</div>
	<c:remove var="errorMsg" />
</c:if>
<c:if
	test="${sessionScope.informationMsg != null and sessionScope.informationMsg != ''}">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
		<spring:message code="${sessionScope.informationMsg}" />
	</div>
	<c:remove var="informationMsg" />
</c:if>