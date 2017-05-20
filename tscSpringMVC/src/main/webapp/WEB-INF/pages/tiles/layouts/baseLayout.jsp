<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- ... -->
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/bootstrap-theme.min.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/bootstrap-table.min.css' />"  rel="stylesheet"></link>
    <script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap-table.min.js' />"></script>
</head>

<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 600px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}
</style>
  
<body onload='document.loginForm.username.focus();'>
	<div class="container">             
	       <tiles:insertAttribute name="body" />
	</div>
</body>
</html>