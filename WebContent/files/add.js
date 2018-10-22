function save() {
	if (!$('#form').validator("isFormValid"))
		return false;

	$.post("/files/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			parent.reload();
		} else {
			$("#message").text("操作失败：" + data.message);
			$("#my-alert").modal('open');
		}

	});
}
function initWebUploader() {
	// 初始化Web Uploader
	var uploader = WebUploader.create({
		auto : true,
		swf : '/js/webuploader/Uploader.swf',
		server : '/file/upload.action',
		pick : '#filePicker',
		duplicate : true,
		formData : {
			upload_type : "project"
		},
		threads : 1,
		fileNumLimit : 1,// 文件上传数量限制
		dnd : '#uploader .queueList',
		disableGlobalDnd : true,
	});
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on('uploadSuccess', function(file, response) {
		var originalName = response.data.originalName;
		var $li = $('<div id="' + file.id + '" class="file-item">'
				+ originalName + '</div>');
		$('#fileList').append($li);
		$('#mingCheng').val(originalName);
		$('#originalName').val(originalName);
		$('#realPath').val(response.data.realPath);
		$('#size').val(response.data.size);
		$('#format').val(response.data.format);
	});
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btClose").click(function() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index)
	});

	initWebUploader();
});