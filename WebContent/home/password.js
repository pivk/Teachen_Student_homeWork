function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	if($("#newMiMa").val()!=$("#newMiMa1").val()){
		$("#message").text("新密码与确认密码不一致，请重新填写。");
		$("#my-alert").modal('open');
		return false;
	}
	$.post("/home/doLogin.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$.post("/home/doPassword.action", $("#form").serialize(),
			function(data) {
				if (data.state == true) {
					$("#message").text("操作成功");
				} else {
					$("#message").text("操作失败：" + data.message);
				}
				$("#my-alert").modal('open');
			});
		} else {
			$("#message").text("原密码错误");
			$("#my-alert").modal('open');
			return false;
		}
	});
	
	
		
	
	
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href = "/home/index.action";
	});
});

