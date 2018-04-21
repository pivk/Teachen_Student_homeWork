<%@page import="doc.system.entity.User"%>
<%@page import="java.util.List"%>
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
	height:400px;
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
					<div class="am-hide-sm-only am-u-md-6"></div>
				</div>
				<hr>
<form id="form" class="am-form">
	<input id="fuJianName" name="fuJianName" type="hidden">
					<input id="fuJianUrl" name="fuJianUrl" type="hidden">
				<table class="am-table am-table-bordered">
					<tr>
						<th>标题(必选)</th>
						<td rowspan="1" colspan="7"><input  id="biaoTi" name="biaoTi" type="text" class="am-input-sm" required maxlength="25">
						</td>
					</tr>

					<tr>
						<th>内容</th>
						<!-- 加载编辑器的容器 -->
						 <td rowspan="1" colspan="7">
						 <script id="container" name="neiRong" type="text/plain"  style="width:100%;height:400px;">
       						
   							 </script>
   							</td>
					</tr>
					<tr>
						<th>上传附件：</th>
						<td colspan="6">
							<button id="fj" type="button"
								class="am-btn am-btn-primary am-btn-sm">附件上传</button>
						</td>
						<td colspan="6"><input id="fuJianPath"
							name="fuJianPath" type="hidden" /> <input id="fuJianMC"
							name="fuJianMC" type="hidden" />
							<div id="fuJian"></div></td>
					</tr>
					<tr>
					    <th>选择通知人(必选)</th>
					    <th rowspan="1" colspan="6">
					    	<input id="zeRen" name="zeRen" type="hidden">
					   		<input id="xingMing" name="xingMing" type="text" required>
						</th>
						<td><button id="btOK" type="button" class="am-btn am-btn-primary">选择用户</button></td>
					</tr>
				</table>
				
				
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
				<span id="queDing"  class="am-modal-btn">确定</span>
			</div>
		</div>
	</div>
		<div class="am-modal am-modal-alert" tabindex="-1" id="shangchuan">
		<div class="am-modal-dialog">
			 <form action="simpleFileupload" method="post" enctype="multipart/form-data">  
  				 		<input class="am-btn am-btn-primary" type="file" name="fileupload"/>  
    					<input class="am-modal-hd" type="submit" value="submit"/>  
   			</form>  
			<div class="am-modal-footer">
				<span class="am-modal-btn">确定</span>
			</div>
		</div>
	</div> 
	
	
	<script src="/js/jquery.min.js"></script>
	<script src="/js/layer/layer.js"></script>
	<script src="/js/amazeui/js/amazeui.datetimepicker.min.js"></script>
	<script type="text/javascript" src="/js/webuploader/webuploader.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/api.js"></script>
	<script src="/information/noticeAdd.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btSave").click(function() {
			save();
		});
		$("#btBack").click(function() {
			location.href = "/information/noticeAdminPage.action";
		});
		$("#queDing").click(function() {
			 window.location.reload();
		});
		$("#fujian").click(function() {
			$("#message").text("操作成功");
			
			$("#shangchuan").modal('open');
		});
		
		$("#btOK").click(function() {
				$("#zeRen").val();
				$("#xingMing").val();
				layer.open({
					type : 2,
					title : '选择用户',
					area : [ '80%', '80%' ],
					shade : 0,
					maxmin : true,
					content : '/system/user/popup.action',
					btn : [ '确定', '关闭' ],
					yes : function(index,layero) {
						var iframeWin = window[layero.find('iframe')[0]['name']];
						var selected=iframeWin.getSelectedJson();//获取选择的数据Json格式
						var users="";
						var userIds="";
						jQuery.each(selected,function(key, val) {
							users+=val.name+";";
							userIds+=val.id+";"
						});
						$("#zeRen").val(userIds);
						$("#xingMing").val(users.substring(0,users.length-1));
						layer.closeAll();//关闭弹出窗
					},
					btn2 : function() {
						layer.closeAll();
					},
					zIndex : layer.zIndex,
					success : function(layero) {
						layer.setTop(layero);
					}
				});
		});
		UEditor()
	});
	</script>
	<!-- 配置文件 -->
    <script type="text/javascript" src="/js/UEditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/js/UEditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
</body>
</html>