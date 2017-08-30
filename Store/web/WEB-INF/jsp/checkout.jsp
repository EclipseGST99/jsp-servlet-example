<!DOCTYPE html>
<jsp:useBean id="message" scope="request" class="java.lang.String" />
<jsp:useBean id="formDisplay" scope="request" class="java.lang.String" />
<jsp:useBean id="returnToLoginDisplay" scope="request" class="java.lang.String" />

<html>
	<head>
		<title>
			My Store
		</title>
		<script>
			if (!sessionStorage.currentProductId) {
				sessionStorage.currentProductId=1;
			}
		</script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('form:first *:input[type!=hidden]:first').focus();
			});
		</script>
		<style>
			a:visited {color:#0000FF;} /* register link should still be blue*/
			a, footer {
				font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
				font-size:small;
			}
			div {
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
		<div align="center">
			
			<div style="width:500px; margin-left:auto; margin-right:auto;">
				<div style="float:left; text-align:left; width:150px;">
					<a href="logout">
						Logout
					</a>
				</div>
				<div style="float:left; text-align:center; width:200px;">
					<label>
						My Store
					</label>
				</div>
				<div style="float:left; text-align:right; width:150px;">
					<a href="cart">
						Back to Cart
					</a>		
				</div>							
			</div>
			<br><br><br>
			<div style="width: 500px; margin-left:auto; margin-right:auto; background-color:#FFFFFF; border:1px solid; border-radius:8px;">
			<h2 align="center">
				<font size="4" color="blue">
					Checkout
				</font>
			</h2>
				
				
				
				<div align="center">
					<%=message%>
				</div><br>
				<div style="display: <%=formDisplay%>">
					<form action="${pageContext.request.contextPath}/checkout" method="post">
						
						<table align="center">
							<tr>
								<td align="right">
									Credit Card #:
								</td>
							
								<td>
									<input type="text" name="creditCardNumber" maxlength="25">
								</td>
							</tr>
							
							<tr>
								<td align="right">
									Expiration Date:
								</td>
							
								<td>
									<input type="text" name="expirationDate" maxlength="7">
								</td>
							</tr>
							
							<tr>
								<td align="right">
									Security Code:
								</td>
							
								<td>
									<input type="text" name="securityCode" maxlength="4">
								</td>
							</tr>
							
						</table>
						<input type="submit" value="Submit Payment" style="margin:15px;">
					</form>
				</div>
			</div>
			<div style="display:<%=returnToLoginDisplay%>; margin:15px;">
				<a href="product">
					Return to Products
				</a>
			</div>
			<br><br><br><br><br>
			<footer>
				Website built by Clark Kent<br>
				Contact information: <a href="mailto:kal.el@krypton.net">
				kal.el@krypton.net</a>
			</footer>
		</div>
	</body>
</html>