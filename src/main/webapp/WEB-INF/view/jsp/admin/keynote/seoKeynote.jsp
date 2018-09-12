<!doctype html>
<%@page import="com.bharti.constraints.DateFormats"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<html>
<body class="fixed-header">

<section id="main">
  <article class="content">
	<div class="container">

	  <div class="row seo_keynote">
		<div class="col-sm-12 col-md-12">
		  <div class="seo_keynote_list">
		  	<div class="panel panel-info">
				  <div class="panel-heading"><span>List of KeyNotes</span>
				  
				  </div>
				  <table class="table table-bordered table-responsive table-condensed">
					<thead>
					  <tr>
						<th>#</th>
						<th class="text-left">KeyNote</th>
						<th class="text-left">Subject</th>
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
										<td width="50px"><%= i++ %></td>
										<td class="text-left"><%= kn.getKeynote() %></td>
										<td class="text-left"><%= kn.getSubject().getSubject() %></td>
										<td width="100px">
											<a href="adminAddSeoKeynote?kid=<%= kn.getKid()%>" title='Click for add SEO for this keynote'>
												Add SEO
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
	  </div>
	  
	  
	  <div class="clearfix"></div>
	</div>
	
  </article>
</section><!-- #main -->


<script type="text/javascript">
// jQuery(document).ready(function() {
// 	getSubjectList(1);
// });
</script>
</body>
</html>