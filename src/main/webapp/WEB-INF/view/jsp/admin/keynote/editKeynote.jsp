<!doctype html>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
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
						<form:form action="adminEditKeynote"  role="form" commandName="form_keynote" method="POST">
							  <div class="form-group col-sm-6 col-md-6 no-padding">
								<label class="control-label">Select Subject</label>
								<form:select path = "subject.sid" id="sid" class="form-control" >
									<form:option value="0">Select Subject</form:option>
									<c:forEach var="itam" items="${sList}">
										<form:option value='${itam.sid }'>${itam.subject}</form:option>
									</c:forEach>
								</form:select>
								<span class="text-danger"><form:errors path="subject"></form:errors></span>
							  </div>
							  <div class="form-group col-sm-6 col-md-6">
								<label class="control-label">Select Parent Keynote</label>
								<form:select path = "parent.kid" id="parent_kid" class="form-control" >
									<form:option value="0">Select Parent Keynote</form:option>
									<c:forEach var="itam" items="${kList}">
										<form:option value='${itam.kid }'>${itam.keynote}</form:option>
									</c:forEach>
								</form:select>
								<span class="text-danger"><form:errors path="parent"></form:errors></span>
							  </div>
							  <div class="form-group">
								<label class="control-label">KeyNote</label>
								<form:hidden path="kid" />
								<form:input path="keynote" class="form-control" />
								<span class="text-danger"><form:errors path="keynote"></form:errors></span>
							  </div>
							  <div class="form-group  col-sm-6 col-md-6 no-padding">
								<label for="displayOrder" class="control-label">Show On Home Page</label>
								<form:select path = "showOnHomePage" type="number" class="form-control number_only" >
									<form:option value="0">No</form:option>
									<form:option value="1">Yes</form:option>
								</form:select>
								<span class="text-danger"><form:errors path="displayOrder"></form:errors></span>
							  </div>
							  <div class="form-group col-sm-6 col-md-6 no-padding" style="padding-left: 15px!important;">
								<label class="control-label">Display Order</label>
								<form:input type="number" path = "displayOrder" class="form-control number_only" />
								<span class="text-danger"><form:errors path="displayOrder"></form:errors></span>
							  </div>
							  <div class="form-group">
								<label class="control-label">SEO Description</label>
								<form:textarea  path = "seoDescription" id="kn_seoDescription" class="form-control" />
								<span class="text-danger"><form:errors path="seoDescription"></form:errors></span>
							  </div>
							  <div class="form-group">
								<label class="control-label">Key Note Detail</label>
								<form:textarea  path = "knDetail" id="kn_detail" class="form-control" />
								<span class="text-danger"><form:errors path="knDetail"></form:errors></span>
							  </div>
							  <div class="form-group">
							  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Save">
							  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Save And Publish">
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
// 		return false;
	}
	
}


</script>
</body>
</html>


