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

  <!-- Favicon -->
<!--   <link rel="shortcut icon" href="img/favicon.ico"> -->
  
  <!-- Font -->
  <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic'>

  <!-- Plagins CSS -->
  <link rel="stylesheet" href="theme/css/bootstrap.css">
	<link rel="stylesheet" href="theme/css/font-awesome.min.css">
  <link rel="stylesheet" href="theme/css/jslider.css">
  <link rel="stylesheet" href="theme/css/settings.css">
  <link rel="stylesheet" href="theme/css/jquery.fancybox.css">
  <link rel="stylesheet" href="theme/css/animate.css">
  <link rel="stylesheet" href="theme/css/video-js.min.css">
  <link rel="stylesheet" href="theme/css/morris.css">
  <link rel="stylesheet" href="theme/css/royalslider/royalslider.css">
  <link rel="stylesheet" href="theme/css/royalslider/skins/minimal-white/rs-minimal-white.css">
  <link rel="stylesheet" href="theme/css/layerslider/layerslider.css">
  <link rel="stylesheet" href="theme/css/ladda.min.css">
  <link rel="stylesheet" href="theme/css/datepicker.css">
  <link rel="stylesheet" href="theme/css/jquery.scrollbar.css">
  
  <!-- Theme CSS -->
  <link rel="stylesheet" href="theme/css/style.css">
  
  <!-- Custom CSS -->
  <link rel="stylesheet" href="theme/css/customizer/pages.css">

  <!-- IE Styles-->
<!--   <link rel='stylesheet' href="css/ie/ie.css"> -->
  
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<link rel='stylesheet' href="css/ie/ie8.css">
  <![endif]-->
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

<!--[if lte IE 8]>
  <script src="theme/js/jquery-1.9.1.min.js"></script>
<![endif]-->
<script src="theme/js/bootstrap.min.js"></script>
<script src="theme/js/price-regulator/jshashtable-2.1_src.js"></script>
<script src="theme/js/price-regulator/jquery.numberformatter-1.2.3.js"></script>
<script src="theme/js/price-regulator/tmpl.js"></script>
<script src="theme/js/price-regulator/jquery.dependClass-0.1.js"></script>
<script src="theme/js/price-regulator/draggable-0.1.js"></script>
<script src="theme/js/price-regulator/jquery.slider.js"></script>
<script src="theme/js/jquery.carouFredSel-6.2.1-packed.js"></script>
<script src="theme/js/jquery.touchwipe.min.js"></script>
<script src="theme/js/jquery.elevateZoom-3.0.8.min.js"></script>
<script src="theme/js/jquery.imagesloaded.min.js"></script>
<script src="theme/js/jquery.appear.js"></script>
<script src="theme/js/jquery.sparkline.min.js"></script>
<script src="theme/js/jquery.easypiechart.min.js"></script>
<script src="theme/js/jquery.easing.1.3.js"></script>
<script src="theme/js/jquery.fancybox.pack.js"></script>
<script src="theme/js/isotope.pkgd.min.js"></script>
<script src="theme/js/jquery.knob.js"></script>
<script src="theme/js/jquery.selectBox.min.js"></script>
<script src="theme/js/jquery.royalslider.min.js"></script>
<script src="theme/js/jquery.tubular.1.0.js"></script>
<script src="theme/js/SmoothScroll.js"></script>
<script src="theme/js/country.js"></script>
<script src="theme/js/spin.min.js"></script>
<script src="theme/js/ladda.min.js"></script>
<script src="theme/js/masonry.pkgd.min.js"></script>
<script src="theme/js/morris.min.js"></script>
<script src="theme/js/raphael.min.js"></script>
<script src="theme/js/video.js"></script>
<script src="theme/js/pixastic.custom.js"></script>
<script src="theme/js/livicons-1.4.min.js"></script>
<script src="theme/js/layerslider/greensock.js"></script>
<script src="theme/js/layerslider/layerslider.transitions.js"></script>
<script src="theme/js/layerslider/layerslider.kreaturamedia.jquery.js"></script>
<script src="theme/js/revolution/jquery.themepunch.tools.min.js"></script>
<script src="theme/js/revolution/jquery.themepunch.revolution.min.js"></script>
<script src="theme/js/bootstrapValidator.min.js"></script>
<script src="theme/js/bootstrap-datepicker.js"></script>
<script src="theme/js/jplayer/jquery.jplayer.min.js"></script>
<script src="theme/js/jplayer/jplayer.playlist.min.js"></script>
<script src="theme/js/jquery.scrollbar.min.js"></script>
<script src="theme/js/main.js"></script>

</body>
</html>