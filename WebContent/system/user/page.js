function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/user/doPage.action",{ 
			page: page,
			xingMing:$("#xingMing").val(),
/*			unitId:$("#unitId").val(),
*/			pathName:$("#unitName").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick=\"check2(this)\" value="+val.id+" name='check' /></td>" 
				+ "<td><a href='/system/user/show.action?id="+val.id+"'>"+ val.id + "</a></td>"
				+ "<td>"+ (val.xingMing==null?"":val.xingMing) + "</td>"
				+ "<td>"+ (val.mobile==null?"":val.mobile) + "</td>"
				+ "<td>"+ (val.email==null?"":val.email) + "</td>"
				+ "<td>"+ (val.pathName==null?"":val.pathName) + "</td>"
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
	if(id == "") {
		$("#message").text("请至少选择一个用户进行批量删除操作");
		$("#my-alert").modal('open');
		return false;
	}
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
	var href = '/system/user/userRole.action?id='+uid;
	openLayer(href,false);
}
function Edit(uid){
	var href = '/system/user/edit.action?id='+uid;
	openLayer(href,true);
}
function Show(uid){
	var href = '/system/user/show.action?id='+uid;
	openLayer(href,false);
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
function openLayer(href,flog){
	layer.open({
		type : 2,
		title : '',
		area : [ '80%', '80%' ],
		shade : 0,
		maxmin : true,
		content : href,
		yes : function(index,layero) {
			flog && load(1);
			layer.closeAll();//关闭弹出窗
		},
	
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);

		}
	});
}
function btAgain(){
	
	 $("#my-modal-loading").modal('open');
	$.get("/system/user/doAgain.action",function(data,status) {
		if (data.state == true) {
			$("#my-modal-loading").modal('close');
			load(1);
			$("#message").text("操作成功");
		} else {
			$("#my-modal-loading").modal('close');
			$("#message").text("操作失败："+data.message);
		}
		$("#my-alert").modal('open');
	});
}
$(document).ready(function () {
	$("#btAdd").click(function(){
		var href = '/system/user/add.action';
		openLayer(href,true);
	});
	$("#btExport").click(function(){
		var href = '/system/user/importUser.action';
		openLayer(href,true);
	});
	$("#btAgain").click(function(){
		btAgain();
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