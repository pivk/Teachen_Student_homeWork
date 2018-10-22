function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/role/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+"<td><button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">编辑</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger\">删除</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Power('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">权限</button>&nbsp;</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}

function Edit(id){
	parent.openTab("2","修改角色","/system/role/edit.action?id="+id);
}
function Show(id){
	parent.openTab("3","查看角色","/system/role/show.action?id="+id);
}
function Power(id){
	parent.openTab("1","角色权限","/system/role/power.action?id="+id);
}
function Remove(uid){
    var onConfirm = function() {
		$.get("/system/role/doRemove.action", {id:uid}, function(data,status) {
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
		parent.openTab("1","添加角色","/system/role/add.action");
	});	
	$("#btQuery").click(function(){
		load(1);
	});
	$("#btExport").click(function() {
		location.href = "/system/role/importRole.action";
	});
	$("#btSave").click(function() {
		save();
	});
	
	load(1);
});