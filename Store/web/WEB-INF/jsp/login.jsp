<!DOCTYPE html>
<jsp:useBean id="failedLoginMessage" scope="request" class="java.lang.String" />

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
		<div style=" width: 300px; margin-left: auto; margin-right: auto; background-color:#FFFFFF; border:1px solid; border-radius:8px;">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<table align="center" style="margin-top:15px;">
					<tr>
						<td align="right">
							Username:
						</td>
					
						<td align="left">
							<input type="text" name="username">
						</td>
					</tr>
					<tr style="margin-top:15px;">
						<td align="right">
							Password:
						</td>
					
						<td align="left">
							<input type="password" name="password">
						</td>
					</tr>
				</table>
				<input type="submit" name="login" value="Login" style="margin:15px;">
			</form>
		</div>
		<br>
		<a href="register">
			Register
		</a>
		<div id="failedLoginMessage" style="margin-top:20px; color:red;">
			<%=failedLoginMessage%>
		</div>
	</body>
</html>