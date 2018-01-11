<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
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
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<p>
			<c:if test="${not empty msg}">
				Hello ${msg}
			</c:if>

			<c:if test="${empty msg}">
				Welcome Welcome!
			</c:if>
		<p>
			<a class="btn btn-primary btn-lg" href="register" role="button">Register</a>
		</p>
	</div>
</div>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Update</title>

</head>



<div>
	<c:if test="${empty currentStudent}">

		<form:form id="regForm" modelAttribute="studentId"
			action="update" method="post">
			<form:label path="id">Enter ID of the student to be updated</form:label>
			<form:input path="id" name="id" id="id" />
		</form:form>
	</c:if>
	<c:if test="${not empty currentStudent}">
		<form:form id="regForm" modelAttribute="student"
			action="updateStudent" method="post">

			<table align="center">
				<tr>
					<td><form:label path="id">Id</form:label></td>
					<td><form:input path="id" name="id" id="id" readonly="true"
							value="${currentStudent.id} }" /></td>
				</tr>

				<tr>
					<td><form:label path="name">Name</form:label></td>
					<td><form:input path="name" name="name" id="name"
							readonly="true" value="${currentStudent.name}" /></td>
				</tr>

				<tr>
					<td><form:label path="age">Age</form:label></td>
					<td><form:input path="age" name="age" id="age" /></td>
				</tr>
				<tr>
					<td><form:label path="university">University</form:label></td>
					<td><form:input path="university" name="university"
							id="university" /></td>
				</tr>
				<tr>
					<td><form:label path="age">Course</form:label></td>
					<td><form:input path="course" name="course" id="course" /></td>
				</tr>

				<tr>
					<td></td>
					<td><form:button id="update" name="update">Update</form:button>
					</td>

				</tr>

				<tr></tr>

				<tr>

					<td></td>

					<td><a href="index.jsp">Home</a></td>

				</tr>

			</table>

		</form:form>

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