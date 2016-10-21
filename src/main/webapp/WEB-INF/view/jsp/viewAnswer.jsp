<!doctype html>
<%@page import="com.bharti.domain.Answers"%>
<%@page import="com.bharti.domain.Tag"%>
<%@page import="com.bharti.domain.Question"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="${path_url}/theme/ckeditor/plugins/codesnippet/lib/highlight/styles/monokai_sublime.css" rel="stylesheet">
    <script src="${path_url}/theme/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
</head>
<body>
<section id="main">
<%
	Question que = (Question)request.getAttribute("que");
	if(que != null)
	{
		%>
			<article class="content subject_data">
				<header class="page-header margin-20">
					<div class="container">
						<h3 class="title"><%= que.getQustion() %></h3>
					</div>
				</header>
				<div class="container">
				<div class="row">
					
					<div class="content blog blog-post col-sm-9 col-md-9">
						<div class="col-sm-12 col-md-12">
						<footer class="entry-meta">
						  	<% 
							  	if(que.getTags().size() > 0)
								{
									%>
									<ul class="tags">
										<%
											Iterator<Tag> it1 = que.getTags().iterator();
											while(it1.hasNext())
											{
												Tag tag = it1.next();
												%>
													<li><a href="${path_url}/questions/tagged/<%= tag.getTag() %>" class="tag"><%= tag.getTag() %></a></li>
												<%
											}
										%>
									</ul>
									<%
								}
						  	%>
						  </footer>
						  </div>
						  <div class="clearfix"></div>
						<%
							Iterator<Answers> it = que.getAnswers().iterator();
							if(it.hasNext())
							{
								Answers ans = it.next();
								%>
									<article class="post clearfix">
									  <div class="entry-content">
										<%= ans.getAnswer() %>
									  </div>
									  
									</article><!-- .post -->
								<%
							}
						%>
						
				      </div><!-- .content -->
					  <div class="sidebar col-sm-3 col-md-3" id="sidebar">
							<aside class="widget list">
							  <header>
								<h3 class="title">Top Tutorials</h3>
							  </header>
							  <ul>
								<li><a href="${path_url}/note/java">Java Tutorials</a></li>
								<li><a href="${path_url}/note/php">PHP Tutorials</a></li>
								<li><a href="${path_url}/note/asp_net">ASP.NET Tutorials</a></li>
								<li><a href="${path_url}/note/mysql">MYSQL Tutorials</a></li>
							  </ul>
							</aside><!-- .list -->
							
							<aside class="widget tags">
							  <header>
								<h3 class="title">Tags</h3>
							  </header>
							  <ul class="clearfix">
								<%
							  		List<Tag> topTags = (List)request.getAttribute("topTags");
							  		if(topTags != null && !topTags.isEmpty())
							  		{
							  			for(Tag tag : topTags)
							  			{
							  				%>
												<li><a href="${path_url}/questions/tagged/<%= tag.getTag() %>"><%= tag.getTag() %></a></li>
							  				<%
							  			}
							  		}
							  	%>
							  </ul>
							</aside><!-- .tags -->
						  </div>
				</div>
			</div>
		  </article>
		<%
	}
%>
  
</section><!-- #main -->

<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>