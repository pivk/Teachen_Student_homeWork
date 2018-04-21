function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	if($("#lei").val()=="-"){
		$("#message").text("类别不能为空");
		$("#my-alert").modal('open');
		return false;
	}else{
		$.post("/system/dictionary/doAdd.action", $("#form").serialize(), function(data) {
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
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href = "/system/dictionary/page.action";
	});
	init();
});