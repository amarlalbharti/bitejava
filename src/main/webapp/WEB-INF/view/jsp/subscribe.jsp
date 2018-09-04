<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="com.bharti.domain.Keynote"%>
<%@page import="java.util.List"%>
<html>

<body>
<%
String subscribed = (String)request.getAttribute("subscribed");
if(subscribed !=  null && subscribed.equals("success"))
{
	%>
		<section id="main">
			<article class="content">
				<div class="container">
					<div class="content-block frame text-center">
						<h3 class="title text-success">Thank you, You have subscribed for newsletter !</h3>
						<p class="lead">Welcome to our site. There are many variations alteration in some form, by injected humour, or randomised words which don't look even slightly believable. Donec pharetra, lectus nec dignissim pharetra quis libero. </p>
						<a href="index"><button data-appear-animation="bounceIn" class="btn btn-default animated bounceIn"><i class="fa fa-home"></i> Home</button></a>
				  	</div>
				</div>
			</article>
		</section>
	<%
} else if (subscribed != null && subscribed.equals("exist")) 
{
	%>
		<section id="main">
			<article class="content">
				<div class="container">
					<div class="content-block frame text-center">
						<h3 class="title text-success">Thank you, You have already subscribed for newsletter !</h3>
						<p class="lead">Welcome to our site. There are many variations alteration in some form, by injected humour, or randomised words which don't look even slightly believable. Donec pharetra, lectus nec dignissim pharetra quis libero. </p>
						<a href="index"><button data-appear-animation="bounceIn" class="btn btn-default animated bounceIn"><i class="fa fa-home"></i> Home</button></a>
				  	</div>
				</div>
			</article>
		</section>
	<%
}
else
{
	%>
		<section id="main">
			<article class="content">
				<div class="container">
					<div class="content-block frame text-center">
						<h3 class="title text-danger">Oops, Something wrong Please try again !</h3>
						<div class="col-sm-6 col-md-4 sidebar" style="margin: 0 auto; float: none">
							<div class="newsletter">
								<form  class="" method="post" action="${pageContext.request.contextPath}/subscribe">
								  <input class="form-control" type="email" name="email" value="${email}" required="required">
								  <button class="submit" type="submit">
									<span class="glyphicon glyphicon-arrow-right"></span>
								  </button>
								</form>
							</div>
						</div>
				  	</div>
				</div>
			</article>
		</section>
	<%
}
%>

</body>
</html>