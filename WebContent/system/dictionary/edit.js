function save() {
	if(!$('#form').validator("isFormValid"))
		return false;
	$.post("/system/dictionary/doEdit.action", $("#form").serialize(),function(data) {
			if (data.state==true) {
				$("#message").text("操作成功");
			} else {
				$("#message").text("操作失败："+data);
			}
			$("#my-alert").modal('open');
		});
}

function init() {
	var option = "<option value='-'>--选择类别--</option>";
	$("#lei").append(option);
	$.ajax({
		type : "get",
		url : "/system/dictionary/doList.action",
		async: false,
		success : function(data) {
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.lei + "'>"+ item.lei + "</option>";
					$("#lei").append(option);
				});
		}
	});
}

function load() {
	var id=$("#id").val();	
	$.getJSON("/system/dictionary/doGet.action", {id:id}, function(data) {
		if (data.state == false)
			return;
		$("#lei").val(data.data.lei);
		$("#jian").val(data.data.jian);
		$("#zhi").val(data.data.zhi);
		$("#xu").val(data.data.xu);
	});		
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href="/system/dictionary/page.action";
	});	
	init();
	load();
});