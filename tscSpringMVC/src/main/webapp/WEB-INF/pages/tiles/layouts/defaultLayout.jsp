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

<style>

/* Tooltip */
.test + .tooltip > .tooltip-inner {
    background-color: #73AD21; 
    color: #FFFFFF; 
    border: 1px solid red; 
    padding: 15px;
    font-size: 20px;
}
/* Tooltip on top */
.test + .tooltip.top > .tooltip-arrow {
    border-top: 5px solid yellow;
}
/* Tooltip on bottom */
.test + .tooltip.bottom > .tooltip-arrow {
    border-bottom: 5px solid yellow;
}
/* Tooltip on left */
.test + .tooltip.left > .tooltip-arrow {
    border-left: 5px solid yellow;
}
/* Tooltip on right */
.test + .tooltip.right > .tooltip-arrow {
    border-right: 5px solid yellow;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>

<body>
	<!-- header -->
	<div class="panel-heading">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div class="container-fluid">
		<div class="row">
     		<div class="col-md-3">
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="allarmGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="header">
							<thead>
							    <tr>
							        <th>ab_codi</th>
							        <th>nominativo</th>
							        <th>data</th>
							        <th>user</th>
							    </tr>
							</thead>
					    </jsp:attribute>
					    <jsp:attribute name="fields">
							{ data: 'ab_codi',
								render:function(data,type,row) 
								{
									return "<a href='#' data-toggle='tooltip'  data-placement='right' title='" +row.nominativo+"'>"+row.ab_codi+"</a>";
								}},
					        { data: 'nominativo'},
					        { data: 'data' },
					        { data: 'user' }
					    </jsp:attribute>
					</grid:grid>
     			</div>
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="testGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="header">
							<thead>
							    <tr>
							        <th>ab_codi</th>
							        <th>nominativo</th>
							        <th>data</th>
							        <th>user</th>
							    </tr>
							</thead>
					    </jsp:attribute>
					    <jsp:attribute name="fields">
							{ data: 'ab_codi' },
					        { data: 'nominativo' },
					        { data: 'data' },
					        { data: 'user' }
					    </jsp:attribute>
					</grid:grid>
     			</div>
			</div>
    		<div class="col-md-6">
    			<div class="row">
    				<tiles:insertAttribute name="body" />
    			</div>
    			<div class="row">
    				<tiles:insertAttribute name="footer" />
    			</div>
    		</div>
    		<div class="col-md-3">
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="callGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="header">
							<thead>
							    <tr>
							        <th>ab_codi</th>
							        <th>nominativo</th>
							        <th>data</th>
							        <th>user</th>
							    </tr>
							</thead>
					    </jsp:attribute>
					    <jsp:attribute name="fields">
							{ data: 'ab_codi' },
					        { data: 'nominativo' },
					        { data: 'data' },
					        { data: 'user' }
					    </jsp:attribute>
					</grid:grid>
     			</div>
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="dontDoGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="header">
							<thead>
							    <tr>
							        <th>ab_codi</th>
							        <th>nominativo</th>
							        <th>data</th>
							        <th>user</th>
							    </tr>
							</thead>
					    </jsp:attribute>
					    <jsp:attribute name="fields">
							{ data: 'ab_codi' },
					        { data: 'nominativo' },
					        { data: 'data' },
					        { data: 'user' }
					    </jsp:attribute>
					</grid:grid>
     			</div>
    		</div>
		</div>
	 </div>

</body>
</html>