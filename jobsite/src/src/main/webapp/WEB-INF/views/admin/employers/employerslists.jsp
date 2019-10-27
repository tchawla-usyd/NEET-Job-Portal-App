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
					Employers <small>Manage Employers Information and Quota</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<h1>Companies in the system</h1>
				<table class="table table-striped table-bordered"
					style="width: 100%" id="user_list">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Company Name</th>
							<th>Business Contact</th>
							<th>Web Site</th>
							<th>Primary User</th>
						</tr>
					</thead>
					<c:forEach items="${companies}" var="details">
						<tr>
							<td>
								<div class="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">...</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<a class="dropdown-item"
											href="<c:url value="/admin/company/view/${details.user.id}" />">
											<div>
												<i class="fa fa-edit"></i>&nbsp;Details
											</div>
										</a>
									</div>
								</div>
							</td>
							<td><c:out value="${details.company.id}" /></td>
							<td><c:out value="${details.company.companyName}" /></td>
							<td><c:out value="${details.company.businessPhone}" /></td>
							<td><c:out value="${details.company.webSite}" /></td>
							<td><c:out value="${details.user.firstName}" />&nbsp;<c:out value="${details.user.lastName}" /></td>
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
		});
	</script>
</body>
</html>