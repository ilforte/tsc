<%@page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
		<h2>Welcome : ${pageContext.request.userPrincipal.name}
        </h2>
		<c:url var="logoutUrl" value="/login?logout"/>
		<form action="${logoutUrl}"
			method="post">
		<input type="submit"
			value="Log out" />
		<input type="hidden"
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		</form>
</body>
</html>