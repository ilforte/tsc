<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<div id="login-box">
	<h2><spring:message code="label.login.access" text=""/></h2>

	<c:if test="${not empty error}">
		<div class="error"><spring:message code="message.accessDenied" text=""/></div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg"><spring:message code="message.logout" text=""/></div>
	</c:if>

	<form name='loginForm'
	  action="<c:url value='j_spring_security_check' />" method='POST' role="form" 
	  data-toggle="validator" >


	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label label-sm">Username:</label>
		    <input type="text" class="form-control input-sm" id="username" name="username" required>
		    <div class="help-block">Username not empty</div>
		  </div>
	  </div>
	  
	  <div class="form-group row">
	  	<div class="col-xs-4">
	    	<label for="password" class="control-label label-sm">Password:</label>
		    <input type="password" class="form-control input-sm" id="password" name="password" required>
		    <div class="help-block">Password not empty</div>
		</div>
	  </div>
	  
	  <div class="form-group">
        <button type="submit" class="btn btn-primary btn-sm">Invio</button>
      </div>
      
      <a href="<c:url value='/askNewPassword' />"  rel="stylesheet" media="screen"><spring:message code="label.password.forgotten" text="Non ricordo la password"/></a>

	  <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
</div>
