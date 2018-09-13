<!doctype html>
<%@page import="com.bharti.constraints.DateFormats"%>
<%@page import="com.bharti.domain.Comments"%>
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
					<a href="${pageContext.request.contextPath}/note/<%= subject.getUrl() %>"><img alt="<%=  subject.getSubject() %>" src="<%= url%>"></a>
				<%
			}
		%>
		
		<aside class="widget menu bj_menu">
		  <nav>
			<ul >
				<%
					if(subject != null) {
						%>
							<li class="active"><a href="#"><%= subject.getSubject()%></a></li>
						<%
					}
					List<Keynote> kList = (List)request.getAttribute("kList");
					if(kList != null && !kList.isEmpty()) {
						if(keynote.getParent_keynote() != null) {
							for(Keynote kn : kList) {
								if(kn.getKid() != keynote.getParent_keynote().getKid()) {
									%>
									  <li><a href="${pageContext.request.contextPath}/note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								} else {
									%>
									  <li class="bg-info current"><a href="${pageContext.request.contextPath}/note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								}
							}
						} else {
							for(Keynote kn : kList) {
								if(kn.getKid() != keynote.getKid()) {
									%>
									  <li><a href="${pageContext.request.contextPath}/note/<%= kn.getSubject().getUrl() %>/<%= kn.getUrl() %>"><%= kn.getKeynote() %></a></li>
									<%
								} else {
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
		</article><!-- .post -->
		<h3 class="title slim">Comments</h3>
		<ul class="commentlist">
			<%
				if(keynote.getComments() != null && !keynote.getComments().isEmpty()){
					for(Comments comment : keynote.getComments()){
						%>
						<li>
							<div class="meta">
							  <span><%= comment.getName() %></span>, 
							  <span class="time"><%= DateFormats.ddMMMyyyyathhmm.format(comment.getCreateDate()) %></span>
							</div>
							<p class="description">
							  <%= comment.getComment() %>
							</p>
						 </li>
						<%
					}
				}
			%>
		</ul>
		<h3 class="title slim">Leave a Reaply</h3>
		
		<form class="comments-form">
		  <label>Name: <span class="required">*</span></label>
		  <div class="row">
			<div class="col-sm-6 col-md-6">
			  <input class="form-control" type="text">
			</div>
		  </div>
		  
		  <label>Email Adress: <span class="required">*</span></label>
		  <div class="row">
			<div class="col-sm-6 col-md-6">
			  <input class="form-control" type="email">
			</div>
		  </div>

		  <label>Comment: </label>
		  <div class="row">
			<div class="comment-box col-sm-10 col-md-10">
			  <textarea class="form-control"></textarea>
			  <i>Note: HTML is not translated!</i>
			</div>
		  </div>

		  <div class="clearfix"></div>
		  <div class="button-set">
			<span class="required pull-right"><b>*</b> Required Field</span>
			<button class="btn btn-default">Submit</button>
		  </div>
		</form>
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