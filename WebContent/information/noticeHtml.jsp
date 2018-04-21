<%@page import="doc.information.entity.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("appName")%></title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<style>
.middle {
	text-align:center
}
.ydq{
width: 100%;
text-align: right;
}
.jz{
width: 100%;
text-align: center;
}
</style>
<body>
<%Notice notice = (Notice) request.getAttribute("notice"); %>
	<div class="admin-content">
			<div class="am-container">
				<h1 class="jz"><%=notice.getBiaoTi()== null?"":notice.getBiaoTi()%> </h1>
				<h5 class="ydq" ><%=notice.getCreateTime()== null?"":notice.getCreateTime().substring(0,16)%></h5>
			</div>
			<div class="am-container">
				<p class="am-u-sm-17 am-u-sm-centered">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=notice.getNeiRong()== null?"":notice.getNeiRong()%>
				</p>
			
			</div>
					<div class="am-container ydq">
						<span class="ydq">编辑人：<%=notice.getCreator()== null?"":notice.getCreator()%></span>
						<span class="am-avg-sm-5"></span>
					</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/pager.js"></script>
	<script>
	</script>
</body>
</html>