function load(newpage) {
	loadData(newpage,"/library/doCompany.action")
}
$(document).ready(function() {
	$("#btQuery").click(function() {
		reload();
	});
	$("#btCreateDirectory").click(function() {
		var parentId=$("#parentId").val();
		createDirectory(parentId);
	});
	$("#btCreateFile").click(function() {
		var parentId=$("#parentId").val();
		createFile(parentId);
	});	
	$("#btRemove").click(function() {
		var onConfirm = function() {
			remove();
		};

		var $confirm = $('#my-confirm');
		var confirm = $confirm.data('amui.modal');
		if (confirm) {
			confirm.options.onConfirm = onConfirm;
			confirm.toggle(this);
		} else {
			$confirm.modal({
				relatedElement : this,
				onConfirm : onConfirm,
			});
		}
	});
	$("#btDownload").click(function() {
		downloadFile();
	});
	load(1);
});