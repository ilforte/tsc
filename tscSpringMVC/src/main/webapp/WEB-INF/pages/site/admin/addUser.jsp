<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<ajaxForm:form function="addPortalUserFunction" action="/admin/userService/jsonAddUser" id="addPortalUser"
	failure_message="Failure adding user" success_message="Add Portal user succesfully" >
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
                		<spring:message code="label.password" text="" />
                	</span>
                </td>
                <td><ajaxForm:input type="text" cssClass="form-control input-sm" id="password" /></td>
            </tr>
			<tr>
                <td>
                	<span class="label label-primary label-sm">
                		<spring:message code="label.email" text="" />
                	</span>
                </td>
                <td><ajaxForm:input type="text" cssClass="form-control input-sm" id="email" /></td>
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
						class="btn btn-primary btn-sm"><spring:message code="label.addUser" text="" />
		</button>
	</jsp:attribute>
</ajaxForm:form>
    