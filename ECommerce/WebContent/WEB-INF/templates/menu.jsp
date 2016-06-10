<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="span6 pull-right" style="text-align: right">
	<spring:url value="/resources/anglais.jpg" var="angl" />
	<spring:url value="/resources/francais.jpg" var="fran" />
	<a href="${urlByFilter}?lang=en"><img src="${angl}"
		style="width: 23px; height: 19px;" border="0"></a> <a
		href="${urlByFilter}?lang=fr"><img src="${fran}"
		style="width: 23px; height: 19px;" border="0"></a>
</div>
<!--${requestScope['javax.servlet.forward.request_uri']}  -->
<hr />

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="${pageContext.servletContext.contextPath}/accueil"
				class="navbar-brand">Vente en ligne</a>
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
	<div class="errorblock">
		<spring:message code="${sessionScope.errorMsg}" />
	</div>
	<c:remove var="errorMsg" />
</c:if>
<c:if
	test="${sessionScope.informationMsg != null and sessionScope.informationMsg != ''}">
	<div class="informationBlock">
		<spring:message code="${sessionScope.informationMsg}" />
	</div>
	<c:remove var="informationMsg" />
</c:if>