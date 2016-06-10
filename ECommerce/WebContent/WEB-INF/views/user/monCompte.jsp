<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="panel panel-info">
	<div class="panel-heading">
		<h4>
			<spring:message code="monCompte.form.titre" />
		</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-6">
				<form:form method="POST" modelAttribute="utilisateurDto"
					action="monCompte" class="form-horizontal">

					<form:errors path="*" cssClass="errorblock" element="div" />

					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.login" /> :</label>
						<div class="col-sm-10">${utilisateurDto.login}</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.prenom" /> :</label>
						<div class="col-sm-10">${utilisateurDto.prenom}</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.nom" /> :</label>
						<div class="col-sm-10">${utilisateurDto.nom}</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.datedenaissance" /> :</label>
						<div class="col-sm-10">${utilisateurDto.dateNaissance}</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.adresse" /> :</label>
						<div class="col-sm-10">${utilisateurDto.adresse}</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.mail" /> :</label>
						<div class="col-sm-10">${utilisateurDto.mail}</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2"><spring:message
								code="monCompte.form.profil" /> :</label>
						<div class="col-sm-10">${utilisateurDto.nomProfil}</div>
					</div>
				</form:form>
			</div>
			<div class="col-xs-6">
				<table>
					<tr>
						<td class="tdtable"><form:form method="GET"
								action="${pageContext.servletContext.contextPath}/modifierUtilisateur">

								<form:errors path="*" cssClass="errorblock" element="div" />
								<div class="col-sm-offset-2 col-sm-10">

									<input type="submit"
										value="<spring:message
								code="monCompte.form.modifier" />"
										class="btn btn-info" />
								</div>
							</form:form></td>
					</tr>
					<tr>
						<td class="tdtable"><form:form method="DELETE"
								action="${pageContext.servletContext.contextPath}/monCompte">

								<form:errors path="*" cssClass="errorblock" element="div" />
								<div class="col-sm-offset-2 col-sm-10">

									<input type="submit"
										value="<spring:message
								code="monCompte.form.supprimer" />"
										class="btn btn-info" />
								</div>
							</form:form></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>