function save() {
	if (!$('#form').validator("isFormValid"))
		return false;

	$.post("/system/role/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			$("#form")[0].reset();
			$('#form').validator('destroy');
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function init() {

}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		parent.closeTab(getFrameId());
	});

	init();
});