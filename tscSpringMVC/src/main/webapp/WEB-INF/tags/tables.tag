<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="data_toggle" required="true"  type="java.lang.String"%>
<%@ attribute name="data_url" required="true"  type="java.lang.String"%>

<jsp:doBody var="tableBody"/>

<script type="text/javascript">
$(document).ready(function(){
		$.getJSON('${data_url}', function( data ) {
		var tableId = '#${id}';
		$(tableId).bootstrapTable({
			${tableBody},
		   data:$.parseJSON(data)
		});
	});
});
</script>

<table id="${id}" ></table>