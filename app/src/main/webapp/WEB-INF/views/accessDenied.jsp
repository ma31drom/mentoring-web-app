<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>AccessDenied</h2>
				<c:forEach var="message" items="${messages}">
					<div class="alert alert-success lead">
						<spring:message code="${message}" />
					</div>
				</c:forEach>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">"${errorMessage}"</div>
				</c:if>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />

		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
