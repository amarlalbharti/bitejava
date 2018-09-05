<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<html>
<head>
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
			<p><strong>Bite Java Tutorials</strong> Provides the free quality education materials. The content on Bite Java Tutorials is collected from the experts of various domains. </p>
			<blockquote class="no-margin">
			  <p>This website is an example of our endless effort we have spent on to develop good content. We want to remove this  financial barrier to good education.</p>
			</blockquote>
		  </div>
		</div>
		
		<div class="clearfix"></div>
	  </div>
	</div>
	<div class="row">
		  <div class="bottom-padding col-sm-6 col-md-6">
			<div class="title-box">
			  <h2 class="title">Our Vision</h2>
			</div>
			
			<div class="panel-group one-open" id="accordion">
			  <div class="panel panel-default active">
				<div class="panel-heading">
				  <div class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
					  Who We Are
					</a>
				  </div>
				</div>
				<div id="collapse1" class="panel-collapse collapse in">
				  <div class="panel-body">
					We are the collections of Utility classes in different technologies to provide the better solutions, reusability, faster development of your knowledge.
				  </div>
				</div>
			  </div>
			  
			  <div class="panel panel-default">
				<div class="panel-heading">
				  <div class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
					  What We Want
					</a>
				  </div>
				</div>
				<div id="collapse2" class="panel-collapse collapse">
				  <div class="panel-body">
					We want to be inherited to improve your skills. 
				  </div>
				</div>
			  </div>
			  
			  <div class="panel panel-default">
				<div class="panel-heading">
				  <div class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
					  What We Can Give
					</a>
				  </div>
				</div>
				<div id="collapse3" class="panel-collapse collapse">
				  <div class="panel-body">
					We can give way to prepare for your better future. We can only provide route for you destinations but you have to walk to reach to your destination.
				  </div>
				</div>
			  </div>
			</div>
		  </div>
		  <div class="bottom-padding col-sm-6 col-md-6">
			<div class="title-box">
			  <h2 class="title">Our Offer</h2>
			</div>
			
			<p>We also offers an opportunity to share your knowledge. If your expert in any domain you can share your domain knowledge to our learners. If you have any request/feedback regarding any article you can comment in that article. You can also get the notifications on new articles in future by subscribing us. </p>
			<button class="btn btn-block">Dont Wait - Join Us Now</button>
		  </div>
		</div>
  </div>
  
</section><!-- #main -->

</body>
</html>