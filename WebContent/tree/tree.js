function load(newpage) {
	loadData(newpage,"/tree/doTree.action")
}
$(document).ready(function() {
	$("#btQuery").click(function() {
		reload();
	});
	$("#btCreateDirectory").click(function() {
		var parentId=$("#parentId").val();
		var xueqi=$("#xueqi").val();
		createDirectory(parentId,xueqi);
	});
	$("#btCreateFile").click(function() {
		var parentId=$("#parentId").val();
		createFile(parentId);
	});	
	$("#btRemove").click(function() {
		var onConfirm = function() {
			var selectedList=$(".ui-selected");
			var selectedItem=selectedList[0];
			var dataId=$(selectedItem).attr("data-id");
			var dataLei=$(selectedItem).attr("data-lei"); 
			if(dataId==null) return;
			
			remove(dataId,dataLei);
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
	$("#btShow").click(function() {
		var selectedList=$(".ui-selected");
		var selectedItem=selectedList[0];
		var dataId=$(selectedItem).attr("data-id");
		var dataLei=$(selectedItem).attr("data-lei"); 
		if(dataId==null) return;
		
		show(dataId,dataLei);
	});	
	$("#btDownload").click(function() {
		downloadFile();
	});
	load(1);
});