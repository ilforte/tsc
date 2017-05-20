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
  
<body>
<%-- 
Current Locale : ${pageContext.response.locale} --%>

<div class="container">             
  
   <header id="header">
       <tiles:insertAttribute name="header" />
   </header>

   <section id="sidemenu">
       <tiles:insertAttribute name="menu" />
   </section>
        
   <section id="site-content"class="active">
       <tiles:insertAttribute name="body" />
   </section>
    
   <footer id="footer">
       <tiles:insertAttribute name="footer" />
   </footer>
  
</div>


</body>
</html>