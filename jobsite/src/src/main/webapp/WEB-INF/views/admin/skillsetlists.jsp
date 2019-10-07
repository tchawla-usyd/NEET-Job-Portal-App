<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	All skills
</h1>
<a href="../adminSkillset/add"><button>Add</button></a>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
		</tr>
	</thead>
	<c:forEach items="${skillSets}" var="skillSet">
	    <tr>
	        <td><c:out value="${skillSet.id}"/></td>
	        <td><c:out value="${skillSet.name}"/></td>
	       	<td><a href="../adminSkillset/edit/${skillSet.id }">edit</a></td>
			<td><a href="../adminSkillset/delete/${skillSet.id }">delete</a></td>
	    </tr>
	</c:forEach>
</table>

</body>
</html>
