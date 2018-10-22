function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/course/doUserPage.action",{ 
		id:$("#id").val(),
		userName:$("#userName").val(),

		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+ "<td>"+ (val.userName==null?"":val.userName) + "</td>"
				+ "<td>"+ (val.late==null?"":val.late) + "</td>"
				+ "<td>"+ (val.crunk==null?"":val.crunk) + "</td>"
				+ "<td>"+ (val.normal==null?"":val.normal) + "</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
$(document).ready(function () {
	$("#btQuery").click(function(){
		load(1);
	});
	
	load(1);
});