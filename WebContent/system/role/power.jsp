<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="admin-content-body" style="margin-bottom: 20px">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">${title}</strong>
					</div>
				</div>
				<hr>
				<form id="form" class="am-form">
					<input type="hidden" id="FirstRoleId" value="${id}">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">角色</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<select id="roleId" name="roleId" data-am-selected
								class="am-input-sm"></select>
						</div>
					</div>
					<c:forEach var="power" items="${tree.nodes}" varStatus="status">
						<div class="panel panel-default">
							<div class="panel-heading">
								<label class="checkbox-inline"> <input type="checkbox"
									id="${power.id}" value="${power.id }">${power.mingCheng }
								</label>
							</div>
							<div class="panel-body" id="${power.id }_Power">
								<c:forEach var="power2" items="${power.nodes}"
									varStatus="status">
									<label class="checkbox-inline"> <input type="checkbox"
										id="${power2.id}" name="Power" value="${power2.id }">${power2.mingCheng }
									</label>
								</c:forEach>
							</div>
						</div>
					</c:forEach>

				</form>
				<div style="height: 20px"></div>
				<div class="am-form-group">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
						<button id="btBack" type="button" class="am-btn am-btn-primary">返回</button>
					</div>
				</div>
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
	<script src="/system/role/power.js"></script>
</body>
</html>