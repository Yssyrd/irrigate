$(document).ready(function(){
	
	$('.nodeCardParent').delegate('.card', 'mouseover', function() {
		$(this).removeClass('bg-light');
		$(this).addClass('text-white bg-secondary');
	});	
	$('.nodeCardParent').delegate('.card', 'mouseout', function() {
		
		$(this).removeClass('text-white bg-secondary');
		$(this).addClass('bg-light');
	});	
	
	$('.terCardParent').delegate('.card', 'mouseover', function() {
		if($(this).find(".statu").val()=="0"){
			$(this).removeClass('bg-light');
			$(this).addClass('text-white bg-secondary');
		}else{
			$(this).removeClass('text-white bg-info');
			$(this).addClass('text-white bg-secondary');
		}
		
	});	
	$('.terCardParent').delegate('.card', 'mouseout', function() {
		
		if($(this).find(".statu").val()=="0"){
			$(this).removeClass('text-white bg-secondary');
			$(this).addClass('bg-light');
		}else{
			$(this).removeClass('text-white bg-secondary');
			$(this).addClass('text-white bg-info');
		}
	});	
	$('.terCardParent').delegate('.card', 'click', function() {
		
		var $id = $(this).find(".statu");
		
		$(".statu").each(function(){
	    	$(this).val("0");
	    });
		
		$id.val("1");
		$(this).removeClass('bg-light');
		$(this).addClass('text-white bg-info');
		
		$('.terCardParent').find(".card").each(function(){
			if($(this).find(".statu").val()=='0'){
				$(this).removeClass('text-white bg-info');
				$(this).addClass('bg-light');
			}
	    });
	});	
	
	$('.terCardParent').delegate('.terCard', 'click', function() {
		var id = $(this).find(".init_id").val();
		
		loadNodes(id);
		$(".nodeCardParent").fadeIn();
	});	
	
	$('.nodeCardParent').delegate('.nodeCard', 'click', function() {
		$(".modal_init_id").val($(this).find(".init_id").val());
		$("#nodeModal").modal();
	});
	
	$(".sendMsg").click(function() {
		sendWS("","bbbbbbbbbbb");
	});
	$(".openNodeBtn").click(function() {
		var topic = "/user/"+$(".userId").val()+"/dev/"+$(".modal_init_id").val();
		var data = {
			topic : topic,
			code : "NODE_OPEN_TAP_OK"
		};
		
		sendMqtt(data);
	});
	
	$(".closeNodeBtn").click(function() {
		var topic = "/user/"+$(".userId").val()+"/dev/"+$(".modal_init_id").val();
		var data = {
			topic : topic,
			code : "NODE_CLOSE_TAP_OK"
		};
		
		sendMqtt(data);
	});
	getBaseInfo();
});

function getBaseInfo() {
	var userId = $(".userId").val();
	if(userId!=null&&userId!=""){
		$.ajax({
			type: "post",
			url: "getBaseInfo",
			dataType: "json",
			data : {
				userId : userId
			},
			success: function(data){
				
				var html = "";
				var terArr = data.terminal;
				$(".terCard").html("");
				for(var i=0; i< terArr.length; i++){
					html += "<div class='card bg-light mb-4 terCard' style='max-width: 30rem;cursor: pointer;'> " +
							"<div class='card-header'>" + terArr[i].proId +
							"</div><div class='card-body'><h5 class='card-title'>"+terArr[i].name +
							"</h5><p class='card-text ter_status'>当前状态："+terArr[i].status +
							"</p></div><input class='init_id' type='hidden' value='"+terArr[i].SN+"'>" +
							"<input class='statu' type='hidden' value='0'></div>";
				}
				$(".terCard").append(html);
			}
		});
	}else{
		window.location.href = "login.html";
	}
}
function loadNodes(id) {
	if(id!=null&&id!=""){
		$.ajax({
			type: "post",
			url: "getNodesInfo",
			dataType: "json",
			data : {
				terId : id
			},
			success: function(nodeArr){
				
				var html = "";
				$(".nodeCard").html("");
				for(var i=0; i< nodeArr.length; i++){
					html += "<div class='card bg-light mb-4 nodeCard' style='max-width: 30rem;cursor: pointer;'> " +
							"<div class='card-header'>"+ nodeArr[i].proId +
							"</div><div class='card-body'><h5 class='card-title'>"+nodeArr[i].name +
							"</h5><p class='card-text node_status'>在线状态："+nodeArr[i].nodeStatus +
							"</p><p class='card-text open_status'>开关状态："+nodeArr[i].openStatus +
							"</p><p class='card-text last_update'>最新更新时间："+nodeArr[i].lastUpdate +
							"</p></div><input class='init_id' type='hidden' value='"+nodeArr[i].SN+"'></div>";
				}
				$(".nodeCard").append(html);
			}
		});
	}
}
function sendWS(cid,msg){
	$.ajax({
		type: "post",
		url: "/socket/push",
		data:{
			cid : cid,
			message : msg
		},
		dataType: "json",
		success: function(data){
			alert(data);
		}
	});
}

function sendMqtt(data){
	
	$.ajax({
		type: "post",
		url: "sendMqtt",
		data : data,
		dataType: "json",
		success: function(res){
			console.log(JSON.stringify(res));
		}
	});
}

function analysisJson(data){
	
	var code = data.code;
	var dev = data.dev;
	
	switch (key) {
		case 0xA3:
			$(".nodeCard").each(function(){
				var id = $(this).find(".init_id").val();
				if(id ==dev){
					$(this).find(".open_status").html("开关状态：阀门打开");
					$(this).find(".last_update").html("最新更新时间："+data.update);
				}
			});
			break;
		case 0xA4:
			$(".nodeCard").each(function(){
				var id = $(this).find(".init_id").val();
				if(id ==dev){
					$(this).find(".open_status").html("开关状态：阀门关闭");
					$(this).find(".last_update").html("最新更新时间："+data.update);
				}
			});
			break;
		case 0xA5:
			$(".nodeCard").each(function(){
				var id = $(this).find(".init_id").val();
				if(id ==dev){
					$(this).find(".open_status").html("开关状态：阀门打开错误！");
					$(this).find(".last_update").html("最新更新时间："+data.update);
				}
			});
			break;
		case 0xA6:
			$(".nodeCard").each(function(){
				var id = $(this).find(".init_id").val();
				if(id ==dev){
					$(this).find(".open_status").html("开关状态：阀门关闭错误！");
					$(this).find(".last_update").html("最新更新时间："+data.update);
				}
			});
			break;
		case 0xA7:
			$(".nodeCard").each(function(){
				var id = $(this).find(".init_id").val();
				if(id ==dev){
					$(this).find(".open_status").html("开关状态：阀门正在打开.....");
					$(this).find(".last_update").html("最新更新时间："+data.update);
				}
			});
			break;
		case 0xA8:
			$(".nodeCard").each(function(){
				var id = $(this).find(".init_id").val();
				if(id ==dev){
					$(this).find(".open_status").html("开关状态：阀门正在关闭.....");
					$(this).find(".last_update").html("最新更新时间："+data.update);
				}
			});
			break;
		case 0xC2:
			
			if(data.type=="0"){
				$(".nodeCard").each(function(){
					var id = $(this).find(".init_id").val();
					if(id ==dev){
						$(this).find(".node_status").html("在线状态：在线");
						$(this).find(".last_update").html("最新更新时间："+data.update);
					}
				});
			}
			if(data.type=="1"){
				$(".terCard").each(function(){
					var id = $(this).find(".init_id").val();
					if(id ==dev){
						$(this).find(".ter_status").html("在线状态：在线");
					}
				});
			}
			
			break;
		case 0xC3:
			if(data.type=="0"){
				$(".nodeCard").each(function(){
					var id = $(this).find(".init_id").val();
					if(id ==dev){
						$(this).find(".node_status").html("在线状态：离线");
						$(this).find(".last_update").html("最新更新时间："+data.update);
					}
				});
			}
			if(data.type=="1"){
				$(".terCard").each(function(){
					var id = $(this).find(".init_id").val();
					if(id ==dev){
						$(this).find(".ter_status").html("在线状态：离线");
					}
				});
			}
			break;
		default:
			break;
	}
	
	
	
	
}