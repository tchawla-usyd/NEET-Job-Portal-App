<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Job Description</title>
</head>

<body style="padding:15px;">
	<div class="container">
		<div class = "row">
			<div class="col-sm-1">
				<img src="url" alt="Neet.com">
			</div>
			<div class="col-sm-11">
				<button type="button" id="myApplicationsPageButton" class="btn btn-primary btn-lg">My Application</button>
			</div>
			<div class="col-sm-1">
				<img src="url" alt="User profile">
			</div>
		</div>
		
		<div>
			<h4>
				Software Developer
			</h4>
			<div>
				<span>Google</span><span>Sydney, NSW, Australia</span>
			</div>
		</div>
		
		<div>
			<h4>Description</h4>
			<div id="jobDescriptionSection">
				<p>description about the job</p>
			</div>
		</div>
		
		<div>
			<h4>Desired Skills</h4>
			<div id="desiredSkillsSection">
				<p>Java</p>
			</div>
		</div>
		
		<div>
			<button class="btn btn-secondary dropdown-toggle" type="button" id="applyJobButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    Apply
			</button>
		</div>
	</div>
</body>
</html>