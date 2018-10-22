<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title><%=request.getAttribute("appName") %></title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<style type="text/css">
	a {
		cursor:pointer;
	}
	.th{
	font-size: 1.4rem;
	font-weight: 400;
line-height: 1.6;
color: #333;
	}
	
</style>
<body>
	<div class="admin-content">
			<div class="admin-content-body">
				
				<div class="am-topbar">
					<form id="form"
						class="am-topbar-form am-topbar-left am-form-inline">
						<div class="am-form-group">
							<input id="userName" name="userName" type="text"
								class="am-form-field am-input-sm" placeholder="学生姓名" maxlength="25">
						</div>
						 <input type="hidden" name="id" id="id" value="${param.id }">
						 <input type="hidden" name="userId" id="userId" value="">
						 <input type="hidden" name="zhi" id="zhi" value="">
						<button id="btQuery" type="button" class="am-btn am-btn-primary"
							style="margin-left: 5px">
							<span class="am-icon-search"></span>查询
						</button>
						<button id="btSave" type="button" class="am-btn am-btn-primary"
							style="margin-left: 5px">
							<span class="am-icon-search"></span>保存
						</button>
					</form>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12">
					<div class="am-u-sm-4 th" id="th">
					姓名
					</div>
					<div class="am-u-sm-8 th" >
					操作
					</div>
					<hr id="a">
				
					</div>

				</div>
			</div>
		</div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示信息</div>
			<div id="message" class="am-modal-bd"></div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" id="btSure">确定</span>
			</div>
		</div>
	</div>
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示信息</div>
			<div class="am-modal-bd">确定要删除？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="waiting-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">请等待</div>
			<div class="am-modal-bd">
				<div class="am-progress">
					<div class="am-progress-bar" style="width: 0%">0%</div>
				</div>
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span>
			</div>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/layer/layer.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/pager.js"></script>
	<script src="/js/api.js"></script>
	<script src="/kaoqin/show.js"></script>
</body>
</html>