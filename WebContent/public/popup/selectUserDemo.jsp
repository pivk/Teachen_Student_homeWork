<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<button id="btOK" type="button" class="am-btn am-btn-primary">选择用户</button>
			<br>
			<textarea id="users" class="form-control" rows="3" placeholder="此处显示选择的用户"></textarea>
		</div>
	</div>

	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/layer/layer.js"></script>
	<script>
		$(document).ready(function() {
			$("#btOK").click(function() {
				layer.open({
					type : 2,
					title : '选择用户',
					area : [ '800px', '600px' ],
					shade : 0,
					maxmin : true,
					content : '/system/user/popup.action',
					btn : [ '确定', '关闭' ],
					yes : function(index,layero) {
						var iframeWin = window[layero.find('iframe')[0]['name']];
						var selected=iframeWin.getSelectedJson();//获取选择的数据Json格式
						var usersText="";
						jQuery.each(selected,function(key, val) {
							usersText+=val.id+","+val.name+"\r\n";
						});
						$("#users").text(usersText);
						layer.closeAll();//关闭弹出窗
					},
					btn2 : function() {
						layer.closeAll();
					},
					zIndex : layer.zIndex,
					success : function(layero) {
						layer.setTop(layero);

					}
				});
			});

		});
	</script>
</body>
</html>