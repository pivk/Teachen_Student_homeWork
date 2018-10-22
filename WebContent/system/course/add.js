function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/system/course/doAdd.action", $("#form").serialize(), function(data) {
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


function init() {

}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#userName").click(function() {
		layer.open({
			type : 2,
			title : '选择教师',
			area : [ '80%', '80%' ],
			shade : 0,
			maxmin : true,
			content : '/system/user/teacherpage.action',
			btn : [ '确定', '关闭' ],
			yes : function(index,layero) {
				var iframeWin = window[layero.find('iframe')[0]['name']];
				var selected=iframeWin.getSelectedJson();//获取选择的数据Json格式
				$("#userId").val(selected.id.split(',')[0])
				$("#userName").val(selected.id.split(',')[1])
				layer.closeAll();//关闭弹出窗
			},
			btn2 : function() {
				layer.closeAll();
			},
			zIndex : layer.zIndex,
		});
	});
	
	init();
});