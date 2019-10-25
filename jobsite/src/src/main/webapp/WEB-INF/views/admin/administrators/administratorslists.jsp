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
					Administrators <small>System Users</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Administrators</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<div class="toolbar">
					<div class="btn-group" role="group" aria-label="Basic example">
					  <a href="<c:url value="/admin/addadmin/" />"><button type="button" class="btn btn-secondary"><i class="fa fa-plus-square"></i>&nbsp;Add</button></a>
					</div>
				</div>
				<table class="table table-striped table-bordered" style="width:100%" id="user_list">
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
					<c:forEach items="${users}" var="user">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td>
								<c:choose>
									 <c:when test="${user.isActive == true}">
									 	<i class="fa fa-check"></i>
									 </c:when>
								</c:choose>
							</td>
							<td>
								<c:choose>
									 <c:when test="${user.isLocked == true}">
									 	<i class="fa fa-check"></i>
									 </c:when>
								</c:choose>
							</td>
							<td><a href="<c:url value="/admin/viewadmin/${user.id}" />">View Details</a></td>
						</tr>
					</c:forEach>
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