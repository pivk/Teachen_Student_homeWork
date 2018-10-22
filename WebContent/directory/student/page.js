function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/directory/student/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
			xueqi:$("#xueqi").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+ "<td>"+ val.createTime+ "</td>"
				+ "<td>"+ val.endTime+ "</td>"
				+ "<td>"+ val.locked+ "</td>"
				+"<td><button type=\"button\" onclick=\"Show('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs\">查看</button>&nbsp;"
				+"<button type=\"button\" onclick=\"upload('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs\">上传文件</button>&nbsp;</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
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
function upload(uid){
	var href = '/files/add.action?directoryId='+uid;
	openLayer(href,false);
}

function Show(uid){
	var href = '/directory/showFile.action?id='+uid;
	openLayer(href,false);
}



$(document).ready(function () {

	$("#btQuery").click(function(){
		load(1);
	});
	load(1)

});