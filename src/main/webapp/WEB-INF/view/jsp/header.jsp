<!doctype html>
<%@page import="com.bharti.domain.Registration"%>
<%@page import="java.util.Date"%>
<%@page import="com.bharti.constraints.DateFormats"%>
<html>
<head>
  
  <!-- Font -->
  <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic'>

  <!-- Plagins CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/font-awesome.min.css">
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/jslider.css"> --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/settings.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/jquery.fancybox.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/animate.css">

  <!-- Theme CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/bitejava_css.css">
  
  
  <!-- Custom CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/customizer/pages.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/customizer/home-pages-customizer.css">
  <script src="${pageContext.request.contextPath}/theme/js/jquery-2.1.3.min.js"></script>
  <script src="${pageContext.request.contextPath}/theme/js/common_js.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/lobibox.min.css"/>
  <script src="${pageContext.request.contextPath}/theme/js/lobibox.min.js"></script>
  <script src="${pageContext.request.contextPath}/theme/js/notifications.min.js"></script>
  <style type="text/css">
	    .subject_data h1{
	    font-size: 22px;
	    }
	    .subject_data h2{
	    font-size: 20px;
	    }
	    .bj_menu .current a{
	    color: #fff !important;
	    }
  </style>
  <script>
		function getLogOut(){
			if (XMLHttpRequest)
			{
				x=new XMLHttpRequest();
			}
			else
			{
				x=new ActiveXObject("Microsoft.XMLHTTP");
			}
		     x.onreadystatechange=function()
			{
		    	 if(x.readyState==4 && x.status==200)
					{
		    		 var res = x.responseText;
		    		 window.location.href="${pageContext.request.contextPath}/j_spring_security_logout";
		    		}
			}
			x.open("GET", "${pageContext.request.contextPath}/insertLogOut",true);
			x.send();
			return true;
		}
</script>
</head>
<body class="fixed-header">
<div class="page-box" style="min-height: 300px;">
<div class="page-box-content">
<%
Registration reg = (Registration) request.getSession().getAttribute("registration");
%>
<header class="header header-two">
  <div class="header-wrapper">
	<div class="container">
	  <div class="row">
		<div class="col-xs-6 col-md-2 col-lg-3 logo-box">
		  <div class="logo">
				<a href="${pageContext.request.contextPath}/index.html">
					<img src="${pageContext.request.contextPath}/theme/images/bitejava-logo.jpg" class="logo-img" alt="">
				</a>
		  </div>
		</div><!-- .logo-box -->
		
		<div class="col-xs-6 col-md-10 col-lg-9 right-box">
		  <div class="right-box-wrapper">
			<div class="header-icons">
			  <div class="search-header hidden-600">
				<a href="#">
				  <svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
					<path d="M12.001,10l-0.5,0.5l-0.79-0.79c0.806-1.021,1.29-2.308,1.29-3.71c0-3.313-2.687-6-6-6C2.687,0,0,2.687,0,6
					s2.687,6,6,6c1.402,0,2.688-0.484,3.71-1.29l0.79,0.79l-0.5,0.5l4,4l2-2L12.001,10z M6,10c-2.206,0-4-1.794-4-4s1.794-4,4-4
					s4,1.794,4,4S8.206,10,6,10z"></path>
					<image src="theme/img/png-icons/search-icon.png" alt="" width="16" height="16" style="vertical-align: top;">
				  </svg>
				</a>
			  </div><!-- .search-header
			  
			  --><div class="phone-header hidden-600">
				<a href="#">
				  <svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
					<path d="M11.001,0H5C3.896,0,3,0.896,3,2c0,0.273,0,11.727,0,12c0,1.104,0.896,2,2,2h6c1.104,0,2-0.896,2-2
					c0-0.273,0-11.727,0-12C13.001,0.896,12.105,0,11.001,0z M8,15c-0.552,0-1-0.447-1-1s0.448-1,1-1s1,0.447,1,1S8.553,15,8,15z
					M11.001,12H5V2h6V12z"></path>
					<image src="theme/img/png-icons/phone-icon.png" alt="" width="16" height="16" style="vertical-align: top;">
				  </svg>
				</a>
			  </div><!-- .phone-header -->
			  	<%
			  		if(reg != null)
			  		{
			  			%>
						  <div class="btn-group cart-header">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								  <div class="icon">
									<i class="fa fa-user"></i>
								  </div>
								  <span class="count"><%= reg.getFirstName()+" " +reg.getLastName() %></span>
								</a>
								<div class="dropdown-menu">
								  <strong>Welcome <%= reg.getLastName() %></strong>
								  <ul class="list-unstyled">
									<li>
									  <a href="#" class="product-image "><img class="replace-2x image  img-circle" src="theme/images/Preview-icon.png" alt="" width="70" height="70"></a>
									  <h4 class="product-name"><%= reg.getLoginInfo().getUserid() %></h4>
									  <div class="product-name">Since : <%= DateFormats.ddMMMyyyy.format(reg.getCreateDate()) %></div>
									  <%
									  	if(reg.getGender() != null)
									  	{
									  		%>
											  <div class="product-name">Gender : <%=  reg.getGender()%></div>
									  		<%
									  	}
									  %>
									  <div class="clearfix"></div>
									</li>
								  </ul>
								  <div class="cart-button">
									<a href="profile" class="btn btn-default pull-left">Profile</a>
									<button class="btn checkout btn-default" onclick="getLogOut()">Logout</button>
								  </div>
								</div>
						  </div>
			  			<%
			  		}
			  		else
			  		{
			  			%>
			  				<div class="btn-group cart-header">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								  <div class="icon">
									<i class="fa fa-user"></i>
								  </div>
								  <span class="count">Guest</span>
								</a>
								<div class="dropdown-menu">
								  <strong>Welcome Guest</strong>
								  <ul class="list-unstyled">
									<li>
<!--  									  <a href="#" class="product-image "><img class="replace-2x image  img-circle" src="theme/images/Preview-icon.png" alt="" width="70" height="70"></a> -->
<!-- 									  <h4 class="product-name"></h4> -->
									  <div class="">If you have already registered please Log In, otherwise Sign Up to use other feature.</div>
<!-- 									  <div class="product-name">Gender : Male</div> -->
									  
									  <div class="clearfix"></div>
									</li>
								  </ul>
								  <div class="cart-button">
									<a href="signup" class="btn btn-default pull-left">Sign Up</a>
									<a href="${pageContext.request.contextPath}/login" class="btn checkout btn-default" >Sign In</a>
								  </div>
								</div>
						  </div>
			  			<%
			  		}
			  	%>
			</div><!-- .header-icons -->
			
			<div class="primary">
			  <div class="navbar navbar-default" role="navigation">
				<button type="button" class="navbar-toggle btn-navbar collapsed" data-toggle="collapse" data-target=".primary .navbar-collapse">
				  <span class="text">Menu</span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</button>
	  
				<nav class="collapse collapsing navbar-collapse">
				  <ul class="nav navbar-nav navbar-center">
					<li>
					  <a href="${pageContext.request.contextPath}/note/core_java">Core Java</a>
					</li>
					<li>
					  <a href="${pageContext.request.contextPath}/note/collections">Collections</a>
					</li>
					<li>
					  <a href="${pageContext.request.contextPath}/note/hibernate">Hibernate</a>
					</li>
					
					<li>
					  <a href="${pageContext.request.contextPath}/note/spring">Spring MVC</a>
					</li>
					<li>
					  <a href="${pageContext.request.contextPath}/questions">Articles</a>
					</li>
					
				  </ul>
				</nav>
			  </div>
			</div><!-- .primary -->
		  </div>
		</div>
		
		<div class="phone-active col-sm-9 col-md-9">
		  <a href="#" class="close"><span>close</span>X</a>
		  <span class="title">Call Us</span> <strong>+91 7007 614 898</strong>
		</div>
		<div class="search-active col-sm-9 col-md-9">
		  <a href="#" class="close"><span>close</span>X</a>
		  <form name="search-form" class="search-form">
			<input class="search-string form-control" type="search" placeholder="Search here" name="search-string">
			<button class="search-submit">
			  <svg x="0" y="0" width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
				<path fill="#231F20" d="M12.001,10l-0.5,0.5l-0.79-0.79c0.806-1.021,1.29-2.308,1.29-3.71c0-3.313-2.687-6-6-6C2.687,0,0,2.687,0,6
				s2.687,6,6,6c1.402,0,2.688-0.484,3.71-1.29l0.79,0.79l-0.5,0.5l4,4l2-2L12.001,10z M6,10c-2.206,0-4-1.794-4-4s1.794-4,4-4
				s4,1.794,4,4S8.206,10,6,10z"></path>
				<image src="theme/img/png-icons/search-icon.png" alt="" width="16" height="16" style="vertical-align: top;">
			  </svg>
			</button>
		  </form>
		</div>
		
		
	  </div><!--.row -->
	</div>
  </div><!-- .header-wrapper -->
</header><!-- .header -->


</body>
</html>