
function show(url){
	layer.open({
		type : 2,
		title : '查看',
		area : [ '80%', '95%' ],
		shade : 0,
		maxmin : true,
		content : url,
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);
		}
	});
}
function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/information/doDoorPage.action",{ 
			page: page,
			biaoti:$("#biaoti").val(),
		},function(response,status){
			var index=1
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td>"+index+"</td>" 
				+ "<td>"+ (val.biaoTi==null?"":val.biaoTi) + "</td>"
				+ "<td>"+ (val.memo==null?"":val.memo) + "</td>"
				+ "<td>"+ (val.createTime==null?"":val.createTime.substring(0,16)) + "</td>"
				+"<td><button type=\"button\" onclick=\"show('"+val.url+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">"+val.nextName+"</button>&nbsp;</td>"
				+"</tr>";
				index++;
			$("#table > tbody").append(row);
		});
	  });
}
function load2(){
	$.get("/information/doDoorPage.action",{ 
			page: page,
			biaoti:$("#biaoti").val(),
		},function(response,status){
			var index=1
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td>"+index+"</td>" 
				+ "<td>"+ (val.biaoTi==null?"":val.biaoTi) + "</td>"
				+ "<td>"+ (val.memo==null?"":val.memo) + "</td>"
				+ "<td>"+ (val.createTime==null?"":val.createTime.substring(0,16)) + "</td>"
				+"<td><button type=\"button\" onclick=\"show('"+val.url+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">"+val.nextName+"</button>&nbsp;</td>"
				+"</tr>";
				index++;
			$("#table > tbody").append(row);
		});
	  });
}
$(document).ready(function() {
	load(1)
	$("#btQuery").click(function(){
		load(1);
	});
});
