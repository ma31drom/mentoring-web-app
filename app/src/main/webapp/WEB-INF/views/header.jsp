<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="adjust-nav">
		<div class="navbar-header">
			<c:url var="home" value="/home"/>
			<a class="navbar-brand" href="${home}"> <img class="logo"
				src="/app/static/assets/img/logo.png" />
			</a>
		</div>
		<sec:authorize access="isAuthenticated()">
				<span class="logout-spn" style="float:none;">Dear <c:out value="${currentUserName}" />! We glad to see you!</span>
				<span class="logout-spn"> <a href="logout"
				style="color: #fff;">LOGOUT</a></span>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<span class="logout-spn"> <a href="login" style="color: #fff;">LOGIN</a></span>
		</sec:authorize>

	</div>
</div>
