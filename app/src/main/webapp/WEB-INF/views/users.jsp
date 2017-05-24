<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div id="page-wrapper">
	<div id="page-inner">
		<sec:authorize access="hasRole('ADMIN')">

			<div class="panel panel-default">
				<!-- Default panel contents -->
				<c:forEach var="message" items="${messages}">
					<div class="alert alert-success lead">
						<spring:message code="${message}" />
					</div>
				</c:forEach>

				<div class="panel-heading">
					<span class="lead"><spring:message code="user.list" /></span>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Email</th>
							<th>SSO ID</th>
							<th>Balance</th>
							<th width="100"></th>
							<th width="100"></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td><c:out value="${user.firstName}" /></td>
								<td><c:out value="${user.lastName}" /></td>
								<td><c:out value="${user.email}" /></td>
								<td><c:out value="${user.ssoId}" /></td>
								<td><c:out value="${user.account.balance}" /></td>
								<td><a href="<c:url value='/users/edit-user-${user.ssoId}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><c:if test="${user.ssoId ne currentUserName}">
										<a href="<c:url value='/users/delete-user-${user.ssoId}' />"
											class="btn btn-danger custom-width">delete</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="well">
				<a href="<c:url value='/registration' />">Add New User</a>
			</div>
		
		</sec:authorize>
	</div>
</div>