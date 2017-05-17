<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
	#container {
	    width:100%;
	    text-align:center;
	}
	
	#left {
	    float:left;
	    width:29%;<sec:authorize access="hasRole('ADMIN')">
			This content will only be visible to users who have
			the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
		</sec:authorize>
	}
	
	#center {
	    display: inline-block;
	    margin:0 auto;
	    width:50%;
	}
	
	#right {container
	    float:right;
	}
</style>
<div id="container">
	<div id="left"><h1>default Header Tiles Demo</h1>
<%-- 		<sec:authorize access="hasRole('ADMIN')">
			This content will only be visible to users who have
			the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
		</sec:authorize> --%>
	</div>
    <div id="center">	
    	<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>User : ${pageContext.request.userPrincipal.name}
	        </h2>
		</c:if>
	</div> 
    <div id="right">	
    	<c:if test="${pageContext.request.userPrincipal.name != null}">
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
	</div>  
</div>