<!doctype html>
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
				<h3 class="title">Change Password</h3>
			  </header>
			  <nav>
				<ul>
				  <li >
					<a href="${pageContext.request.contextPath}/profile"><i class="fa fa-gears item-icon"></i>My Profile</a>
				  </li>
				  <li class="active">
					<a href="${pageContext.request.contextPath}/changePassword"><i class="fa fa-user item-icon"></i>Change Password</a>
				  </li>
				  <li>
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
			<%
				Registration user = (Registration)request.getAttribute("user");
				if(user != null) {
					%>
					<div class="col-sm-9" >
					<form:form action="changePassword"  role="form" commandName="form_pwd" method="POST" >
						
						  <div class="form-group">
							<label for="fullname" class="col-sm-4 control-label"  >Old Password</label>
							<div class = "col-sm-8">
								<form:input path="oldPwd" class="form-control" />
								<span class="text-danger"><form:errors path="oldPwd"></form:errors></span>
							</div>
						  </div>
						  <br>
						  <div class="form-group">
							<label for="email" class="col-sm-4 control-label">New Password</label>
							<div class = "col-sm-8">
								<form:input path="newPwd" class="form-control" />
								<span class="text-danger"><form:errors path="newPwd"></form:errors></span>
							</div>
						  </div>
						  <br>
						  <div class="form-group">
							<label for="email" class="col-sm-4 control-label">Confirm Password</label>
							<div class = "col-sm-8">
								<form:input path="confPwd" class="form-control" />
								<span class="text-danger"><form:errors path="confPwd"></form:errors></span>
							</div>
						  </div>
						  
						  <div class="clearfix"></div>
						  <div class="form-group button-set pull-right">
						  	<div class = "col-sm-12">
							<button class="btn btn-default ">Submit</button>
							</div>
						  </div>
					</form:form>
					</div>
					<%
				}
			
			%>
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