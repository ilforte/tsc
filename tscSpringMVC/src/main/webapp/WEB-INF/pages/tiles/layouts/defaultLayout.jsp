<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
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
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</head>
  
<body>
<%-- 
Current Locale : ${pageContext.response.locale} --%>

<div class="container">             
  <ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="#"><spring:message code="label.allarms" /></a></li>
    <li><a href="#"><spring:message code="label.anagrafic" /></a></li>
    <li><a href="#"><spring:message code="label.rescuers" /></a></li>
    <li><a href="#"><spring:message code="label.password" text="n.d."/></a></li>    
    <li><a href="#"><spring:message code="label.password" text="n.d."/></a></li>  
    <li><a href="#"><spring:message code="label.password" text="n.d."/></a></li>  
    <sec:authorize access="hasRole('ADMIN')">
		<li><a href="#"><spring:message code="label.password" text="Amministrazione"/></a></li> 
	</sec:authorize>
  </ul>
  
   <header id="header">
       <tiles:insertAttribute name="header" />
   </header>

   <section id="sidemenu">
       <tiles:insertAttribute name="menu" />
   </section>
        
   <section id="site-content">
       <tiles:insertAttribute name="body" />
   </section>
    
   <footer id="footer">
       <tiles:insertAttribute name="footer" />
   </footer>
  
</div>


</body>
</html>