function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/directory/doEdit.action", $("#form").serialize(), function(data) {
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
function load() {
	var id=$("#id").val();
	$.getJSON("/directory/doGet.action", {
		id : id
	}, function(data) {
		if (data.state == false)
			return;
		$("#mingCheng").val(data.data.mingCheng);
		$("#courseId").val(data.data.courseId);
		$("#courseName").val(data.data.courseName);
		$("#endTime").datepicker("setValue",data.data.endTime);
		$("#memo").val(data.data.memo);
	});
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btClose").click(function() {
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index)
	});
	load();	
	$("#courseName").click(function() {
		layer.open({
			type : 2,
			title : '选择课程',
			area : [ '80%', '80%' ],
			shade : 0,
			maxmin : true,
			content : '/system/course/coursepage.action',
			btn : [ '确定', '关闭' ],
			yes : function(index,layero) {
				var iframeWin = window[layero.find('iframe')[0]['name']];
				var selected=iframeWin.getSelectedJson();//获取选择的数据Json格式
				$("#courseId").val(selected.id.split(',')[0])
				$("#courseName").val(selected.id.split(',')[1])
				layer.closeAll();//关闭弹出窗
			},
			btn2 : function() {
				layer.closeAll();
			},
			zIndex : layer.zIndex,
		});
	});
	
});