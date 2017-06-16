<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<div id="renew-password-box">
	<h2><spring:message code="label.password.renew" text=""/></h2>
	<ajaxForm:form function="renewPassword" action="jsonRenewPassword" id="renewPassword"
	  failure_message="Failure for password renew service" success_message="Password renew succesfully requested" cssClass="form-inline">
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
		 <table>
			<tr>
                <td>
			    	<label for="username" class="control-label label-sm">Username:</label>
                </td>
                <td>
			    	<input type="text" class="form-control input-sm" id="username" name="username" value="${username}" readonly required>
				</td>
			</tr>
			<tr>
                <td>
			    	<label for="password" class="control-label label-sm">Email:</label>
                </td>
                <td>
				    <input type="text" class="form-control" id="email" name="email" value="${email}">
                </td>
			</tr>
			<tr>
                <td>
		    		<label for="password" class="control-label label-sm">Passwod:</label>
                </td>
                <td>
			    	<input type="text" class="form-control" id="password" name="password" >
				</td>
			</tr>
			<tr>
                <td>
			    	<label for="password_again" class="control-label label-sm">Ripeti Password:</label>
                </td>
                <td>
				    <input type="text" class="form-control" id="password_again" name="password_again" >
		  			<input type="hidden" class="form-control" id="tmpPassword" name="tmpPassword" value="${tmpPassword}" required>
                </td>
			</tr>
			<tr>
                <td>
	        		<button type="submit" class="btn btn-primary btn-sm"><spring:message code="label.button.send" text="label.button.send" /></button>
				</td>
            </tr>
		 </table>	  
		</jsp:attribute>
	</ajaxForm:form>
</div>
