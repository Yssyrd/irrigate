
$(document).ready(function(){
	
	feather.replace();
	
	$(".insertNode").click(function() {
		$("#myModal").modal();
		
	});
	$(".deleteBtn").click(function(){
	    //删除当前员工的
	    $("#deleteNodeForm").attr("action",$(this).attr("del_uri")).submit();
	    return false;
    });
	
});