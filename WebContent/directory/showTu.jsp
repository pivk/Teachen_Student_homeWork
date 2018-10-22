<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	
<input type="hidden" id="courseId" value="${param.courseId }">
		<div class="admin-content">
			<div class="admin-content-body">
				<!-- 正文开始 -->
		    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
		    <div id="main2" style="width: 900px;height:700px;"></div>
			</div>
		</div>

	<script src="/js/jquery.min.js"></script>
	<script src="/js/layer/layer.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
		<script src="/js/pager.js"></script>
	<script src="/js/api.js"></script>
	<script src="/js/echarts.min.js"></script>
	<script src="/directory/showTu.js"></script>
 
</body>
</html>