<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html class="full-height"><head>
  <title>Login - BiteJava Tutorials</title>
  <meta name="keywords" content="HTML5 Template">
  <meta name="description" content="Progressive — Responsive Multipurpose HTML Template">
  <meta name="author" content="itembridge.com">
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

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
			  	<%
			  		String resetPwd = (String)request.getAttribute("resetPwd");
			  		if(resetPwd != null && resetPwd.equals("forgot"))
			  		{
			  			%>
						  	<form action="forgotpassword" class="form-box register-form bv-form"  role="form" method="POST">
								  <button type="submit" class="bv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>
								  <h3 class="title">Retrieve your password here</h3>
								  <p>Please enter your email address below. You will receive a link to reset your password.</p><br>
								  <%
								  	String reset = (String)request.getAttribute("reset");
								  	if(reset != null && reset.equals("success"))
								  	{
								  		%>
										  <p class="text-success">Reset link has been send </p>
								  		<%
								  	}
								  %>
								  
								  <div class="form-group has-feedback">
									<label>Email Address <span class="required">*</span></label>
									<input class="form-control" name="email" required="required" data-bv-field="Email Register" type="email"><i style="display: none;" class="form-control-feedback" data-bv-icon-for="Email Register"></i>
									<small class="text-danger" data-bv-validator="emailAddress" data-bv-for="Email Register" data-bv-result="NOT_VALIDATED">${emailError}</small>
								  	<small style="display: none;" class="help-block" data-bv-validator="emailAddress" data-bv-for="Email Register" data-bv-result="NOT_VALIDATED">Please enter a valid email address</small><small style="display: none;" class="help-block" data-bv-validator="notEmpty" data-bv-for="Email Register" data-bv-result="NOT_VALIDATED">Please enter a value</small>
							  	  </div>
					  				
								  <div class="buttons-box clearfix">
									<button class="btn btn-default">Submit</button>
									<a href="login" class="btn btn-border "><i class="fa fa-long-arrow-left"></i> Back to Login</a>
									<span class="required"><b>*</b> Required Field</span>
								  </div>
								</form><!-- .form-box -->
			  			<%
			  		}
			  		else if(resetPwd != null && resetPwd.equals("reset"))
			  		{
			  			%>
			  				<form action="forgotpassword" class="form-box register-form bv-form"  role="form" method="POST">
							  <h3 class="title">Change your password here</h3>
							  <p>Please enter new password.</p><br>
							  
							  <div class="form-group has-feedback">
								<label>New Password<span class="required">*</span></label>
								<input class="form-control" name="email" required="required" data-bv-field="Reset Password" type="password">
								<input type="hidden" name="email" value="${email}">
								<input type="hidden" name="token" value="${token}">
								
						  	  </div>
				  				
							  <div class="buttons-box clearfix">
								<button class="btn btn-default">Change Password</button>
								<a href="login" class="btn btn-border "><i class="fa fa-long-arrow-left"></i> Back to Login</a>
								<span class="required"><b>*</b> Required Field</span>
							  </div>
							</form><!-- .form-box -->
			  			<%
			  		}
			  			
			  	%>
			  
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