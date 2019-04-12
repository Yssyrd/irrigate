$(document).ready(function(){
    var socket;  
    if(typeof(WebSocket) == "undefined") {  
        console.log("您的浏览器不支持WebSocket");  
    }else{  
        console.log("您的浏览器支持WebSocket");  
        var user = getCookie("user");
        console.log("name:"+user);  
    	//实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接  
        //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");  
        socket = new WebSocket("ws://localhost/websocket/"+user);  
        //打开事件  
        socket.onopen = function() {  
            console.log("Socket 已打开");  
            //socket.send("这是来自客户端的消息" + location.href + new Date());  
        };  
        //获得消息事件  
        socket.onmessage = function(msg) {  
        	
        	console.log(msg.data);  
//        	analysisJson(JSON.parse(msg.data));
        	
        };  
        //关闭事件  
        socket.onclose = function() {  
            console.log("Socket已关闭");  
        };  
        //发生了错误事件  
        socket.onerror = function() {  
            alert("Socket发生了错误");  
            //此时可以尝试刷新页面
        }  
    }
});