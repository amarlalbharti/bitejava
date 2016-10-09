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
			  	<span>Add New Subject</span>
			  </div>
			  <div class="panel-body form_subject">
				<form:form action="adminEditSubject"  role="form" commandName="form_subject" method="POST" onsubmit="return validateSubjectForm()" enctype="multipart/form-data" >
					  <div class="form-group col-md-12">
						<label class="control-label ">Subject</label>
						<form:hidden path="sid"/>
						<form:input path = "subject" class="form-control " />
						<span class="text-danger"><form:errors path="subject"></form:errors></span>
					  </div>
					  <div class="form-group  col-sm-6 col-md-6">
						<label class="control-label">Subject URL</label>
						<form:input path = "url" class="form-control " />
						<span class="text-danger"><form:errors path="url"></form:errors></span>
					  </div>
					  <div class="form-group  col-sm-6 col-md-6">
						<label for="displayOrder" class="control-label">Display Order</label>
						<form:input path = "displayOrder" type="number" class="form-control number_only" />
						<span class="text-danger"><form:errors path="displayOrder"></form:errors></span>
					  </div>
					  <div class="form-group  col-sm-6 col-md-6">
						<label for="displayOrder" class="control-label">Show On Home Page</label>
						<form:select path = "showOnHomePage" type="number" class="form-control number_only" >
							<form:option value="0">No</form:option>
							<form:option value="1">Yes</form:option>
						</form:select>
						<span class="text-danger"><form:errors path="displayOrder"></form:errors></span>
					  </div>
					  <div class="form-group  col-sm-6 col-md-6">
 						<label class="control-label">&nbsp;</label><br>
					  	<label class="btn btn-primary btn-flat">
		                  <input type="file" id="doc_file" name="subject_image">
		                  <i class="fa fa-fw fa-cloud-upload"></i> Browse image for subject
                        </label>
					  </div>
					  <div class="form-group  col-sm-12 col-md-12">
					  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Save">
					  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Save And Publish">
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
		return false;
	}
	
	
}

jQuery(document).ready(function() {
	$(document.body).on('keyup', '.form_subject #subject' ,function(){
		var url =  $(this).val();
		url = url.replace(/\s/g,"_");
		$(".form_subject #url").val(url.toLowerCase());
	});
});
	
</script>		
		
		  
</body>
</html>