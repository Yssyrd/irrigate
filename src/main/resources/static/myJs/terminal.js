
$(document).ready(function(){
	
	
	
	feather.replace();
	
	$(".deleteBtn").click(function(){
	    //删除当前员工的
	    $("#deleteForm").attr("action",$(this).attr("del_uri")).submit();
	    return false;
    });
	
});