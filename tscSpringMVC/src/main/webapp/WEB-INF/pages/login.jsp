<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<div id="login-box">
	<h2>Login with Username and Password</h2>

	<c:if test="${not empty error}">
		<div class="error"><spring:message code="error.login" text=""/></div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg"><spring:message code="message.logout" text=""/></div>
	</c:if>

	<form name='loginForm'
	  action="<c:url value='j_spring_security_check' />" method='POST' role="form" 
	  data-toggle="validator" >


	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label">Username:</label>
		    <input type="text" class="form-control" id="username" name="username" required>
		    <div class="help-block">Username not empty</div>
		  </div>
	  </div>
	  
	  <div class="form-group row">
	  	<div class="col-xs-4">
	    	<label for="password" class="control-label">Password:</label>
		    <input type="password" class="form-control" id="password" name="password" required>
		    <div class="help-block">Password not empty</div>
		</div>
	  </div>
	  
	  <div class="form-group">
        <button type="submit" class="btn btn-primary">Invio</button>
      </div>

	  <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
</div>
