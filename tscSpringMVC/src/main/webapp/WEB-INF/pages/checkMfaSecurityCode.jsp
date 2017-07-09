<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

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
		<div class="message">Message: ${message}</div>
	</c:if>

	<h2><spring:message code="label.login.checkmfa" text="label.login.checkmfa"/></h2>
	
	<spring:url value="/checkMfaSecurityCode" var="userActionUrl" />
	
	<form name="checkMfaSecurityCode"
	  action="${userActionUrl}" method='GET' role="form" 
	  data-toggle="validator" >


	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label label-sm">mfaCode:</label>
		    <input type="text" class="form-control input-sm" id="mfaCode" name="mfaCode" required>
		    <div class="help-block">mfaCode not empty</div>
		  </div>
	  </div>
	  
	  <div class="form-group">
		<button type="submit" class="btn btn-primary btn-sm">Verifica MFA</button>
      </div>
      
	  <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
</div>
