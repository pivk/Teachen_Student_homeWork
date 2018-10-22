function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/course/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+ "<td>"+ (val.userName==null?"":val.userName) + "</td>"
				+ "<td>"+ (val.code==null?"":val.code) + "</td>"
				+"<td><button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">编辑</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger\">删除</button>&nbsp;"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function Show(uid){
	layer.open({
		type : 2,
		title : '查看课程',
		area : [ '80%', '95%' ],
		shade : 0,
		maxmin : true,
		content : '/system/course/show.action?id='+uid,
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);

		}
	});
}
function Edit(uid){
	layer.open({
		type : 2,
		title : '修改课程',
		area : [ '80%', '95%' ],
		shade : 0,
		maxmin : true,
		content : '/system/course/edit.action?id='+uid,
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);
			
		}
	});
}

function Remove(uid){
    var onConfirm = function() {
		$.get("/system/course/doRemove.action", {id:uid}, function(data,status) {
			if (data.state == true) {
				$("#message").text("操作成功");
				load(page);
			} else {
				$("#message").text("操作失败："+data.message);
			}
			$("#my-alert").modal('open');
		});
    };
    
    var $confirm = $('#my-confirm');
    var confirm = $confirm.data('amui.modal');
    if (confirm) {
      confirm.options.onConfirm =  onConfirm;
      confirm.toggle(this);
    } else {
      $confirm.modal({
        relatedElement: this,
        onConfirm: onConfirm,
      });
    }
}

$(document).ready(function () {
	$("#btAdd").click(function(){
		layer.open({
			type : 2,
			title : '添加课程',
			area : [ '80%', '95%' ],
			shade : 0,
			maxmin : true,
			content : '/system/course/add.action',
			zIndex : layer.zIndex,
			success : function(layero) {
				layer.setTop(layero);
				
			}
		});
	});	
	$("#btQuery").click(function(){
		load(1);
	});
	$("#btExport").click(function() {
		location.href = "/system/course/importRole.action";
	});
	$("#btSave").click(function() {
		save();
	});
	
	load(1);
});