function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/role/doPage.action",{ 
			page: page,
			xingMing:$("#xingMing").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ val.id + "</td>"
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+"<td><button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">编辑</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger\">删除</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Power('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">权限</button>&nbsp;</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function Edit(uid){
	location.href="/system/role/edit.action?id="+uid;
}
function Show(uid){
	location.href="/system/role/show.action?id="+uid;
}
function Power(uid){
	location.href="/system/role/power.action?id="+uid;
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
		location.href="/system/role/add.action";
	});	
	load(1);
});