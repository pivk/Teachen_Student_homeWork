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
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-topbar">
				<form id="form" class="am-topbar-form am-topbar-left am-form-inline">
					<div class="am-form-group">
						<input id="unitname" name="unitname" type="text"
							class="am-form-field am-input-sm" placeholder="组织" maxlength="25">
						<input id="unitId" name="unitId" type="hidden">
					</div>
					<div class="am-form-group">
						<input id="xingMing" name="xingMing" type="text"
							class="am-form-field am-input-sm" placeholder="姓名" maxlength="25">
					</div>
					<button id="btQuery" type="button" class="am-btn am-btn-default"
						style="margin-left: 5px">
						<span class="am-icon-search"></span>查询
					</button>
				</form>
			</div>

			<div class="am-g">
				<div class="am-u-sm-12">
					<table id="table"
						class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th class="table-check"></th>
								<th style="width: 150px">用户名</th>
								<th style="width: 100px">姓名</th>
								<th style="width: 100px">组织</th>
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
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/pager.js"></script>
	<script>
	function method(){
		alert("我来自用户选择");
	}
	function getSelected(){
		var cks = $('#table tbody input[type="checkbox"]:checked');
		if (cks.length == 0) {
			return null;
		}
		var json='[';
		$(cks).each(function(index,item){
			if(index>0){
				json+=",";
			}
			var id=$(item).val();
			var name=$(item).attr("data-name");
			json+='{"id":"'+id+'","name":"'+name+'"}';
		});
		json+=']';
		return json;
	}
	function getSelectedJson(){	
		var json=getSelected();
		return JSON.parse(json);
	}
	function load(newpage) {
		calPage(newpage);
		$("#table > tbody").empty();
		$.get("/system/user/doPage.action",
			{
				page : page,
				xingMing : $("#xingMing").val(),
				unitId : $("#unitId").val(),
			},function(response, status) {
				showPage(response.data.total);
				jQuery.each(response.data.data,function(key, val) {
						var row = "<tr>"
								+ "<td><input type='checkbox' value='"+ val.id+ "' data-name='"+ val.xingMing+ "' /></td>"
								+ "<td>"+ val.id+ "</td>"
								+ "<td>"+ (val.xingMing == null ? "": val.xingMing)+ "</td>"
								+ "<td>"+ (val.unitName == null ? "": val.unitName)+ "</td>"
								+ "</tr>";
								$("#table > tbody").append(row);
				});
		});
	}
	$(document).ready(function() {
		$("#btQuery").click(function() {
			load(1);
		});
		load(1);
	});
	</script>
</body>
</html>