<!doctype html>
<%@page import="com.bharti.domain.Subject"%>
<%@page import="java.util.List"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Bite Java Tutorials</title>
  
  <meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script type="text/javascript" src="${path_url}/theme/js/question_js.js"></script>
</head>
<body class="fixed-header">

<section id="main">
  <article class="content">
	<div class="container">

	  <div class="row bite_que">
		<div class="col-md-12" style="margin-bottom: 5px;">
			<div class="pull-right"><a  class="btn btn-sm btn-flat btn-default add_keynote" href="adminAskQuestion">
				<i class="fa fa-plus"></i> Ask Question</a>
			</div>
		</div>
		<div class="col-sm-12 col-md-12">
		  <div class="que_view">
			  
		  </div>
		</div>
	  </div>
	</div>
  </article>
</section><!-- #main -->


<script type="text/javascript">
jQuery(document).ready(function() {
	getQuestionList(1);
});
</script>
<script type="text/javascript">
jQuery(document).ready(function() {
	
	<%
		String add_que = request.getParameter("add_que");
		if(add_que != null && add_que.trim().length() > 0)
		{
			if(add_que.equals("success"))
			{
				%>
					Lobibox.notify('success', {
				        size: 'mini',
				        msg: 'Question added successfully!'
				    });
				<%
			}
			else
			{
				%>
					Lobibox.notify('error', {
	                    size: 'mini',
	                    msg: 'Oops, Something wrong !'
	                });
				<%
			}
		}
		
		String edit_que = request.getParameter("edit_que");
		if(edit_que != null && edit_que.trim().length() > 0)
		{
			if(edit_que.equals("success"))
			{
				%>
					Lobibox.notify('success', {
				        size: 'mini',
				        msg: 'Question updated successfully!'
				    });
				<%
			}
			else
			{
				%>
					Lobibox.notify('error', {
	                    size: 'mini',
	                    msg: 'Oops, Something wrong !'
	                });
				<%
			}
		}
		
	%>
	
});

</script>
</body>
</html>