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
			<div class="panel panel-info">
			  <div class="panel-heading">
					<span>Update Question</span>
			  </div>
			  <div class="panel-body">
				<form:form action="adminEditQuestion"  role="form" commandName="queForm" method="POST" onsubmit="return validateForm()">
					  <div class="form-group">
						<label class="control-label">Question</label>
						<form:hidden path="qid" />
						<form:textarea path="question" class="form-control" />
						<span class="text-danger"><form:errors path="question"></form:errors></span>
					  </div>
					  <div class="form-group">
						<label class="control-label">Add Related Tags </label>
						<form:select path="tags" id="tid" class="form-control" >
							<c:forEach items="${tags}" var="item">
								<form:option value="${item}" selected="selected">${item}</form:option>
							</c:forEach>
						</form:select>
						<span class="text-danger"><form:errors path="tags"></form:errors></span>
					  </div>
					  <div class="form-group">
						<label class="control-label">SEO Image</label>
						<form:input path="image" class="form-control" />
						<span class="text-danger"><form:errors path="image"></form:errors></span>
					  </div>
					  <div class="form-group">
						<label class="control-label">Answer</label>
						<form:textarea path="answer" class="form-control" />
						<span class="text-danger"><form:errors path="answer"></form:errors></span>
					  </div>
					  <div class="form-group">
					  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Save">
					  	<input class="btn btn-default btn-flat" type="submit"  name="submit" value="Update And Publish">
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
<script src="theme/js/select2.full.min.js"></script>
<script type="text/javascript">
$(function () {
	$(function () {
	    $("#tid").select2({
	    	minimumInputLength: 2,
	    	multiple: true,
	    	tags: true,
	        minimumResultsForSearch: 10,
	        ajax: {
	            url: 'searchTags',
	            dataType: 'json',
	            type: "GET",
	            quietMillis: 50,
	            data: function (term) {
	                return {
	                    q: term.term
	                };
	            },
	            processResults: function (data) {
	                return {
	                    results: $.map(data, function (item) {
	                        return {
	                            text: item.tag,
	                            id: item.tag
	                        }
	                    })
	                };
	            }
	        }
	    });
	});
});
    
</script>
<script type="text/javascript" src="theme/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
CKEDITOR.replace( 'answer');
</script>
</body>
</html>


