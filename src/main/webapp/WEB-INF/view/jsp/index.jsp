<!doctype html>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.Map"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

</head>
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
				<img class="replace-2x" src="theme/images/JAVA-ICON.png" alt="" width="570" height="320">
			  </div>
			</div>
			<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<img class="replace-2x" src="theme/images/javascript.png" alt="" width="570" height="320">
			  </div>
			</div>
			<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<img class="replace-2x" src="theme/images/nodejs.png" alt="" width="570" height="320">
			  </div>
			</div>
			<div class="bottom-padding col-xs-3 col-sm-3 col-md-3">
			  <div class="frame frame-padding">
				<img class="replace-2x" src="theme/images/HTML5.png" alt="" width="570" height="320">
			  </div>
			</div>
			<!-- 
			
			<p class="description">
			  If debugging is the process of removing software bugs,<br>
			  Then programming must be the process of putting them in.<br>
			  <strong>&ndash; Edsger Dijkstra</strong>
			</p> -->
		  </div>
		</div>
	  </div>
</div>
<section id="main">
  <article class="content">
	<div class="container">
	  <div class="title-box">
		<h1 class="title">Bite Java</h1>
	  </div>

	  <div class="row">
		<div class="col-sm-5 col-md-5 bottom-padding">
		  <p class="lead"><strong>Bite Java provides best online tutorials with proper definitions and a lot of examples with each concept. </strong></p>
		  <p>
			  Are you  newcomer or experienced in java ? check concepts you have, 
			  is right or not. There is too many examples with real world relationship. 
		  </p>
 		  <p></p>
		  <a href="#" class="btn btn-default">Read more</a>
		</div>
		
		<div class="bottom-padding respond-carousel col-sm-7 col-md-7">
		  <div class="carousel-box no-nav no-pagination" data-carousel-one="true" data-carousel-autoplay="true" data-carousel-nav="false">
			
			
			<div class="row">
			  <div class="carousel">
				<div class="respond respond-blockquote bg col-sm-12 col-md-12">
				  <div class="description bg-info">
					<blockquote>
					  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Enim ducimus eveniet distinctio amet quaerat maxime fugit nesciunt sunt ut quasi nulla dolor soluta ratione incidunt necessitatibus cumque culpa esse dolorum ab magni quae dolorem aliquam. Ratione consequatur optio obcaecati laborum voluptas adipisci quia placeat quos maiores enim non.
					</blockquote>
				  </div>
				  <div class="name">
					<div class="icon">
					  <img class="replace-2x img-circle" src="theme/images/amar1.jpg" alt="">
					</div>
					
					<strong>John Doe</strong>
					<div>Designer, Infostyle</div>
				  </div>
				</div>
				
				<div class="respond respond-blockquote bg col-sm-12 col-md-12">
				  <div class="description bg-info">
					<blockquote>
					  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Enim ducimus eveniet distinctio amet quaerat maxime fugit nesciunt sunt ut quasi nulla dolor soluta ratione incidunt necessitatibus cumque culpa esse dolorum ab magni quae dolorem aliquam. Ratione consequatur optio obcaecati laborum voluptas adipisci quia placeat quos maiores enim non.
					</blockquote>
				  </div>
				  <div class="name">
					<div class="icon">
					  <img class="replace-2x img-circle" src="theme/images/amar1.jpg" alt="">
					</div>
					<strong>Derek Smitt</strong>
					<div>Director, Infostyle</div>
				  </div>
				</div>
				
				<div class="respond respond-blockquote bg col-sm-12 col-md-12">
				  <div class="description bg-info">
					<blockquote>
					  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Enim ducimus eveniet distinctio amet quaerat maxime fugit nesciunt sunt ut quasi nulla dolor soluta ratione incidunt necessitatibus cumque culpa esse dolorum ab magni quae dolorem aliquam. Ratione consequatur optio obcaecati laborum voluptas adipisci quia placeat quos maiores enim non.
					</blockquote>
				  </div>
				  <div class="name">
					<div class="icon">
					  <img class="replace-2x img-circle" src="theme/images/amar1.jpg" alt="">
					</div>
					
					<strong>John Doe</strong>
					<div>Designer, Infostyle</div>
				  </div>
				</div>
			  </div>
			</div>
			<div class="clearfix"></div>
			<div class="pagination switches"></div>
		  </div>
		</div>
	  </div>
	  
	  
	  <div class="clearfix"></div>
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
	
	
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-12">
			  <div class="title-box">
				<h1 class="title">Tutorials</h1>
			  </div>
			</div>
			<%
				List<Subject> sList = (List)request.getAttribute("sList");
				Map<Long, List<Keynote>> mapList = (Map)request.getAttribute("mapList");
				for (Subject sub : sList) 
				{
					List<Keynote> kList = mapList.get(sub.getSid());
					System.out.println(sub.getSubject() + " : " + kList.size());
					%>
						<div class="col-sm-6 col-md-4 col-lg-3">
							<div class="pricing">
							  <div class="title"><a href="${path_url}/note/<%= sub.getUrl()%>"><%= sub.getSubject() %></a></div>
							  <ul class="options">
								<%
								
								for(Keynote kn : kList)
								{
									%>
										<a href="${path_url}/note/<%= sub.getUrl()%>/<%= kn.getUrl()%>"><li class="active"><span><i class="fa fa-check"></i></span><%= kn.getKeynote() %></li></a>
									<%	
								}
								%>
							  </ul>
							</div>
					 	</div>
					<%
					
				}
			%>
			
		</div>
	</div>
	
	
  </article>
</section><!-- #main -->



</body>
</html>