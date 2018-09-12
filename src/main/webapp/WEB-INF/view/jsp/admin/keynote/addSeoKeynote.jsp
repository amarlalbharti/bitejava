<!doctype html>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script type="text/javascript" src="theme/js/keynote_js.js"></script>
</head>
<body class="fixed-header">

<section id="main">
  <article class="content">
	<div class="container">
	  <div class="row my_keynotes">
		<div class="col-sm-12 col-md-12 bottom-padding">
		  <div class="kn_add">
				<%
					Keynote keynote = (Keynote)request.getAttribute("keynote");
					List<Subject> sList = (List)request.getAttribute("sList");
					%>
					<div class="panel panel-info">
					  <div class="panel-heading">
							<span>Add New KeyNote</span>
							<c:forEach var="itam" items="${sList}">
								<c:if test="${itam.url == sub}">
								  	<div class="pull-right"><a href="adminKeynotes?sid=${itam.sid}" class="btn btn-xs btn-danger back_keynote"><i class="fa fa-reply"></i> Back</a></div>
								</c:if>
							</c:forEach>
					  </div>
					  <div class="panel-body">
						<form:form action="adminAddSeoKeynote"  role="form" commandName="seoModel" method="POST" onsubmit="return validateForm()">
							  <div class="form-group">
								<label class="control-label">SEO Title</label>
								<form:hidden path="keynote.kid"/>
								<form:input path="title" id="seo_title" class="form-control" />
								<span class="text-danger"><form:errors path="title"></form:errors></span>
							  </div>
							  <div class="form-group">
								<label class="control-label">SEO Description</label>
								<form:textarea  path = "description" id="seo_description" class="form-control" />
								<span class="text-danger"><form:errors path="description"></form:errors></span>
							  </div>
							  <div class="form-group">
								<label class="control-label">SEO Keywords</label>
								<form:textarea  path = "keywords" id="seo_keywords" class="form-control" />
								<span class="text-danger"><form:errors path="keywords"></form:errors></span>
							  </div>
							  <div class="form-group">
								<label class="control-label">SEO Image URL</label>
								<form:input path="imageUrl" id="seo_imageUrl" class="form-control" />
								<span class="text-danger"><form:errors path="imageUrl"></form:errors></span>
							  </div>
							  <div class="form-group">
							  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Save">
							  </div>
							  
						</form:form>
					  </div>
					</div>
			</div>
		</div>
	  </div>
	  
	  
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->
<script src="theme/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="theme/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
CKEDITOR.replace( 'kn_detail');
</script>
<script type="text/javascript">
function validateForm()
{
	var sid = $("#sid").val();
	var keynote = $("#keynote").val();
	var displayOrder = $("#displayOrder").val();
	var knDetail = CKEDITOR.instances['kn_detail'].getData();
// 	alert("data : " +sid+", " +keynote +", "+knDetail);
	var valid = true;
	if(sid == "0")
	{
		valid = false;
		$("#sid").parent().addClass("has-error");
	}
	if(keynote == "")
	{
		valid = false;
		$("#keynote").parent().addClass("has-error");
	}
	if(displayOrder == "")
	{
		valid = false;
		$("#displayOrder").parent().addClass("has-error");
	}
	if(knDetail == "")
	{
		valid = false;
		$("#knDetail").parent().addClass("has-error");
	}
	if(!valid)
	{
		return false;
	}
	
}


</script>
</body>
</html>


