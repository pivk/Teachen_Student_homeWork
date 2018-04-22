function save() {
	if (!$('#form').validator("isFormValid"))
		return false;

	$.post("/system/user/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			$("#form")[0].reset();
			$('#form').validator('destroy');
			parent.load(1);
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
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
	$("#unitName").click(function() {
		layer.open({
			type : 2,
			title : '选择机构',
			area : [ '80%', '80%' ],
			shade : 0,
			maxmin : true,
			content : '/system/unit/popup.action',
			btn : [ '确定', '关闭' ],
			yes : function(index,layero) {
				var iframeWin = window[layero.find('iframe')[0]['name']];
				var selected=iframeWin.getSelectedJson();//获取选择的数据Json格式
				$("#unitName").val(selected[0].name);
				$("#unitId").val(selected[0].id);
				layer.closeAll();//关闭弹出窗
			},
			btn2 : function() {
				layer.closeAll();
			},
			zIndex : layer.zIndex,
			success : function(layero) {
				layer.setTop(layero);

			}
		});
	});
});