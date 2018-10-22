function save() {
	var powerIds="";
	var selectedPowers=$('#tree').treeview('getChecked');
	$(selectedPowers).each(function(index, item) {
		powerIds+=item.id+",";
	});
	$.post("/system/power/saveRolePower.action",{
		roleId:$("#roleId").val(),
		powerIds:powerIds,
	}, function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function getPower() {
	$('#tree').treeview('uncheckAll',{ silent: true });
	var roleId = $("#roleId").val();
	$.post("/system/power/getRolePower.action", 
		{
			roleId : roleId
		}, function(data) {
			$(data.data).each(function(index, item) {
				checkNodeById(item.id);
			});
	});
}
function getRoleList() {
	var option = "<option value='-'>--选择角色--</option>";
	$("#roleId").append(option);
	$.ajax({
		type : "get",
		url : "/system/role/roleList.action",
		async : false,
		success : function(data) {
			if (data.state == false)
				return;
			$(data.data).each(
					function(key, item) {
						var option = "<option value='" + item.id + "'>"+ item.mingCheng + "</option>";
						$("#roleId").append(option);
					});
		}
	});
}
function load() {
	getRoleList();
	$("#roleId").val($("#FirstRoleId").val());
	getPower();
}
$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});

    $("#roleId").change(function () {
        getPower();
    });	
    
	$('#tree').treeview({
		data : myMenu,
		uiLibrary : 'bootstrap',
		levels : 1,
		showCheckbox:true,
        onNodeChecked:nodeChecked ,  
        onNodeUnchecked:nodeUnchecked,
	});    
	load();
});