<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<div class="tab-content">

	  <div id="anagrafic" class="tab-pane fade in active">
	    <h3>Anagrafica</h3>
	  </div>
	  
	  <div id="rescuers" class="tab-pane fade">
	    <h3>Soccorritori</h3>
	  </div>
	  
	  <div id="user" class="tab-pane fade">
	    <h3>Utente</h3>
		  	<tables:tables id="user-table" 
		  	data_toggle="tab" 
		  	data_url="${pageContext.request.contextPath}/user/userService/jsonGetUser" >
				columns: [{
			        field: 'username',
			        title: 'Nome utente'
			    }, {
			        field: 'role',
			        title: 'Ruolo'
			    }, {
			        field: 'email',
			        title: 'email'
			    }]
		  	</tables:tables>
	  </div>
	  
	  <sec:authorize access="hasRole('ADMIN')">
		  <div id="list-group" class="tab-pane fade">
		  	<tables:tables id="all-user-table" 
		  	data_toggle="tab" 
		  	data_url="${pageContext.request.contextPath}/admin/userService/jsonGetAllUsers" >
				columns: [{
			        field: 'username',
			        title: 'Nome utente',
			        class:'col-sm-2'
			    }, {
			        field: 'role',
			        title: 'Ruolo',
			        class:'col-sm-2'
			    }, {
			        field: 'email',
			        title: 'email',
			        class:'col-sm-2'
			    }]
		  	</tables:tables>
		    <h3>Utente/Permessi</h3>
		  </div>
		  
		  <div id="add-user" class="tab-pane fade">
	  		<%-- <tiles:insertTemplate template="addUser.jsp" /> --%>
		  </div>
		  
		  <div id="remove-user" class="tab-pane fade">
		  
		  </div>
	  </sec:authorize>	  
	  
	  <!-- logout -->
	  <div id="logout">
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
	  </div>
	  
	</div>