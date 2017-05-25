<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="width" required="true"  type="java.lang.String"%>
<%@ attribute name="height" required="true"  type="java.lang.String"%>
<%@ attribute name="maxHeight" required="true"  type="java.lang.String"%>
<%@ attribute name="maxWidth" required="true"  type="java.lang.String"%>
<%@ attribute name="minHeight" required="true"  type="java.lang.String"%>
<%@ attribute name="minWidth" required="true"  type="java.lang.String"%>
<%@ attribute name="style" required="false"  type="java.lang.String"%>

<jsp:doBody var="resizableBody"/>

<style>
	#${id}
	{
		width: ${width}; 
		height: ${height}; 
		padding: 0px;
	    border:1px solid #337ab7;
	    ${style}    
	}
</style>

<script>
$( function() {
  $( "#${id}" ).resizable({
    maxHeight: "${maxHeight}",
    maxHeight: "${maxHeight}",
    minHeight: "${minHeight}",
    minWidth:  "${minWidth}"
  });
} );

/* 
 *store size on cookie 
 */ 
 $(document).ready(function(){
	    if(typeof($.cookie('height')!='undefined'))
	       {
	           $("#${id}").height($.cookie("height"));
	           $("#${id}").width($.cookie("width"));
	       }
	    $( "#${id}" ).resizable({
	        stop: function(event, ui) {
	            var width = ui.size.width;
	            var height = ui.size.height;
	            $.cookie("height",height);
	            $.cookie("width",width);
	        }
	    });
	 });
 
</script>
 
<div id="${id}" class="ui-widget-content">
  ${resizableBody}
</div>
