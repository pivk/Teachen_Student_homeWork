function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/directory/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
			xueqi:$("#xueqi").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ val.mingCheng+ "</td>"
				+ "<td>"+ val.countshu+'/'+val.zong+ "</td>"
				+ "<td>"+ val.createTime.substring(0,10)+ "</td>"
				+ "<td>"+ val.endTime+ "</td>"
				+ "<td>"+ val.locked+ "</td>"
				+"<td><button type=\"button\" onclick=\"Show('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">查看</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">修改</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">删除</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Open('"+val.id+"','"+val.mingCheng+"','"+val.courseId+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">打开</button>&nbsp;"
				if(val.locked=='是'){
					row+="<button type=\"button\" onclick=\"unlocked('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">解锁</button>&nbsp;"
				}
				if(val.locked=='否'){
					row+="<button type=\"button\" onclick=\"locked('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">锁定</button>&nbsp;"
				}
				row+="<button type=\"button\" onclick=\"tu('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">图表</button>&nbsp;</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}

function locked(uid){
	$.get("/directory/doEdit.action", {id:uid,locked:'是'}, function(data,status) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败："+data.message);
		}
		$("#my-alert").modal('open');
	});
}
function unlocked(uid){
	$.get("/directory/doEdit.action", {id:uid,locked:'否'}, function(data,status) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败："+data.message);
		}
		$("#my-alert").modal('open');
	});
}
function upload(uid){
	var href = '/files/add.action?directoryId='+uid;
	openLayer(href,false);
}

function Edit(uid){
	var href = '/directory/edit.action?id='+uid+'&xueqi='+$("#xueqi").val();
	openLayer(href,true);
}
function Open(uid,mingCheng,courseId){
	var href = '/files/page.action?directoryId='+uid+"&fileName="+mingCheng+"&courseId="+courseId;
	openLayer(href,true);
}

function Show(uid){
	var href = '/directory/show.action?id='+uid;
	openLayer(href,false);
}
function tu(uid){
	var href = '/directory/showTu.action?courseId='+uid;
	openLayer(href,false);
}

function openLayer(href,flog){
	layer.open({
		type : 2,
		title : '',
		area : [ '90%', '90%' ],
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
function Remove(uid){
    var onConfirm = function() {
		$.get("/directory/doRemove.action", {id:uid}, function(data,status) {
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

$(document).ready(function () {

	
	$("#btQuery").click(function(){
		load(1);
	});
	$("#btsure").click(function(){
		load(1);
	});
	$("#btCreateDirectory").click(function() {
		layer.open({
			type : 2,
			title: false,
			area : [ '90%', '90%' ],
			shade : 0,
			maxmin : true,
			content : '/directory/add.action?xueqi='+$("#xueqi").val(),
			zIndex : layer.zIndex,
			success : function(layero) {
				layer.setTop(layero);
			}
		});
	});
	$("#btCreateFile").click(function() {
		layer.open({
			type : 2,
			title: false,
			area :  [ '90%', '90%' ],
			shade : 0,
			maxmin : true,
			content : '/file/add.action?parentId='+parentId,
			zIndex : layer.zIndex,
			success : function(layero) {
				layer.setTop(layero);
			}
		});
	});	
	load(1);
	
});