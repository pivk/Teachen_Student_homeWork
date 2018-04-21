function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/log/doPage.action",{ page: page ,BiaoTi:$("#BiaoTi").val()},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var unixTimestamp = new Date( val.date) ;
			commonTime = unixTimestamp.toLocaleString();
			commonTime = commonTime.substring(0,19)
			var row = "<tr>"
				+ "<td>"+ val.biaoTi + "</td>"
				+ "<td>"+ commonTime + "</td>"
				+ "<td>"+ val.xingMing + "</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function doExport(){
	$.post("/system/log/doExport.action.action",{
		biaoTi:$("#biaoTi").val()
	},function(data) {
		if (data.state == true) {
			$("#message").text("导出成功");
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
$(document).ready(function () {
	$("#btQuery").click(function(){
		load(1);
	});	
	load(1);
	$("#btExport").click(function(){
		doExport();
	});
});