<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><spring:message code="title" /></title>
<link href="<spring:theme code='stylesheet'/>" rel="stylesheet" />
<link href="<spring:theme code='font-awesome'/>" rel="stylesheet" />
<link href="<spring:theme code='custom'/>" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<div>
			<center>
				<tiles:insertAttribute name="header" />
			</center>
		</div>
		<div>
			<div>
				<tiles:insertAttribute name="menu" />
			</div>
			<div>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>

	<div>
		<center>
			<tiles:insertAttribute name="footer" />
		</center>
	</div>

	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="/app/static/assets/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="/app/static/assets/js/bootstrap.min.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="/app/static/assets/js/custom.js"></script>

</body>
</html>