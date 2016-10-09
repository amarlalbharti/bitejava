<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>

	<meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script type="text/javascript" src="${path_url}/theme/js/uploadfile_js.js"></script>
</head>
<body class="fixed-header">

<section id="main">
  <article class="content">
	<div class="container">

	  <div class="row my_uploads">
	  	<div class="col-sm-12 col-md-12" style="margin-bottom: 5px;">
	  		<div class="pull-right"><a href="adminUploadFile" class="btn btn-sm btn-flat btn-default"><i class="fa fa-plus"></i> Upload File</a></div>
	  	</div>
	  	<div class="clearfix"></div>
		<div class="col-sm-12 col-md-12">
		  <div class="file_view">
			  
		  </div>
		</div>
	  </div>
	  
	  
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->


<script type="text/javascript">
jQuery(document).ready(function() {
	getUploadFileList(1);
});
</script>
</body>
</html>