<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html class="full-height"><head>
  <meta charset="utf-8">
  <title>${pageTitle}</title>
  <meta name="description" content="${pageDescription}">
  <meta name="keywords" content="${pageKeywords}">
  <meta name="author" content="${pageAuthor}">
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	
  <!-- Favicon -->
<!--   <link rel="shortcut icon" href="img/favicon.ico"> -->
  
  <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic'>

  <!-- Plagins CSS -->
  <link rel="stylesheet" href="theme/css/bootstrap.css">
  <link rel="stylesheet" href="theme/css/font-awesome.min.css">
  <link rel="stylesheet" href="theme/css/settings.css">
  <link rel="stylesheet" href="theme/css/animate.css">

  <!-- Theme CSS -->
  <link rel="stylesheet" href="theme/css/style.css">
  
</head>
<body class="page-login-promo blur-page" data-blur-image="theme/img/bg-image.png" data-blur-amount="1">

<div class="page-box">
<section id="main">
  <div class="container login-register">
    <div class="row">
      <div class="col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<div data-appear-animation="bounceInDown">
		  <div class="rotation">
			<div class="front-end">
			  <div class="form-content" style="height: 600px">
			  	<form:form action="regUser" class="form-box register-form bv-form"  role="form" commandName="regForm" method="POST">
					  <button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>
					  <h3 class="title">Sign Up <small>or <a href="login">Sign In</a></small></h3>
					  <p>If you have an account with us, please log in.</p>
					  
					  <div class="form-group has-feedback">
						<label>Full name: <span class="required">*</span></label>
						<form:input class="form-control" path="name" /><i style="display: none;" class="form-control-feedback" data-bv-icon-for="Full Name Register"></i>
						  <small class="text-danger" ><form:errors path="name" /></small>
					  </div>
					  <div class="form-group has-feedback">
						<label>Email Address <span class="required">*</span></label>
						  <form:hidden class="form-control" path="refId" />
						  <form:input class="form-control" path="userid" type="email"/><i style="display: none;" class="form-control-feedback" data-bv-icon-for="Email Register"></i>
						  <small class="text-danger" ><form:errors path="userid"/></small>
					  </div>
					  <div class="form-group has-feedback">
						<label>Password: <span class="required">*</span></label>
						<form:password class="form-control" path="password" /><i style="display: none;" class="form-control-feedback" data-bv-icon-for="Password Register"></i>
					  <small class="text-danger" ><form:errors path="password"/></small>
					  </div>
						
					  <div class="form-group">
						<label class="checkbox">
						  <input type="checkbox">
						  Sign Up for Newsletter
						</label>
					  </div>
		  
					  <div class="buttons-box clearfix">
						<button class="btn btn-default">Create my account</button>
						<span class="required"><b>*</b> Required Field</span>
					  </div>
					</form:form><!-- .form-box -->
			  
			  </div>
			</div><!-- .front-end -->
			
		  </div>
		</div>
      </div>
    </div>
  </div><!-- .container -->
</section><!-- #main -->

</div><!-- .page-box -->
<!--[if (!IE)|(gt IE 8)]><!-->
  <script src="theme/js/jquery-2.1.3.min.js"></script>
<!--<![endif]-->

<script src="theme/js/bootstrap.min.js"></script>
<script src="theme/js/jquery.appear.js"></script>
<script src="theme/js/isotope.pkgd.min.js"></script>
<script src="theme/js/raphael.min.js"></script>
<script src="theme/js/video.js"></script> 
<script src="theme/js/pixastic.custom.js"></script>
<script src="theme/js/livicons-1.4.min.js"></script>
<script src="theme/js/main.js"></script>

</body>
</html>