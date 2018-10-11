<!doctype html>
<%@page import="com.bharti.constraints.DateFormats"%>
<%@page import="com.bharti.domain.Comments"%>
<%@page import="com.bharti.domain.Registration"%>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<style type="text/css">
input[type="file"] {
    display: none !important;
}
.custom-file-upload {
    cursor: pointer;
    margin-top: -30px !important;
    position: absolute !important;
}
.bodyCoverWait {
    position: fixed;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    z-index: 9999;
    opacity: 0.8;
    background: #ececec;
}
.form-group {

    margin-bottom: 25px !important;

}
</style>
<body>
<section id="main">
  
  <div class="container">
    <div class="row">
      <div class="content col-sm-12 col-md-12">
		<div class="row">
		  <div id="sidebar" class="sidebar col-sm-3 col-md-3">
			<aside class="widget menu">
			  <header>
				<h3 class="title">My Comments</h3>
			  </header>
			  <nav>
				<ul>
				  <li>
					<a href="${pageContext.request.contextPath}/profile"><i class="fa fa-gears item-icon"></i>My Profile</a>
				  </li>
				  <li>
					<a href="${pageContext.request.contextPath}/changePassword"><i class="fa fa-user item-icon"></i>Change Password</a>
				  </li>
				  <li class="active">
					<a href="${pageContext.request.contextPath}/comments"><i class="fa fa-comments-o item-icon"></i>My Comments</a>
				  </li>
<!-- 				  <li> -->
<!-- 					<a href="shop-account-orders.html"><i class="fa fa-shopping-cart item-icon"></i>My Orders</a> -->
<!-- 				  </li> -->
				</ul>
			  </nav>
			</aside><!-- .menu-->
		  </div>
		  
		  <div class="col-sm-9 col-md-9 bottom-padding">
		  <header>
			<h3 class="title"></h3>
		  </header>
		  <div class="timeline">
			<%
				Registration user = (Registration)request.getAttribute("user");
				if(user != null) {
					List<Comments> comments = (List<Comments>)request.getAttribute("comments");
					if(comments!= null && !comments.isEmpty()){
						boolean flagLeft = true;
						String fade = "fadeInLeft";
						for(Comments comment : comments){
							
							%>
							<article class="post">
							  <div class="timeline-time">
								<time datetime="2014-06-23"><%=DateFormats.ddMMMyyyyathhmm.format(comment.getCreateDate()) %></time>
					          </div>
							  
							  <div class="timeline-icon icon-40 bg-danger" style="padding-top: 12px">
								<i class="fa fa fa-comment item-icon"></i>
							  </div>
							  
							  <div class="timeline-content bg bg-danger" data-appear-animation="<%= fade %>">
								<h2 class="entry-title">
								  <a href="${pageContext.request.contextPath}/note/<%=comment.getKeynote().getSubject().getUrl() %>/<%=comment.getKeynote().getUrl() %>"><%= comment.getKeynote().getKeynote() %></a>
								</h2>
								
								<div class="entry-content">
								  <%= comment.getComment() %>
								</div>
							  </div>
							</article><!-- .post -->
							<%
							if(flagLeft){
								flagLeft = false;
								fade = "fadeInRight";
							}else{
								flagLeft = true;
								fade = "fadeInLeft";
							}
						}
					}
					%>
						
					<%
				}
			
			%>
			</div>
		  </div>
		</div>
		
		
		<div class="clearfix"></div>
	  </div>
	</div>
  </div>
  
</section><!-- #main -->
<script src="${pageContext.request.contextPath}/theme/js/bootstrap-datepicker.js"></script>
<script type="text/javascript">

jQuery(document).ready(function() {
	$('.datepicker').datepicker({
		format: 'dd/mm/yyyy'
	});
});
</script>
</body>
</html>