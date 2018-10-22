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
<link rel="stylesheet"
	href="/js/bootstrap-treeview/bootstrap-treeview.css">
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link id="skin_css" rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<div id="header">
		<header class="am-topbar am-topbar-inverse admin-header">
			<div class="am-topbar-brand">
				<div class="am-show-md-up">
					<img src="/images/logo-mini.png"
						style="height: 60px; margin-top: -10px; margin-left: -10px">
					<strong>${appName}</strong>
				</div>
				<div class="am-show-md-down am-hide-md-up">
					<strong>${appName}</strong>
				</div>
			</div>

			<button
				class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success"
				data-am-offcanvas="{target: '#admin-offcanvas'}">
				<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
			</button>
			<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
				<ul
					class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
					<li class="am-dropdown" data-am-dropdown id="ddlUser"><a
						class="am-dropdown-toggle" id="user" data-am-dropdown-toggle
						href="javascript:;"> <span class="am-icon-users"></span>${sessionScope.get('USER').xingMing}<span
							class="am-icon-caret-down"></span>
					</a>
						<ul class="am-dropdown-content">
							<li><a href="#" onclick="changePassword()"><span
									class="am-icon-cog"></span>修改密码</a></li>
							<li><a href="/home/logout.action"><span
									class="am-icon-power-off"></span>退出</a></li>
						</ul></li>
					<li class="am-hide-sm-only"><a href="javascript:;"
						id="admin-fullscreen"><span class="am-icon-arrows-alt"></span>
							<span class="admin-fullText">全屏</span></a></li>
					<li class="am-hide-sm-only"><a href="/home/help.action"
						target="_blank" id="admin-help"><span
							class="am-icon-question-circle"></span> <span
							class="admin-fullText">帮助</span></a></li>
					<li class="am-hide-sm-only"><a href="javascript:toggleMenu()"
						id="admin-menu"><span class="am-icon-bars"></span> <span
							class="admin-fullText">菜单</span></a></li>
				</ul>
			</div>
		</header>
	</div>
	<div class="am-cf admin-main">
		<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
			<div class="am-offcanvas-bar admin-offcanvas-bar">
				<div id="tree"></div>
			</div>
		</div>
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="mainTab">
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="javascript: void(0)">欢迎</a></li>
					</ul>

					<div class="am-tabs-bd">
						<div class="am-tab-panel am-active">
							<iframe id="tab_welcome" src="/home/welcome.action" width="100%"
								onload="Javascript:setAutoHeight(this)"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script>
		var tongyongMenu = {
			text : "系统中心",
			nodes : [ {
				id : "index",
				text : "首页",
				url : "/",
				icon : "glyphicon glyphicon-home",
			}, {
				id : "password",
				text : "修改密码",
				icon : "glyphicon glyphicon-edit",
				url : "/home/password.action",
			}, {
				id : "me",
				text : "个人资料",
				url : "/home/me.action",
				icon : "glyphicon glyphicon-user",
			}, ]
		};
		var powerMenu = ${menu};
		var myMenu = [];
		myMenu.push(tongyongMenu);
		if (powerMenu) {
			myMenu = myMenu.concat(powerMenu);
		}
	</script>
	<script src="/js/app.js"></script>
	<script src="/home/index.js"></script>
</body>
</html>