<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<title>Gradle + Spring MVC</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Student Registration System</a>
		</div>
		<div class="col-md-6">
			<form action="logout" method="post">
				<input type="submit" value="Sign Out" />
			</form>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
					<a class="btn btn-primary" href="register" role="button">Register</a>
			</div>
			<div class="col-md-2">
					<a class="btn btn-primary" href="update" role="button">Update</a>
			</div>
		</div>

	</div>
</div>

<div class="container">
	<c:if test="${not empty students}">
		<div>
			<h3>Registered Students</h3>
		</div>
		<c:forEach items="${students}" var="student">

			<div class="row">
				<div class="col-md-3">
					<p>Name</p>
					<p>
						<a class="btn btn-default" href="#" role="button">${student.name}</a>
					</p>
				</div>
				<div class="col-md-3">
					<p>Age</p>
					<p>
						<a class="btn btn-default" href="#" role="button">${student.age}</a>
					</p>
				</div>
				<div class="col-md-3">
					<p>University</p>
					<p>
						<a class="btn btn-default" href="#" role="button">${student.university}</a>
					</p>
				</div>
				<div class="col-md-3">
					<p>Course</p>
					<p>
						<a class="btn btn-default" href="#" role="button">${student.course}</a>
					</p>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>


<hr>
<footer>
	<p>&copy; Demo</p>
</footer>


<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>