<!doctype html>
<%@page import="com.bharti.constraints.ProjectConfig"%>
<%@page import="com.bharti.constraints.DateFormats"%>
<%@page import="com.bharti.domain.Registration"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- Favicon -->
  	<link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/theme/favicon//apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/theme/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="${pageContext.request.contextPath}/theme/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/theme/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/theme/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/theme/favicon/favicon-16x16.png">
	<link rel="manifest" href="theme/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/theme/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	 
  <!-- Font -->
<!--   <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic'> -->

  
  <!-- Plagins CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/bootstrap.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/font-awesome.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/jslider.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/settings.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/jquery.fancybox.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/animate.css">
  <!-- Theme CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/bitejava_css.css">
  
  <!-- Custom CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/customizer/pages.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/customizer/home-pages-customizer.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/select2.min.css">
  
  <script src="${pageContext.request.contextPath}/theme/js/jquery-2.1.3.min.js"></script>
  <script src="${pageContext.request.contextPath}/theme/js/common_js.js"></script>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/lobibox.min.css"/>
  <script src="${pageContext.request.contextPath}/theme/js/lobibox.min.js"></script>
  <script src="${pageContext.request.contextPath}/theme/js/notifications.min.js"></script>
  
<style type="text/css">
input[type="file"] {
    display: none !important;
}
.custom-file-upload {
    cursor: pointer;
    margin-top: -30px !important;
    position: absolute !important;
}
.bodyCoverWait {
    position: fixed;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    z-index: 9999;
    opacity: 0.8;
    background: #ececec;
}

</style>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/model.popup.css" /> --%>


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
<div class="bodyCoverWait" style="text-align: center; ">
	<img style="position: relative;top: 250px;" alt="Please wait..." src="${pageContext.request.contextPath}/theme/images/loading_spinner.gif">
</div>
<%
Registration reg = (Registration) request.getSession().getAttribute("registration");
if(reg != null)
{
	%>
	<div class="page-box" style="min-height: 520px;">
	<div class="page-box-content">
	
	<header class="header header-two">
	  <div class="header-wrapper">
		<div class="container">
		  <div class="row">
			<div class="col-xs-6 col-md-2 col-lg-3 logo-box">
			  <div class="logo">
					<a href="adminDashboard">
						<img src="${pageContext.request.contextPath}/theme/images/bitejava_log.png" class="logo-img" alt="Bite Java Logo">
					</a>
			  </div>
			</div><!-- .logo-box -->
			
			<div class="col-xs-6 col-md-10 col-lg-9 right-box">
			  <div class="right-box-wrapper">
				<div class="header-icons">
				  
				  <div class="btn-group cart-header">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					  <div class="icon">
						<i class="fa fa-user"></i>
					  </div>
					  <span class="count"><%= reg.getName() %></span>
					</a>
					<div class="dropdown-menu">
					  <strong><%= reg.getName() %></strong>
					  <ul class="list-unstyled">
						<li>
						  <%
						  	if(reg.getProfileImage() != null) {
						  		%>
								  <a href="#" class="product-image "><img class="replace-2x image  img-circle" src="<%=ProjectConfig.UPLOAD_FOLDER+reg.getProfileImage() %>" alt="" width="70" height="70"></a>
						  		<%
						  	}else{
						  		%>
						  		<a href="#" class="product-image "><img class="replace-2x image  img-circle" src="theme/images/Preview-icon.png" alt="" width="70" height="70"></a>
						  		<%
						  	}
						  %>
						  <h4 class="product-name"><%= reg.getLoginInfo().getUserid() %></h4>
						  <div class="product-name">Since : <%= DateFormats.ddMMMyyyy.format(reg.getCreateDate()) %></div>
						  <div class="product-name">Gender : <%= reg.getGender() %></div>
						  <div class="clearfix"></div>
						</li>
					  </ul>
					  <div class="cart-button">
						<a class="btn btn-default pull-left" href="${pageContext.request.contextPath}/profile">Profile</a>
						<button class="btn checkout btn-default" onclick="getLogOut()">Logout</button>
					  </div>
					</div>
				  </div>
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
						  <a href="adminSubjects">Subjects</a>
						</li>
						<li>
						  <a href="adminKeynotes">KeyNotes</a>
						</li>
						<li>
						  <a href="adminSeoKeynotes">SEO KeyNotes</a>
						</li>
						<li>
						  <a href="adminUploads">Uploaded Files</a>
						</li>
						<li>
						  <a href="adminQuestions">Questions</a>
						</li>
						
					  </ul>
					</nav>
				  </div>
				</div><!-- .primary -->
			  </div>
			</div>
			
			<div class="phone-active col-sm-9 col-md-9">
			  <a href="#" class="close"><span>close</span>�</a>
			  <span class="title">Call Us</span> <strong>+91 (919) 853 01 54</strong>
			</div>
			<div class="search-active col-sm-9 col-md-9">
			  <a href="#" class="close"><span>close</span>�</a>
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
	<%
}

%>


</body>
</html>