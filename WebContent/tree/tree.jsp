<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${appName}</title>
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet"
	href="/js/jquery.contextMenu/jquery.contextMenu.css">
<link rel="stylesheet" href="/js/jquery-ui/jquery-ui.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-topbar">
				<form id="form" class="am-topbar-form am-topbar-left am-form-inline">
					<input type="hidden" name="parentId" id="parentId" value="${parentId}" />
					<select id="xueqi" name="xueqi"
						data-am-selected="{btnSize: 'sm'}">
						<option value="2017-2018上学期" >2017-2018上学期</option>
						<option value="2017-2018下学期">2017-2018下学期</option>
						<option value="2016-2017上学期">2016-2017上学期</option>
						<option value="2016-2017下学期">2016-2017下学期</option>
						<option value="2015-2016上学期">2015-2016上学期</option>
						<option value="2015-2016下学期">2015-2016下学期</option>
					</select>
					<div class="am-form-group">
						<input id="mingCheng" name="mingCheng" type="text"
							class="am-form-field am-input-sm" placeholder="文档名称">
					</div>
					<button id="btQuery" type="button" class="am-btn am-btn-default"
						style="margin-left: 5px">
						<span class="am-icon-search"></span>查询
					</button>
					<button id="btCreateDirectory" type="button" class="am-btn am-btn-primary am-radius">
						<span class="am-icon-create"></span>创建目录
					</button>
				</form>
			</div>
			<div class="am-g">
				<ol id="navPath" class="am-breadcrumb">
					<li class="am-active">首页</li>
				</ol>
				<div id="filesview" class="filesview">

				</div>
				<div style="margin-top: 50px"></div>
				<div class="am-u-sm-12">
				<div class="am-cf">
					<div class="am-fl am-pagination">
						共<span id="total"></span>条 第<span id="page"></span>/<span
							id="pages"></span>页
					</div>
					<div class="am-fl">
						<ul data-am-widget="pagination"
							class="am-pagination am-pagination-default">
							<li class="am-pagination-first "><a
								href="javascript:load(1)" class="">第一页</a></li>

							<li class="am-pagination-prev "><a
								href="javascript:load(page-1)" class="">上一页</a></li>

							<li class="am-pagination-next "><a
								href="javascript:load(page+1)" class="">下一页</a></li>

							<li class="am-pagination-last "><a
								href="javascript:load(pages)" class="">最末页</a></li>
						</ul>
					</div>
				</div>
				</div>		
			</div>
		</div>
	</div>
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示信息</div>
			<div class="am-modal-bd">操作成功</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn">确定</span>
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
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.contextMenu/jquery.contextMenu.js"></script>
	<script src="/js/layer/layer.js"></script>
	<script src="/js/jquery-ui/jquery-ui.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/pager.js"></script>
	<script src="/tree/teacher/tree-public.js"></script>
	<script src="/tree/teacher/tree.js"></script>
</body>
</html>