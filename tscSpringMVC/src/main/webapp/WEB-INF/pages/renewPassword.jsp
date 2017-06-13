<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<div id="login-box">
	<h2><spring:message code="label.password.renew" text=""/></h2>

	<c:if test="${not empty error}">
		<div class="error"><spring:message code="message.accessDenied" text=""/></div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg"><spring:message code="message.logout" text=""/></div>
	</c:if>

	<form name='renewPasswordForm'
	  action="<c:url value='j_spring_security_check' />" method='POST' role="form" 
	  data-toggle="validator" >


	  <div class="form-group row">
		  <div class="col-xs-4">
		    <label for="username" class="control-label label-sm">Username:</label>
		    <input type="text" class="form-control input-sm" id="username" name="username" value="${username}" readonly required>
		    <div class="help-block">Username not empty</div>
		  </div>
	  </div>
	  
	  <div class="form-group row">
	  	<div class="col-xs-4">
	    	<label for="password" class="control-label label-sm">Email:</label>
		    <input type="text" class="form-control" id="email" name="email" value="${email}" required>
		    <div class="help-block">Email not empty</div>
		</div>
	  </div>
	  
	  <input type="hidden" class="form-control" id="tmpPassword" name="tmpPassword" value="${tmpPassword}" required>
	  
	  <div class="form-group">
        <button type="submit" class="btn btn-primary btn-sm">Invio</button>
      </div>

	  <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
</div>
