<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/meta.jsp"></jsp:include>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../common/mainheader.jsp"></jsp:include>
		<jsp:include page="../common/mainsidebar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content-header">
				<h1>
					Users <small>Manage Users</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<h1>Employees in the system</h1>
				<table class="display" id="user_list">
					<thead>
						<tr>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Active</th>
							<th>Locked</th>
						</tr>
					</thead>
					<c:forEach items="${users}" var="user">
						<tr>
							<td><c:out value="${user.Id}" /></td>
<%-- 							<td><c:out value="${user.FirstName}" /></td> --%>
<%-- 							<td><c:out value="${user.LastName}" /></td> --%>
<%-- 							<td><c:out value="${user.Email}" /></td> --%>
<%-- 							<td><c:out value="${user.IsActive}" /></td> --%>
<%-- 							<td><c:out value="${user.IsLocked}" /></td> --%>
<%-- 							<td><a href="../adminSkillset/edit/${user.Id}">View Details</a></td> --%>
						</tr>
					</c:forEach>
				</table>
			</section>
		</div>
		<jsp:include page="../common/mainfooter.jsp"></jsp:include>
		<jsp:include page="../common/controlsidebar.jsp"></jsp:include>
	</div>
	<jsp:include page="../common/footerscript.jsp"></jsp:include>
</body>
</html>