<!DOCTYPE html>
<jsp:useBean id="description" scope="request" class="java.lang.String" />
<jsp:useBean id="price" scope="request" class="java.lang.String" />
<jsp:useBean id="productImage" scope="request" class="java.lang.String" />
<jsp:useBean id="inStockColor" scope="request" class="java.lang.String" />
<jsp:useBean id="inStock" scope="request" class="java.lang.String" />
<jsp:useBean id="addedToCartMessage" scope="request" class="java.lang.String" />

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
						Shopping Cart
					</a>		
				</div>							
			</div>
			<br><br><br>
			<div style="width: 500px; margin-left:auto; margin-right:auto; border:1px solid; border-radius:8px;">
				<h2>
					<%=description%>
				</h2>
				<div align="center">
					<font size="6" color="red" style="width:130px">
						$<%=price%>
					</font>
					<font size="2" face="Arial Black" color="<%=inStockColor%>" style="position: relative; left: 20px;" align="center">
						<%=inStock%>
					</font>
				</div>
				<div align="center">
					<img align="center" src="<%=productImage%>" width="400" height="400" style="margin-top:20px;">
				</div>
				<div align="center" style="position:relative; right:20px; margin-top:8px;">
					<form action="${pageContext.request.contextPath}/product" method="post">
						<button
							align="left"
							type="submit" name="previous" 
							style="border:0px; background: url('image/PreviousButton.png'); width:104px; height:35px;">
						</button>
						<button 
							type="submit" name="next" 
							style="border:0px; background: url('image/NextButton.png'); width:59px; height:35px; position:relative; left:20px;">
						</button>
						<br>
						<button 
							type="submit" name="addToCart" 
							style="border:0px; background: url('image/AddToCartButton.png'); width:104px; height:35px; position:relative; left:20px;">
						</button>
					</form>
					<div style="position:relative; left:19px; font-weight:bold; color:red">
						<%=addedToCartMessage%>
					</div>
				</div>
				
				</div><br><br>
				<footer>
				 	Website built by Clark Kent<br>
				 	Contact information: <a href="mailto:kal.el@krypton.net">
				  	kal.el@krypton.net</a>
				</footer>
			
		</div>
	</body>
</html>