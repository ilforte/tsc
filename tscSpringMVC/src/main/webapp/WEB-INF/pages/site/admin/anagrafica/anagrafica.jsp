<%@include file="/WEB-INF/pages/jspf/include.jspf" %> 
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="ajaxForm" %>

<spring:message code="label.anagrafica.success" text="label.anagrafica.success"  var="label.anagrafica.success"/>
<spring:message code="label.anagrafica.failure" text="label.anagrafica.failure"  var="label.anagrafica.failure"/>

<div id="anagraficContainer">
	<input data-bind="value: ab_codi, uniqueName: true"  readonly="readonly"/>
	<input data-bind="value: nominativo, uniqueName: true"  readonly="readonly"/>
</div>

<script type="text/javascript">
var viewModel = {
	    ab_codi: ko.observable(),
	    nominativo: ko.observable()
};

ko.applyBindings(viewModel,document.getElementById('anagraficContainer'));

function loadAnagrafica(data,url) {
	//Receiving data from the server<h1 data-bind="text: name"></h1>
	$.ajax({ 
		    url:url,  
		    data:JSON.stringify(data),
		    type:"POST", 
		    contentType: "application/json; charset=utf-8",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	            xhr.setRequestHeader(HEADER, TOKEN);
	        },
		    success: function(resposeJsonObject){
		        // Success Message Handler
	        	var parsed;
				try {
						parsed = JSON.parse(resposeJsonObject);
		    		} catch(e) {
		    			toastr.error(e);
		    	}
		        if (parsed.length >0) {
			        viewModel.ab_codi(parsed[0].ab_codi);
			        viewModel.nominativo(parsed[0].nominativo);
				}
		    }
		});
}
</script>