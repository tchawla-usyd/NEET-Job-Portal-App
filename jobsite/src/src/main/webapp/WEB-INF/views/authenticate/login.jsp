<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
		<table align="center">
			<tr>
				<td><form:label path="Email">Email: </form:label></td>
				<td><form:input type="email" path="Email" name="Email" id="Email" /></td>
			</tr>
			<tr>
				<td><label for="Password">Password:</label></td>
				<td><input type="password" name="Password" id="Password"/></td>
			</tr>
			<tr>
				<td></td>
				<td align="left"><form:button id="login" name="login">Login</form:button></td>
			</tr>

			<tr></tr>
<!-- 			<tr> -->
<!-- 				<td></td> -->
<!-- 				<td><a href="home.jsp">Home</a></td> -->
<!-- 			</tr> -->
		</table>
	</form:form>
<!-- 	<table align="center"> -->
<!-- 		<tr> -->
<%-- 			<td style="font-style: italic; color: red;">${message}</td> --%>
<!-- 		</tr> -->
<!-- 	</table> -->

</body>
</html>