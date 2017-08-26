<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/modal" prefix="modal" %>

  <style>
    .mainContent {
      float:left;
      width:250px;
      height:250px;
    }
  </style>

<div id="check-mfa-box">
	<c:if test="${not empty error}">
		<div class="error">Error: ${error}</div>
	</c:if>
	
	<c:if test="${not empty message}">
		<div class="msg">Message: ${message}</div>
	</c:if>

	<h2><spring:message code="label.login.checkmfa" text="label.login.checkmfa"/></h2>
	
	<spring:url value="/checkMfaSecurityCode" var="userActionUrl" />
	
	<form name="checkMfaSecurityCode"
	  action="${userActionUrl}" method='GET' role="form" 
	  data-toggle="validator" >

	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="user" class="control-label label-sm">User:${pageContext.request.userPrincipal.name}</label>
		  </div>
	  </div>

	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label label-sm">mfaCode:</label>
		    <input type="text" class="form-control input-sm" id="mfaCode" name="mfaCode" required>
		    <div class="help-block">mfaCode not empty</div>
		  </div>
	  </div>
	  
	  <div class="btn-toolbar">
		<button type="submit" class="btn btn-primary btn-sm"><spring:message code="label.login.verifymfa" text="label.login.verifymfa"/></button>
		<button type="submit" class="btn btn-primary btn-sm" onclick="$('#logoutForm').submit();">Logout</button>
     	<button type="submit" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#resetmfaModal" ><spring:message code="label.login.resetmfa" text="label.login.resetmfa"/></button>
		<!-- modal window -->
		<modal:modal button_close="Chiudi" button_send="Reset mfa" 
			text="Si vuole resettare il codice mfa?" title="Reset mfa" id="resetmfaModal"
			callback_function="$('#resetmfa').submit();" formModal="false">
		</modal:modal>
      </div>
      
	  <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
</div>

<spring:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}"
	method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<spring:url value="/resetmfa" var="resetmfa" />
<form action="${resetmfa}"
	method="post" id="resetmfa">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>



