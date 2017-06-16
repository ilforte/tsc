<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/panel" prefix="panel" %>
<%@ taglib tagdir="/WEB-INF/tags/grid" prefix="grid" %>

<div class="row">
	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.proveDay" text="label.proveDay"/></div>
	<grid:grid
		id="proveDayGrid">
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
			{name: "ab_codi",type: "text",width:50},
	       	{name: "nominativo",type: "text",width:50},
	       	{name: "foglio",type:"text",width:45}
	    </jsp:attribute>
	</grid:grid>
</div>
<div class="row">
	<div id="gradient" style="color:black;text-align:center;"><spring:message code="label.nonFare" text="label.nonFare"/></div>
	<grid:grid
		id="DNDGrid">
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
			{name: "ab_codi",type: "text",width:50},
	       	{name: "nominativo",type: "text",width:50},
	       	{name: "foglio",type:"text",width:45}
	    </jsp:attribute>
	</grid:grid>
</div>