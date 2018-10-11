<!doctype html>
<%@page import="com.bharti.domain.Registration"%>
<%@page import="com.bharti.constraints.Roles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
<%
int pn = (Integer)request.getAttribute("pn");
int rpp = (Integer)request.getAttribute("rpp");
int total_count = (Integer)request.getAttribute("total_count");

%>
	<div class="panel panel-info">
	  <div class="panel-heading"><span>List of Subjects</span>
	  <div class="pull-right"><a href="adminAddSubject" class="btn btn-xs btn-flat btn-danger"><i class="fa fa-plus"></i> Subject</a></div>
	  </div>
	  <table class="table table-bordered table-responsive table-condensed">
		<thead>
		  <tr>
			<th>#</th>
			<th class="text-left">Subject</th>
			<th class="text-left">URL</th>
			<th >Display Order</th>
			<th title="Show on home page">SOHP</th>
			<th >Published</th>
			<th >Create</th>
			<th >Action</th>
		  </tr>
		</thead>
		<tbody>
			<%
				List<Subject> sList = (List)request.getAttribute("sList");
				int i = ((pn-1)*rpp)+1;
				if(sList != null && !sList.isEmpty())
				{
					for(Subject subject : sList)
					{
						%>
						  <tr sid="<%= subject.getSid()%>">
							<td><%= i++ %></td>
							<td class="text-left"><a href="adminKeynotes?sid=<%=subject.getSid() %>"><%= subject.getSubject() %></a></td>
							<td class="text-left"><%= subject.getUrl() %></td>
							<td><%= subject.getDisplayOrder() %></td>
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
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="adminEditSubject?sid=<%= subject.getSid() %>&url=<%= subject.getUrl() %>" title="Click for Edit">
										<img alt="Un-Pub" src="theme/images/edit-icon.png" width="20px">
									</a>
									<%
										if(subject.getPublishDate() != null)
										{
											%>
												<a class="unpublish_subject" href="javascript:void(0);" title="Click to Un-Publish">
													<img alt="Un-Pub" src="theme/images/NoSymbol.png" width="20px">
												</a>
											<%
										}
										else
										{
											%>
												<a class="publish_subject" href="javascript:void(0);" title="Click to Publish">
													<img alt="Pub" src="theme/images/Publish.png" width="20px">
												</a>
											<%
										}
									
										if(subject.getDeleteDate() == null)
										{
											%>
												<a href="javascript:void(0);" class="delete_subject" title="Click here to delete this subject.">
													<img alt="Delete" src="theme/images/trash.png" width="20px;">
												</a>
											<%
										}
									%>
								</sec:authorize>
								<sec:authorize access="!hasRole('ROLE_ADMIN')">
									<%
										Registration reg = (Registration)session.getAttribute("registration");
										if(reg != null && subject.getLoginInfo() != null &&  reg.getLoginInfo().getUserid().equals(subject.getLoginInfo().getUserid())){
											%>
												<a href="adminEditSubject?sid=<%= subject.getSid() %>&url=<%= subject.getUrl() %>" title="Click for Edit">
													<img alt="Un-Pub" src="theme/images/edit-icon.png" width="20px">
												</a>
											<%
										}
										if(subject.getDeleteDate() == null && reg != null && subject.getLoginInfo() != null &&  reg.getLoginInfo().getUserid().equals(subject.getLoginInfo().getUserid()))
										{
											%>
												<a href="javascript:void(0);" class="delete_subject" title="Click here to delete this subject.">
													<img alt="Delete" src="theme/images/trash.png" width="20px;">
												</a>
											<%
										}
									%>
								</sec:authorize>
							</td>
						  </tr>
						<%
					}
				}
				else
				{
					%>
						<tr>
							<td  class="text-left" colspan="8">No Data in data source</td>
						</tr>
					<%
				}
			%>
		</tbody>
	  </table>
		
	</div>
	<%
		
		int lp = total_count/rpp;
		if(total_count%rpp > 0)
		{
			lp++;
		}
	%>
	<div class="pagination-box">
		<ul class="pagination">
			<%
				if(pn == 1)
				{
					%>
						<li class="disabled">
							<a href="javascript:void(0)"> <i class="fa fa-angle-double-left text-bold" style="line-height: 24px;"></i> First</a>
						</li>
					<%
				}
				else
				{
					%>
						<li>
							<a href="javascript:getSubjectList(1);"> <i class="fa fa-angle-double-left text-bold" style="line-height: 24px;"></i> First</a>
						</li>
					<%
				}
			
				if(pn > 1)
				{
					%>
						<li>
							<a href="javascript:getSubjectList(<%= pn-1 %>);"> <i class="fa fa-chevron-left" style="line-height: 24px;"></i> Previous</a>
						</li>
					<%
				}
				else
				{
					%>
						<li class="disabled">
							<a href="javascript:void(0);"> <i class="fa fa-chevron-left" style="line-height: 24px;"></i> Previous</a>
						</li>
					<%
				}
				
			
			%>
			<li class="active"><span id="current_page"><%= pn %></span></li>
			
			
			<%
				if(pn < lp)
				{
					%>
						<li>
							<a href="javascript:getSubjectList(<%= pn+1 %>);">Next <i class="fa fa-chevron-right" style="line-height: 24px;"></i> </a>
						</li>
					<%
				}
				else
				{
					%>
						<li class="disabled">
							<a href="javascript:void(0);">Next <i class="fa fa-chevron-right" style="line-height: 24px;"></i> </a>
						</li>
					<%
				}
					
				if(pn == lp)
				{
					%>
						<li class="disabled">
							<a href="javascript:void(0);">Last <i class="fa fa-angle-double-right" style="line-height: 24px;"></i></a>
						</li>
					<%
				}
				else
				{
					%>
						<li>
							<a href="javascript:getSubjectList(<%= lp %>);">Last <i class="fa fa-angle-double-right" style="line-height: 24px;"></i></a>
						</li>
					<%
				}
			
			
			%>
			
			
		</ul>
		<i class="pagination-text">Displaying <%=((pn-1)*rpp)+1 %> to <%=i-1 %> (of <%=total_count %> posts)</i>
	</div>
	<!-- .pagination-box -->

</body>
</html>