function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/home/doEdit.action", $("#form").serialize(),
			function(data) {
				if (data.state == true) {
					$("#message").text("操作成功");
				} else {
					$("#message").text("操作失败：" + data.message);
				}
				$("#my-alert").modal('open');
			});
}
function init() {
	getUnitList();
}
function load() {
	var id = $("#id").val();
	$.getJSON("/home/doMe.action", {
		id : id
	}, function(data) {
		if (data.state == false)
			return;
		$("#xingMing").val(data.data.xingMing);
		$("#mobile").val(data.data.mobile);
		$("#email").val(data.data.email);
		$("#unitId").val(data.data.unitId);
	});
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href = "/home/welcome.action";
	});
	load();
	init()
});