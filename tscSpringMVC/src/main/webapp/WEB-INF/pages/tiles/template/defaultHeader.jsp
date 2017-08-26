<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/modal" prefix="modal" %>

<script type="text/javascript">
$('#portalTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
</script>

<!-- Nav tabs -->
<ul class="nav nav-tabs" id="portalTab">
    <li class="dropdown">
	    <a class="dropdown-toggle" data-toggle="dropdown" href="#noAction1">
	    <spring:message code="label.tsc" text="label.tsc"/><span class="caret"></span></a>
	        <ul class="dropdown-menu">
	            <li><a href="#anagrafic" data-toggle="tab"><spring:message code="label.anagrafic" /></a></li>
	            <li><a href="#rescuers"  data-toggle="tab" ><spring:message code="label.rescuers" /></a></li>
	        </ul>
	    </li>
    <li class="dropdown">
	    <a class="dropdown-toggle" data-toggle="dropdown" href="#noAction2">
	    <spring:message code="label.profile" text="label.profile" /><span class="caret"></span></a>
	        <ul class="dropdown-menu">
	            <li><a href="#user" data-toggle="tab" ><spring:message code="label.profile" text="label.profile" />:${pageContext.request.userPrincipal.name}</a></li>
	            <sec:authorize access="hasRole('ADMIN') or hasRole('SADMIN')">
	            	<li><a href="#list-group"  data-toggle="tab" ><spring:message code="label.list-group" text="label.list-group"/></a></li>
	            </sec:authorize>
	        </ul>
    </li>
    <sec:authorize access="hasRole('ADMIN') or hasRole('SADMIN')">
	    <li class="dropdown">
		    <a class="dropdown-toggle" data-toggle="dropdown" href="#noAction2">
		    <spring:message code="label.user.management" text="label.user.management" /><span class="caret"></span></a>
		        <ul class="dropdown-menu">
		            <li><a href="#add-user" data-toggle="tab" ><spring:message code="label.add-user" text="label.add-user"/></a></li>
		        	<li><a href="#remove-user" data-toggle="tab" ><spring:message code="label.remove-user" text="label.remove-user"/></a></li>
					<li><a href="#add-group" data-toggle="tab"><spring:message code="label.add-group" text="label.add-group"/></a></li> 
					<li><a href="#remove-group" data-toggle="tab"><spring:message code="label.remove-group" text="label.remove-group"/></a></li> 
		        </ul>
	    </li>
    </sec:authorize>
    <!-- logout -->
	<li>
		<c:set var="image" >
			<c:url value='/resources/images/logout.png' />
		</c:set>
		<a href="#logout" style="background:url('${image}') left no-repeat;display:block;" 
			data-toggle="modal" data-target="#logoutModal" >
				<spring:message code="label.logout" text="Logout"/>
				<!-- modal window -->
				<modal:modal button_close="Chiudi" button_send="Logout" 
					text="Si vuole effettuare il logout?" title="Logout" id="logoutModal"
					callback_function="$('#logoutForm').submit();" formModal="true">
					
						<!-- logout form -->
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<div id="logoutFormDiv" style="display: none;">
								<c:url var="logoutUrl" value="/logout"/>
								<form action="${logoutUrl}" method="post" id="logoutForm" >
									<input type="hidden"
										name="${_csrf.parameterName}"
										value="${_csrf.token}"/>
								</form>
							</div>
						</c:if>
				</modal:modal>
		</a>
	</li>
</ul>



	