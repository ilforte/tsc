<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- stylesheet -->
<link href="<c:url value='/resources/css/bootstrap.min.css' />"  rel="stylesheet" media="screen"></link>
<link href="<c:url value='/resources/css/bootstrap-theme.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/bootstrap-table.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jquery-ui.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/toastr.min.css' />"  rel="stylesheet"></link>
<!-- grid -->
<link href="<c:url value='/resources/css/jsgrid.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jsgrid-theme.min.css' />"  rel="stylesheet"></link>
<link href="<c:url value='/resources/css/jsgrid-custom-theme.css' />"  rel="stylesheet"></link>

<!-- js library -->
<script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap-table.min.js' />"></script>
<script src="<c:url value='/resources/js/jquery-ui.min.js' />"></script>
<script src="<c:url value='/resources/js/jquery.cookie.js' />"></script>
<!-- notifcation  lib -->
<script src="<c:url value='/resources/js/toastr.min.js' />"></script>
<!-- audio lib -->
<script src="<c:url value='/resources/js/howler.core.js' />"></script>
<!-- grid -->
<script src="<c:url value='/resources/js/jsgrid.min.js' />"></script>
<!-- validation -->
<script src="<c:url value='/resources/js/jquery.validate.min.js' />"></script>
<script src="<c:url value='/resources/js/additional-methods.min.js' />"></script>

<sec:authorize access="hasRole('ROLE_USER')" var="isUser" />
<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
<sec:authorize access="hasRole('ROLE_SADMIN')" var="isSuperUser" />

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

.msg {$.noConflict();
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {		<security:authorize access="hasRole('ROLE_USER')" var="isUser" />
			<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
			<security:authorize access="hasRole('ROLE_SADMIN')" var="isSuperUser" />
	width: 600x;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
}
.jsgrid-header-cell{
	font-size:10px;
}
.jsgrid-cell{
	font-size:10px;
}
</style>

<!-- Global Scripts -->
<script type="text/javascript">
	var PORTAL_USER = '${pageContext.request.userPrincipal.name}';
	var CONTEXT_PATH = '${pageContext.request.contextPath}';
	var TOKEN = $("meta[name='_csrf']").attr("content");
	var HEADER = $("meta[name='_csrf_header']").attr("content");
	/*  utente richiesto */
	<c:if test="${not empty ab_codi}">
		var AB_CODI = ${ab_codi};
	</c:if>
	
	/* add context path to url  */
	function addContextPath(url) {
		return CONTEXT_PATH + url;
	}
	
	/* getUserRole */
	function getUserRole(){
		var user;
		<c:choose>
		  <c:when test="${isSuperUser}">
		  	user = 'ROLE_SADMIN';
		  </c:when>
		  <c:when test="${isAdmin}">
		  	user = 'ROLE_ADMIN';
		  </c:when>
		  <c:when test="${isUser}">
		    user = 'ROLE_USER';
		  </c:when>
		  <c:otherwise>
		   user = '';
		  </c:otherwise>
		</c:choose>
		return user;
	}
	
	/* get path from user role */
	function getPathFromRole(){
		if (getUserRole() == 'ROLE_ADMIN') {
			return '/admin';
		} else if (getUserRole() == 'ROLE_USER'){
			return '/user';
		};
	}
	
</script>

