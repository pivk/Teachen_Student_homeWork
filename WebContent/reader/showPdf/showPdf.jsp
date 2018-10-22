<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${appName}</title>
<link rel="stylesheet" href="/js/amazeui/css/amazeui.min.css" />
<link rel="stylesheet" href="/js/amazeui/css/admin.css">
<link id="skin_css" rel="stylesheet" href="/skin/default/css/main.css">
</head>
<body>
	<%-- <canvas id="the-canvas"></canvas> --%>
	<div class="am-cf am-padding" style="width:100%;height:5%">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">${title}</strong>
					</div>
				</div>
	<iframe id="view" style="width:100%;height:95%" src="/reader/showPdf/pdfjs/web/viewer.html?file=${pdfPath}">
	</iframe> 
<!-- 	<iframe id="view" style="width:100%;height:95%" src="/reader/showPdf/pdfjs/web/viewer.html?file=../../胶州市卫计局OA系统需求说明书.pdf">
	</iframe>  -->
	<%out.clear();
	out = pageContext.pushBody(); %>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script type="text/javascript">
		 function changeFrameHeight(){
	        var ifm= document.getElementById("view");
	        ifm.height=document.documentElement.clientHeight-56;
	    }
	    window.onresize=function(){
	    	changeFrameHeight();}
	    $(function(){changeFrameHeight();
	    });
	</script>
</body>
</html>