function UEditor(){ 
var editor = UE.getEditor('container',{  
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
        toolbars: [
    ['fullscreen', 'source', 'undo', 'redo'],
    ['bold', 'italic', 'underline', 'fontborder',
    'strikethrough', 'superscript', 
    'subscript', 'removeformat', 
    'simpleupload', //单图上传
    'fontfamily', //字体
    'fontsize', //字号
    'inserttable', //插入表格
    'justifyleft', //居左对齐
    'justifyright', //居右对齐
    'justifycenter', //居中对齐
    'justifyjustify', //两端对齐
    'attachment', //附件
    'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
],  
        //focus时自动清空初始化时的内容  
        autoClearinitialContent:true,  
        //关闭字数统计  
        wordCount:false,  
        //关闭elementPath  
        elementPathEnabled:false,  
        //默认的编辑区域高度  
        initialFrameHeight:200 
        //更多其他参数，请参考ueditor.config.js中的配置项  
    }); 
}
function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/work/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			$("#form")[0].reset();
			$('#form').validator('destroy');
		} else {
			$("#message").text("操作失败：" + data.message);
			$("#my-alert").modal('open');
		}
		
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
	UEditor()
});