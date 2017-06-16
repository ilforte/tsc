<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<div class="container">
	<ajaxForm:form function="addPortalUserFunction" action="admin/userService/jsonAddUser" id="addPortalUser"
		failure_message="Failure adding user" success_message="Add Portal user succesfully" cssClass="form-horizontal" >
	  	<jsp:attribute name="validateContent">
			username: 'required',
			role: 'required',
		    email: {
		        required: true,
		        email: true
		    },
		    password: {
		        required: true,
		        minlength: 8
		      }
		    },
		    messages: {
		      username: '<spring:message code="label.enter.username" text="label.enter.username" />',
		      password: {
		        required: '<spring:message code="label.enter.password" text="label.enter.password" />',
		        minlength: '<spring:message code="label.enter.password.minlength" text="label.enter.password.minlength" />'
		      },
		      email: '<spring:message code="label.enter.email" text="label.enter.email" />',
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
	                	<label class="control-label col-sm-2" for="password">
	                		<spring:message code="label.password" text="label.password" />
	                	</label>
						<div class="col-xs-4">
							<ajaxForm:input type="text" cssClass="form-control input-sm" id="password" />
						</div>
					</div>
					<div class="form-group">
	                	<label class="control-label col-sm-2" for="email">
	                		<spring:message code="label.email" text="label.email" />
	                	</label>
						<div class="col-xs-4">
							<ajaxForm:input type="text" cssClass="form-control input-sm" id="email"/>
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
							<spring:message code="label.addUser" text="label.addUser" />
						</button>
						<button type="reset" id="btn-search" class="btn btn-primary btn-sm">
							<spring:message code="label.reset" text="label.reset" />
						</button>
					</div>
		</jsp:attribute>
	</ajaxForm:form>
</div>
    