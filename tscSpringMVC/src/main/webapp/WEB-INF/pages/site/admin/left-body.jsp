<%@include file="/WEB-INF/pages/jspf/include.jspf" %>

<script>
//A $( document ).ready() block.
$( document ).ready(function() {
	$("#grid-basic").bootgrid();
/* 	jQuery("#grid-basic").bootgrid({
	    selection: true,
	    formatters: {
	        "link": function(column, row)
	        {
	            return "<a href=\"#\">" + column.id + ": " + row.id + "</a>";
	        }
	    }
	}).on("selected.rs.jquery.bootgrid", function(e, rows)
	{
	    var rowIds = [];
	    for (var i = 0; i < rows.length; i++)
	    {
	        rowIds.push(rows[i].id);
	    }
	    alert("Select: " + rowIds.join(","));
	}).on("deselected.rs.jquery.bootgrid", function(e, rows)
	{
	    var rowIds = [];
	    for (var i = 0; i < rows.length; i++)
	    {
	        rowIds.push(rows[i].id);
	    }
	    alert("Deselect: " + rowIds.join(","));
	}); */
});
</script>

<table id="grid-basic" class="table table-condensed table-hover table-striped">
    <thead>
        <tr>
            <th data-column-id="username" data-type="numeric" data-identifier="true" data-sortable="true" >username</th>
            <th data-column-id="role">role</th>
            <th data-column-id="email" data-order="desc" data-formatter="link" data-sortable="true" >email</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>10238</td>
            <td>eduardo@pingpong.com</td>
            <td>14.10.2013</td>
        </tr>
        <tr>
            <td>10239</td>
            <td>eduardo@pingpong.com</td>
            <td>14.11.2013</td>
        </tr>
        <tr>
            <td>10240</td>
            <td>eduardo@pingpong.com</td>
            <td>14.12.2013</td>
        </tr>
    </tbody>
</table>

