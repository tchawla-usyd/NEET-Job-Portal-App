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
					Administrator <small>View</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>
							Administrator</a></li>
					<li class="active">View</li>
				</ol>
			</section>
			<section class="content container-fluid">
				<div class="row">
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Please enter the information below to create a new administrator</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<sf:form method="POST" modelAttribute="user">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1">First Name</label> <sf:input path="FirstName"
											class="form-control" id="exampleFirstName" type="text"
											placeholder="Enter First Name" name="firstName"/>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Last Name</label> <sf:input path="LastName"
											class="form-control" id="exampleLastName" type="text"
											placeholder="Enter Last Name" name="lastName"/>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Email</label> <sf:input path="Email"
											class="form-control" id="exampleInputEmail1" type="email"
											placeholder="Enter email" name="email"/>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Change Password</label> <sf:input path="Password"
											class="form-control" id="exampleInputPassword1"
											type="password" placeholder="Password" name="password"/>
									</div>
								</div>
								<div class="box-footer">
									<a href="<c:url value="/admin/administrators" />"><button class="btn btn-primary" type="button">Back</button></a>
									<button class="btn btn-primary" type="submit">Update</button>
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