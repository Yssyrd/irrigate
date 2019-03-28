$(document).ready(function(){
	
	$("form").submit(function() {
		setCookie("user",$(".username").val(),1);
	});
	
});