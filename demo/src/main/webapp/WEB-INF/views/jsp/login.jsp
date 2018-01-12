<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<title>Spring Security Example</title>
</head>
<body>
	<form th:action="@{/login}" method="post">
		<table>
			<tr>
				<td><label> User Name : </label></td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td><label> Password: </label></td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>
		<div>
			<input type="submit" value="Sign In" />
		</div>
	</form>
</body>
</html>