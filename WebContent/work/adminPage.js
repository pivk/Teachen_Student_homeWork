function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/work/doAdminPage.action",{ 
			page: page,
			biaoti:$("#biaoti").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick='check2(this)' value="+val.id+" name='check' /></td>" 
				+ "<td><a onclick=Show('"+val.id+"')>"+ (val.biaoTi==null?"":val.biaoTi) + "</a></td>"
				+ "<td>"+ (val.createTime==null?"":val.createTime.substring(0,16)) + "</td>"
				+ "<td>"+ (val.creator==null?"":val.creator) + "</td>"
				+"<td><button type='button' onclick=Edit('"+val.id+"') class='am-btn am-btn-default am-btn-xs am-text-secondary'>编辑</button>&nbsp;"
				+"<button type='button' onclick=Show('"+vad
				+"<button type='button' onclick=Remove('"+val.id+"') class='am-btn am-btn-default am-btn-xs am-text-danger'>删除</button></td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function Show(uid){
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
function Edit(uid){
	location.href="/work/edit.action?id="+uid;
}
function Remove(uid){
    var onConfirm = function() {
		$.get("/information/doNoticeRemove.action", {id:uid}, function(data,status) {
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

function deleteNotice() {
	var id = "";
	$("[name='check']").each(function(index,element){
		if($(this).prop("checked")==true){
			id+= $(this).val()+",";
		} 
	});	
	$.post("/information/deleteNotice.action", {id:id}, function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			load(page);
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}

$(document).ready(function () {
	$("#btAdd").click(function(){
		location.href="/work/add.action";
	});
	$("#btQuery").click(function(){
		load(1);
	});
	$("#btDelete").click(function(){
		deleteNotice();
	});
	$("#main").click(function(){
		check(this);
	});
	load(1);
	
});