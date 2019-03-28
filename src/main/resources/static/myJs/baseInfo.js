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
		
		$("#nodeModal").modal();
	});
	
	$(".sendMsg").click(function() {
		sendWS("","bbbbbbbbbbb");
	});
	$(".mqttTest").click(function() {
		mqttTest();
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
							"</h5><p class='card-text'>当前状态："+terArr[i].status +
							"</p></div><input class='init_id' type='hidden' value='"+terArr[i].id+"'>" +
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
							"</h5><p class='card-text'>在线状态："+nodeArr[i].nodeStatus +
							"</p><p class='card-text'>开关状态："+nodeArr[i].openStatus +
							"</p><p class='card-text'>最新更新时间："+nodeArr[i].lastUpdate +
							"</p></div><input class='init_id' type='hidden' value='"+nodeArr[i].id+"'></div>";
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

function mqttTest(){
	$.ajax({
		type: "post",
		url: "mqttTest",
		dataType: "json",
		success: function(data){
			alert(data);
		}
	});
}
