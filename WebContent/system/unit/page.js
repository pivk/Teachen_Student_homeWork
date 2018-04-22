function load(newpage,text,parentId){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/unit/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
			parentId:parentId,
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+ "<td>"+ (val.code==null?"":val.code)+ "</td>"
				+ "<td>"+ (val.parentName==null?"":val.parentName) + "</td>"
				+"<td>"
				+"<button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">编辑</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Show('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-primary\">查看</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger\">删除</button></td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
	$("#select").text(text);
	$("#parentId").val(parentId);
}
function save() {
	if (!$('#formUnit').validator("isFormValid"))
		return false;
	$.post("/system/unit/doAddUnit.action", $("#formUnit").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			$("#form")[0].reset();
			$('#form').validator('destroy');
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}

function Edit(uid){
	var href = '/system/unit/edit.action?id='+uid;
	openLayer(href,true);
}
function Show(uid){
	var href = '/system/unit/show.action?id='+uid;
	openLayer(href,false);
}
function Remove(uid){
    var onConfirm = function() {
		$.get("/system/unit/doRemove.action", {id:uid}, function(data,status) {
			if (data.state == true) {
				$("#message").text("操作成功");
				location.href="/system/unit/page.action";
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
function openLayer(href,flog){
	layer.open({
		type : 2,
		title : '',
		area : [ '80%', '80%' ],
		shade : 0,
		maxmin : true,
		content : href,
		yes : function(index,layero) {
			flog && (location.href="/system/unit/page.action");
			layer.closeAll();//关闭弹出窗
		},

		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);

		}
	});
}
function treeList(){
	$('#tree').treeview({
		data : myMenu,
		uiLibrary : 'bootstrap',
		levels : 5,
		expandIcon:"glyphicon glyphicon-folder-close",
		collapseIcon:"glyphicon glyphicon-folder-open",
		emptyIcon:"glyphicon glyphicon-file",
		onNodeSelected : function(event, data) {
			load(1,data.text,data.id);
		}
	});
}

function openLayer(href,flog){
	layer.open({
		type : 2,
		title : '',
		area : [ '80%', '80%' ],
		shade : 0,
		maxmin : true,
		content : href,
		btn : ['关闭' ],
		yes : function(index,layero) {
			flog && load(1);
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
}
function btAgain(){
	 $("#my-modal-loading").modal('open');
	$.get("/system/unit/doAgain.action",function(data,status) {
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
	treeList();
	$("#btQuery").click(function(){
		load(1,$("#select").text(),$("#parentId").val());
	});

	$("#btAddUnit").click(function(){
		var href = '/system/unit/add.action?parentid='+$("#parentId").val()+'&text='+$("#select").text();
		openLayer(href,true);
	});
	
	load(1,null,null);
	$("#btSave").click(function() {
		save();
	});
	$("#btAgain").click(function(){
		btAgain();
		
		
//		var href = '/system/unit/importUnit.action';
//		openLayer(href,true);
	});
	//分页
	$("#first").click(function(){
		load(1,$("#select").text(),$("#parentId").val());
	});
	$("#befor").click(function(){
		load(page-1,$("#select").text(),$("#parentId").val());
	});
	$("#behind").click(function(){
		load(page+1,$("#select").text(),$("#parentId").val());
	});
	$("#end").click(function(){
		load(pages,$("#select").text(),$("#parentId").val());
	});

	
	
});

