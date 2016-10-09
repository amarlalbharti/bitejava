<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script type="text/javascript" src="${path_url}/theme/js/subject_js.js"></script>
</head>
<body class="fixed-header">

<section id="main">
  <article class="content">
	<div class="container">

	  <div class="row my_subjects">
		<div class="col-sm-12 col-md-12">
		  <!-- <div class="title-box">
			<h1 class="title">Bite Java</h1>
		  </div> -->
		  <div class="sub_view">
			  
		  </div>
			<div class="sub_add" style="display: none;">
				
			</div>
		</div>
	  </div>
	  
	  
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->


<script type="text/javascript">
jQuery(document).ready(function() {
	getSubjectList(1);
});
</script>
</body>
</html>