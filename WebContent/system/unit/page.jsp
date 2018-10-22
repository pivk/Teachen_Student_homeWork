<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>${appName}</title>
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/js/bootstrap-treeview/bootstrap-treeview.min.css">
</head>
<!-- <style>
.admin-sidebar {
	width: 30%;
	height: 750px;
	min-height: 50%;
	border-right: 1px solid #cecece;
}
</style> -->
<body>

	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">${title }</strong>
				</div>
			</div>
			<hr>
			<div class="am-g">
				<div class="admin-sidebar am-offcanvas am-u-sm-3" id="admin-offcanvas">
					<div id="tree"></div>
				</div>
				<div class="am-u-sm-9">
					<div class="am-topbar">
						<form id="form"
							class="am-topbar-form am-topbar-left am-form-inline">
							<div class="am-form-group">
								<input id="mingCheng" name="xingMing" type="text"
									class="am-form-field am-input-sm" placeholder="名称"
									maxlength="25">
							</div>
							<button id="btQuery" type="button" class="am-btn am-btn-primary"
								style="margin-left: 5px">
								<span class="am-icon-search"></span> 查询
							</button>
													<button id="btAddUnit" type="button"
								class="am-btn am-btn-primary">
								<span class="am-icon-plus"></span> 增加机构班级
							</button>
							
						   <input type="hidden" name="parentId" id="parentId">
							<span style="margin-left: 15px">当前机构班级：</span><span id="select">全部</span>
						</form>
					</div>
					<div class="am-u-sm-12">
						<table id="table"
							class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th style="width: 150px">名称</th>
									<th style="width: 100px">上级名字</th>
									<th class="table-set" style="width: 150px">操作</th>
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
							<div class="am-fl">
								<ul data-am-widget="pagination"
									class="am-pagination am-pagination-default">
									<li class="am-pagination-first "><a
										href="#" class="" id="first">第一页</a></li>

									<li class="am-pagination-prev "><a
										href="#" class="" id="befor">上一页</a></li>

									<li class="am-pagination-next "><a
										href="#" class="" id="behind">下一页</a></li>

									<li class="am-pagination-last "><a
										href="#" class="" id="end">最末页</a></li>
								</ul>
							</div>
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
				<span class="am-modal-btn" id="btsure">确定</span>
			</div>
		</div>
	</div>
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">提示信息</div>
			<div class="am-modal-bd">确定删除该班级及子班级？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span>
				<span class="am-modal-btn" data-am-modal-confirm>确定</span>
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
	 <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">正在初始...</div>
	    <div class="am-modal-bd">
	      <span class="am-icon-spinner am-icon-spin"></span>
	    </div>
	  </div>
    </div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script>
		var powerMenu =${units};
		var myMenu = [];
		if (powerMenu) {
			myMenu = myMenu.concat(powerMenu);
		}
	</script>
	<script src="/js/app.js"></script>
	<script src="/js/pager.js"></script>
	<script src="/js/api.js"></script>
	<script src="/js/layer/layer.js"></script>
	<script src="/system/unit/page.js"></script>
</body>
</html>