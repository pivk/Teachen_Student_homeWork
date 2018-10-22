<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("appName")%></title>
<link rel="stylesheet" href="/js/Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
</head>
<body>
		<div class="admin-content">
			<div class="admin-content-body" style="margin-bottom: 20px">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg"><%=request.getAttribute("title")%></strong>
					</div>
				</div>
				<hr>
				<form id="form" class="am-form">
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">上传文件保存地址</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
							<input id="uploadPath" name="uploadPath" type="text" class="am-input-sm">
						</div>
					</div>
				<!-- 	<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">审核延迟(分钟)</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
							<input id="delayTime" name="delayTime" type="number" class="am-input-sm">
						</div>
					</div> -->
					<!-- 
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">是否自动审核</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<select id="review" name="review" class="am-input-sm">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
						</div>
					</div> 
					 -->
					 <input id="review" name="review" type="hidden" value="false">
					<input id="apply" name="apply" type="hidden" value="true">
					<input id="shenPi" name="shenPi" type="hidden" value="true">
					<!-- 
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">是否自动申请</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<select id="apply" name="apply" class="am-input-sm">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
						</div>
					</div> 
					 -->
					 <!-- 
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">是否自动审批</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<select id="shenPi" name="shenPi" class="am-input-sm">
								<option value="true">是</option>
								<option value="false">否</option>
							</select>
						</div>
					</div> 
					 -->
			<!-- 		<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">预警设置</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
							<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
								<input id="date" name="date" type="number"  min="1"  class="am-input-sm"/>
							</div>
						</div>
					</div>  -->
			<!-- 		<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">领导ID</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
							<input id="boss" name="boss" type="text" class="am-input-sm">
						</div>
					</div>		
						<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">是否推送</div>
						<div class="am-u-sm-8 am-u-md-6 am-u-end col-end">
							<select id="flog" name="flog" class="am-input-sm">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
						</div>
					</div> -->
							
				</form>
				<div style="height: 20px"></div>
				<div class="am-form-group">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<button id="btSave" type="button" class="am-btn am-btn-primary">保存</button>
						<button id="btSync" type="button" class="am-btn am-btn-primary">取消</button>
					</div>
				</div>
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
	<script src="/js/bootstrap-treeview/bootstrap-treeview.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/system/config/get.js"></script>
</body>
</html>