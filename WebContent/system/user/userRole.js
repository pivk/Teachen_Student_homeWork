function save() {
	
	var roleId = "";
	$("[name='check']").each(function(index,element){
		if($(this).prop("checked")==true){
			roleId+= $(this).val()+",";
		} 
	});	
	$.post("/system/user/setUserRole.action", {id:$("#id").val(),roleId:roleId}, function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function getRoleList(){
	$("#role").empty();
	$.ajax({
		type : "get",
		url : "/system/role/roleList.action",
		async: false,
		success : function(data) {
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var label = "<label class=\"am-checkbox-inline\">"
					+"<input type='checkbox' name='check' value="+item.id+" data-am-ucheck> "+item.mingCheng+""
				    +"</label>"
					$("#role").append(label); 
				});
		}
	});
}
function load() {
	var id = $("#id").val();
	var roleId = $("[name='check']");
	$.getJSON("/system/user/getRole.action", {
		id : id
	}, function(data) {
		if (data.state == false)
			return;
		for(var i = 0;i < roleId.length; i++){
			var res = [].slice.call(data.data.roles);
			for(var j = 0;j < res.length;j++){
				if(roleId[i].value==res[j].id){
					roleId[i].checked=true;
				}
			}
		}
	});
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href = "/system/user/page.action";
	});
	getRoleList();
	load();
});