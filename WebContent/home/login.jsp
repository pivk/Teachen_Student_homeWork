<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${appName}</title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
<link rel="stylesheet" href="/css/app/login.css">

</head>
<body>

	<div class="header-box">
		<!--标题-->
		<div class="container">
			<img class="pull-left am-fl"
				src="/images/title.png">
			<h4 class="pull-left ">师生作业交流系统</h4>
		</div>
	</div>
	
	<div class="container-fluid body-box" style="background: url(&quot;/images/bg-2.png&quot;) 0% 0% / cover no-repeat;">
			<!--表单-->
			<div class="container">
				<div class="loginForm pull-right">
					<div class="input-group input-group-lg title">
						<div class="pull-left col-xs-6">
							<h5 class="text-emphasis">用户登录</h5>
						</div>
					</div>
					<form id="casLoginForm" class="fm-v clearfix amp-login-form">
					<div class="form-group clearfix">
					    <label for="name" class="col-xs-3 control-label">用户名：</label>
					    <div class="col-xs-9">
							<input id="username" name="uid" placeholder="输入用户名" class="form-control" type="text" value="">
					    </div>
					</div>
					<div class="form-group clearfix">
					    <label for="password" class="col-xs-3 control-label">密 &nbsp;&nbsp;&nbsp;码：</label>
					    <div class="col-xs-9">
						<input id="password" name="pwd" placeholder="输入密码"  class="form-control" type="password" value="" autocomplete="off">
					    </div>
					</div>
					
					

					<div class="form-group clearfix">
						<div class="col-xs-12 text-right" id="code-box">
						     <a href="getBackPasswordMainPage.do" class="forgetPwd">忘记密码？</a>
					    </div>
					</div>
					<div class="row">
					    <div class="col-xs-12">
					      <button type="button" id="btLogin" class="btn btn-primary btn-block btn-lg">登 &nbsp;&nbsp;&nbsp;录</button>
					    </div>
					</div> 
					</form>
				</div>
			</div>
		</div>
	

	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/home/login.js"></script>
</body>
</html>
