function save() {
	$.post("/system/power/saveRolePower.action",$("#form").serialize(), function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}
function getPower() {
	$("input[name='Power']").removeAttr("checked");
	var roleId = $("#roleId").val();
	$.post("/system/power/getRolePower.action", {
		roleId : roleId
	}, function(data) {
		$(data.data).each(function(index, item) {
			$("#" + item.id).prop("checked", true);
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
$(document).ready(
		function() {
			$("#btSave").click(function() {
				save();
			});
			$("#btBack").click(function() {
				location.href = "/system/role/page.action";
			});
		    $("#roleId").change(function () {
		        getPower();
		    });		
			$(".checkbox-inline :checkbox").each(
					function(index, item) {
						var id = $(item).attr("id");
						$(item).change(function() {
									$("#" + id + "_Power :checkbox").prop("checked",$("#" + id).prop("checked"));
								});
						$("#" + id + "_Power :checkbox").change(
								function() {
									var found = false;
									$("#" + id + "_Power :checkbox").each(
											function(index, item) {
												if ($(item).prop("checked")) {
													found = true;
													return false;
												}
											});
									$("#" + id).prop("checked", found);
								});
					});

			load();
		});