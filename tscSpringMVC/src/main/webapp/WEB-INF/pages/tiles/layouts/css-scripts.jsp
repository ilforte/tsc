<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- stylesheet -->
<link href="<c:url value='/resources/css/bootstrap.min.css' />"  rel="stylesheet" media="screen"></link>
<link href="<c:url value='/resources/css/bootstrap-theme.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap-table.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jsgrid.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jsgrid-theme.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jquery-ui.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jsgrid.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jsgrid-theme.min.css' />"  rel="stylesheet"></link>
<!-- javascript lib -->
<script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap-table.min.js' />"></script>
<script src="<c:url value='/resources/js/jsgrid.min.js' />"></script>
<script src="<c:url value='/resources/js/jquery-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/jquery.cookie.js' />"></script>
<script src="<c:url value='/resources/js/jsgrid.min.js' />"></script>

<!-- spring Error - Message -->

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
	width: 600x;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}

.jsgrid-header-cell{
	position: relative; 
	font-size:11px;
	border:1px solid #e9e9e9;
}
.jsgrid-cell{
	position: relative; 
	font-size:11px;
	border:1px solid #e9e9e9;
}
</style>
