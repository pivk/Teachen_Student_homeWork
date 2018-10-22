<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("appName")%></title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/js/bootstrap-treeview/bootstrap-treeview.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
<style>
.treeview .list-group-item {
	background-image: none;
}
</style>
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-topbar">
				<form id="form" class="am-topbar-form am-topbar-left am-form-inline">
					<div class="am-form-group">
						<input id="mingCheng" name="mingCheng" type="text"
							class="am-form-field am-input-sm" placeholder="名称" maxlength="25">
					</div>
					<button id="btQuery" type="button" class="am-btn am-btn-default"
						style="margin-left: 5px">
						<span class="am-icon-search"></span>查询
					</button>
				</form>
			</div>

			<div class="am-g">
				<div class="am-u-sm-12">
					<div id="tree"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script>
	function getSelected(){
		var selected=$('#tree').treeview('getSelected');
		if (selected.length == 0) {
			return null;
		}
		var json='[';
		$(selected).each(function(index,item){
			if(index>0){
				json+=",";
			}
			var id=item.id;
			var name=item.text;
			json+='{"id":"'+id+'","name":"'+name+'"}';
		});
		json+=']';
		return json;
	}
	function getSelectedJson(){	
		var json=getSelected();
		return JSON.parse(json);
	}
	function load() {
		$("#table > tbody").empty();
		$.get("/project/doselectProject.action",
			{
				mingCheng : $("#mingCheng").val(),
			},function(response, status) {
			
				$('#tree').treeview({
					data : response.data,
					uiLibrary : 'bootstrap',
					levels : 3,
				});
			}
		);
	}
	$(document).ready(function() {
		$("#btQuery").click(function() {
			load();
		});
		load();
	});
	</script>
</body>
</html>