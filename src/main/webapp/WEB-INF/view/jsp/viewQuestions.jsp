<!doctype html>
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
<body class="fixed-header">
<section id="main">
  <article class="content subject_data">
	<div class="container">
	  <div class="row">
	  	<div class="col-md-12" style="margin-bottom: 20px;border-bottom: 1px solid #e1e1e1;">
	  		<div class="tabs">
		  		<%
		  			String mode = (String)request.getAttribute("mode");
		  			if(mode != null)
		  			{
		  				if(mode.equalsIgnoreCase("recent"))
		  				{
		  					%>
						  		<span><strong>Recent Questions</strong></span>
		  					<%
		  				}
		  				else if(mode.equalsIgnoreCase("featured"))
		  				{
		  					%>
						  		<span><strong>Featured Questions</strong></span>
		  					<%
		  				}
		  				
		  				else if(mode.equalsIgnoreCase("most"))
		  				{
		  					%>
						  		<span><strong>Most Viewed Questions</strong></span>
		  					<%
		  				}
		  				else if(mode.equalsIgnoreCase("tagged"))
		  				{
		  					%>
						  		<span><strong>Tagged With :</strong> ${tag.tag} </span>
		  					<%
		  				}
		  					
		  			}
		  		%>
		  		
			  <ul class="nav nav-tabs pull-right">
				<li class="ponter ${mode == 'recent' ? 'active' : ''}">
				  <a href="${path_url}/questions?mode=recent"><i class="fa fa-gears"></i> Recent</a>
				</li>
				<li class="${mode == 'featured' ? 'active' : ''}">
				  <a href="${path_url}/questions?mode=featured"><i class="fa fa-share-square-o"></i> Featured</a>
				</li>
				<li class="${mode == 'most' ? 'active' : ''}">
				  <a href="${path_url}/questions?mode=most"><i class="fa fa-folder-open-o"></i> Most Viewed</a>
				</li>
			  </ul>
			</div>
			<div class="clearfix"></div>
	  	</div>
      <div class="sidebar col-sm-12 col-md-3 pull-right" id="sidebar" style="display: none;">
		<aside class="widget menu bj_menu">
		  <nav>
			<ul >
				  <li class="bg-info current"><a href="#">Title</a></li>
			</ul>
		  </nav>
		</aside><!-- .menu-->
	  </div><!-- .sidebar -->
	  <div class="content blog blog-post col-sm-9 col-md-9">
	  	<article class="post">
		  <div class="entry-content">
			<%
				List<Question> qList = (List)request.getAttribute("qList");
				if(qList != null && !qList.isEmpty())
				{
					%>
						<div class="list-group">
							<%
								for(Question que : qList)
								{
									String q = que.getQustion();
									q = q.replaceAll(" ", "-");
									%>
									<div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
										<span class="btn btn-sm btn-view disabled btn-inverse">
											<div class="count-num"><%= que.getViews() %></div>
											<div class="count-text">views</div>
										</span>
									</div>
									<div class="col-xs-9 col-sm-10 col-md-10 col-lg-11">
										  <div class="list-group-item border-bottom-only" >
											<a href="${path_url}/questions/<%= que.getQid() %>/<%= q %>"  class="list-group-item-heading ">
												<%= que.getQustion() %>
											</a>
											<%
												if(que.getTags().size() > 0)
												{
													%>
													<ul class="tags">
														<%
															Iterator<Tag> it = que.getTags().iterator();
															while(it.hasNext())
															{
																Tag tag = it.next();
																%>
																	<li><a href="${path_url}/questions/tagged/<%= tag.getTag() %>" class="tag"><%= tag.getTag() %></a></li>
																<%
															}
														%>
													</ul>
													<%
												}
											%>
										  </div>
									  </div>
									  <div class="clearfix"></div>
									  <hr style="margin-top:0px;margin-bottom: 5px;margin-left: 15px;margin-right: 15px;">
									<%
								}
							%>
						</div>
					<%
				}
				
				%>
				
		  </div>
		  
		</article><!-- .post -->
	
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
	  <div class="clearfix"></div>
	</div>
  </article>
</section><!-- #main -->

<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>