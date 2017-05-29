<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="pageName" value="tiles_template_name"></c:set>

<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<!-- <span class="badge">Included</span> -->

			<li <c:if test="${homePage}">class="active-link"</c:if>><c:url
					var="home" value="/home" /> <a href="${home}"><i
					class="fa fa-desktop "></i> <spring:message code="home" /></a></li>

			<sec:authorize access="hasRole('ADMIN')">
				<li <c:if test="${usersPage}">class="active-link"</c:if>><c:url
						var="users" value="/users" /> <a href="${users}"><i
						class="fa fa-table "></i> <spring:message code="user.management" /></a></li>
			</sec:authorize>


			<li <c:if test="${flightsPage}">class="active-link"</c:if>><a
				href="flights"><i class="fa fa-edit "></i>Flights</a></li>

			<li <c:if test="${ticketsPage}">class="active-link"</c:if>><a
				href="tickets"><i class="fa fa-qrcode "></i>Tickets</a></li>
			<li <c:if test="${contactsPage}">class="active-link"</c:if>><c:url
					var="contacts" value="/contacts" /> <a href="${contacts}"><i
					class="fa fa-bar-chart-o"></i>
				<spring:message code="contacts" /></a></li>
		</ul>
	</div>

</nav>
