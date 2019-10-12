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
					Skill Sets <small>Manage Skill Sets</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<h1>All skills changed</h1>
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
							<td><c:out value="${skillSet.id}" /></td>
							<td><c:out value="${skillSet.name}" /></td>
							<td><a href="../adminSkillset/edit/${skillSet.id }">edit</a></td>
							<td><a href="../adminSkillset/delete/${skillSet.id }">delete</a></td>
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