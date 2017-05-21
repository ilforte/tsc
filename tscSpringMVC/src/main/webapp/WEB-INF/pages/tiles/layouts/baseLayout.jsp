<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- ... -->
    <title><tiles:getAsString name="title" /></title>
    <tiles:insertAttribute name="dependency" />
</head>
  
<body>
<%-- 
Current Locale : ${pageContext.response.locale} --%>

<div class="container">             
   <section id="site-content"class="active">
       <tiles:insertAttribute name="body" />
   </section>
</div>
</body>
</html>