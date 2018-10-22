var tree_data=null;
function getTreeNode(id){
	var item=null;
	jQuery.each(tree_data,function(key, val) {
		if(val.id==id){
			item=val;
			return false
		}
	});
	return item;
}
/**
 * 从外面刷新页面
 * @returns
 */
function reload(){
	load(page);
}
/**
 * 刷新页面
 * @param newpage
 * @returns
 */
function loadData(newpage,url) {
	calPage(newpage);
	$("#filesview").empty();
	$.get(url,{
		page : page,
		parentId:$("#parentId").val(),
		xueqi:$("#xueqi").val(),
		mingCheng:$("#mingCheng").val(),
	},function(response, status) {
		showPage(response.data.data.total);
		tree_data=response.data.data.data;//缓存数据
		jQuery.each(response.data.data.data,function(key, val) {
			var node = '<div class="am-thumbnail-item" data-id="'+val.id+'" data-lei="'+val.lei+'">';
			node+='<img src="/images/'+val.icon+'.png" />';
			node+='<div>'+val.mingCheng+'</div>';
			node+='</div>';
			$("#filesview").append(node);
		});
		$( ".filesview" ).selectable({
		   autoRefresh : true,
		});
		$( ".am-thumbnail-item" ).dblclick(function(){
			var dataId=$(this).attr("data-id"); 
			var dataLei=$(this).attr("data-lei");
			show(dataId,dataLei);
		});
		
		
	    $.contextMenu({
	        selector: '.am-thumbnail-item', 
	        callback: function(key, options) {
	            var m = "clicked: " + key;
	            var len=$(".ui-selected").length;
	         
	        },
	        items: {
	            "edit": {name: "编辑", icon: "edit"},
	            "cut": {name: "剪切", icon: "cut"},
	            "copy": {name: "复制", icon: "copy"},
	            "paste": {name: "粘贴", icon: "paste"},
	            "delete": {name: "删除", icon: "delete"},
	            "sep1": "---------",
	            "lock": {name: "锁定", icon: ""},
	            "version": {name: "版本", icon: ""},
	            "sep2": "---------",
	            "quit": {name: "关闭", icon: "quit"}
	        }
	    });
	    $("#navPath").html(response.data.nav);
	});
}
function show(id,type){
	if(type=="目录"){
		showDirectory(id);
	}
	else{
		showFile(id);
	}
}
function showDirectory(id){
	$("#parentId").val(id);
	reload();
}
function showFile(id){
	alert("文件="+id);
}
/**
 * 创建目录
 * @returns
 */
function createDirectory(parentId,xueqi){
	layer.open({
		type : 2,
		title: false,
		area : [ '90%', '90%' ],
		shade : 0,
		maxmin : true,
		content : '/directory/add.action?parentId='+parentId+'&xueqi='+xueqi,
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);
		}
	});
}

/**
 * 删除
 * @returns
 */
function remove(id,type){
	if(type=="目录"){
		$.post("/directory/doRemove.action",{id:id}, function(data) {
			if (data.state == true) {
				reload();
			}
		});	
	}
	else if(type=="文件"){
		$.post("/file/doRemove.action",{id:id}, function(data) {
			if (data.state == true) {
				reload();
			}
			
		});	
	}
}

function createFile(parentId){
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
}

function downloadFile(){
	var selectedList=$(".ui-selected");
	var selectedItem=selectedList[0];
	var dataId=$(selectedItem).attr("data-id"); 
	if(dataId==null) return;
	
	location.href="/file/download2.action?id="+dataId;
}
