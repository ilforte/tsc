<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<spring:message code="label.anagrafica.success" text="label.anagrafica.success"  var="label.anagrafica.success"/>
<spring:message code="label.anagrafica.failure" text="label.anagrafica.failure"  var="label.anagrafica.failure"/>

<ajaxForm:form function="anagraficaFunction" action="user/userService/jsonAddUser" id="anagraficaForm"
	failure_message="${label.anagrafica.success}" success_message="${label.anagrafica.failure}" cssClass="form-inline" >
	
</ajaxForm:form>


${sessionScope.ab_codi}
<button type="button" id="changetabbutton" class="btn btn-primary btn-sm">Set second tab</button>
<script type="text/javascript">
    $('#changetabbutton').click(function(e){
    	e.preventDefault();
    	swowTab('portalTab','list-group');
    })
</script>