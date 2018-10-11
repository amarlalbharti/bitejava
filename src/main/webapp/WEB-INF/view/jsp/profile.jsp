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
				<h3 class="title">My Account</h3>
			  </header>
			  <nav>
				<ul>
				  <li class="active">
					<a href="${pageContext.request.contextPath}/profile"><i class="fa fa-gears item-icon"></i>My Profile</a>
				  </li>
				  <li >
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
					
					<form:form action="updateProfile"  role="form" commandName="profile_form" method="POST" enctype="multipart/form-data" >
						
						
						<div class="form-group col-md-6 pull-right">
							<div class="col-md-6 gallery">
								<%
									if(user.getProfileImage() != null){
										
										%>
											<div class="images-box col-md-12  pull-right" style="max-width: 200px; max-height: 150px;">
												<a class="gallery-images text-center frame-shadow-raised parent_blah" rel="fancybox" href="/bj_uploads<%= user.getProfileImage()%>">
												  <img class="replace-2x" id="blah" src="/bj_uploads<%= user.getProfileImage()%>" alt="" style="max-height: 140px;">
												  <span class="bg-images text-left"><i class="fa fa-search"></i></span>
												</a>
											  </div>
										<%
									}else{
										%>
										  <div class="images-box col-md-12  pull-right" style="max-width: 200px; max-height: 150px;">
											<a class="gallery-images text-center frame-shadow-raised parent_blah" rel="fancybox" href="theme/images/no-preview-large.jpg">
											  <img class="replace-2x" id="blah" src="theme/images/no-preview-large.jpg" alt="" style="max-height: 140px;">
											  <span class="bg-images text-left"><i class="fa fa-search"></i></span>
											</a>
										  </div>
										<%
									}
								%>
							  	
							  </div>
							<div class="form-group  col-sm-12 col-md-12">
		 						<label class="btn btn-primary btn-flat">
				                  <input type="file" id="profile_file" name="file">
				                  <i class="fa fa-fw fa-cloud-upload"></i> Browse file from Desktop
		                        </label>
							  </div>
						</div>
						<div class="form-group col-md-6 pull-right">
						  <div class="form-group">
							<label for="fullname" class="col-sm-3 control-label"  >Name</label>
							<div class = "col-sm-9">
								<form:input path="name" class="form-control" />
								<span class="text-danger"><form:errors path="name"></form:errors></span>
							</div>
						  </div>
						  <br>
						  <div class="form-group">
							<label for="email" class="col-sm-3 control-label">Email Id</label>
							<div class = "col-sm-9">
								<label class="form-control"><%= user.getLoginInfo().getUserid() %></label>
								<form:hidden path="email" class="form-control" />
								<span class="text-danger"><form:errors path="email"></form:errors></span>
							</div>
						  </div>
						  <br>
						  <div class="form-group">
							<label for="email" class="col-sm-3 control-label">Gender</label>
							<div class = "col-sm-9">
								<form:select path = "gender" id="gender" class="form-control" >
									<form:option value="Male">Male</form:option>
									<form:option value="Female">Female</form:option>
								</form:select>
								<span class="text-danger"><form:errors path="gender"></form:errors></span>
							</div>
						  </div>
						  <br>
						  <div class="form-group">
							<label for="email" class="col-sm-3 control-label">Contact</label>
							<div class = "col-sm-9">
								<form:input path="contact" class="form-control" />
								<span class="text-danger"><form:errors path="contact"></form:errors></span>
							</div>
						  </div>
						  
						  <br>
						  <div class="form-group">
							<label for="email" class="col-sm-3 control-label">DOB</label>
							<div class = "col-sm-9">
								<form:input path="dob" class="form-control datepicker" />
								<span class="text-danger"><form:errors path="dob"></form:errors></span>
							</div>
						  </div>
						  
						  <div class="clearfix"></div>
						  <div class="form-group button-set">
						  	<div class = "col-sm-12">
							<button class="btn btn-default ">Submit</button>
							</div>
						  </div>
						  </div>
					</form:form>
									
					
					
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
	    format: 'mm/dd/yyyy'
	});
});
</script>
</body>
</html>