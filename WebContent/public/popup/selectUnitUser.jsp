<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("appName")%></title>
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/js/bootstrap-treeview/bootstrap-treeview.css">
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-g">
				<div class="am-u-sm-6 am-u-lg-6">
					<div id="tree"></div>
				</div>
				<div class="am-u-sm-6 am-u-lg-6">
					<div id="list"></div>
				</div>
				<div class="am-u-sm-6 am-u-lg-6">
					<div id="list2"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview.js"></script>
	<script src="/js/bootstrap-treeview/bootstrap-treeview-pushunsoft-user.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script>
		var myMenu =${unitUsers};
		$('#tree').treeview({
			data : myMenu,
			uiLibrary : 'bootstrap',
			levels : 1,
			showCheckbox:true,
	        onNodeChecked:nodeChecked ,  
	        onNodeUnchecked:nodeUnchecked,
		}); 
		function getSelected(){
			var cks = $('#list input');
			if (cks.length == 0) {
				return null;
			}
			var json='[';
			$(cks).each(function(index,item){
				if(index>0){
					json+=",";
				}
				var id=$(item).val();
				var name=$(item).attr("name");
				var unit = $(item).attr("class");
				json+='{"id":"'+id+'","name":"'+name+'","unit":"'+unit+'"}';
			});
			json+=']';
			return json;
		}
		function getSelectedJson(){	
			var json=getSelected();
			return JSON.parse(json);
		}
	</script>
</body>
</html>