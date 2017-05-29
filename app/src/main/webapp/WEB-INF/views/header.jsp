<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="adjust-nav">
		<div class="navbar-header">
			<c:url var="home" value="/home" />
			<a class="navbar-brand" href="${home}"> <img class="logo"
				src="/app/static/assets/img/logo.png" />
			</a>
		</div>
		<sec:authorize access="isAuthenticated()">
			<span class="register-spn"><spring:message code="hello" /> <c:out
					value="${currentUserName}" />!</span>
			<span class="logout-spn"> <c:url var="logoutUrl"
					value="logout" /> <a href="${logoutUrl}" style="color: #fff;"><spring:message
						code="logout" /></a>
			</span>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<span class="register-spn"><c:url var="registrationUrl"
					value="registration" /> <a href="${registrationUrl}"
				style="color: #fff;"><spring:message code="registration" /></a> </span>
			<span class="logout-spn"> <c:url var="loginUrl" value="login" />
				<a href="${loginUrl}" style="color: #fff;"><spring:message
						code="login" /></a></span>
		</sec:authorize>
	</div>
	<div style="float: right; margin: 5px;">
		<span style="color: #86b6ff"><spring:message code="theme" /></span> :
		<a href="?theme=bright"><spring:message code="norm.theme" /></a> | <a href="?theme=dark"><spring:message code="notnorm.theme" /></a>
	</div>
</div>
