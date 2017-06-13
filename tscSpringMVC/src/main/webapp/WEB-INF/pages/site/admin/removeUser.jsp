<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<ajaxForm:form function="removePortalUserFunction" action="/admin/userService/jsonRemoveUser" id="removePortalUser"
failure_message="Failure removing user" success_message="Remove Portal user succesfully">
	<jsp:attribute name="content">
		 <table>
			<tr>
                <td>
                	<span class="label label-primary label-sm">
                		<spring:message code="label.username" text="" />
                	</span>
                </td>
                <td><input type="text" class="form-control input-sm" id="username" name="username" ></td>
            </tr>
			<tr>
                <td>
                	<span class="label label-primary label-sm">
                		<spring:message code="label.role" text="" />
                	</span>
                </td>
                <td>
		            <select class="form-control input-sm" name="role">
					    <c:forEach items="${roles}" var="role">
					        <option value="${role.key}">${role.value}</option>
					    </c:forEach>
					</select>
                </td>
            </tr>
		 </table>
		<button type="submit" id="btn-search"
						class="btn btn-primary btn-sm"><spring:message code="label.removeUser" text="" /></button>
	</jsp:attribute>
</ajaxForm:form>
    