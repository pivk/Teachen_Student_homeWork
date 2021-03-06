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
			<form id="form" class="am-form">
				<input type="hidden" name="xueqi"
					id="xueqi" value="${param.xueqi}" />
				<input type="hidden" name="id"
					id="id" value="${param.id}" />
				<div class="am-g am-margin-top">
					<div class="am-u-sm-3 am-u-md-2 am-text-right">文件夹名称</div>
					<div class="am-u-sm-9 am-u-md-8 am-u-end col-end">
						<input id="mingCheng" name="mingCheng" type="text" maxlength="25"
							class="am-input-sm">
					</div>
					<div class="am-hide-sm-only am-u-md-2">*必填，不可重复</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-3 am-u-md-2 am-text-right">选择课程</div>
					<div class="am-u-sm-9 am-u-md-6 am-u-end col-end">
						<input id="courseId" name="courseId" type="hidden"> <input
							id="courseName" name="course" type="text">
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-md-2 am-text-right">截至	</div>
					<div class="am-u-md-10 am-input-group ">
							<input id="endTime" name="endTime" type="text" data-am-datepicker readonly required>	
					</div>
					
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-md-2 am-text-right">说明</div>
					<div class="am-u-md-7">
						<textarea id="memo" name="memo" class="form-control" rows="3"></textarea>
					</div>
					<div class="am-hide-sm-only am-u-md-3">最多支持1000字</div>
				</div>

				<div style="height: 50px"></div>
				<div class="am-form-group">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
						<button id="btClose" type="button" class="am-btn am-btn-primary">关闭</button>
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
	<script src="/js/layer/layer.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/api.js"></script>
	<script src="/directory/edit.js"></script>
</body>
</html>