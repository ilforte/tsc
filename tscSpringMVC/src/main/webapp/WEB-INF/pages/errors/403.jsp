<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
You don' t have permission to access this page

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<h2>Welcome : ${pageContext.request.userPrincipal.name}
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