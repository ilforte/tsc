<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true"  type="java.lang.String"%>
<%@ attribute name="function" required="true"  type="java.lang.String"%>
<%@ attribute name="action" required="true"  type="java.lang.String"%>
<%@ attribute name="success_message" required="true"  type="java.lang.String"%>
<%@ attribute name="failure_message" required="true"  type="java.lang.String"%>

<%@attribute  name="content" fragment="true" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#${id}").submit(function(event) {
		var data = $(this).serializeFormJSON();
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		${function}(data);
	});
});
	
function ${function}(data) {
	$.ajax({ ${id},
	    url:CONTEXT_PATH + '${action}',  
	    data:JSON.stringify(data),
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(HEADER, TOKEN);
        },
	    success: function(resposeJsonObject){
	        // Success Message Handler
			console.log("success: ", JSON.stringify(resposeJsonObject));
 	        if (resposeJsonObject.status=='FAILURE'){
	        	var message ='${failure_message} <br><ul>';
                $.each(resposeJsonObject.result, function(){
                	message +='<li> fieldName: ' + this.fieldName + ' error: ' + this.message + '</li>';
                });
                message +='<ul>';
                toastr.error(message, "Title", {
                    "timeOut": "0",
                    "extendedTImeout": "0"
                });
	        }else{
	        	toastr.success('${success_message}');
	        };
	    },
		error : function(e) {
			console.log("ERROR: ", e);
			toastr.error(JSON.stringify(e));
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}
	
(function ($) {
    $.fn.serializeFormJSON = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);
</script>

<form method="post" id="${id}" name= "${id}">
	<jsp:invoke fragment="content"/>
</form>





