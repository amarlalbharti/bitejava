<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
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
		<div class="col-sm-12 col-md-12">
		  <div>
				<div class="col-xs-6 col-sm-6 col-md-4">
					<select id="sid" class="form-control input-sm">
						<option value="0">Select Subject</option>
						<%
							String sid = (String)request.getAttribute("sid");
							List<Subject> sList = (List)request.getAttribute("sList");
							if(sList != null && !sList.isEmpty())
							{
								for(Subject sub : sList)
								{
									if(sid!= null && sid.equals(String.valueOf(sub.getSid())))
									{
										%>
											<option value="<%= sub.getSid()%>" selected="selected"><%= sub.getSubject()%></option>
										<%
									}
									else
									{
										%>
											<option value="<%= sub.getSid()%>" ><%= sub.getSubject()%></option>
										<%
									}
										
								}
							}
						%>
					</select>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-8">
				<%
					if(sid != null && sid.trim().length() > 0)
					{
						%>
							<div class="pull-right"><a href="adminAddKeynote?sid=${sid} " class="btn btn-sm btn-flat btn-default add_keynote"><i class="fa fa-plus"></i> Keynote</a></div>
						<%
					}
					else
					{
						%>
						<div class="pull-right"><a href="javascript:void(0);" class="btn btn-sm btn-flat btn-default add_keynote" disabled="disabled" ><i class="fa fa-plus"></i> Keynote</a></div>
						<%
					}
				%>
		  </div>
		  <div class="clearfix"></div>
		  <div class="kn_view col-md-12">
			  <div class="panel panel-info">
				  <div class="panel-heading"><span>List of KeyNotes</span>
				  
				  </div>
				  <table class="table table-bordered table-responsive table-condensed">
					<thead>
					  <tr>
						<th>#</th>
						<th class="text-left">KeyNote</th>
						<th class="text-left">URL</th>
						<th class="text-left">Subject</th>
						<th>Display Order</th>
						<th>Published</th>
						<th>Create</th>
						<th>Action</th>
					  </tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8" class="text-left">Select subject first !</td>
						</tr>
					</tbody>
				  </table>
				</div>
		  </div>
		</div>
	  </div>
	  
	  
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->


<script type="text/javascript">
jQuery(document).ready(function() {
	getKeynoteList(1);
});

</script>
</body>
</html>