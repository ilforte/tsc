<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<div id="login-box">
	<h2><spring:message code="label.password.asknew" text=""/></h2>


<ajaxForm:form function="askNewPasswordForm" action="jsonAskNewPassword" id="addPortalUser"
	failure_message="Password [errore richiesta]" success_message="Password [effettuata richiesta con successo] attendere email conferma" 
	cssClass="form-inline" >
  	<jsp:attribute name="validateContent">
		 username: 'required',
     	    email: {
        	required: true,
	        email: true
    		}
	    },
	    messages: {
	      username: '<spring:message code="label.enter.username" text="label.enter.username" />',
	      email: '<spring:message code="label.enter.email" text="label.enter.email" />'
	</jsp:attribute>
	<jsp:attribute name="content">
		 <table>
			<tr>
                <td>
                	<span class="label label-primary label-sm">
                		<spring:message code="label.username" text="" />
                	</span>
                </td>
                <td><ajaxForm:input type="text" cssClass="form-control input-sm" id="username" /></td>
            </tr>
			<tr>
                <td>
                	<span class="label label-primary label-sm">
                		<spring:message code="label.email" text="" />
                	</span>
                </td>
                <td><ajaxForm:input type="text" cssClass="form-control input-sm" id="email" /></td>
            </tr>
		 </table>
		<button type="submit" id="btn-search"
						class="btn btn-primary btn-sm"><spring:message code="label.askNewPassword" text="label.askNewPassword" />
		</button>
	</jsp:attribute>
</ajaxForm:form>
</div>
