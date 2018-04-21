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
				<div class="am-u-sm-4 am-u-md-2 am-text-left"
					style="font-weight: bold;">基本信息</div>
				<table id="table"
					class="am-table am-table-striped am-table-hover table-main"
					style="font-size: 12pt">
					<tbody>
						<tr>
							<th style="width: 200px">用户名</th>
							<td>${user.id }</td>
						</tr>
						<tr>
							<th>姓名</th>
							<td>${user.xingMing }</td>
						</tr>
						<tr>
							<th>手机</th>
							<td>${user.mobile }</td>
						</tr>
						<tr>
							<th>邮箱</th>
							<td>${user.email }</td>
						</tr>
						<tr>
							<th>所属机构</th>
							<td>${user.unitName }</td>
						</tr>
					</tbody>
				</table>
			<div style="height: 20px"></div>
			<div class="am-form-group">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<a class="am-btn am-btn-primary" href="/system/user/page.action">返回</a>
					</div>
				</div>
			</div>
		</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
</body>
</html>