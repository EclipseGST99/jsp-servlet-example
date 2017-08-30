<!DOCTYPE html>
<jsp:useBean id="items" scope="request" class="java.lang.String" />
<jsp:useBean id="checkoutButtonDisplay" scope="request" class="java.lang.String" />

<html>
	<head>
		<title>
			My Store
		</title>
		<script>
			if (!sessionStorage.currentProductId) {
				sessionStorage.currentProductId=1;
			}
			if (!sessionStorage.cart) {
				sessionStorage.cart="";
			}
		</script>
		<style>
			a:visited {color:#0000FF;} /* visited link should still be blue*/
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
					<a href="product">
						Back to Products
					</a>		
				</div>							
			</div>
			<br><br><br>
			<div style="width: 500px; margin-left:auto; margin-right:auto; background-color:#FFFFFF; border:1px solid; border-radius:8px;">
			<h2 align="center">
				<font size="4" color="blue">
					Shopping Cart
				</font>
			</h2>
			<%=items%>
			<div style="display: <%=checkoutButtonDisplay%>">
				<form method="link" action="checkout">
					<input type="submit" value="Checkout" style="margin:15px;">
				</form>
			</div>
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