<%@page import="model.StaffRepository"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff Management</title>
<link href="view/css/bootstrap.min.css" rel="stylesheet">
<script src="component/jquery-3.5.0.min.js" type="text/javascript"></script>
<!-- <script src="view/js/bootstrap.min.js" type="text/javascript"></script> -->
<script src="component/staff.js" type="text/javascript"></script>
</head>
<body>
	<div>

		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
			<a class="navbar-brand" href="#">Staff Management</a>
		</nav>

		<div class="container shadow-sm p-3 mb-5 bg-white rounded ">

			<div id="signupbox" margin-top:50px" class="mainbox">
				<div class="panel panel-info">
		
					<div class="panel-body container">
						<form id="detailform" class="form-horizontal" role="form">
							<div class="form-group">
								<label for="staffName" class="col-md-3 control-label ">
									<h6>Name</h6>
								</label>
								<div class="col-md-9">
									<input class="form-control" type="text" class="form-control" name="staffName"
										placeholder="Name" id="staffName" >
								</div>
							</div>


							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label"><h6>age</h6></label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="StaffAge"
										placeholder="Age" id="StaffAge" >
								</div>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label"><h5>Gender :</h5></label>
								<div class="col-md-9">
									<select class="form-control" id="StaffGender" name="StaffGender">
										<option value="0">choose....</option>
										<option value="female">Female</option>
										<option value="male">Male</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="Nic" class="col-md-3 control-label"><h6>Nic</h6></label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="StaffNic"
										placeholder="Nic" id="StaffNic">
								</div>
							</div>
							<div class="form-group">
								<label for="Address" class="col-md-3 control-label"><h6>Address</h6></label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="StaffAddress"
										placeholder="Address" id="StaffAddress">
								</div>
							</div>
							
								<div class="form-group">
								<label for="Email" class="col-md-3 control-label"><h6>Email</h6></label>
								<div class="col-md-9">
									<input type="email" class="form-control" name="StaffEmail"
										placeholder="Email" id="StaffEmail">
								</div>
							</div>

							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label"><h5>type :</h5></label>
								<div class="col-md-9">
									<select class="form-control" id="StaffType" name="StaffType">
										<option value="0">choose....</option>
										<option value="true">Medical staff</option>
										<option value="false">none medical staff</option>
									</select>
								</div>
							</div>
							<input type="hidden" id="hidItemIDSave" name="StaffId"
								value="">

							<div id="alertSuccess" class="alert alert-success "></div>

							<div id="alertError" class="alert alert-danger"></div>

							<button type="button" id="btnSave" class="btn btn-primary">
								<h6>Register</h6>
							</button>


						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="divItemsGrid"
			style="margin-top: 10px; margin-left: 10%; margin-right: 10%"
			class="table-responsive-md">
			<%
				StaffRepository repository = new StaffRepository();
			out.print(repository.getstaff());
			%>
		</div>
	</div>



</body>
</html>