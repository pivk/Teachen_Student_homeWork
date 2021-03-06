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
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right"><span style="color:red">*&nbsp;</span>用户名</div>
						<div class="am-u-sm-8 am-u-md-4">
							<input id="id" name="id" type="text" class="am-input-sm" required maxlength="20"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6">*必填，不可重复</div>
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">姓名</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input id="xingMing" name="xingMing" type="text" maxlength="50"
								class="am-input-sm">
						</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">手机</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input id="mobile" name="mobile" type="text" maxlength="11"
								class="am-input-sm" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
						</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">邮箱</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input id="email" name="email" type="text" maxlength="25"
								class="am-input-sm">
						</div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right"><span style="color:red">*&nbsp;</span>班级</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input id="a"   name="unitName" type="text" class="am-form-field am-input-sm" placeholder="班级" maxlength="25">
							<input id="unitId" required name="unitId" type="hidden">
						</div>
					</div>
					<div style="height: 50px"></div>
					<div class="am-form-group">
						<div class="am-u-sm-9 am-u-sm-push-3">
							<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
							<button id="btCancel" type="button" class="am-btn am-btn-primary">取消</button>						</div>
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
	<script src="/js/api.js"></script>
	<script src="/js/layer/layer.js"></script>	
	<script src="/system/user/add.js"></script>
</body>
</html>