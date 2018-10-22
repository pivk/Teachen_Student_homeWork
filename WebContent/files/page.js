function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/files/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
			directoryId:$("#directoryId").val(),
			unitId:$("#unitId").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick=\"check2(this)\" value="+val.id+" name='check' /></td>" 
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+ "<td>"+ (val.fname==null?"":val.fname) + "</td>"
				+ "<td>"+ (val.size==null?"":val.size/1024/1024).toFixed(2)+'M' + "</td>"
				+ "<td>"+ (val.geShi==null?"":val.geShi) + "</td>"
				+ "<td>"+ (val.score==null?"":val.score) + "</td>"
				+"<td><button type=\"button\" onclick=\"Showword('"+val.realPath+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">预览</button>&nbsp;"
				+"<button type=\"button\" onclick=\"down('"+val.realPath+"'\,'"+val.originalName+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">下载</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Dafen('"+val.id+"'\,'"+val.score+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">打分</button>&nbsp;</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
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
$("#btLoad").click(function() {
	var id = "";
	$("[name='check']").each(function(index,element){
		if($(this).prop("checked")==true){
			id+= $(this).val()+",";
		} 
	});	
	location.href="/files/downFiles.action?id="+id+"&mingCheng="+$("#fileName").val()
});	
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
function down(realPath,originalName){
	location.href = "/file/down.action?realPath="+realPath+"&fileName="+originalName
}
function Dafen(id,score){
	$("#lunwenScore").val(score);
	 var onConfirm = function() {
			$.get("/files/doEdit.action", {
				id:id,
				score:$("#lunwenScore").val(),
			}, function(data,status) {
				if (data.state == true) {
					$("#message").text("操作成功");
					$("#lunwenScore").val('');
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
function Showword(realPath){

	window.open( '/files/showDoc.action?realPath='+realPath)
}


$("#unitName").click(function() {
	layer.open({
		type : 2,
		title : '选择班級	',
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



$(document).ready(function () {
	$("#btAdd").click(function(){
		var href = '/system/user/add.action';
		openLayer(href,true);
	});
	
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
			content : '/directory/add.action?parentId=xueqi='+$("#xueqi").val(),
			zIndex : layer.zIndex,
			success : function(layero) {
				layer.setTop(layero);
			}
		});
	});
	$("#main").click(function(){
		check(this);
	});
	$("#btCreateFile").click(function() {
		layer.open({
			type : 2,
			title: false,
			area : [ '1000px', '700px' ],
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