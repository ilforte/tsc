<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 

<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<ajaxForm:form function="removePortalUserFunction" action="admin/userService/jsonRemoveUser" id="removePortalUser"
failure_message="Failure removing user" success_message="Remove Portal user succesfully" cssClass="form-horizontal">
  	<jsp:attribute name="validateContent">
		 username: 'required',
	      role: 'required'
	    },
	    messages: {
	      username: '<spring:message code="label.enter.username" text="label.enter.username" />',
	      role: '<spring:message code="label.enter.role" text="label.enter.role" />'
	</jsp:attribute>
	<jsp:attribute name="content">
		<div class="form-group">
              	<label class="control-label col-sm-2" for="username">
              		<spring:message code="label.username" text="label.username" />
              	</label>
			<div class="col-xs-4">
				<ajaxForm:input type="text" cssClass="form-control input-sm" id="username" />
			</div>
		</div>
		<div class="form-group">
              	<label class="control-label col-sm-2" for="role">
              		<spring:message code="label.role" text="label.role" />
              	</label>
			<div class="col-xs-4">
	            <select class="form-control input-sm" name="role">
				    <c:forEach items="${roles}" var="role">
				        <option value="${role.key}">${role.value}</option>
				    </c:forEach>
				</select>
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" id="btn-search" class="btn btn-primary btn-sm">
				<spring:message code="label.removeUser" text="label.removeUser" />
			</button>
			<button type="reset" id="btn-search" class="btn btn-primary btn-sm">
				<spring:message code="label.reset" text="label.reset" />
			</button>
		</div>
	</jsp:attribute>
</ajaxForm:form>
    