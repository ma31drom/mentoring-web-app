<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>
					<srping:message code="contacts" />
				</h2>
				<c:forEach var="message" items="${messages}">
					<div class="alert alert-success lead">
						<spring:message code="${message}" />
					</div>
				</c:forEach>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">"${errorMessage}"</div>
				</c:if>

				<ul class="nav nav-tabs">
					<li class="active"><a href="#phone" data-toggle="tab"><spring:message
								code="contacts.phone" /></a></li>
					<li class=""><a href="#mail" data-toggle="tab"><spring:message
								code="contacts.compose.email" /></a></li>

				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="phone">
						<h4>
							<srping:message code="phoneus" />
						</h4>
						<br />
						<p>Tel: + 375 29 5739742</p>
						<br />
						<p>
							<spring:message code="contacts.info" />
						</p>
					</div>
					<div class="tab-pane fade" id="mail">
						<h4>
							<srping:message code="mailus" />
						</h4>

						<form:form method="POST" modelAttribute="feedback"
							class="form-horizontal">


							<sec:authorize access="isAuthenticated()">
								<spring:message code="contacts.mail.feedback.auth" />
								<br />
							</sec:authorize>
							<sec:authorize access="!isAuthenticated()">
								<div class="row">
									<div class="form-group col-md-12">
										<label class="col-md-3 control-lable" for="email"><spring:message
												code="contacts.mail.feedback.notauth" /></label>
										<div class="col-md-7">
											<form:input type="text" path="email" id="email"
												class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="email" class="help-inline" />
											</div>
										</div>
									</div>
								</div>
							</sec:authorize>


							<!-- text inp -->
							<div class="row">
								<div class="form-group col-md-12">
									<label class="col-md-3 control-lable" for="message"><spring:message
											code="contacts.mail.feedback.message" /></label>
									<div class="col-md-7">
										<form:textarea type="textarea" path="message" id="message"
											class="form-control input-sm" />
										<div class="has-error">
											<form:errors path="message" class="help-inline" />
										</div>
									</div>
								</div>
							</div>
							<!-- btn with send link -->
							<input type="submit"
								value="<spring:message code="feedback.send"/>"
								class="btn btn-info" />
							<a href="<c:url value='/contacts' />"><spring:message
									code="cancel" /></a>

						</form:form>
					</div>



				</div>
			</div>
			<!-- /. ROW  -->
			<hr />

			<!-- /. ROW  -->
		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->