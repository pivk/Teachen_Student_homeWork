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
				<form id="form" class="am-form" >
					<div class="am-g am-margin-top">
					</div>

					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right"><span style="color:red">*&nbsp;</span>名称</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input id="mingCheng" name="mingCheng" type="text" required maxlength="50"
								class="am-input-sm"  >
						</div>											
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">内部号</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
							<input id="code" name="code" type="text" maxlength="20"
								class="am-input-sm"  >
						</div>						
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">上级单位</div>
						<div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
						<!-- 	<select id="unitId" name="parentId">
						</select>	 -->	
						<input type="hidden"name="parentId" value="<%=request.getParameter("parentid")==null?"":request.getParameter("parentid")%>">
						<input type="text" class="am-input-sm am-form-field"  value="<%=request.getParameter("text")==null?"":request.getParameter("text")%>" disabled>				
						</div>
					</div>
					<div style="height: 50px"></div>
					<div class="am-form-group">
						<div class="am-u-sm-9 am-u-sm-push-3">
							<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
							<button id="btCancel" type="button" class="am-btn am-btn-primary">取消</button>
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
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/api.js"></script>
	<script src="/system/unit/add.js"></script>
</body>
</html>