<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
You don' t have permission to access this page

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<h2>Authenticated user : ${pageContext.request.userPrincipal.name}
       </h2>
	<c:url var="logoutUrl" value="/logout"/>
	<form action="${logoutUrl}"
		method="post">
	<input type="submit"
		value="Log out" />
	<input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>
	</form>
</c:if>