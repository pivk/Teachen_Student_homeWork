<%@page import="doc.information.entity.Notice"%>
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
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
<% Notice notice =(Notice) request.getAttribute("notice"); %>
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg"><%=request.getAttribute("title") %></strong>
					</div>
				</div>
				<hr>
				<div class="am-u-sm-4 am-u-md-2 am-text-left"
					style="font-weight: bold;">基本信息</div>
				<table id="table"
					class="am-table am-table-striped am-table-hover table-main"
					style="font-size: 12pt">
					<tbody>
						<tr>
							<th>标题</th>
							<td><%if(notice.getBiaoTi() != null){ %> <%=notice.getBiaoTi() %> <%}%></td>
						</tr>
						<tr>
							<th>创建人</th>
							<td><%if(notice.getCreator() != null){ %> <%=notice.getCreator() %> <%}%></td>
						</tr>
						<tr>
							<th>创建时间</th>
							<td><%if(notice.getCreateTime() != null){ %> <%=notice.getCreateTime().substring(0,16) %> <%}%></td>
						</tr>
						<tr>
							<th>附件</th>
							<td><%if(notice.getFuJian() != null){ %> <%=notice.getFuJian() %> <%}%></td>
						</tr>
						<tr>
							<th>内容</th>
							<td><%if(notice.getNeiRong() != null){ %> <%=notice.getNeiRong() %> <%}%></td>
						</tr>
					</tbody>
				</table>
			<div style="height: 20px"></div>
			<div class="am-form-group">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<a class="am-btn am-btn-primary" href="/information/noticeAdminPage.action">返回</a>
					</div>
				</div>
			</div>
		</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
</body>
</html>