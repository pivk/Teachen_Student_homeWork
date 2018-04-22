<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="doc.system.view.PowerTree"
	import="doc.system.view.PowerV"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("appName")%></title>
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/js/bootstrap-treeview/bootstrap-treeview.css">
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
<style>
.treeview .list-group-item {
	background-image: none;
}
</style>
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body" style="margin-bottom: 20px">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg"><%=request.getAttribute("title")%></strong>
				</div>
			</div>
			<hr>
			<div class="am-topbar">
				<form id="form" class="am-topbar-form am-topbar-left am-form-inline">
					<input type="hidden" id="FirstRoleId" value="<%=request.getAttribute("id")%>">
					<div class="am-form-group">
						<select id="roleId" name="roleId" data-am-selected
							class="am-input-sm"></select>
					</div>
					<button id="btSave" type="button" class="am-btn am-btn-primary"
						style="margin-left: 5px">
						<span class="am-icon-search"></span>保存
					</button>	
				</form>
			</div>
			<div id="tree"></div>
		</div>
	</div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示信息</div>
			<div id="message" class="am-modal-bd"></div>
			<div class="am-modal-footer">
				<span class="am-modal-btn">确定</span>
			</div>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview-pushunsoft.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script>
		var myMenu =<%=request.getAttribute("tree")%>;
	</script>	
	<script src="/system/role/power.js"></script>
</body>
</html>