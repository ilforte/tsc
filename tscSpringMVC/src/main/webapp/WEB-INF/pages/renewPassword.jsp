<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<div id="login-box">
	<h2><spring:message code="label.password.renew" text=""/></h2>

	<c:if test="${not empty error}">
		<div class="error"><spring:message code="message.accessDenied" text="message.accessDenied"/></div>
	</c:if>

	<ajaxForm:form function="renewPassword" action="jsonRenewPassword" id="renewPassword"
	  failure_message="Failure for password renew service" success_message="Password renew succesfully requested" cssClass="form-horizontal">
	  	<jsp:attribute name="validateContent">
		    password: {
		        required: true,
		        minlength: 8
		      },
		    password_again: {
      			equalTo: "#password"
    		 }
		    },
		    messages: {
		      password: {
		        required: '<spring:message code="label.enter.password" text="label.enter.password" />',
		        minlength: '<spring:message code="label.enter.password.minlength" text="label.enter.password.minlength" />'
		      },
		      password: {
		        equalTo: '<spring:message code="label.enter.password.equalTo" text="label.enter.password.equalTo" />'
		      }
		</jsp:attribute>
		<jsp:attribute name="content">
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
			    <div class="help-block">Password not empty</div>
			</div>
		  </div>
		  
		  <div class="form-group row">
		  	<div class="col-xs-4">
		    	<label for="password" class="control-label label-sm">Passwod:</label>
			    <input type="text" class="form-control" id="password" name="password" >
			    <div class="help-block">Password not empty</div>
			</div>
		  </div>
		  
		  <div class="form-group row">
		  	<div class="col-xs-4">
		    	<label for="password_again" class="control-label label-sm">Ripeti Password:</label>
			    <input type="text" class="form-control" id="password_again" name="password_again" >
			    <div class="help-block">Ripeti Password not empty</div>
			</div>
		  </div>
		  
		  <input type="hidden" class="form-control" id="tmpPassword" name="tmpPassword" value="${tmpPassword}" required>
		  
		  <div class="form-group">
	        <button type="submit" class="btn btn-primary btn-sm">Invio</button>
	      </div>

	  </jsp:attribute>
	</ajaxForm:form>
</div>
