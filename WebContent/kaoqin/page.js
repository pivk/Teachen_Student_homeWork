function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/course/doUSerPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val()
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+"<td><button type='button' onclick=Kaoqin('"+val.id+"') class='am-btn am-btn-default am-btn-xs am-text-primary'>考勤	</button>&nbsp;"
				+"<button type='button' onclick=Show('"+val.id+"') class='am-btn am-btn-default am-btn-xs am-text-primary'>查看	</button>&nbsp;</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function Kaoqin(uid){
	layer.open({
		type : 2,
		title : '考勤',
		area : [ '80%', '95%' ],
		shade : 0,
		maxmin : true,
		content : '/kaoqin/show.action?id='+uid,
		zIndex : layer.zIndex,
		success : function(layero) {
			layer.setTop(layero);

		}
	});
}
function Show(uid){
	layer.open({
		type : 2,
		title : '查看',
		area : [ '80%', '95%' ],
		shade : 0,
		maxmin : true,
		content : '/kaoqin/adminPage.action?id='+uid,
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