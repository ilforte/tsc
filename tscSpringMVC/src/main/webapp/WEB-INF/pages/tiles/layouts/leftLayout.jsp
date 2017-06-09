<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/panel" prefix="panel" %>
<%@ taglib tagdir="/WEB-INF/tags/grid" prefix="grid" %>

<script type="text/javascript">
var allarmUser = '${pageContext.request.userPrincipal.name}';

/* row callback */
function allarmGridRowClick(args,url) {
	var allarm = args.item;
	var allarmData = {serial_uuid:allarm.serial_uuid,ab_codi:allarm.ab_codi};
	url = '${pageContext.request.contextPath}' + url;

	if (allarm.user==='') {
		$.ajax({ 
		    url:url,  
		    data:JSON.stringify(allarmData),
		    type:"POST", 
		    contentType: "application/json; charset=utf-8",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	            xhr.setRequestHeader(HEADER, TOKEN);
	        },
		    success: function(resposeJsonObject){
		        // Success Message Handler
		        var message = 'Allarme </br>';
		        message += resposeJsonObject.serial_uuid + '</br>';
		        message += 'codice: ' + resposeJsonObject.ab_codi + '</br>';
		        message += 'Chiuso con successo </br>';
		        message += 'Gestito da <b>' + PORTAL_USER + '</b></br>';
		        toastr.success(message);
		    }
		});
	}else {
		toastr.info(PORTAL_USER);
	}
}

/* row callback */
function proveGridRowClick(args,url) {
	var allarm = args.item;
	var allarmData = {serial_uuid:allarm.serial_uuid,ab_codi:allarm.ab_codi};
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	url = '${pageContext.request.contextPath}' + url;

	if (allarm.user==='') {
		$.ajax({ 
		    url:url,  
		    data:JSON.stringify(allarmData),
		    type:"POST", 
		    contentType: "application/json; charset=utf-8",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	            xhr.setRequestHeader(header, token);
	        },
		    success: function(resposeJsonObject){
		        // Success Message Handler
		        var message = 'Allarme </br>';
		        message += resposeJsonObject.serial_uuid + '</br>';
		        message += 'codice: ' + resposeJsonObject.ab_codi + '</br>';
		        message += 'Chiuso con successo </br>';
		        message += 'Gestito da <b>' + allarmUser + '</b></br>';
		        toastr.success(message);
		    }
		});
	}else {
		
	}
}
</script>

<div class="row">
	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.allarmi" text=""/></div>
	<grid:grid
		id="allarmGrid"
		callbackFunction="allarmGridRowClick(args,'/user/allarmService/updateAllarm');">
		<jsp:attribute name="options">
			height:"50%",
			width: "100%",
		    filtering: false,
		    autoload: true,
		    editing: false,
		    sorting: true,
		    selecting:true
		</jsp:attribute>
	    <jsp:attribute name="fields">
			{name: "ab_codi",type: "text",width:15,
			    itemTemplate: function(value,item) {
			    	var tooltip = $('<div>' + value + '</div>').attr("title",item.ab_codi);
			        return tooltip;
			    }
			},
	       	{name: "nominativo",type:"text",visible:false,width:0},
	       	{name: "serial_uuid",type:"text",visible:false,width:0},
			{name: "data_arrivo",type:"date",width:15,
			    itemTemplate: function(value,item) {
			    	var tooltip = $('<div>' + value.slice(11,19) + '</div>').attr("title",item.serial_uuid);
			        return tooltip;
			    }
	       				},
	       	{name: "evento",type:"text",width:10},
	       	{name: "user",type:"text",width: 15}
	    </jsp:attribute>
	</grid:grid>
</div>
<div class="row">
	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.prove" text=""/></div>
	<grid:grid
		id="proveGrid"
		callbackFunction="proveGridRowClick(args,'/user/allarmService/updateAllarm');">
		<jsp:attribute name="options">
			height:"50%",
			width: "100%",
		    filtering: false,
		    autoload: true,
		    editing: false,
		    sorting: true,
		    selecting:true
		</jsp:attribute>
	    <jsp:attribute name="fields">
			{name: "ab_codi",type: "text",width:15,
			    itemTemplate: function(value,item) {
			    	var tooltip = $('<div>' + value + '</div>').attr("title",item.ab_codi);
			        return tooltip;
			    }
			},
	       	{name: "nominativo",type:"text",visible:false,width:0},
	       	{name: "serial_uuid",type:"text",visible:false,width:0},
			{name: "data_arrivo",type:"date",width:15,
			    itemTemplate: function(value,item) {
			    	var tooltip = $('<div>' + value.slice(11,19) + '</div>').attr("title",item.serial_uuid);
			        return tooltip;
			    }
	       				},
	       	{name: "evento",type:"text",width:10},
	       	{name: "user",type:"text",width: 15}
	    </jsp:attribute>
	</grid:grid>
</div>