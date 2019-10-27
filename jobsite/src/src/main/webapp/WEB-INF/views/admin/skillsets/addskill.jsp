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
					Skill Sets <small>Add Skill</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Add SKill</li>
				</ol>
			</section>
			<section class="content container-fluid">				
				<div class="row">
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Add Skill Below</h3>
							</div>
							<form method="POST" action="add">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1">Skill</label> 
										<input class="form-control" type="text" name="name"/>
									</div>
								</div>
								<div class="box-footer">
									<a href="<c:url value="/admin/skillsets" />"><button class="btn btn-primary" type="button">Back</button></a>
									<button class="btn btn-primary" type="submit">Submit</button>
								</div>
							</form>
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