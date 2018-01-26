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
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>

<!-- Script to utilise the WebSocket -->
<script type="text/javascript">
/**
 * WebSocket js file
 */
    var webSocket;
    function openSocket(){
    	console.log('open socket');
        // Ensures only one connection is open at a time
        if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
            console.log("WebSocket is already opened.");
            return;
        }
        // Create a new instance of the websocket/tscSpringMVC/
        var webSocketurl = 'ws://';
        webSocketurl += '${pageContext.request.getServerName()}' + ':';
        webSocketurl += '${pageContext.request.serverPort}';
        webSocketurl += '${pageContext.request.contextPath}';
        webSocketurl += '/user/allarmEndpoint';
        webSocket = new WebSocket(webSocketurl);
         
        /**
         * Binds functions to the listeners for the websocket.
         */
        webSocket.onopen = function(event){
            // For reasons I can't determine, onopen gets called twice
            // and the first time event.data is undefined.
            // Leave a comment if you know the answer.
            if(event.data === undefined)
                return;
            /**
            * fire event versus grid
            **/
            console.log(JSON.stringify(event));
        };

        webSocket.onmessage = function(event){
        	// loadData with custom filter
			console.log('onmessage data: ',event.data);
			try {
				$("#allarmGrid").jsGrid({data:JSON.parse(event.data)});
		    	} catch(e) {
		    		toastr.error(e,"#allarmGrid onmessage");
		    }
        	//console.log(JSON.stringify(event.data));
        	/**
        	* Play allarm 
        	**/
        	
        	var arr = {};
			try {
					arr = JSON.parse(event.data);
	    		} catch(e) {
	    			toastr.error('websocket: ' + e);
	    	}
        	var totalSoundDuration;

        	if(userExists(arr,'')){
        		;
        		/* sound config */
        		var sound = new Howl({
        		      src: ["<c:url value='/resources/sound/beep-beep.mp3' />"],
        		      autoplay: true,
        		      loop: false,
        		      volume: 1,
        		      onload: function() {
        		          totalSoundDuration = sound.duration();
        		      },
        		      onplay: function(getSoundId) {
        		          //sound playing
        		      },
        		      onend: function() {
        		          //sound play finished
        		      }
        		    });
        		sound.play();
        	};
        };
        
        webSocket.onclose = function(event){
            var reason;
            console.log('event.code:',event);
            // See http://tools.ietf.org/html/rfc6455#section-7.4.1
            if (event.code == 1000)
                reason = "Normal closure, meaning that the purpose for which the connection was established has been fulfilled.";
            else if(event.code == 1001)
                reason = "An endpoint is \"going away\", such as a server going down or a browser having navigated away from a page.";
            else if(event.code == 1002)
                reason = "An endpoint is terminating the connection due to a protocol error";
            else if(event.code == 1003)
                reason = "An endpoint is terminating the connection because it has received a type of data it cannot accept (e.g., an endpoint that understands only text data MAY send this if it receives a binary message).";
            else if(event.code == 1004)
                reason = "Reserved. The specific meaning might be defined in the future.";
            else if(event.code == 1005)
                reason = "No status code was actually present.";
            else if(event.code == 1006)
               reason = "The connection was closed abnormally, e.g., without sending or receiving a Close control frame";
            else if(event.code == 1007)
                reason = "An endpoint is terminating the connection because it has received data within a message that was not consistent with the type of the message (e.g., non-UTF-8 [http://tools.ietf.org/html/rfc3629] data within a text message).";
            else if(event.code == 1008)
                reason = "An endpoint is terminating the connection because it has received a message that \"violates its policy\". This reason is given either if there is no other sutible reason, or if there is a need to hide specific details about the policy.";
            else if(event.code == 1009)
               reason = "An endpoint is terminating the connection because it has received a message that is too big for it to process.";
            else if(event.code == 1010) // Note that this status code is not used by the server, because it can fail the WebSocket handshake instead.
                reason = "An endpoint (client) is terminating the connection because it has expected the server to negotiate one or more extension, but the server didn't return them in the response message of the WebSocket handshake. <br /> Specifically, the extensions that are needed are: " + event.reason;
            else if(event.code == 1011)
                reason = "A server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.";
            else if(event.code == 1015)
                reason = "The connection was closed due to a failure to perform a TLS handshake (e.g., the server certificate can't be verified).";
            else
                reason = "Unknown reason";
        	/**
        	* close then reopen url
        	**/
            console.log('Connection closed reason:',reason);
        };
    }
   
    function closeSocket(){
        webSocket.close();
    }
    
    /**
    * check id user is empty,null or undefined
    */
    function userExists(arr,user) {
   	  return arr.some(function(el) {
   	    return el.user === user || el.user === undefined || el.user === null;
   	  }); 
   	}

	$(document).ready(function(){
		openSocket();
	});
	
	$( window ).unload(function() {
		closeSocket();
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
				<tiles:insertAttribute name="leftLayout" />
			</div>
    		<div class="col-md-6">
    			<div class="row">
    				<tiles:insertAttribute name="body" />
    			</div>
    			<div class="row vbottom" id="footer">
    				<tiles:insertAttribute name="footer" />
    			</div>
    		</div>
    		<div class="col-md-3">
    			<tiles:insertAttribute name="rightLayout" />
    		</div>
		</div>
	 </div>

</body>
</html>