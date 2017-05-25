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
        height: "auto",
        width: "100%",
        height: "50%",
        sorting: true,
        paging: false,
        autoload: false,
        fields:[${fields}]
    });
 
});
</script>

<div id="${id}" ></div>