<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="${pageContext.request.contextPath}/theme/ckeditor/plugins/codesnippet/lib/highlight/styles/monokai_sublime.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/theme/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
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
</head>
<body>
<section id="main">
  <header class="page-header">
    <div class="container">
      <h1 class="title">About Us</h1>
    </div>	
  </header>
  <div class="container">
    <div class="row">
      <div class="content col-sm-12 col-md-12">
		<div class="row">
		  <div class="col-sm-6 col-md-6 bottom-padding">
			<div class="layer-slider">
			  <img class="" src="theme/images/About-Us.jpg" width="798" height="532" alt="">
		  </div>
		  </div>
		  
		  <div class="col-sm-6 col-md-6 bottom-padding">
			<div class="title-box">
			  <h2 class="title" style="padding: 0;">Intelligent & creative</h2>
			</div>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam in minima iusto voluptatem aliquam odit odio. Aliquam voluptatibus beatae officiis?</p>
			<blockquote class="no-margin">
			  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quis distinctio alias deserunt vel itaque pariatur debitis natus provident eum quos aliquid voluptates quia eligendi nemo illum! Ratione quisquam perferendis dolor minima modi necessitatibus repellat. Ipsam cum natus quia minus deserunt!</p>
			  <footer>Lorem ipsum dolor sit amet, consectetur adipisicing.</footer>
			</blockquote>
		  </div>
		</div>
		
		<div class="row">
		  <div class="clearfix"></div>
		  <div class="bottom-padding col-sm-6 col-md-6">
			<div class="title-box">
			  <h2 class="title" style="padding-top: 0;">Services we offer</h2>
			</div>
			
			<div class="tabs">
			  <ul class="nav nav-tabs">
				<li class="active">
				  <a href="#services" data-toggle="tab">Graphic design</a>
				</li>
				<li>
				  <a href="#features" data-toggle="tab">Web development</a>
				</li>
				<li>
				  <a href="#text" data-toggle="tab">Web design</a>
				</li>
			  </ul>
			  <div class="tab-content">
				<div class="tab-pane active fade in" id="services">
				  <img class="replace-2x alignleft" src="theme/images/partner-3.jpg" width="200" height="200" alt="">
				  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsam quidem voluptatem accusantium praesentium inventore quae illum aliquid dolores totam perspiciatis. Officia illum eos quos voluptate omnis deleniti molestiae numquam fugiat delectus aliquam ab cupiditate dicta commodi</p>
				</div>
				
				<div class="tab-pane fade in" id="features">
				  <img class="replace-2x alignright" src="theme/images/partner-2.jpg" width="200" height="200" alt="">
				  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsam quidem voluptatem accusantium praesentium inventore quae illum aliquid dolores totam perspiciatis. Officia illum eos quos voluptate omnis deleniti molestiae numquam fugiat delectus aliquam ab cupiditate dicta commodi</p>
				</div>
				
				<div class="tab-pane fade in" id="text">
				  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo quaerat praesentium odio vero necessitatibus vel fuga minima cum provident veniam ducimus perferendis non voluptates qui aperiam quisquam magni minus eligendi eos rerum doloremque mollitia ex at molestias eius veritatis placeat eaque totam culpa. Atque reprehenderit voluptatum tenetur maiores vel blanditiis?</p>
				</div>
			  </div>
			</div>
		  </div>
		  
		  <div class="bottom-padding col-sm-6 col-md-6">
			<div class="title-box">
			  <h2 class="title">Our Skills</h2>
			</div>
			<div class="progress progress-striped active" data-appear-progress-animation="80%">
			  <div class="progress-bar progress-bar-warning">Web Design 80%</div>
			</div>
			
			<div class="progress progress-striped active" data-appear-progress-animation="70%">
			  <div class="progress-bar progress-bar-danger">HTML/CSS 70%</div>
			</div>
			
			<div class="progress progress-striped active" data-appear-progress-animation="40%">
			  <div class="progress-bar progress-bar-success">Opencart 40%</div>
			</div>

			<div class="progress progress-striped active" data-appear-progress-animation="58%">
			  <div class="progress-bar progress-bar-info">WordPress 58%</div>
			</div>
			
			<div class="progress progress-striped active no-margin" data-appear-progress-animation="60%">
			  <div class="progress-bar">Magento 60%</div>
			</div>
		  </div>
		</div>
		  
		<div class="clearfix"></div>
	  </div>
	</div>
  </div>
  
  <div class="full-width-box bottom-padding cm-padding-bottom-36">
	<div class="fwb-bg fwb-blur" data-blur-image="theme/images/band-5.jpg" data-blur-amount="3"><div class="overlay"></div></div>
	
	<div class="container">
	  <div class="row text-center">
		<div class="col-sm-3 col-md-3 rotation employee">
		  <div class="default">
			<div class="image">
			  <img class="replace-2x" src="theme/images/amar1.jpg" alt="" title="" width="270" height="270">
			</div>
			<div class="description">
			  <div class="vertical">
				<h3 class="name">Mett Rayan</h3>
				<div class="role">Manager</div>	
			  </div>
			</div>
		  </div>
		  <div class="employee-hover">
			<h3 class="name">Mett Rayan</h3>
			<div class="role">Manager</div>
			<div class="image">
			  <img class="replace-2x" src="theme/images/amar1.jpg" alt="" title="" width="270" height="270">
			</div>
			<div>
			  <p>Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet.</p>
			  <div class="contact"><b>Email: </b>mett@itembridge.com</div>
			  <div class="contact"><b>Phone: </b>11 555 333 77</div>
			</div>
			<div class="social">
			  <a class="icon rounded" href="#"><span class="icon-facebook"><i class="fa fa-facebook"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-twitter"><i class="fa fa-twitter"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-google"><i class="fa fa-google"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-linkedin"><i class="fa fa-linkedin"></i></span></a>
			</div>
		  </div><!-- .employee-hover -->
		</div><!-- .employee 
		
		--><div class="col-sm-3 col-md-3 rotation employee">
		  <div class="default">
			<div class="image">
			  <img class="replace-2x" src="theme/images/partner-3.jpg" alt="" title="" width="270" height="270">
			</div>
			<div class="description">
			  <div class="vertical">
				<h3 class="name">Jon O. Example</h3>
				<div class="role">Web designer</div>	
			  </div>
			</div>
		  </div>
		  <div class="employee-hover">
			<h3 class="name">Jon O. Example</h3>
			<div class="role">Web designer</div>
			<div class="image">
			  <img class="replace-2x" src="theme/images/partner-3.jpg" alt="" title="" width="270" height="270">
			</div>
			<div>
			  <p>Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet.</p>
			  <div class="contact"><b>Email: </b>mett@itembridge.com</div>
			  <div class="contact"><b>Phone: </b>11 555 333 77</div>
			</div>
			<div class="social">
			  <a class="icon rounded" href="#"><span class="icon-facebook"><i class="fa fa-facebook"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-twitter"><i class="fa fa-twitter"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-google"><i class="fa fa-google"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-linkedin"><i class="fa fa-linkedin"></i></span></a>
			</div>
		  </div><!-- .employee-hover -->
		</div><!-- .employee
		
		--><div class="col-sm-3 col-md-3 rotation employee">
		  <div class="default">
			<div class="image">
			  <img class="replace-2x" src="theme/images/partner-2.jpg" alt="" title="" width="270" height="270">
			</div>
			<div class="description">
			  <div class="vertical">
				<h3 class="name">Turanga Leela</h3>
				<div class="role">Graphic designer</div>	
			  </div>
			</div>
		  </div>
		  <div class="employee-hover">
			<h3 class="name">Turanga Leela</h3>
			<div class="role">Graphic designer</div>
			<div class="image">
			  <img class="replace-2x" src="theme/images/partner-2.jpg" alt="" title="" width="270" height="270">
			</div>
			<div>
			  <p>Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet.</p>
			  <div class="contact"><b>Email: </b>mett@itembridge.com</div>
			  <div class="contact"><b>Phone: </b>11 555 333 77</div>
			</div>
			<div class="social">
			  <a class="icon rounded" href="#"><span class="icon-facebook"><i class="fa fa-facebook"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-twitter"><i class="fa fa-twitter"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-google"><i class="fa fa-google"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-linkedin"><i class="fa fa-linkedin"></i></span></a>
			</div>
		  </div><!-- .employee-hover -->
		</div><!-- .employee
		
		--><div class="col-sm-3 col-md-3 rotation employee">
		  <div class="default">
			<div class="image">
			  <img class="replace-2x" src="theme/images/partner-1.jpg" alt="" title="" width="270" height="270">
			</div>
			<div class="description">
			  <div class="vertical">
				<h3 class="name">David X. Cohen</h3>
				<div class="role">Developer</div>	
			  </div>
			</div>
		  </div>
		  <div class="employee-hover">
			<h3 class="name">David X. Cohen</h3>
			<div class="role">Developer</div>
			<div class="image">
			  <img class="replace-2x" src="theme/images/partner-1.jpg" alt="" title="" width="270" height="270">
			</div>
			<div>
			  <p>Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet.</p>
			  <div class="contact"><b>Email: </b>mett@itembridge.com</div>
			  <div class="contact"><b>Phone: </b>11 555 333 77</div>
			</div>
			<div class="social">
			  <a class="icon rounded" href="#"><span class="icon-facebook"><i class="fa fa-facebook"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-twitter"><i class="fa fa-twitter"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-google"><i class="fa fa-google"></i></span></a>
			  <a class="icon rounded" href="#"><span class="icon-linkedin"><i class="fa fa-linkedin"></i></span></a>
			</div>
		  </div><!-- .employee-hover -->
		</div><!-- .employee -->
	  </div>
	</div>
  </div><!-- .full-width-box -->

</section><!-- #main -->

</body>
</html>