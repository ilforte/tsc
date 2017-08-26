<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Support error page</title>
</head>
<body>
<h1>Error Page</h1>
<p>Application has encountered an error. Please contact support on ...</p>
  <!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    
        ${ste} 
    </c:forEach>
  -->
</body>
</html>