<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<ajaxForm:form function="removePortalUserFunction" action="admin/userService/jsonRemoveUser" id="removePortalUser"
failure_message="Failure removing user" success_message="Remove Portal user succesfully" cssClass="form-inline">
  	<jsp:attribute name="validateContent">
		  username: 'required',
	      role: 'required'
	    },
	    messages: {
	      username: '<spring:message code="label.enter.username" text="label.enter.username" />',
	      role: '<spring:message code="label.enter.role" text="label.enter.role" />'
	</jsp:attribute>
	<jsp:attribute name="content">
		 <table>
			<tr>
                <td>
	              	<label class="control-label col-sm-2" for="username">
	              		<spring:message code="label.username" text="label.username" />
	              	</label>
               </td>
               <td>
			   		<ajaxForm:input type="text" cssClass="form-control input-sm" id="username" />
			   <td>
			</tr>
			<tr>
                <td>
	              	<label class="control-label col-sm-2" for="role">
	              		<spring:message code="label.role" text="label.role" />
	              	</label>
               </td>
               <td>
		            <select class="form-control input-sm" name="role" id="role">
					    <c:forEach items="${roles}" var="role">
					        <option value="${role.key}">${role.value}</option>
					    </c:forEach>
					</select>
               </td>
			</tr>
			<tr>
				<td>
					<button type="submit" id="btn-search" class="btn btn-primary btn-sm">
						<spring:message code="label.removeUser" text="label.removeUser" />
					</button>
                </td>
                <td>
					<button type="reset" id="btn-search" class="btn btn-primary btn-sm">
						<spring:message code="label.reset" text="label.reset" />
					</button>
				</td>
            </tr>
		 </table>
	</jsp:attribute>
</ajaxForm:form>
    