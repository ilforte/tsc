<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="width" required="true"  type="java.lang.String"%>
<%@ attribute name="height" required="true"  type="java.lang.String"%>
<%@ attribute name="style" required="false"  type="java.lang.String"%>

<jsp:doBody var="fields"/>

<style>
.${id}
{
    ${style} 
}
</style>

<script>
$(function() {
    $("#${id}").jsGrid({
        width: "100%",
        height: "50%",
        sorting: true,
        paging: false,
        heading: true,
        filtering: false,
        inserting: false,
        editing: false,
        selecting: true,
        autoload: true,
        controller: {
            loadData: function() {
                var d = $.Deferred();
 
                $.ajax({
                    url: "http://services.odata.org/V3/(S(3mnweai3qldmghnzfshavfok))/OData/OData.svc/Products",
                    dataType: "json"
                }).done(function(response) {
                    d.resolve(response.value);
                });
 
                return d.promise();
            }
        },
        fields:[${fields}],
        rowClick: function(args) {
        	console.log(JSON.stringify(args));
        }
    });
 
});
</script>

<div id="${id}" ></div>