<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" 				required="true"  type="java.lang.String"%>
<%@ attribute name="callbackFunction" 	required="false"  type="java.lang.String"%>
<%@attribute  name="fields" fragment="true" %>
<%@attribute  name="options" fragment="true" %>

<style>
  .ui-tooltip, .arrow:after {
    background: blue;
    border: 1px solid white;
  }
  .ui-tooltip {
    padding: 10px 20px;
    color: white;fields
    border-radius: 20px;
    font: bold 14px "Helvetica Neue", Sans-Serif;
    text-transform: uppercase;
    box-shadow: 0 0 7px black;
  }
  .arrow {
    width: 70px;
    height: 16px;
    overflow: hidden;
    position: absolute;
    left: 50%;
    margin-left: -35px;
    bottom: -16px;fields
  }
  .arrow.top {
    top: -16px;
    bottom: auto;
  }
  .arrow.left {
    left: 20%;
  }
  .arrow:after {
    content: "";
    position: absolute;
    
    left: 20px;
    top: -20px;fields
    width: 25px;
    height: 25px;
    box-shadow: 6px 5px 9px -9px black;
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    transform: rotate(45deg);
  }
  .arrow.top:after {
    bottom: -20px;
    top: auto;
  }
</style>

<script>
$(document).ready(function(){
	$( function() {
	    $(document ).tooltip({
	      position: {
	        my: "center bottom-20",
	        at: "center top",
	        
	        using: function( position, feedback ) {
	          $( this ).css( position );
	          $( "<div>" )
	            .addClass( "arrow" )
	            .addClass( feedback.vertical )
	            .addClass( feedback.horizontal )
	            .appendTo( this );
	        }
	      }
	    });
	  });
	
	var TitleField = function(config) {
	    jsGrid.Field.call(this, config);
	};
    
    TitleField.prototype = new jsGrid.Field({
    	itemTemplate: function(value,item) {
            return value;
        }
    });
    
    jsGrid.fields.title = TitleField;
	
	$("#${id}").jsGrid({
		fields:[<jsp:invoke fragment="fields"/>],
		rowClick: function(args){${callbackFunction}},
		<jsp:invoke fragment="options"/>
	});
	

});
</script>
<div id="${id}" >
</div>