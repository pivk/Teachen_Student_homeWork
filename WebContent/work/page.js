function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/work/doWorkPageUser.action",{ 
			page: page,
			biaoti:$("#biaoti").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td><a onclick=Show('"+val.id+"')>"+ (val.biaoTi==null?"":val.biaoTi) + "</a></td>"
				+ "<td>"+ (val.createTime==null?"":val.createTime.substring(0,16)) + "</td>"
				+ "<td>"+ (val.creator==null?"":val.creator) + "</td>"
				+"<td><button type='button' onclick=Show('"+val.id+"') class='am-btn am-btn-default am-btn-xs am-text-primary'>查看</button>&nbsp;"
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

$(document).ready(function () {
	$("#btQuery").click(function(){
		load(1);
	});
	load(1);
});