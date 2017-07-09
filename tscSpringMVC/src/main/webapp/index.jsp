<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Hello World!</h2>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
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
	</c:if>
</body>
</html>
