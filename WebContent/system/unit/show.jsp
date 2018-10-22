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
<link id="skin_css" rel="stylesheet" href="/skin/default/css/main.css">
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
							<th style="width: 200px">名称</th>
							<td>${unit.mingCheng }</td>
						</tr>
						<tr>
							<th style="width: 200px">上级单位</th>
							<td>${unit.parentName }</td>
						</tr>
						
					</tbody>
				</table>
			</div>
		</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>

</body>
</html>