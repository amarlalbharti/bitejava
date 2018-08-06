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
      <h1 class="title">Contacts Us</h1>
    </div>	
  </header>
  <div class="container">
    <div class="row">
      <div class="content col-sm-12 col-md-12">
		<div class="row">
		  <div class="col-sm-6 col-md-6 contact-info bottom-padding">
			<address>
			  <div class="title">Address</div>
			  Ram Vihar Colony Para Crossing Rajajipuram<br> Lucknow, India
			</address>
			<div class="row">
			  <address class="col-sm-6 col-md-6">
				<div class="title">Phones</div>
				<div><a href="tel:917007614898">+91 7007 614 898</a></div>
				<div><a href="tel:919198530154">+91 9198 530 154</a></div>
			  </address>
			  <address class="col-sm-6 col-md-6">
				<div class="title">Email Addresses</div>
				<div>Info :<a href="mailto:support@example.com">info@bitejava.com</a></div>
				<div>Support: <a href="mailto:manager@example.com">support@bitejava.com</a></div>
			  </address>
			</div>
			
		  </div>
		  <div class="col-sm-6 col-md-6 bottom-padding">
			<form id="contactform" class="form-box register-form contact-form" method="POST">
			  <h3 class="title">Quick Contact</h3>
			  <div id="success"></div>
			  <label>Name: <span class="required">*</span></label>
			  <input class="form-control" type="text" name="name">
			  <label>Email Address: <span class="required">*</span></label>
			  <input class="form-control" type="email" name="email">
			  <label>Mobile:</label>
			  <input class="form-control" type="text" name="phone">
			  <label>Comment:</label>
			  <textarea class="form-control" name="comment"></textarea>
			  <div class="clearfix"></div>
			  <div class="buttons-box clearfix">
				<button id="submit" class="btn btn-default">Submit</button>
				<span class="required"><b>*</b> Required Field</span>
			  </div><!-- .buttons-box -->
			</form>
		  </div>
		  
		</div>
      </div>
    </div>
  </div><!-- .container -->
</section><!-- #main -->
</body>
</html>