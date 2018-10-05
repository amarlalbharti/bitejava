<!doctype html>
<%@page import="com.bharti.constraints.Roles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@page import="com.bharti.domain.Registration"%>
<%@page import="com.bharti.domain.UploadFile"%>
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
	  <div class="panel-heading"><span>List of Uploaded files</span></div>
	  <table class="table table-bordered table-responsive table-condensed">
		<thead>
		  <tr>
			<th>#</th>
			<th>Icon</th>
			<th class="text-left">Name/URL</th>
			<th >Create</th>
			<th >Action</th>
		  </tr>
		</thead>
		<tbody>
			<%
				List<UploadFile> fList = (List)request.getAttribute("fList");
				int i = ((pn-1)*rpp)+1;
				if(fList != null && !fList.isEmpty())
				{
					for(UploadFile file : fList)
					{
						%>
						  <tr fid="<%= file.getFid()%>">
							<td><%= i++ %></td>
							<%
								if(file.getFileType().equals("image"))
								{
									
									%>
										<td width="140px">
											<div class="images-box">
												<a class="gallery-images frame-shadow-raised" rel="fancybox" href="/bj_uploads/uploadedfiles/<%=file.getFid()%>/<%=file.getFileURL()%>">
												  <img class="replace-2x"  src="/bj_uploads/uploadedfiles/<%=file.getFid()%>/<%=file.getFileURL()%>" alt="" style="max-height: 120px;">
												  <span class="bg-images text-left"><i class="fa fa-search"></i></span>
												</a>
											  </div>
										 </td>
									<%
								}
								else
								{
									%>
										<td width="160px"><img alt="" src='/theme/images/no-preview-large.jpg' style="max-height: 100px; max-width: 140px;"></td>
									<%
								}
							%>
							<td class="text-left">
								<%= file.getFileName() %> /<br><br>
								<a href="/bj_uploads/uploadedfiles/<%=file.getFid()%>/<%=file.getFileURL()%>">/bj_uploads/uploadedfiles/<%=file.getFid()%>/<%=file.getFileURL()%></a>
							</td>
							<td><%= DateFormats.ddMMMyyyyathhmm.format(file.getCreateDate()) %></td>
							<td>
								<sec:authorize access="hasRole('<%=Roles.ROLE_ADMIN %>')">
								<button class="btn btn-sm btn-flat btn-default btn_file_del" fid="<%=file.getFid()%>"><i class="fa fa-times"></i> Delete</button>
								</sec:authorize>
								<sec:authorize access="!hasRole('<%=Roles.ROLE_ADMIN %>')">
									<%
										Registration reg = (Registration)session.getAttribute("registration");
										System.out.print("Reg : " + reg.getLoginInfo());
										if(reg != null && file.getLoginInfo() != null &&  reg.getLoginInfo().getUserid().equals(file.getLoginInfo().getUserid())){
											%>
											<button class="btn btn-sm btn-flat btn-default btn_file_del" fid="<%=file.getFid()%>"><i class="fa fa-times"></i> Delete</button>
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
							<td colspan="5">No Data in data source</td>
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
							<a href="javascript:getUploadFileList(1);"> <i class="fa fa-angle-double-left text-bold" style="line-height: 24px;"></i> First</a>
						</li>
					<%
				}
			
				if(pn > 1)
				{
					%>
						<li>
							<a href="javascript:getUploadFileList(<%= pn-1 %>);"> <i class="fa fa-chevron-left" style="line-height: 24px;"></i> Previous</a>
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
							<a href="javascript:getUploadFileList(<%= pn+1 %>);">Next <i class="fa fa-chevron-right" style="line-height: 24px;"></i> </a>
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
							<a href="javascript:getUploadFileList(<%= lp %>);">Last <i class="fa fa-angle-double-right" style="line-height: 24px;"></i></a>
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