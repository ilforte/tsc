<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="width" required="true"  type="java.lang.String"%>
<%@ attribute name="height" required="true"  type="java.lang.String"%>
<%@ attribute name="style" required="false"  type="java.lang.String"%>

<%@attribute  name="header" fragment="true" %>
<%@attribute  name="fields" fragment="true" %>

<style>
table
{
	table-layout:fixed;
    width:100%;
}

th {
    text-align: right;
    font-size:small;
}

td {
    text-align: right;
    font-size:small;
}
</style>

<script>
var dataset = [
    { "ab_codi": "N00001","nominativo":"Otto","data": "1", "user": ""},
    { "ab_codi": "N00002","nominativo":"Connor", "data": "2", "user": "matteo"},
    { "ab_codi": "N00003","nominativo":"Lacey","data": "3", "user": ""},
    { "ab_codi": "N00004","nominativo":"Timothy","data": "1", "user": ""},
    { "ab_codi": "N00005","nominativo":"Ramona","data": "3", "user": ""}
];

$(document).ready(function() {
    var table = $("#${id}").DataTable({
    	"columnDefs": [
            {
                "targets": [1],
                "visible": false,
                "searchable": false
            }],
    	"bPaginate": false,
        "bFilter": false,
        "bInfo": false,
    	"paging":   false,
        "ordering": false,
        "info":     false,
        "scrollY":        "200px",
        "scrollCollapse": true,
        "paging":         false,
        pageResize: true,
        "dom": '<"${adapt_to}"flipt>',
    	data:dataset,
        columns:[<jsp:invoke fragment="fields"/>]
    });
    $('#${id} tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        console.log('click on ' + JSON.stringify(data));
    });
});
</script>
<table id="${id}" class="display compact row-border hover" >
	<jsp:invoke fragment="header"/>
</table>