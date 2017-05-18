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

			<li><a href="home"><i class="fa fa-desktop "></i> <spring:message
						code="home" /></a></li>

			<sec:authorize access="hasRole('ADMIN')">
			<li><a href="users"><i class="fa fa-table "></i><spring:message
						code="user.management" /></a></li>
			</sec:authorize>
			<li class="active-link"><a href="flights"><i
					class="fa fa-edit "></i>Flights<span class="badge">Included</span></a>
			</li>



			<li><a href="#"><i class="fa fa-qrcode "></i>My Link One</a></li>
			<li><a href="#"><i class="fa fa-bar-chart-o"></i>My Link Two</a></li>
		</ul>
	</div>

</nav>
