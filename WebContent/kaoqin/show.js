	var zhi="";
	var userId="";
function load(){	
	$("#a").after().empty()
	$.get("/system/course/doUserPage.action",{ 
		userName:$("#userName").val(),
			id:$("#id").val()
		},function(response,status){
		jQuery.each(response.data.data, function(key, val) {
			zhi+='kaoqin'+val.userId+',';
			userId+=val.userId+',';
			var row = '<div class="am-u-sm-4">'+val.userName+'</div>	<div class="am-u-sm-8">'
				+'<div class="am-form-group" id='+val.userId+'><label class="am-radio-inline"><input type="radio"  value="1" name="kaoqin'+val.userId+'"> 迟到</label>'
				+'<label class="am-radio-inline"><input type="radio" value="2" name="kaoqin'+val.userId+'"> 旷课</label>'
				+'<label class="am-radio-inline"><input type="radio" value="3" name="kaoqin'+val.userId+'"> 正常</label></div></div>'
			$("#a").before(row);
		});
		$("#userId").val(userId);
	  });
}
function Kaoqin(uid){
	layer.open({
		type : 2,
		title : '查看通知',
		area : [ '80%', '95%' ],
		shade : 0,
		maxmin : true,
		content : '/work/show.action?id='+uid,
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);
		}
	});
}

$("#btSave").click(function(){
	var b="";
	for (var int = 0; int < zhi.substring(0,zhi.length-1).split(',').length; int++) {
        b+=  $('input[name="'+zhi.split(',')[int].substring(0,zhi.length-1)+'"]:checked').val()+',';
	}
	$("#zhi").val(b);
	$.post("/kaoqin/doAdd.action", $("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			$("#form")[0].reset();
			$('#form').validator('destroy');
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
});
$(document).ready(function () {
	$("#btQuery").click(function(){
		load();
	});
	$("#btQuery").click(function(){
		load();
	});
	$("#btSure").click(function(){
		parent.layer.closeAll();
	});
	
	load();
});