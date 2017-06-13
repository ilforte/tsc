<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<html>
<head>
<title>${title}</title>
</head>
<body>${message}
	<br>
	<div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
		Click per il login <a
			href="<c:url value='/user' />">qui</a>
	</div>
</body>
</html>