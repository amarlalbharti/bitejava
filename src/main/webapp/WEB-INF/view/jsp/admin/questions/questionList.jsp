 <!doctype html>
<%@page import="com.bharti.domain.Tag"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bharti.domain.Question"%>
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
<%
int pn = (Integer)request.getAttribute("pn");
int rpp = (Integer)request.getAttribute("rpp");
int total_count = (Integer)request.getAttribute("total_count");

%>
	<div class="panel panel-info">
	  <div class="panel-heading"><span>List of Recent Question</span></div>
	  <table class="table table-bordered table-responsive table-condensed">
		<thead>
		  <tr>
			<th>#</th>
			<th class="text-left">Question</th>
			<th >Tags</th>
			<th >Answers</th>
			<th >Publish</th>
			<th >Create</th>
			<th >Action</th>
		  </tr>
		</thead>
		<tbody>
			<%
				List<Question> qList = (List)request.getAttribute("qList");
				int i = ((pn-1)*rpp)+1;
				if(qList != null && !qList.isEmpty())
				{
					for(Question qu : qList)
					{
						String qurl = qu.getQustion();
						if(qurl != null)
						{
							qurl = qurl.replaceAll(" ", "-");
						}
						%>
						  <tr qid="<%= qu.getQid()%>">
							<td><%= i++ %></td>
							<td class="text-left"><%= qu.getQustion() %></td>
							<td class="text-left">
								<% 
									boolean flag = true;
									Iterator<Tag> it = qu.getTags().iterator();
									while(it.hasNext())
									{
										if(flag)
										{
											flag = false;
										}
										else
										{
											out.println(", ");
										}
										%>
											<a href="#"><%= it.next().getTag() %></a>
										<%
									}
								%>
								
							</td>
							<td> <%= qu.getAnswers().size() %></td>
							<td >
								<%
									if(qu.getPublishDate() != null)
									{
										out.println(DateFormats.ddMMMyyyy.format(qu.getPublishDate()));
									}
									else
									{
										%>
											Not Published
										<%
									}
								%>
							</td>
							<td ><%= DateFormats.ddMMMyyyy.format(qu.getCreateDate()) %></td>
							<td style="min-width: 100px">
								<a href="adminEditQuestion?qid=<%= qu.getQid()%>" title='Click for edit this quesion'>
									<img alt="" src="theme/images/edit-icon.png" width="20px;">
								</a>
								<a target="_blank" href="questions/<%= qu.getQid() %>/<%= qurl %>" title='Click for preview'>
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
							<a href="javascript:getQuestionList(1);"> <i class="fa fa-angle-double-left text-bold" style="line-height: 24px;"></i> First</a>
						</li>
					<%
				}
			
				if(pn > 1)
				{
					%>
						<li>
							<a href="javascript:getQuestionList(<%= pn-1 %>);"> <i class="fa fa-chevron-left" style="line-height: 24px;"></i> Previous</a>
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
							<a href="javascript:getQuestionList(<%= pn+1 %>);">Next <i class="fa fa-chevron-right" style="line-height: 24px;"></i> </a>
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
							<a href="javascript:getQuestionList(<%= lp %>);">Last <i class="fa fa-angle-double-right" style="line-height: 24px;"></i></a>
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