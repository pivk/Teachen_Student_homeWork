function save() {
	if(!$('#form').validator("isFormValid")) return false;
	$.post("/system/role/doEdit.action", $("#form").serialize(),
			function(data) {
				if (data.state == true) {
					$("#message").text("操作成功");
				} else {
					$("#message").text("操作失败：" + data.message);
				}
				$("#my-alert").modal('open');
			});
}
function load() {
	var id=$("#id").val();
	
	$.getJSON("/system/role/doGet.action", {
		id : id
	}, function(data) {
		if (data.state == false)
			return;
		$("#mingCheng").val(data.data.mingCheng);
	});
}
function init(){
	
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		parent.closeTab(getFrameId());
	});
	
	init();
	load();
});