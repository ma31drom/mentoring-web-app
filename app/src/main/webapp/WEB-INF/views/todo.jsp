<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>IN PROGRESS FOR NOW :)</h2>
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
<!-- /. PAGE WRAPPER  -->
