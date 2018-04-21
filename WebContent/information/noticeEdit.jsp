<%@page import="doc.information.entity.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("appName") %></title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
<link rel="stylesheet" href="/js/webuploader/webuploader.css" />
<link rel="stylesheet" href="/skin/default/css/main.css">
<style type="text/css">
.biaoti{
	padding: 10%;
}
.fuwenben{
	width: 100%;
	height:30%;
}
.ban {
	width:50%;
}
</style>
</head>
<body>

		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg"><%=request.getAttribute("title") %></strong>
					</div>
				</div>
				<hr>
				<form id="form" class="am-form">
					<input id="id" name="id" type="hidden" value="<%=request.getAttribute("id") %>" />


	<table class="am-table am-table-bordered">
					<tr>
						<th>标题(必填)</th>
						<td rowspan="1" colspan="7"><input id="biaoTi" name="biaoTi" type="text" class="am-input-sm" required maxlength="25">
						</td>
					</tr>

					<tr>
						<th>内容</th>
						<!-- 加载编辑器的容器 -->
						 <td rowspan="1" colspan="7">
						 <script id="container" name="neiRong" type="text/plain" style="width:100%;height:400px;">
   							 </script>
   							</td>
					</tr>
					<tr>
						<th>上传附件：</th>
						<td class="ban">
							<button id="fj" type="button"
								class="am-btn am-btn-primary am-btn-sm">附件上传</button>
						</td>
						<td><input id="fuJianUrl"
							name="fuJianUrl" type="hidden" /> <input id="fuJianName"
							name="fuJianName" type="hidden" />
							<div id="fuJian"></div></td>
					</tr>
				</table>
					<div style="height: 50px"></div>
					<div class="am-form-group">
						<div class="am-u-sm-9 am-u-sm-push-5">
							<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
							<button id="btBack" type="button" class="am-btn am-btn-primary">返回</button>
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
	<script src="/js/amazeui/js/amazeui.datetimepicker.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/api.js"></script>
	<script src="/information/noticeEdit.js"></script>
	<!-- 配置文件 -->
    <script type="text/javascript" src="/js/UEditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/js/UEditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
</body>
</html>