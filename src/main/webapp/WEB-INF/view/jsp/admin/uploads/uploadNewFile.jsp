<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<section id="main">
  <article class="content">
	<div class="container">
	  <div class="row">
		<div class="col-sm-12 col-md-12">
		  <div class="panel panel-info">
			  <div class="panel-heading">
			  	<span>Upload New File</span>
			  </div>
			  <div class="panel-body form_subject">
				<form:form action="adminUploadFile"  role="form" commandName="uploadForm" method="POST" onsubmit="return validateUploadForm()" enctype="multipart/form-data" >
					  <div class="col-md-6">
						  <div class="form-group col-md-12">
							<label class="control-label ">File Name</label>
							<form:input path = "fileName" class="form-control " />
							<span class="text-danger"><form:errors path="fileName"></form:errors></span>
						  </div>
						  <div class="form-group  col-sm-12 col-md-12">
	 						<label class="btn btn-primary btn-flat">
			                  <input type="file" id="doc_file" name="file">
			                  <i class="fa fa-fw fa-cloud-upload"></i> Browse file from Desktop
	                        </label>
						  </div>
					  </div>
					  <div class="col-md-6 gallery">
					  	<div class="images-box col-md-12  pull-right" style="max-width: 200px; max-height: 150px;">
							<a class="gallery-images text-center frame-shadow-raised parent_blah" rel="fancybox" href="theme/images/no-preview-large.jpg">
							  <img class="replace-2x" id="blah" src="theme/images/no-preview-large.jpg" alt="" style="max-height: 140px;">
							  <span class="bg-images text-left"><i class="fa fa-search"></i></span>
							</a>
						  </div><!-- .images-box -->
					  </div>
					  <div class="clearfix"></div>
					  <div class="col-sm-12 col-md-12">
						  <div class="form-group  col-sm-12 col-md-12">
						  	<label class="control-label ">File Detail</label>
						  	<form:textarea path = "fileDetail" class="form-control " />
							<span class="text-danger"><form:errors path="fileDetail"></form:errors></span>
						  </div>
					  </div>
					  <div class="col-sm-12 col-md-12">
						  <div class="form-group  col-sm-12 col-md-12">
						  	<button class="btn btn-default btn-flat" type="submit"><i class="fa fa-upload"></i> Upload</button>
						  </div>
					  </div>
				</form:form>
			  </div>
			</div>
		</div>
	  </div>
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->
<script type="text/javascript">
$("#doc_file").change(function(){
    readURL(this);
});
function readURL(input) {
// 	alert("hi")
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
            $('.parent_blah').attr('href', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function validateSubjectForm()
{
	var subject = $(".form_subject #subject").val();
	var url = $(".form_subject #url").val();
	var displayOrder = $(".form_subject #displayOrder").val();
	
	$(".has-error").removeClass("has-error");
	
	var valid = true;
	if(subject == "")
	{
		$(".form_subject #subject").parent().addClass("has-error");
		valid = false;
	}
	if(url == "")
	{
		$(".form_subject #url").parent().addClass("has-error");
		valid = false;
	}
	if(displayOrder == "")
	{
		$(".form_subject #displayOrder").parent().addClass("has-error");
		valid = false;
	}
	
	if(!valid)
	{
// 		return false;
	}
	
	
}

</script>		
		
		  
</body>
</html>