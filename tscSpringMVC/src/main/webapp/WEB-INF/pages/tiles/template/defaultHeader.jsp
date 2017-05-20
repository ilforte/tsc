<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<ul class="nav nav-tabs" role="tablist" id="tabContainer">
  <li class="active"><a href="#anagrafic" data-toggle="tab" ><spring:message code="label.anagrafic" /></a></li>
  <li><a href="#rescuers" data-toggle="tab" ><spring:message code="label.rescuers" />        </a></li> 
  <c:if test="${pageContext.request.userPrincipal.name != null}">
  	<li><a href="#user" data-toggle="tab" >profili:${pageContext.request.userPrincipal.name}</a></li>
  </c:if>
  <sec:authorize access="hasRole('ADMIN')">
<li>
	<a href="#list-group" data-toggle="tab">
	<spring:message code="label.list-group" text="Gruppi"/>
	</a>
</li>
<li>
	<a href="#add-user" data-toggle="tab">
	<spring:message code="label.add-user" text="Aggiungi Utente"/>
	</a>
</li> 
<li>
	<a href="#remove-user" data-toggle="tab">
	<spring:message code="label.remove-user" text="Rimuovi Utente"/>
	</a>
</li> 
</sec:authorize>
<li>
	<c:set var="image" >
		<c:url value='/resources/img/logout.png' />
	</c:set>
	<a href="#logout" style="background:url('${image}') left no-repeat;display:block;" data-toggle="tab">
	<spring:message code="label.logout" text="Logout"/>
	</a>
</li>     
</ul>
	