<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/modal" prefix="modal" %>

<script type="text/javascript">
$('#portalTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
</script>

<style>
/* 	.nav>li>a {
	    padding-top: 3px;
	    padding-bottom: 3px;
	    padding-left: 3px;
	    padding-right: 3px;
	} */
.nav-tabs { border-bottom: 2px solid #DDD; }
    .nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover { border-width: 0; }
    .nav-tabs > li > a { border: none; color: #666; }
        .nav-tabs > li.active > a, .nav-tabs > li > a:hover { border: none; color: #4285F4 !important; background: transparent; }
        .nav-tabs > li > a::after { content: ""; background: #4285F4; height: 2px; position: absolute; width: 100%; left: 0px; bottom: -1px; transition: all 250ms ease 0s; transform: scale(0); }
    .nav-tabs > li.active > a::after, .nav-tabs > li:hover > a::after { transform: scale(1); }
.tab-nav > li > a::after { background: #21527d none repeat scroll 0% 0%; color: #fff; }
.tab-pane { padding: 0px 0; }
.tab-content{padding:0px}
</style>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist" id="portalTab">
  <li class="active"><a href="#anagrafic" data-toggle="tab" ><spring:message code="label.anagrafic" /></a></li>
  <li><a href="#rescuers" data-toggle="tab" ><spring:message code="label.rescuers" />        </a></li> 
  <c:if test="${pageContext.request.userPrincipal.name != null}">
  	<li><a href="#user" data-toggle="tab" >profili:${pageContext.request.userPrincipal.name}</a></li>
  </c:if>
  <sec:authorize access="hasRole('ADMIN') or hasRole('SADMIN')">
	<li>
		<a href="#list-group" data-toggle="tab">
		<spring:message code="label.list-group" text="label.list-group"/>
		</a>
	</li>
	<li>
		<a href="#add-user" data-toggle="tab">
		<spring:message code="label.add-user" text="label.add-user"/>
		</a>
	</li> 
	<li>
		<a href="#remove-user" data-toggle="tab">
		<spring:message code="label.remove-user" text="Rimuovi Utente"/>
		</a>
	</li> 
	<li>
		<a href="#add-group" data-toggle="tab">
		<spring:message code="label.add-group" text="label.add-group"/>
		</a>
	</li> 
	<li>
		<a href="#remove-group" data-toggle="tab">
		<spring:message code="label.remove-group" text="label.remove-group"/>
		</a>
	</li>
</sec:authorize>
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
				callback_function="$('#logoutForm').submit();">
				
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


	