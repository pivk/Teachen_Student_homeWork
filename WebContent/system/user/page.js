function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/user/doPage.action",{ 
			page: page,
			xingMing:$("#xingMing").val(),
			unitId:$("#unitId").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick=\"check2(this)\" value="+val.id+" name='check' /></td>" 
				+ "<td><a href='/system/user/show.action?id="+val.id+"'>"+ val.id + "</a></td>"
				+ "<td>"+ (val.xingMing==null?"":val.xingMing) + "</td>"
				+ "<td>"+ (val.mobile==null?"":val.mobile) + "</td>"
				+ "<td>"+ (val.email==null?"":val.email) + "</td>"
				+ "<td>"+ (val.unitName==null?"":val.unitName) + "</td>"
				+"<td><button type=\"button\" onclick=\"ResetPassword('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs\">重置密码</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">编辑</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Show('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">查看</button>&nbsp;"
				+"<button type=\"button\" onclick=\"userRole('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-success\">关联角色</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger\">删除</button></td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function ResetPassword(uid){
	$.get("/system/user/doReset.action", {id:uid}, function(data,status) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败："+data.message);
		}
		$("#my-alert").modal('open');
	});
}
function deleteUsers() {
	var id = "";
	$("[name='check']").each(function(index,element){
		if($(this).prop("checked")==true){
			id+= $(this).val()+",";
		} 
	});	
	$.post("/system/user/deleteUsers.action", {id:id}, function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			load(page);
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function userRole(uid){
	location.href="/system/user/userRole.action?id="+uid;
}
function Edit(uid){
	location.href="/system/user/edit.action?id="+uid;
}
function Show(uid){
	location.href="/system/user/show.action?id="+uid;
}
function Remove(uid){
    var onConfirm = function() {
		$.get("/system/user/doRemove.action", {id:uid}, function(data,status) {
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
function check(obj) {
	var o1 = $("[name='check']");
		for(var i=0;i<o1.length;i++){
		o1[i].checked=obj.checked;
		}
		
}

function check2(obj) {
	var allobj = document.getElementById("main");
	if(obj.checked==false){
		allobj.checked=false;
		} else {
			var flag=true;
			var o1 = document.getElementsByName("check");
			for(var i=0;i<o1.length;i++){
				if(o1[i].checked==false){
					flag=false;
					break;
					}
				}
				if(flag==true){
				allobj.checked=true;
				}
			}
	}
function init(){
	getUnitList();
}
$(document).ready(function () {
	$("#btAdd").click(function(){
		location.href="/system/user/add.action";
	});
	$("#btExport").click(function(){
		location.href="/system/user/importUser.action";
	});
	$("#btQuery").click(function(){
		load(1);
	});
	$("#btDelete").click(function(){
		deleteUsers();
	});
	$("#main").click(function(){
		check(this);
	});
	init();
	load(1);
	
});