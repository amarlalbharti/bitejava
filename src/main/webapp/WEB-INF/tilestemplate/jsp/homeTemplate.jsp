<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${pageTitle}</title>
	<meta name="description" content="${pageDescription}">
	<meta name="keywords" content="${pageKeywords}">
	<meta name="author" content="${pageAuthor}">
	<meta class="viewport" name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	 <!-- Twitter Card data -->
	<meta name="twitter:card" content="summary">
	<meta name="twitter:site" content="${pageAuthor}">
	<meta name="twitter:title" content="${pageTitle}">
	<meta name="twitter:description" content="${pageDescription}">
<!-- 	<meta name="twitter:creator" content="@author_handle"> -->
	<meta name="twitter:image" content="${pageImageUrl}">
	
	<!-- Open Graph data -->
	<meta property="og:title" content="${pageTitle}" />
	<meta property="og:type" content="article" />
	<meta property="og:url" content="${pageUrl }" />
	<meta property="og:image" content="${pageImageUrl}" />
	<meta property="og:description" content="${pageDescription}" />
	<meta property="og:site_name" content="${pageAuthor}" />
<!-- 	<meta property="fb:admins" content="Facebook numeric ID" />  -->

</head>
<body id="boxed-bg" class="boxed fixed-header">
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />
</body>
</html>