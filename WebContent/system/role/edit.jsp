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
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg"><%=request.getAttribute("title")%></strong>
				</div>
			</div>
			<hr>
			<form id="form" class="am-form">
				<input type="hidden" id="id" name="id" value="<%=request.getAttribute("id")%>">
				<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">角色</div>
					<div class="am-u-sm-8 am-u-md-4">
						<input id="mingCheng" name="mingCheng" type="text"
							class="am-input-sm" required />
					</div>
					<div class="am-hide-sm-only am-u-md-6"></div>
				</div>
				<div style="height: 50px"></div>
				<div class="am-form-group">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
						<button id="btBack" type="button" class="am-btn am-btn-primary">关闭</button>
					</div>
				</div>
			</form>

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
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/system/role/edit.js"></script>
</body>
</html>