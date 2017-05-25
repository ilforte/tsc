<%@include file="/WEB-INF/pages/jspf/include.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags/panel" prefix="panel" %>
<%@ taglib tagdir="/WEB-INF/tags/grid" prefix="grid" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- ... -->
    <title><tiles:getAsString name="title" /></title>
    <tiles:insertAttribute name="css-scripts" />
</head>
  
<%-- 
Current Locale : ${pageContext.response.locale} --%>

<script type="text/javascript">

</script>

<body>

	<div class="panel panel-default">
		<!-- header -->
		<div class="panel-heading">
				<tiles:insertAttribute name="header" />
		</div>
		<!-- body -->
		<div class="panel-body">
			<div id="container">
			<div id="left">left</div>
			
<%-- 				<panel:resizable height="100%" minWidth="200px" 
					width="25%" maxWidth="600px%" maxHeight="100%" minHeight="200px" id="left-panel" 
					style="float:left;top: 0;left: 0;z-index: 999;" >
 					<grid:grid
						height="50%" width="100%" id="allarmsGrid"
						style=" height: 50%;
							    width: 100%;
							    position: absolute;
							    right: 0;
							    top: 0;">
					    { name: "AB CODI", type: "text", width: 40 },
					    { name: "Nominativo", type: "text", width: 75 },
					    { name: "Allarme", width: 25,type: "text"}
					</grid:grid>
 					<grid:grid
					    height="50%" width="100%" id="testGrid"
						style=" height: 50%;
							    width: 100%;
							    right: 0;
							    top: 0;inline-block;">
					    { name: "AB CODI", type: "text", width: 40 },
					    { name: "Nominativo", type: "text", width: 75 },
					    { name: "Allarme", width: 25,type: "text"}
					</grid:grid>
				</panel:resizable> --%>
				
				<div id="center">
					<tiles:insertAttribute name="body" />
					<!-- footer -->
					<div class="panel-footer align-text-bottom">
						<tiles:insertAttribute name="footer" />
					</div>
				</div>
				
				<div id="right">
 					<grid:grid
						height="50%" width="100%" id="testDayGrid"
						style=" height: 50%;
							    width: 100%;
							    position: absolute;
							    right: 0;
							    top: 0;">
					    { name: "AB CODI", type: "text", width: 40 },
					    { name: "Nominativo", type: "text", width: 40 },
					    { name: "Allarme", width: 25,type: "text"},
					    { name: "Data", width: 25,type: "text"}
					</grid:grid>
 					<grid:grid
					    height="50%" width="100%" id="esitGrid"
						style=" height: 50%;
							    width: 100%;
							    right: 0;
							    top: 0;inline-block;">
					    { name: "AB CODI", type: "text", width: 40 },
					    { name: "Nominativo", type: "text", width: 75 },
					    { name: "Allarme", width: 25,type: "text"}
					</grid:grid>
				</div>
			</div>
		</div>
	</div>

</body>
</html>