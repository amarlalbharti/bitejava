<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  
  <!-- Font -->
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
			  <div class="form-content">
				<form action="j_spring_security_check" method="post" class="form-box login-form form-validator">
				  <h3 class="title">Sign In <small>or <a href="signup" class="">Sign Up</a></small></h3>
				  <p>If you have an account with us, please log in.</p>
				  <c:if test="${param.error}">
						<div class="error text-danger" style="height: auto;">
							Your login attempt was not successful, try again.<br /> Reason:
							${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
						</div>
						<br>
					</c:if>
					<%
						String resetPwd = (String)request.getParameter("resetPwd");
						if(resetPwd != null && resetPwd.equals("true"))
						{
							%>
								<div class="text-success" style="height: auto;">
									Password reset successfully, Login with new password ! <br>
								</div>
								<br>
							<%
						}
					%>
				  <div class="form-group">
					<label>Email Address <span class="required">*</span></label>
					<input class="form-control" type="email"  placeholder="User id" name="j_username" id="j_username" >
				  </div>
				  
				  <div class="form-group">
					<label>Password: <span class="required">*</span></label>
					<input class="form-control" type="password" placeholder="Password" name="j_password" id="j_password">
				  </div>
				  
				  <div class="form-group">
					<label class="checkbox">
					  <input type="checkbox"> Remember password
					</label>
				  </div>
				  
				  <div class="buttons-box clearfix">
					<button type="submit" class="btn btn-default">Login</button>
					<a href="forgotpassword" class="forgot">Forgot Your Password?</a>
					<span class="required"><b>*</b> Required Field</span>
				  </div>
				  
				</form><!-- .form-box -->
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