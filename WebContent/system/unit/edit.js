function save() {
	if(!$('#form').validator("isFormValid"))
		return false;
	$.post("/system/unit/doEdit.action", $("#form").serialize(), function(data) {
		if (data.state==true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败："+data);
		}
		$("#my-alert").modal('open');
	});
}
function init(){
		var id=$("#id").val();	
		var option = "<option value='-'>--选择单位--</option>";
		$("#unitId").append(option);
		$.ajax({
			type : "get",
			url : "/system/unit/doList.action",
			async: false,
			success : function(data) {
				if(data.state==false) return;
				$(data.data).each(
					function(key, item) {
						if(item.id!=id){
							var option = "<option value='" + item.id + "'>"+ item.mingCheng + "</option>";
							$("#unitId").append(option);
						} 
					});
			}
		});
	
}
function load() {
	var id=$("#id").val();	
	$.getJSON("/system/unit/doGet.action", {id:id}, function(data) {
		if (data.state == false)
			return;
		$("#mingCheng").val(data.data.mingCheng);
		$("#code").val(data.data.code);
		$("#unitId").val(data.data.parentId);		
	});
}

$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href="/system/unit/page.action";
	});
	init();
	load();
});