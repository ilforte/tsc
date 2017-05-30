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

<style>

/* Tooltip */
.test + .tooltip > .tooltip-inner {
    background-color: #73AD21; 
    color: #FFFFFF; 
    border: 1px solid red; 
    padding: 15px;
    font-size: 20px;
}
/* Tooltip on top */
.test + .tooltip.top > .tooltip-arrow {
    border-top: 5px solid yellow;
}
/* Tooltip on bottom */
.test + .tooltip.bottom > .tooltip-arrow {
    border-bottom: 5px solid yellow;
}
/* Tooltip on left */
.test + .tooltip.left > .tooltip-arrow {
    border-left: 5px solid yellow;
}
/* Tooltip on right */
.test + .tooltip.right > .tooltip-arrow {
    border-right: 5px solid yellow;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>

<!-- Script to utilise the WebSocket -->
<script type="text/javascript">   
    var webSocket;
    function openSocket(){
    	console.log('open socket');
        // Ensures only one connection is open at a time
        if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
            console.log("WebSocket is already opened.");
            return;
        }
        // Create a new instance of the websocket
        webSocket = new WebSocket("ws://localhost:8080/tscSpringMVC/admin/allarmEndpoint");
         
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
        	$("#allarmGrid").jsGrid({data:JSON.parse(event.data)});
        	/**
        	* Play allarm 
        	**/
        	var arr = JSON.parse(event.data);
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
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="allarmGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="fields">
							{name: "ab_codi",type: "text",width:30,
							    itemTemplate: function(value,item) {
							    	var tooltip = $('<div>' + value + '</div>').attr("title",item.ab_codi);
							        return tooltip;
							    }
							},
            				{name: "nominativo",type:"text",visible:false,width:50},
            				{name: "data_arrivo",type:"text",width:45},
            				{name: "evento",type:"text",width:45},
            				{name: "user",type:"text",width: 50}
					    </jsp:attribute>
					</grid:grid>
     			</div>
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="testGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="fields">
							{name: "ab_codi",type: "text",width:50},
            				{name: "nominativo",type: "text",width:50},
            				{name: "data_arrivo",type:"text",width:45},
            				{name: "user",type:"text",width: 50}
					    </jsp:attribute>
					</grid:grid>
     			</div>
			</div>
    		<div class="col-md-6">
    			<div class="row">
    				<tiles:insertAttribute name="body" />
    			</div>
    			<div class="row">
    				<tiles:insertAttribute name="footer" />
    			</div>
    		</div>
    		<div class="col-md-3">
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="callGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="fields">
							{name: "ab_codi",type: "text",width:50},
            				{name: "nominativo",type: "text",width:50},
            				{name: "data_arrivo",type:"text",width:45},
            				{name: "user",type:"text",width: 50}
					    </jsp:attribute>
					</grid:grid>
     			</div>
     			<div class="row">
					<grid:grid
						height="100%" width="100%" id="dontDoGrid" style="height: 50%;width: 100%;">
					    <jsp:attribute name="fields">
							{name: "ab_codi",type: "text",width:50},
            				{name: "nominativo",type: "text",width:50},
            				{name: "data_arrivo",type:"text",width:45},
            				{name: "user",type:"text",width: 50}
					    </jsp:attribute>
					</grid:grid>
     			</div>
    		</div>
		</div>
	 </div>

</body>
</html>