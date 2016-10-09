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
<body>
	<div class="panel panel-info">
	  <%
	  	Keynote parent_kn = (Keynote)request.getAttribute("parent_kn");
	  	if(parent_kn != null)
	  	{
	  		%>
			  <div class="panel-heading"><span>List of KeyNotes : <%= parent_kn.getKeynote() %></span>
				  <div class="pull-right"><a class="btn btn-xs btn-flat btn-danger" href="javascript:getKeynoteList(1);">
				  	<i class="fa fa-mail-reply"></i> Back</a>
				  </div>
			  </div>
	  		<%
	  	}
	  	else
	  	{
	  		%>
			  <div class="panel-heading"><span>List of KeyNotes</span></div>
	  		<%
	  	}
	  
	  %>
	  
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
				List<Keynote> knList = (List)request.getAttribute("knList");
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
										<td class="text-left" title="Click to view child keynotes"><a href="javascript:getChildKeynoteList(1, <%= kn.getKid()%>)"><%= kn.getKeynote() %></a></td>
									<%
								}
							%>
							<td class="text-left"><%= kn.getUrl() %></td>
							<td class="text-left"><%= kn.getSubject().getSubject() %></td>
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
								<%
									if(kn.getPublishDate() != null)
									{
										%>
											<a class="unpublish_keynote" href="javascript:void(0);" title="Click to Un-Publish">
												<img alt="Un-Pub" src="theme/images/NoSymbol.png" width="20px">
											</a>
										<%
									}
									else
									{
										%>
											<a class="publish_keynote" href="javascript:void(0);"  title="Click to Publish">
												<img alt="Pub" src="theme/images/Publish.png" width="20px">
											</a>
										<%
									}
									if(kn.getDeleteDate() == null)
									{
										%>
											<a href="javascript:void(0);" class="delete_keynote" title="Click here to delete this subject."><img alt="Delete" src="theme/images/trash.png" width="20px;"></a>
										<%
									}
								%>
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
</body>
</html>