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
					Skill Sets<small>Manage Skill Sets</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Skill Set</li>
				</ol>
			</section>
			<section class="content container-fluid">
				
		        <div class="toolbar">
					<div class="btn-group" role="group" aria-label="Basic example">
						<a href="<c:url value= "../adminSkillset/add" />"><button
								type="button" class="btn btn-secondary">
								<i class="fa fa-plus-square"></i>&nbsp;Add
							</button></a>
					</div>
				</div>
				<table id="skill_table" class="table table-striped table-bordered" style="width:100%">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${skillSets}" var="skillSet">
							<tr>
								<td>
									<div class="dropdown">
										<button class="btn btn-secondary dropdown-toggle"
											type="button" id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">...</button>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuButton">
											<a class="dropdown-item"
												href="<c:url value="/adminSkillset/edit/${skillSet.id}" />">
												<div>
													<i class="fa fa-edit"></i>&nbsp;Edit
												</div>
											</a> <a class="dropdown-item"
												data-href="<c:url value="/adminSkillset/delete/${skillSet.id}" />"
												data-toggle="modal" data-target="#confirm-delete">
												<div>
													<i class="fa fa-trash"></i>&nbsp;Delete
												</div>
											</a>
										</div>
									</div>
								</td>
								<td><c:out value="${skillSet.id}" /></td>
								<td><c:out value="${skillSet.name}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</section>
		</div>
		
		<div class="modal fade" id="confirm-delete" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">Delete Skill Set</div>
					<div class="modal-body">Are you sure you want to delete the Skill?</div>
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
		    $('#skill_table').DataTable();
		} );
		
		$('#confirm-delete').on('show.bs.modal', function(e) {
		    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});
	</script>
</body>
</html>