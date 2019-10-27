<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Skill Edit</li>
				</ol>
			</section>
			<section class="content container-fluid">
				
				<div class="row">
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Edit Skill Below</h3>
							</div>
							<sf:form method="POST" modelAttribute="SkillSet">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1">Skill</label> 
										<sf:input class="form-control" path="Name" />
									</div>
								</div>
								<div class="box-footer">
									<sf:hidden path="id" />
									<button class="btn btn-primary" type="submit">Submit</button>
								</div>
							</sf:form>
						</div>
					</div>
				</div>
				
			</section>
		</div>
		<jsp:include page="../common/mainfooter.jsp"></jsp:include>
		<jsp:include page="../common/controlsidebar.jsp"></jsp:include>
	</div>
	<jsp:include page="../common/footerscript.jsp"></jsp:include>
</body>
</html>