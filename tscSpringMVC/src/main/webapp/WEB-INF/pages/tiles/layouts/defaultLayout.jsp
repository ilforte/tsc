<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- ... -->
    <title><tiles:getAsString name="title" /></title>
    <tiles:insertAttribute name="css-scripts" />
</head>
  
<body>
<%-- 
Current Locale : ${pageContext.response.locale} --%>

<div class="panel panel-default">
	<div class="panel-heading">
			<tiles:insertAttribute name="header" />
	</div>
	<div class="panel-body">
	
	<div id="container">
	  <div id="left">
	  	<tiles:insertAttribute name="left-body" />
	  </div>
	  <div id="center">
	  	<tiles:insertAttribute name="center-body" />
	  </div>
	  <div id="right">
	  	<tiles:insertAttribute name="right-body" />
	  </div>
	</div>
		
	</div>
	<div class="panel-footer align-text-bottom">
		<tiles:insertAttribute name="footer" />
	</div>
</div>
</body>
</html>