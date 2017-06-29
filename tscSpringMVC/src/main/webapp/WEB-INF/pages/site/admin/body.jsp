<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<%@ taglib tagdir="/WEB-INF/tags/modal" prefix="modal" %>
<%@ taglib tagdir="/WEB-INF/tags/grid" prefix="grid" %>

<sec:authentication var="user" property="principal" />

<script type="text/javascript">
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	  var target = $(e.target).attr("href") // activated tab
	  console.log(target);
	  if (target=='user') {
		
	}
	  switch (target) {
		case '#user':
			/* load grid with user data */
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
						try {
			        			$("#userGrid").jsGrid({data:JSON.parse(resposeJsonObject)});
					    	} catch(e) {
					    		toastr.error(e);
					    }
				    }
				});
			break;
			
			case '#list-group':
			/* load grid for all access data */
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
						try {
				        		$("#allUserGrid").jsGrid({data:JSON.parse(resposeJsonObject)});
					    	} catch(e) {
					    		toastr.error(e);
					    }
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown) { 
				    	var message = "Status: " + textStatus + "<br>";
				    	message += "Error: " + errorThrown+ "<br>";
				    	toastr.error(message);
				    }       
				});
			break;
		default:
			break;
		}
	});
</script>

<!-- Tab panes -->
<div class="tab-content">
	  <div id="anagrafic" role="tabpanel" class="tab-pane active">
		<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.anagrafica" text="label.anagrafica"/></div>
		<tiles:insertTemplate template="anagrafica/anagrafica.jsp" flush="true"/>
	  </div>	  
	  <div id="rescuers" role="tabpanel" class="tab-pane">
	    <div id="gradient" style="color:black;text-align:center;"><spring:message code="label.soccorritori" text="label.soccorritori"/></div>
	  	<tiles:insertTemplate template="anagrafica/soccorritori.jsp" flush="true"/>
	  </div>
	  
	  <div id="user" role="tabpanel" class="tab-pane">
	    <div id="gradient" style="color:black;text-align:center;"><spring:message code="label.profilo.utente" text="label.profilo.utente"/></div>
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
		  <div id="list-group" role="tabpanel" class="tab-pane">
		    <div id="gradient" style="color:black;text-align:center;"><spring:message code="label.permessi.utente" text="label.permessi.utente"/></div>
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
			  
			  <div id="add-user" role="tabpanel" class="tab-pane">
			  	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.user.add" text="label.user.add"/></div>
		  		<tiles:insertTemplate template="user/addUser.jsp" flush="true"/>
			  </div>
			  <div id="remove-user" role="tabpanel" class="tab-pane">
			  	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.user.remove" text="label.user.remove"/></div>
			  	<tiles:insertTemplate template="user/removeUser.jsp" flush="true"/>
			  </div>
			  
			  <div id="add-group" role="tabpanel" class="tab-pane">
			  	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.group.add" text="label.group.add"/></div>
			  	<tiles:insertTemplate template="group/addGroup.jsp" flush="true"/>
			  </div>
			  
			  <div id="remove-group" role="tabpanel" class="tab-pane">
			  	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.group.remove" text="label.group.remove"/></div>
			  </div>
	  </sec:authorize>	  
	  
	  <!-- logout -->
	  <div id="logout">

	  </div>
	  
	</div>