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
				<div class="am-topbar">
					<form id="form"
						class="am-topbar-form am-topbar-left am-form-inline">
						
						<div class="am-form-group">
							<input id="BiaoTi" name="BiaoTi" type="text"
								class="am-form-field am-input-sm" placeholder="标题">
						</div>
						<button id="btQuery" type="button" class="am-btn am-btn-primary"
							style="margin-left: 5px">
							<span class="am-icon-search"></span> 查询
						</button>
						
									<button id="btExport" type="button" class="am-btn am-btn-primary" style="margin-left: 5px">
										<span class="am-icon-download"></span> 导出
									</button>
								
					</form>
				</div>
				<div class="am-g">
					<div class="am-u-sm-12">
						<table id="table"
							class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th style="width: 200px;">标题</th>
									<th style="width: 200px;">时间</th>
									<th >用户名</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
						<div class="am-cf">
							<div class="am-fl am-pagination">
								共<span id="total"></span>条 第<span id="page"></span>/<span
									id="pages"></span>页
							</div>
							<div class="am-fr">
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
	<script src="/js/pager.js"></script>
	<script src="/system/log/page.js"></script>
</body>
</html>