<!DOCTYPE html>
<jsp:useBean id="registrationMessage" scope="request" class="java.lang.String" />
<jsp:useBean id="formDisplay" scope="request" class="java.lang.String" />

<html>
	<head>
		<title>My Store</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('form:first *:input[type!=hidden]:first').focus();
			});
		</script>
		<style>
			a:visited {color:#0000FF;} /* visited link should still be blue*/
			a {
				font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
				font-size:small;
			}
			td {
				width:200px;
			}
			h1, div {
				font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
			}
			h2 {
				font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
				color:green;
			}
			h3 {
				font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
				color:brown;
			}
			label {
				font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
				font-size:xx-large;
				font-weight:bold;
			}
		</style>
	</head>
	<body style="text-align:center;">
		<div align="center" style="margin-bottom:10px;">
			<label>
				My Store
			</label>
		</div>
		<div style="display:<%=formDisplay%>;">
			<div style=" width: 500px; margin-left: auto; margin-right: auto; background-color:#FFFFFF; border:1px solid; border-radius:8px;">
				<div align="center" style="margin-top:10px; margin-bottom:8px;"> 
					<h2>
						Customer Registration
					</h2>
				</div>
				<form action="${pageContext.request.contextPath}/register" method="post">
						<table align="center">
							<tr>
								<td align="right">
									Desired Username:
								</td>
								<td align="left">
									<input type="text" name="desiredUsername" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									Password:
								</td>
								<td align="left">
									<input type="password" name="password" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									Verify Password:
								</td>
								<td align="left">
									<input type="password" name="verifyPassword" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									First Name:
								</td>
								<td align="left">
									<input type="text" name="firstName" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									Middle Name:
								</td>
								<td align="left">
									<input type="text" name="middleName" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									Last Name:
								</td>
								<td align="left">
									<input type="text" name="lastName" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									Phone Number:
								</td>
								<td align="left">
									<input type="text" name="phoneNumber" maxlength="50">
								</td>
							</tr>
							<tr>
								<td align="right">
									Email:
								</td>
								<td align="left">
									<input type="text" name="email" maxlength="100">
								</td>
							</tr>
							<tr>
								<td align="right">
									<h3 style="margin-bottom:10px;">Mailing Address:</h3>
								</td>
							</tr>
						</table>
						<table align="center">
						
							<tr>
								<td align="right">
									Street1:
								</td>
								<td align="left">
									<input type="text" name="street1" size="30" maxlength="100">
								</td>
							</tr>
							<tr>
								<td align="right">
									Street2:
								</td>
								<td align="left">
									<input type="text" name="street2" size="30" maxlength="100">
								</td>
							</tr>
							<tr>
								<td align="right">
									City:
								</td>
								<td align="left">
									<input type="text" name="city" maxlength="100">
								</td>
							</tr>
							<tr>
								<td align="right">
									State:
								</td>
								<td align="left">
									<input type="text" name="state" size="2" maxlength="2">
								</td>
							</tr>
							<tr>
								<td align="right">
									Zip Code:
								</td>
								<td align="left">
									<input type="text" name="zipCode" size="10" maxlength="10">
								</td>
							</tr>
						</table>
					<div style="text-align:center; margin:15px;">
						<input type="submit" name="register" value="Register">
					</div>
				</form>
			</div>
		</div>
		<div style="text-align:center;">
			<div style="color:red; margin:5px;" id="message">
				<%=registrationMessage%><br>
			</div>
			<a href="login">
				Return to Login
			</a>
		</div>
	</body>
</html>