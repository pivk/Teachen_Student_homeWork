function save() {
	$.post("/system/config/doSet.action",$("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function btSync (){
	 $("#my-modal-loading").modal('open');
		$.get("/system/user/doSync.action",function(data,status) {
			if (data.state == true) {
				$("#my-modal-loading").modal('close');
				load(1);
				$("#message").text("操作成功");
			} else {
				$("#my-modal-loading").modal('close');
				$("#message").text("操作失败："+data.message);
			}
			$("#my-alert").modal('open');
		});
}
function load() {
	$.getJSON("/system/config/doGet.action",function(data) {
		if (data.state == false)
			return;
		$("#uploadPath").val(data.data.uploadPath);
		$("#delayTime").val(data.data.delayTime);
		$("#filePath").val(data.data.filePath);
		
//		$("#review").val(data.data.review);
//		$("#apply").val(data.data.apply);
//		$("#shenPi").val(data.data.shenPi);
		$("#date").val(data.data.date);
			});
}
$(document).ready(function() {
	$("#btSync").click(function() {
		btSync();
	});
	$("#btSave").click(function() {
		save();
	});
	$("#btsure").click(function() {
		load();
	});
	load();
});