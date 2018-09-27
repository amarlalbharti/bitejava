<!doctype html>
<%@page import="com.bharti.domain.Question"%>
<%@page import="com.bharti.constraints.DateFormats"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.Map"%>
<html>

<body class="fixed-header">
<!-- <div class="page-box"> -->
<!-- <div class="page-box-content"> -->
<div class="full-width-box">
	  <div class="fwb-bg bg-error"></div>
	  <div class="container">
		<div class="row">
		  <div class="images-box col-sm-6 col-md-6">
			<img width="568" height="351" alt="" src="theme/images/homepageGraphics-2.png" class="replace-2x">
		  </div>
		  <div class="content-box white col-sm-6 col-md-6">
		  	<div class="input-group input-group-lg">
                <input class="form-control input-group-lg" type="text" style="border: 3px solid #9DA5B1;">
                    <span class="input-group-btn">
                      <button type="button" class="btn btn-info btn-flat"><i class="fa fa-search"></i></button>
                    </span>
          	</div>
          	<br>
          	<div class="text-center">
          		<h2 class="title light ">Top Tutorials</h2>
          	</div>
          	
          	<div class="clearfix"></div>
          	<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<a href="#"><img class="replace-2x" src="theme/images/JAVA-ICON.png" alt="" width="570" height="320"></a>
			  </div>
			</div>
			<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<a href="#"><img class="replace-2x" src="theme/images/javascript.png" alt="" width="570" height="320"></a>
			  </div>
			</div>
			<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<a href="#"><img class="replace-2x" src="theme/images/nodejs.png" alt="" width="570" height="320"></a>
			  </div>
			</div>
			<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<a href="#"><img class="replace-2x" src="theme/images/HTML5.png" alt="" width="570" height="320"></a>
			  </div>
			</div>
		  </div>
		</div>
	  </div>
</div>
<section id="main">
  <article class="content">
	<div class="container">
	  
	  <div class="row">
		<div class="col-sm-12 col-md-6 bottom-padding">
		  <div class="title-box">
			<h2 class="title">Bite Java</h2>
		  </div>
			  
		  <p class="lead"><strong>Bite Java provides best online tutorials with proper definitions and a lot of examples for each concept. </strong></p>
		  <p>
			  Are you  newcomer or experienced in java ? check concepts you have, 
			  is right or not. There is too many examples with real world relationship. 
		  </p>
 		  <p></p>
		  <a href="#" class="btn btn-default">Read more</a>
		</div>
		<div class="col-sm-12 col-md-6">
		  <div class="title-box">
			<h2 class="title">Latest Posts</h2>
		  </div>
		  <%
		  	List<Question> recentArticles = (List<Question>)request.getAttribute("recentArticle");
			if(recentArticles != null && !recentArticles.isEmpty()){
				%>
					<ul class="latest-posts">
						<%
							for(Question article : recentArticles){
								String q = article.getQustion();
								q = q.replaceAll(" ", "-");
								%>
									<li>
									  <div class="meta">
										<span><%= article.getUserType() %></span>, 
										<span class="time"><%=DateFormats.ddMMMyyyyathhmm.format(article.getPublishDate())  %></span>
									  </div>
									  <div class="description">
										<a href="${pageContext.request.contextPath}/questions/<%= article.getQid()%>/<%= q%>">
										  <%=article.getQustion() %> 
										</a>
									  </div>
									</li>
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
	</div>
	
	<div class="container">
		<div class="row">
			
			<%
				List<Subject> sList = (List)request.getAttribute("sList");
				Map<Long, List<Keynote>> mapList = (Map)request.getAttribute("mapList");
				  if(mapList != null && !mapList.isEmpty()){
					  %>
					  	<div class="col-sm-12 col-md-12">
						  <div class="title-box">
							<h1 class="title">Articles</h1>
						  </div>
						</div>
					  <%
					  for (Subject sub : sList) 
						{
							List<Keynote> kList = mapList.get(sub.getSid());
							System.out.println(sub.getSubject() + " : " + kList.size());
							%>
								<div class="col-sm-6 col-md-4 col-lg-3">
									<div class="pricing">
									  <div class="title"><a href="${pageContext.request.contextPath}/note/<%= sub.getUrl()%>"><%= sub.getSubject() %></a></div>
									  <ul class="options">
										<%
										
										for(Keynote kn : kList)
										{
											%>
												<a href="${pageContext.request.contextPath}/note/<%= sub.getUrl()%>/<%= kn.getUrl()%>"><li class="active"><span><i class="fa fa-check"></i></span><%= kn.getKeynote() %></li></a>
											<%	
										}
										%>
									  </ul>
									</div>
							 	</div>
							<%
							
						}
				  }
				
			%>
			
		</div>
	</div>
	<div class="container">
	  <div class="row services">
		<div class="col-sm-12 col-md-12">
		  <div class="title-box">
			<h1 class="title">Services</h1>
		  </div>
		</div>
		
		<div class="big-services-box col-sm-4 col-md-4">
		  <div>
			<div class="big-icon bg" data-appear-animation="wobble"><i class="fa fa-code"></i></div>
			<h4 class="title" data-appear-animation="bounceInLeft">Quality Code</h4>
			<div class="text-small" data-appear-animation="bounceInLeft">
			  Get the best examples of code
			  <div class="clearfix"></div><br>
			  <button class="btn btn-default">Read more</button>
			</div>
		  </div>
		</div><!-- .services-box-two -->
		
		<div class="big-services-box col-sm-4 col-md-4">
		  <div>
			<div class="big-icon bg" data-appear-animation="wobble"><i class="fa fa-wrench"></i></div>
			<h4 class="title" data-appear-animation="bounceInUp">Best Explanation</h4>
			<div class="text-small" data-appear-animation="bounceInUp">
			  Get The Deep knowledge of each concept
			  <div class="clearfix"></div><br>
			  <button class="btn btn-default">Read more</button>
			</div>
		  </div>
		</div><!-- .services-box-two -->
		
		<div class="big-services-box col-sm-4 col-md-4">
		  <div>
			<div class="big-icon bg" data-appear-animation="wobble"><i class="fa fa-bug"></i></div>
			<h4 class="title" data-appear-animation="bounceInRight">Experts Suggestions</h4>
			<div class="text-small" data-appear-animation="bounceInRight">
			  Get Suggestions by exports.
			  <div class="clearfix"></div><br>
			  <button class="btn btn-default">Read more</button>
			</div>
		  </div>
		</div><!-- .services-box-two -->
	  </div>
	  <br><br>
	</div>
	
  </article>
</section><!-- #main -->



</body>
</html>