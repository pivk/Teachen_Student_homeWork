function save() {
	if(!$('#form').validator("isFormValid"))
		return false;
	$.post("/system/unit/doEdit.action", $("#form").serialize(), function(data) {
		if (data.state==true) {
			$("#message").text("操作成功");
			parent.location.reload();
		} else {
			$("#message").text("操作失败："+data);
		}
		$("#my-alert").modal('open');
	});
}

function load() {
	var id=$("#id").val();	
	$.getJSON("/system/unit/doGet.action", {id:id}, function(data) {
		if (data.state == false)
			return;
		$("#mingCheng").val(data.data.mingCheng);
		$("#oldmingCheng").val(data.data.mingCheng);
		$("#code").val(data.data.code);
		$("#unitId").val(data.data.parentId);		
		$("#parentName").val(data.data.parentName);
	});
}

$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btsure").click(function() {
		parent.layer.closeAll();
	});
	$("#btCancel").click(function() {
		parent.layer.closeAll();
	});
	load();
});