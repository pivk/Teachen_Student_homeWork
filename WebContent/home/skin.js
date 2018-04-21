function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/home/doChangeSkin.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function change() {
	var skinName = $("#skin").val();
	parent.changeSkin(skinName);
}
function load() {
	var count = $("#skin option").length;
	for (var i = 0; i < count; i++) {
		if ($("#skin").get(0).options[i].value == skin.type) {
			$("#skin").get(0).options[i].selected = true;
			break;
		}
	}
}
$(document).ready(function() {
	load();
	$("#btSave").click(function() {
		save();
	});
	$("#skin").change(function() {
		change();
	});
});