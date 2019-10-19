<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="user" action="registerProcess"
		method="post">
		<table align="center">

			<tr>
				<td><label path="firstName">FirstName</label></td>
				<td><input type="text" path="firstName" name="firstName"
					id="firstName" /></td>
			</tr>
			<tr>
				<td><label path="lastName">LastName</label></td>
				<td><input type="text" path="lastName" name="lastName"
					id="lastName" /></td>
			</tr>
			<tr>
				<td><label path="Email">Email</label></td>
				<td><input type="Email" path="Email" name="Email" id="Email" /></td>
			</tr>
			<tr>
				<td><label path="Password">Password</label></td>
				<td><input type="password" path="Password" name="Password"
					id="Password" /></td>
			</tr>
		
			<tr>
				<td><label path="userType">userType</label></td>
				<td><label class="radio-inline"> 
				<input type="radio"
						name="radioUser" id="candidate" value="4" checked="checked" />Candidate
				</label> <label class="radio-inline"> <input type="radio"
						name="radioUser" id="employer" value="3" />Employer
				</label></td>
			</tr>
				<tr>
				<td></td>
				<td><button id="register" name="register">Register</button></td>
			</tr>
		</table>
	</form:form>
</body>
</html>