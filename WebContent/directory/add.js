function save() {
	if (!$('#form').validator("isFormValid"))
		return false;

	$.post("/directory/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			parent.reload();
		} else {
			$("#message").text("操作失败：" + data.message);
			$("#my-alert").modal('open');
		}
		
	});
}
function init() {
	getUnitList();
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btClose").click(function() {
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index)
	});

	init();
});