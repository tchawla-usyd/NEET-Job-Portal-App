<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<jsp:include page="../common/meta.jsp"></jsp:include>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="../common/mainheader.jsp"></jsp:include>
		<jsp:include page="../common/mainsidebar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content-header">
				<h1>
					Skill Sets <small>Edit Skill</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<h1>You can edit the product shown below</h1>
					<sf:form method="POST" modelAttribute="SkillSet">
						<fieldset>
							<table>
								<tr>
									<th><label for="product_description">Name:</label></th>
									<td><sf:input path="Name"/></td>
								</tr>
								<tr>
									<th><a href="<c:url value="admin/skillsets""><button>Cancel</button></a></th>
									<!-- This hidden field is required for Hibernate to recognize this Product -->
									<td><sf:hidden path="id"/>
									<td><input type="submit" value="Done"/></td>
								</tr>
							</table>			
						</fieldset>
					</sf:form>
			</section>
		</div>
		<jsp:include page="../common/mainfooter.jsp"></jsp:include>
		<jsp:include page="../common/controlsidebar.jsp"></jsp:include>
	</div>
	<jsp:include page="../common/footerscript.jsp"></jsp:include>
</body>
</html>