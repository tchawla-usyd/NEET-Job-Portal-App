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
						<a href="<c:url value="/admin/addadmin/" />"><button
								type="button" class="btn btn-secondary">
								<i class="fa fa-plus-square"></i>&nbsp;Add
							</button></a>
					</div>
				</div>
				<table class="table table-striped table-bordered"
					style="width: 100%" id="user_list">
					<thead>
						<tr>
							<th></th>
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
							<td>
								<div class="dropdown">
									<button	class="btn btn-secondary dropdown-toggle" type="button"
											id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">...</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<a	class="dropdown-item"
											href="<c:url value="/admin/viewadmin/${user.id}" />">
											<div>
												<i class="fa fa-edit"></i>&nbsp;Edit
											</div>
										</a> 
										<a 	class="dropdown-item"
											data-href="<c:url value="/admin/deleteadmin/${user.id}" />"
											data-toggle="modal" data-target="#confirm-delete">
											<div>
												<i class="fa fa-trash"></i>&nbsp;Delete
											</div>
										</a>
									</div>
								</div>
							</td>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:choose>
									<c:when test="${user.isActive == true}">
										<i class="fa fa-check"></i>
									</c:when>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${user.isLocked == true}">
										<i class="fa fa-check"></i>
									</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
			</section>
		</div>

		<div class="modal fade" id="confirm-delete" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">Delete Administrator</div>
					<div class="modal-body">Are you sure you want to delete the Administrator Account?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<a class="btn btn-danger btn-ok">Delete</a>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="../common/mainfooter.jsp"></jsp:include>
		<jsp:include page="../common/controlsidebar.jsp"></jsp:include>
	</div>
	<jsp:include page="../common/footerscript.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#user_list').DataTable();
		});
		
		$('#confirm-delete').on('show.bs.modal', function(e) {
		    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});
	</script>
</body>
</html>