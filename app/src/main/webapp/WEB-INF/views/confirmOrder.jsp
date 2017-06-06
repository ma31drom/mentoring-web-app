<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2 style="text-align: center;">
					<spring:message code="welcome" />
				</h2>
				<c:forEach var="message" items="${messages}">
					<div class="alert alert-success lead">
						<spring:message code="${message}" />
					</div>
				</c:forEach>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">"${errorMessage}"</div>
				</c:if>
				<img style="width: 100%;" alt="fligts"
					src="/app/static/assets/img/air05-e.jpg">
				<spring:message code="home.into" />
			</div>

		</div>


		<div class="row">
			<form:form method="POST" modelAttribute="ticketOrderDto"
				class="form-horizontal">
				
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles"><spring:message
							code="user.roles" /></label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true"
							itemValue="id" itemLabel="type" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline" />
						</div>
					</div>
				</div>


				<div class="row">
					<div style="margin-left: 15px;" class="form-actions floatRight">
						<input type="submit" value="<spring:message code="order"/>"
							class="btn btn-primary btn-sm" /> <a
							href="<c:url value='/users' />"><spring:message code="cancel" /></a>
					</div>
				</div>
			</form:form>
		</div>

		<!-- /. ROW  -->
		<hr />

		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<!-- /. PAGE WRAPPER  -->
