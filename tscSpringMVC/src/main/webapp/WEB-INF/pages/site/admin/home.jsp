<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<%@ taglib tagdir="/WEB-INF/tags/table" prefix="tables" %>
<%@ taglib tagdir="/WEB-INF/tags/modal" prefix="modal" %>


<div class="tab-content">
	  <div id="anagrafic" class="tab-pane fade mh-100">
		<h3>Anagrafica</h3>
	  </div>	  
	  <div id="rescuers" class="tab-pane fade mh-100">
	    <h3>Soccorritori</h3>
	  </div>
	  
	  <div id="user" class="tab-pane fade mh-100">
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
		  <div id="list-group" class="tab-pane fade mh-100">
		    <h3>Utente/Permessi</h3>
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
		  </div>
		  
		  <div id="add-user" class="tab-pane fade mh-100">
		  	<h3>Aggiungi Utente</h3>
	  		<tiles:insertTemplate template="addUser.jsp" />
		  </div>
		  
		  <div id="remove-user" class="tab-pane fade mh-100">
		  	<h3>Rimuovi Utente</h3>
		  </div>
	  </sec:authorize>	  
	  
	  <!-- logout -->
	  <div id="logout">

	  </div>
	  
	</div>