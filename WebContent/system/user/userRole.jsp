<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${appName}</title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link id="skin_css" rel="stylesheet" href="/skin/${sessionScope.get('USER').skin}/css/main.css">
</head>
<body>
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">${title}</strong>
					</div>
				</div>
				<hr>
				<form id="form" class="am-form">
				<input id="id" name="id" type="hidden" value="${user.id}" />
					<div class="am-form-group">
					用户 <label id="xingMing">${user.xingMing}</label> 当前角色
					</div>
					<div class="am-form-group" id="role" style="margin-left:50px">
					 
					</div>
					<div style="height: 50px"></div>
					<div class="am-form-group">
						<div class="am-u-sm-9 am-u-sm-push-3">
							<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
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
				<span class="am-modal-btn" id="btsure">确定</span>
			</div>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/system/user/userRole.js"></script>
</body>
</html>