<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>HomePage</title>
</head>

<body style="padding:15px;">
	<div class = container>
		<div class = "row">
			<div class="col-sm-1">
				<img src="url" alt="Neet.com">
			</div>
			<div class="col-sm-10" style="">
				<button type="button" id="myApplicationsPageButton" style="float:right; margin:5px;" class="btn btn-primary">My Application</button>
			</div>
			<div class="col-sm-1">
				<img src="url" alt="User profile">
			</div>
		</div>
		
		<div class = "row">
			<div class="col-sm-4">
				<div class="input-group mb-3" style="margin:5px;">
					<div class="input-group-prepend">
				    	<span class="input-group-text"><img src="url">@</span>
				  	</div>
				  	<input type="text" class="form-control" placeholder="Search" aria-label="Search" aria-describedby="basic-addon1">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="row">
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" style="margin:5px" type="button" id="dropdownPositionButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Position
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						    <a class="dropdown-item" href="#">Software Engineer</a>
						    <a class="dropdown-item" href="#">Cloud Engineer</a>
						    <a class="dropdown-item" href="#">Network Engineer</a>
						</div>
					</div>
					
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" style="margin:5px" type="button" id="dropdownLocationButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Location
						</button>
							<div class="dropdown-menu locationDropDown" aria-labelledby="dropdownMenuButton">
							    <a class="dropdown-item" href="#">Sydney</a>
							    <a class="dropdown-item" href="#">Melbourn</a>
							    <a class="dropdown-item" href="#">Brisbane</a>
							</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="row" style="float: right;margin-right: 0;">
                    	<span style="margin:10px">Sort:</span>
                        <button type="button" id="recentJobsButton" style="margin:5px" class="btn btn-primary">Recent</button>
                        <button type="button" id="RelevanceJobsButton" style="margin:5px" class="btn btn-primary">Relevance</button>
				</div>
			</div>
		</div>
		
		<div>
			<table class="table table-hover table-responsive" id="jobsListTable">
				<thead class="thead-dark">
				    <tr>
						<th scope="col">Job Title</th>
						<th scope="col">Company</th>
						<th scope="col">Location</th>
						<th scope="col">skills</th>
						<th scope="col">Date Added</th>
				    </tr>
			  	</thead>
				<tbody id="jobsListTableBody">
				console.log(${CandidateResponse.jobRows});
				<c:forEach items="${CandidateResponse.jobRows}" var="jobRow">
					<tr>
					    <th scope="row"><a href="#" class="text-dark">${jobRow.jobTitle}</a></th>
					    <td>${jobRow.company}</td>
					    <td>${jobRow.location}</td>
					    <td>
					    	<c:forEach items="${jobRow.skills}" var="skill">
					    		${skill} &nbsp;
					    	</c:forEach>
					    </td>
					    <td>${jobRow.dateAdded}</td>
					  </tr>
<!-- 					  <tr> -->
<!-- 					    <th scope="row"><a href="#" class="text-dark">Software Developer</a></th> -->
<!-- 					    <td>Google</td> -->
<!-- 					    <td>Sydney</td> -->
<!-- 					    <td>Java</td> -->
<!-- 					    <td>2019-01-01</td> -->
<!-- 					  </tr> -->
				</c:forEach>
				  
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>