function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/system/unit/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			$("#form")[0].reset();
			$('#form').validator('destroy');
			parent.location.reload();
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function init(){
	getUnitList();
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btCancel").click(function() {
		parent.layer.closeAll();
	});
	$("#btsure").click(function() {
		parent.layer.closeAll();
		
	});
	init();
});