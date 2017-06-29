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
			try {
				$("#allarmGrid").jsGrid({data:JSON.parse(event.data)});
		    	} catch(e) {
		    		toastr.error(e);
		    }
        	//console.log(JSON.stringify(event.data));
        	/**
        	* Play allarm 
        	**/
        	
        	var arr = {};
			try {
					arr = JSON.parse(event.data);
	    		} catch(e) {
	    			toastr.error(e);
	    	}
        	var totalSoundDuration;
        	if(userExists(arr,'')){
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
        	/**
        	* close then reopen url
        	**/
            console.log("Connection closed");
        };
    }
   
    function closeSocket(){
        webSocket.close();
    }
    
    /**
    * check id user is empty
    */
    function userExists(arr,user) {
   	  return arr.some(function(el) {
   	    return el.user === user;
   	  }); 
   	}

	$(document).ready(function(){
		openSocket();
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