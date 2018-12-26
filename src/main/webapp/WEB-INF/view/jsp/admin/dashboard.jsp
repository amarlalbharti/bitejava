<!doctype html>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="com.bharti.constraints.DateFormats"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

</head>
<body class="fixed-header">
<section id="main">
  <article class="content">
	<div class="container">
	  <div class="row">
		<div class="col-sm-12 col-md-12">
		  <div class="panel panel-info">
			  <div class="panel-heading"><span>Recent added Subjects</span>
			  <div class="pull-right"><a href="adminSubjects" class="btn btn-xs btn-flat btn-danger"><i class="fa fa-search-plus"></i> View All</a></div>
			  </div>
			  <table class="table table-bordered table-responsive table-condensed">
				<thead>
				  <tr>
					<th>#</th>
					<th class="text-left">Subject</th>
					<th class="text-left">URL</th>
<!-- 					<th >Display Order</th> -->
					<th title="Show on home page">SOHP</th>
					<th >Published</th>
					<th >Create</th>
					<th >Action</th>
				  </tr>
				</thead>
				<tbody>
					<%
						List<Subject> sList = (List)request.getAttribute("rsList");
						if(sList != null && !sList.isEmpty())
						{
							int i = 1;
							for(Subject subject : sList)
							{
								%>
								  <tr sid="<%= subject.getSid()%>">
									<td><%= i++ %></td>
									<td class="text-left"><a href="adminKeynotes?sid=<%=subject.getSid() %>"><%= subject.getSubject() %></a></td>
									<td class="text-left"><%= subject.getUrl() %></td>
<%-- 									<td><%= subject.getDisplayOrder() %></td> --%>
									<%
										if(subject.isShowOnHomePage())
										{
											%>
												<td>Yes</td>
											<%
										}
										else
										{
											%>
												<td>No</td>
											<%
										}
										if(subject.getPublishDate() != null)
										{
											%>
												<td ><%= DateFormats.ddMMMyyyy.format(subject.getPublishDate()) %></td>
											<%
										}
										else
										{
											%>
												<td >Not Published</td>
											<%
										}
									%>
									
									<td ><%= DateFormats.ddMMMyyyy.format(subject.getCreateDate()) %></td>
									<td >
										<a href="adminEditSubject?sid=<%= subject.getSid() %>&url=<%= subject.getUrl() %>" title="Click for Edit">
											<img alt="Un-Pub" src="theme/images/edit-icon.png" width="20px">
										</a>
									</td>
								  </tr>
								<%
							}
						}
						else
						{
							%>
								<tr>
									<td colspan="5">No Data in data source</td>
								</tr>
							<%
						}
					%>
				</tbody>
			  </table>
				
			</div>
		</div>
		<div class="col-md-12 col-sm-12 ">
			<div class="panel panel-info">
			  <div class="panel-heading">
			  	<span>Recently Added KeyNotes</span>
			  	<div class="pull-right"><a href="adminKeynotes" class="btn btn-xs btn-flat btn-danger"><i class="fa fa-search-plus"></i> View All</a></div>
			  </div>
			  <table class="table table-bordered table-responsive table-condensed">
				<thead>
				  <tr>
					<th>#</th>
					<th class="text-left">KeyNote</th>
					<th class="text-left">URL</th>
					<th class="text-left">Subject</th>
					<th >Display Order</th>
					<th title="Show on home page">SOHP</th>
					<th >Published</th>
					<th >Create</th>
					<th >Action</th>
				  </tr>
				</thead>
				<tbody>
					<%
						List<Keynote> knList = (List)request.getAttribute("rkList");
						if(knList != null && !knList.isEmpty())
						{
							int i = 1;
							for(Keynote kn : knList)
							{
								%>
								  <tr kid="<%= kn.getKid()%>">
									<td><%= i++ %></td>
									<%
										if(kn.getParent_keynote() != null)
										{
											%>
												<td class="text-left"><%= kn.getKeynote() %></td>
											<%
										}
										else
										{
											%>
												<td class="text-left" title="Click to view keynotes"><%= kn.getKeynote() %></td>
											<%
										}
									%>
									<td class="text-left"><%= kn.getUrl() %></td>
									<td class="text-left"><a href="adminKeynotes?sid=<%= kn.getSubject().getSid()%>"><%= kn.getSubject().getSubject() %></a></td>
									<td><%= kn.getDisplayOrder() %></td>
									<%
										if(kn.isShowOnHomePage())
										{
											%>
												<td>Yes</td>
											<%
										}
										else
										{
											%>
												<td>No</td>
											<%
										}
										if(kn.getPublishDate() != null)
										{
											%>
												<td ><%= DateFormats.ddMMMyyyy.format(kn.getPublishDate()) %></td>
											<%
										}
										else
										{
											%>
												<td >Not Published</td>
											<%
										}
									%>
									
									<td ><%= DateFormats.ddMMMyyyy.format(kn.getCreateDate()) %></td>
									<td >
										<a href="adminEditKeynote?kid=<%= kn.getKid()%>" title='Click for edit this keynote'>
											<img alt="" src="theme/images/edit-icon.png" width="20px;">
										</a>
										
										<a target="_blank" href="note/<%= kn.getSubject().getUrl()%>/<%=kn.getUrl() %>" title='Click for preview'>
											<img alt="" src="theme/images/preview-4.png" width="20px;">
										</a>
									</td>
								  </tr>
								<%
							}
						}
						else
						{
							%>
								<tr>
									<td class="text-left" colspan="8">No Data in data source</td>
								</tr>
							<%
						}
					%>
				</tbody>
			  </table>
			</div>
		</div>
	  </div>
	  
	  
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->



</body>
</html>
