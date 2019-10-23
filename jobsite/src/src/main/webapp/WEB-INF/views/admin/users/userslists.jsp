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
				<h1>Job Seekers in the system</h1>
				<table id="user_list" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Active</th>
							<th>Locked</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td><c:out value="${user.id}" /></td>
								<td><c:out value="${user.firstName}" /></td>
								<td><c:out value="${user.lastName}" /></td>
								<td><c:out value="${user.email}" /></td>
								<td><c:out value="${user.isActive}" /></td>
								<td><c:out value="${user.isLocked}" /></td>
								<td><a href="../adminSkillset/edit/${user.id}">View Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</div>
		<jsp:include page="../common/mainfooter.jsp"></jsp:include>
		<jsp:include page="../common/controlsidebar.jsp"></jsp:include>
	</div>
	<jsp:include page="../common/footerscript.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#user_list').DataTable();
		} );
	</script>
</body>
</html>