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
<link id="skin_css" rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-g">
				<div class="am-u-sm-6">
					<form class="form-inline">
						<div class="form-group">
							<label class="sr-only" for="exampleInputAmount"></label>
							<div class="input-group">
								<input type="text" class="form-control" id="biaoti"
									name="biaoti"	placeholder="请输入关键字" style="width: 400px">
								<div class="input-group-addon" id="btQuery">搜索</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div style="margin-top: 20px"></div>
			<ul
				class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
				<li><a href="#" class="am-text-success"><span
						class="am-icon-btn am-icon-file-text"></span><br />上传文档总数<br />
						<span id="luqu">0</span></a></li>
				<li><a href="#" class="am-text-success"><span
						class="am-icon-btn am-icon-file-text"></span><br />待上传文档数<br />
						<span id="baodao">0</span></a></li>
			</ul>
			<div class="am-g">
				<div class="am-u-sm-12 am-u-lg-12">
					<div class="am-panel am-panel-primary">
						<div class="am-panel-hd">待办通知</div>
						<div class="am-panel-bd">
							<table id="table"
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>
										<th style="width: 50px">序号</th>
										<th style="width: 200px">标题</th>
										<th style="width: 100px">说明</th>
										<th style="width: 100px">截止日期</th>
										<th style="width: 100px">操作</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
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
			<script src="/js/layer/layer.js"></script>
		<script src="/home/welcome.js"></script>
</body>
</html>