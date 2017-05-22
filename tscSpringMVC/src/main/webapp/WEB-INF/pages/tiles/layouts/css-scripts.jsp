<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<link href="<c:url value='/resources/css/bootstrap.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap-theme.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap-table.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jquery.bootgrid.min.css' />"  rel="stylesheet"></link>
<script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap-table.min.js' />"></script>
<script src="<c:url value='/resources/js/jquery.bootgrid.min.js' />"></script>
<script src="<c:url value='/resources/js/jquery.bootgrid.fa.min.js' />"></script>

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

#container {
    width:100%;
    height:100%;
    text-align:center;
}

#left {
    float:left;
    width:25%;
    height:100%;
}

#center {
    display: inline-block;
    margin:0 auto;
    width:50%;
    height:100%;
}

#right {
    float:right;
    width:25%;
    height:100%;
}

.panel-footer {
    position: float;
    right: 0;
    bottom: 0;
    left: 0;
}
</style>
