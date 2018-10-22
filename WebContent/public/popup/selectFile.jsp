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
<link rel="stylesheet" href="/js/webuploader/webuploader.css" />
<link rel="stylesheet" href="/skin/default/css/main.css">
<style>
#uploader .queueList {
	margin: 20px;
	border: 3px dashed #e6e6e6;
}

#uploader .queueList.filled {
	padding: 17px;
	margin: 0;
	border: 3px dashed transparent;
}

#uploader .placeholder {
	min-height: 100px;
	padding-top: 100px;
	text-align: center;
	background: url(/images/addFile.png) center 0px no-repeat;
	color: #cccccc;
	font-size: 18px;
	position: relative;
}

#uploader .placeholder .webuploader-pick {
	font-size: 18px;
	background: #00b7ee;
	border-radius: 3px;
	line-height: 44px;
	padding: 0 30px;
	*width: 120px;
	color: #fff;
	display: inline-block;
	margin: 0 auto 20px auto;
	cursor: pointer;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}
.uploader-list{
margin-left: 25px;
}
</style>
</head>
<body>
	<div class="admin-content">
		<input id="realPath" name="realPath" type="hidden" /> 
		<input id="originalName" name="originalName" type="hidden" />
		<input id="fileSize" name="fileSize" type="hidden" />
		<input id="fileFormat" name="fileFormat" type="hidden" />
		<div id="fileListjl" class="uploader-list"></div>
		<input id="upload_type" name="upload_type" type="hidden" value="<%=request.getAttribute("upload_type") %>" />
		<div class="am-g">
			<div id="uploader" class="wu-example">
				<div class="queueList">
					<div id="dndArea" class="placeholder">
						<div id="filePicker">开始上传</div>
						<div style="color: black;">友情提示：支持拖拽</div>
					</div>
				</div>
			</div>
		</div>
		<div id="fileList" class="uploader-list"></div>
	</div>
	<script src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/js/webuploader/webuploader.js"></script>
	
	<script type="text/javascript" src="/js/webuploader/import.js"></script><script src="/js/amazeui/js/amazeui.min.js"></script>
	<script src="/js/amazeui/js/app.js"></script>
	<script src="/js/app.js"></script>
	<script>
		function getSelected() {
			/* var fileName = $("#originalName").val().trim().replace(/[ ]/g, "_");
			var json = '[{"realPath":"' + $("#realPath").val() + '"'
				+',"originalName":"' +fileName + '"'
				+',"fileSize":"' + $("#fileSize").val() + '"'
				+',"fileFormat":"' + $("#fileFormat").val() + '"'
				+ '}]'; */
				var cks = $(".filelist");
				if (cks.length == 0) {
					return null;
				}
				var json='[';
				$(cks).each(function(index,item){
					if(index>0){
						json+=",";
					}
					var fileName=$(item).attr("name").trim().replace(/[ ]/g, "_");
					var realPath=$(item).attr("id");
					var href=$(item).attr("href");
					var img="/images/upload/else.png";
					if(href=="docx"||href=="doc"){
						img="/images/upload/word.png";
					}else if(href=="xlsx"||href=="xls"){
						img="/images/upload/excel.png";
					}else if(href=="bmp"||href=="jpg"||href=="png"||href=="gif"||href=="tiff"||href=="pcx"||href=="wmf"){
						img="/images/upload/img.png";
					}else if(href=="pdf"){
						img="/images/upload/pdf.png";
					}else if(href=="ppt"||href=="pptx"){
						img="/images/upload/ppt.png";
					}else if(href=="txt"){
						img="/images/upload/txt.png";
					}else if(href=="mpeg"||href=="avi"||href=="mov"||href=="asf"||href=="wmv"||href=="navi"||href=="3gp"||href=="mkv"||href=="flv"||href=="mp4"||href=="rmvb"){
						img="/images/upload/video.png";
					}else if(href=="zip"||href=="rar"||href=="7z"){
						img="/images/upload/zip.png";
					}else if(href=="cd"||href=="wave"||href=="aiff"||href=="mpeg"||href=="mp3"||href=="midi"||href=="wma"||href=="vqf"||href=="amr"||href=="ape"){
						img="/images/upload/music.png";
					}
					json+='{"originalName":"'+fileName+'","realPath":"'+realPath+'","img":"'+img+'"}';
				});
				json+=']';
			return json;
		}
		function getSelectedJson() {
			var json = getSelected();
			json=json.replace(/\\/gm,"\\\\");
			return JSON.parse(json);
		}
	</script>
</body>
</html>