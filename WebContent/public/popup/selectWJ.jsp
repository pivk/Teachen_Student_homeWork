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
<link rel="stylesheet" href="/js/webuploader/webuploader.css" />
</head>
<body>
	<div class="admin-content">
			<input id="wenjian" name="wenjian" type="hidden"/>
			<input id="mingcheng" name="mingcheng" type="hidden"/>
			<div id="uploader" style="margin:100px 0 0 100px;border:1px solid #DEDEDE;width:400px">
			    <div id="thelist" class="uploader-list"></div>
			    <div class="btns">
			        <div id="picker" style="width:150px;margin:30px 0px 0px 20px">选择文件</div>
			      <!--  <button id="ctlBtn" style="margin-top:-85px;margin-left:250px;width:100px;height:45px" class="btn btn-default">开始上传</button> -->
			    </div>
			</div>
		</div>
	<script src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="/js/webuploader/import.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/pager.js"></script>
	<script>
	function method(){
		alert("我来自用户选择");
	}
	function getSelected(){
		var wenjian=$("#wenjian").val();
		var mingcheng=$("#mingcheng").val();
		var json='[';
		json+='{"wenjian":"'+wenjian+'","mingcheng":"'+mingcheng+'"}';
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