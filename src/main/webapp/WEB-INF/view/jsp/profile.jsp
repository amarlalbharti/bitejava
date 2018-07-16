<!doctype html>
<%@page import="com.bharti.domain.Registration"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	
</head>
<body>
<section id="main">
  
  <div class="container">
    <div class="row">
      <div class="content col-sm-12 col-md-12">
		<div class="row">
		  <div id="sidebar" class="sidebar col-sm-3 col-md-3">
			<aside class="widget menu">
			  <header>
				<h3 class="title">My Account</h3>
			  </header>
			  <nav>
				<ul>
				  <li class="active">
					<a href="shop-account.html"><i class="fa fa-gears item-icon"></i>My Profile</a>
				  </li>
				  <li >
					<a href="shop-account-information.html"><i class="fa fa-user item-icon"></i>My Articles</a>
				  </li>
				  <li>
					<a href="shop-account-address.html"><i class="fa fa-pencil-square-o item-icon"></i>My Questions</a>
				  </li>
<!-- 				  <li> -->
<!-- 					<a href="shop-account-orders.html"><i class="fa fa-shopping-cart item-icon"></i>My Orders</a> -->
<!-- 				  </li> -->
				</ul>
			  </nav>
			</aside><!-- .menu-->
		  </div>
		  
		  <div class="col-sm-9 col-md-9 bottom-padding">
			<%
				Registration user = (Registration)request.getAttribute("user");
				if(user != null)
				{
					%>
					<form role="form" class="form-horizontal">
					  <div class="form-group col-md-6">
						<label for="fullname" class="col-sm-3 control-label input-sm"  >Full Name</label>
						<div class = "col-sm-9">
							<label class="form-control input-sm" ><%= user.getFirstName()+" "+ user.getLastName() %></label>
						</div>
					  </div>
					  <div class="form-group col-md-6">
						<label for="email" class="col-sm-3 control-label input-sm">Email Id</label>
						<div class = "col-sm-9">
						<label class="form-control input-sm " ><%= user.getLoginInfo().getUserid() %></label>
						</div>
					  </div>
					</form>
									
					
					<%
				}
			
			%>
		  </div>
		</div>
		
		
		<div class="clearfix"></div>
	  </div>
	</div>
  </div>
  
</section><!-- #main -->

</body>
</html>