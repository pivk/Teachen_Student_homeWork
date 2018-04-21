function login() {
	$.post("/home/doLogin.action", {uid:$("#username").val(),pwd:$("#password").val()}, function(data) {
		if (data.state == true) {
			location.href="/home/index.action";
		} else {
			$("#message").text("登陆失败：" + data.message);
			$("#my-alert").modal('open');
		}
		
	});
}
$(document).ready(function() {
	$("#btLogin").click(function() {
		/*	location.href="/home/index.action";*/
		login();
	});
});
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		$("#btLogin").trigger("click");
	}
});