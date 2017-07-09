<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

  <style>
    .mainContent {
      float:left;
      width:220px;
      height:220px;
    }
  </style>
  
<c:set var="generateQrCode" scope="session" value="${generateQrCode}"/>

<div id="login-box">
	<c:if test="${not empty error}">
		<div class="error">Error: ${error}</div>
	</c:if>
	
	<c:if test="${not empty message}">
		<div class="message">Message: ${message}</div>
	</c:if>

	<h2><spring:message code="label.login.addmfa" text="label.login.addmfa"/></h2>
	
	<spring:url value="/addMfaSecurityCode" var="userActionUrl" />
	
	<form name="addMfaSecurityCode"
	  action="${userActionUrl}" method='GET' role="form" 
	  data-toggle="validator" >
	  
	  <div class="form-group row">
	  	<div class="col-xs-4">
	    	<label for="password" class="control-label label-sm">Qr Code:</label>
			<iframe class="mainContent" src="${qrcode_url}"></iframe>
		</div>
	  </div>

	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label label-sm">mfaCode:</label>
		    <input type="text" class="form-control input-sm" id="mfaCode" name="mfaCode" required>
		    <div class="help-block">mfaCode not empty</div>
		  </div>
	  </div>
	  
	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label label-sm">keyId:</label>
		    <input type="text" class="form-control input-sm" id="keyId" name="keyId" value="${keyId}" readonly="readonly">
		    <div class="help-block">mfaCode not empty</div>
		  </div>
	  </div>
	  
	<c:choose>
		<c:when test="${generateQrCode eq true}">
			<input type="hidden" class="form-control input-sm" id="login" name="login" value="false">
		</c:when>
		<c:otherwise>
			<input type="hidden" class="form-control input-sm" id="login" name="login" value="true">
	  </c:otherwise>
	</c:choose>
	  
	  <input type="hidden" class="form-control input-sm" id="generatedBase32Secret" name="generatedBase32Secret" value="${generatedBase32Secret}">
	  <div class="form-group">
		<c:choose>
			<c:when test="${generateQrCode eq true}">
			  <button type="submit" class="btn btn-primary btn-sm">Registra QR code</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-primary btn-sm">Verifica MFA</button>
		  </c:otherwise>
		</c:choose>
	  
        
      </div>
      
	  <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
</div>
