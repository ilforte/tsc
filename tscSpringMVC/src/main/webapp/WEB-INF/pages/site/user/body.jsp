<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<%@ taglib tagdir="/WEB-INF/tags/modal" prefix="modal" %>
<%@ taglib tagdir="/WEB-INF/tags/grid" prefix="grid" %>

<script type="text/javascript">
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	  var target = $(e.target).attr("href") // activated tab
	  console.log(target);
	  if (target=='user') {
		
	}
	  switch (target) {
		case '#user':
			/* load grid */
				var data = {name:PORTAL_USER};
				$.ajax({ 
				    url:addContextPath('/user/userService/jsonGetUser'),  
				    type:"GET", 
				    contentType: "application/json; charset=utf-8",
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			            xhr.setRequestHeader(HEADER, TOKEN);
			        },
				    success: function(resposeJsonObject){
				        // Success Message Handler
				        console.log('resposeJsonObject %O',JSON.parse(resposeJsonObject));
				        $("#userGrid").jsGrid({data:JSON.parse(resposeJsonObject)});
				    }
				});
			break;
			
			case '#list-group':
			/* load grid */
				var data = {name:PORTAL_USER};
				$.ajax({ 
				    url:addContextPath('/admin/userService/jsonGetAllUsers'),  
				    type:"GET", 
				    contentType: "application/json; charset=utf-8",
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			            xhr.setRequestHeader(HEADER, TOKEN);
			        },
				    success: function(resposeJsonObject){
				        // Success Message Handler
				        console.log('resposeJsonObject %O',resposeJsonObject);
				        $("#allUserGrid").jsGrid({data:JSON.parse(resposeJsonObject)});
				    }
				});
			break;
		default:
			break;
		}
	});
</script>


<div class="tab-content">
	  <div id="anagrafic" class="tab-pane fade mh-100">
		<h5>Anagrafica</h5>
	  </div>	  
	  <div id="rescuers" class="tab-pane fade mh-100">
	    <h5>Soccorritori</h5>
	  </div>
	  
	  <div id="user" class="tab-pane fade mh-100">
	    <h5>Profilo utente</h5>
			<grid:grid
				id="userGrid">
				<jsp:attribute name="options">
					height:"100%",
					width: "100%",
				    filtering: false,
				    autoload: true,
				    editing: false,
				    sorting: false,
				    selecting:false
				</jsp:attribute>
			    <jsp:attribute name="fields">
					{name: "username",type:"text",width:30,title:"nome"},
			       	{name: "role",type:"text",width:30,title:"ruolo"},
			       	{name: "email",type:"text",width: 30,title:"email"}
			    </jsp:attribute>
			</grid:grid>
	  </div>
	  
	  <!-- profile admin -->
	  
	  <sec:authorize access="hasRole('ADMIN') or hasRole('SADMIN')">
		  <div id="list-group" class="tab-pane fade mh-100">
		    <h5>Permessi utente</h5>
				<grid:grid
					id="allUserGrid">
					<jsp:attribute name="options">
						height:"100%",
						width: "100%",
					    filtering: false,
					    autoload: true,
					    editing: false,
					    sorting: false,
					    selecting:false
					</jsp:attribute>
				    <jsp:attribute name="fields">
						{name: "username",type:"text",width:30,title:"nome"},
				       	{name: "role",type:"text",width:30,title:"ruolo"},
				       	{name: "email",type:"text",width: 30,title:"email"}
				    </jsp:attribute>
				</grid:grid>
			  </div>
			  
			  <div id="add-user" class="tab-pane fade mh-100">
			  	<h5>Aggiungi Utente</h5>
		  		<tiles:insertTemplate template="addUser.jsp" />
			  </div>
			  
			  <div id="remove-user" class="tab-pane fade mh-100">
			  	<h5>Rimuovi Utente</h5>
			  </div>
	  </sec:authorize>	  
	  
	  <!-- logout -->
	  <div id="logout">

	  </div>
	  
	</div>