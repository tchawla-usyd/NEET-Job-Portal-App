<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
					Company <small>View</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>
							Company</a></li>
					<li class="active">View</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<div class="row">
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Please see the details of the company and its jobs</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<sf:form method="POST" modelAttribute="company">
								<div class="box-body">
									<div class="form-group">
										<label for="lblInputFirstName">Id</label> 
										<sf:input 	path="Id"
													class="form-control" id="inputId" type="text"
													placeholder="User Unique Id" name="Id"/>
									</div>
									<div class="form-group">
										<label for="lblInputFirstName">Name</label> 
										<sf:input 	path="CompanyName"
													class="form-control" id="exampleFirstName" type="text"
													placeholder="Enter First Name" name="CompanyName"/>
									</div>
									<div class="form-group">
										<label for="lblInputLastName">Business Phone</label> 
										<sf:input 	path="BusinessPhone"
													class="form-control" id="exampleLastName" type="text"
													placeholder="Enter Last Name" name="BusinessPhone"/>
									</div>
									<div class="form-group">
										<label for="lblInputEmail">Web Site</label> 
										<sf:input 	path="WebSite"
													class="form-control" id="txtInputEmail" type="text"
													placeholder="Enter email" name="WebSite"/>
									</div>
								</div>
								<div class="box-footer">
									<a href="<c:url value="/admin/company/all" />"><button class="btn btn-primary" type="button">Back</button></a>
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
	
	<script type="text/javascript" lang="javascript">
		$( "#inputId" ).prop( "disabled", true ); //Disable
		$( "#txtInputEmail" ).prop( "disabled", true ); //Disable
	</script>
</body>
</html>