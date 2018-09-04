<!doctype html>
<%@page import="java.util.Iterator"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<html>
<head>
  <link href="${pageContext.request.contextPath}/theme/ckeditor/plugins/codesnippet/lib/highlight/styles/monokai_sublime.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/theme/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
    <style type="text/css">
	    .subject_data h1{
	    font-size: 22px;
	    }
	    .subject_data h2{
	    font-size: 20px;
	    }
	    .bj_menu .current a{
	    color: #fff !important;
	    }
    </style>
</head>
<body class="fixed-header">
<%

Subject subject = (Subject)request.getAttribute("subject");
Keynote keynote = (Keynote) request.getAttribute("keynote");
Keynote prevKn = (Keynote) request.getAttribute("prevKn");
Keynote nextKn = (Keynote) request.getAttribute("nextKn");

if(subject != null && keynote != null)
{
		
	
%>
<section id="main">
  <article class="content subject_data">
	<div class="container">
	  <div class="row">
      <div class="sidebar col-sm-12 col-md-3" id="sidebar">
		<%
			if(subject != null && subject.getSubject_image() != null && subject.getSubject_image().length() > 0)
			{
				String url = "/bj_uploads/subjects/"+subject.getSid() + "/subject_image/"+subject.getSubject_image();
				%>
					<a href="${pageContext.request.contextPath}//note/<%= subject.getUrl() %>"><img alt="<%=  subject.getSubject() %>" src="<%= url%>"></a>
				<%
			}
			else
			{
				%>
					<img alt="<%=  subject.getSubject() %>" src="${pageContext.request.contextPath}/theme/images/banner-corejava.jpg">
				<%
			}
		%>
		
		<aside class="widget menu bj_menu">
		  <nav>
			<ul >
				<%
					System.out.println("subject : "  + subject);
					if(subject != null)
					{
						%>
							<li class="active"><a href="#"><%= subject.getSubject()%></a></li>
						<%
					}
					List<Keynote> kList = (List)request.getAttribute("kList");
					if(kList != null && !kList.isEmpty())
					{
						if(keynote.getParent_keynote() != null)
						{
							for(Keynote kn : kList)
							{
								if(kn.getKid() != keynote.getParent_keynote().getKid())
								{
									%>
									  <li><a href="${pageContext.request.contextPath}//note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								}
								else
								{
									%>
									  <li class="bg-info current"><a href="${pageContext.request.contextPath}/note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								}
							}
						}
						else
						{
							for(Keynote kn : kList)
							{
								if(kn.getKid() != keynote.getKid())
								{
									%>
									  <li><a href="${pageContext.request.contextPath}//note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								}
								else
								{
									%>
									  <li class="bg-info current"><a href="${pageContext.request.contextPath}/note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								}
							}
						}
					}
				
				%>
			</ul>
		  </nav>
		</aside><!-- .menu-->
	  </div><!-- .sidebar -->
	  <div class="content blog blog-post col-sm-9 col-md-9">
		<article class="post">
			<div class="title-box text-center no-margin">
			  <h2 class="title"><%= keynote.getKeynote() %></h2>
			</div>
			<div  class="hr-top">
			  	<%
			  		if(prevKn != null)
			  		{
			  			%>
						  	<a href="${pageContext.request.contextPath}/note/<%= prevKn.getSubject().getUrl() %>/<%= prevKn.getUrl() %>" class="btn btn-default btn-flat btn-sm">
						  		<i class="fa fa-arrow-circle-left"></i> Previous Page 
					  		</a>
			  			<%
			  		}
			  		else
			  		{
			  			%>
						  	<a href="javascript:void(0)" class="btn btn-default btn-flat btn-sm disabled">
						  		<i class="fa fa-arrow-circle-left"></i> Previous Page 
					  		</a>
			  			<%
			  		}
				  	if(nextKn != null)
			  		{
			  			%>
						  	<a href="${pageContext.request.contextPath}/note/<%= nextKn.getSubject().getUrl() %>/<%= nextKn.getUrl() %>" class="btn btn-default btn-flat btn-sm pull-right">
						  		Next Page <i class="fa fa-arrow-circle-right"></i>
						  	</a>
			  			<%
			  		}
			  		else
			  		{
			  			%>
						  	<a href="javascript:void(0)" class="btn btn-default btn-flat btn-sm disabled pull-right">
						  		Next Page <i class="fa fa-arrow-circle-right"></i>
						  	</a>
			  			<%
			  		}
			  			
			  	%>
			  	<hr class="hr-bottom"/>
			</div>
		  <div class="entry-content">
			<%
				if(keynote.getKeynoteDetail() != null)
				{
					out.println(keynote.getKeynoteDetail().getDetail());
				}
				
				List<Keynote> childKeynotes = (List)request.getAttribute("childKeynotes");
				if(childKeynotes != null && !childKeynotes.isEmpty())
				{
					%>
						<div class="list-group">
							<%
								for(Keynote chkn : childKeynotes)
								{
									%>
									  <a href="${pageContext.request.contextPath}/note/<%= chkn.getSubject().getUrl() %>/<%= chkn.getUrl() %>" class="list-group-item <% if(chkn.getKid() == keynote.getKid()){out.println("active");} %>">
										<h5 class="list-group-item-heading"><%= chkn.getKeynote() %></h5>
										<p class="list-group-item-text">List groups are a flexible and powerful component for displaying not only simple lists of elements, but complex ones with custom content.</p>
									  </a>
									<%
								}
							%>
						</div>
					<%
				}
				
				%>
				
		  </div>
		  <%
		  		if(nextKn != null)
		  		{
		  			%>
		  			 <a href="${pageContext.request.contextPath}/note/<%= nextKn.getSubject().getUrl() %>/<%= nextKn.getUrl() %>">
			  			<div class="input-group has-success">
			                <div class="input-group-btn">
			                  <button class="btn btn-success" type="button">Next</button>
			                </div>
							<label  class="form-control" style="cursor: pointer;">
					  			<%= nextKn.getKeynote() %>
					  		</label>
		              	</div>
	              	</a>
		  			<%
		  		}
  			%>
		  <div  class="hr-top">
		  	<%
		  		if(prevKn != null)
		  		{
		  			%>
					  	<a href="${pageContext.request.contextPath}/note/<%= prevKn.getSubject().getUrl() %>/<%= prevKn.getUrl() %>" class="btn btn-default btn-flat btn-sm">
					  		<i class="fa fa-arrow-circle-left"></i> Previous Page 
				  		</a>
		  			<%
		  		}
		  		else
		  		{
		  			%>
					  	<a href="javascript:void(0)" class="btn btn-default btn-flat btn-sm disabled">
					  		<i class="fa fa-arrow-circle-left"></i> Previous Page 
				  		</a>
		  			<%
		  		}
			  	if(nextKn != null)
		  		{
		  			%>
					  	<a href="${pageContext.request.contextPath}/note/<%= nextKn.getSubject().getUrl() %>/<%= nextKn.getUrl() %>" class="btn btn-default btn-flat btn-sm pull-right">
					  		Next Page <i class="fa fa-arrow-circle-right"></i>
					  	</a>
		  			<%
		  		}
		  		else
		  		{
		  			%>
					  	<a href="javascript:void(0)" class="btn btn-default btn-flat btn-sm disabled pull-right">
					  		Next Page <i class="fa fa-arrow-circle-right"></i>
					  	</a>
		  			<%
		  		}
		  			
		  	%>
		  	<hr class="hr-bottom"/>
		  </div>
		  <!-- <footer class="entry-meta">
			<span class="autor-name">Mike Example</span>,
			<span class="time">03.11.2012</span>
			<span class="separator">|</span>
			<span class="meta">Posted in <a href="#">Sports</a>, <a href="#">Movies</a></span>
			<span class="comments-link pull-right">
			  <a href="#"><i class="fa fa-comment"></i> 3 comment(s)</a>
			</span>
		  </footer> -->
		</article><!-- .post -->
	
      </div><!-- .content -->
    </div>
	  <div class="clearfix"></div>
	</div>
  </article>
</section><!-- #main -->
	
<%	
}
%>



<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>