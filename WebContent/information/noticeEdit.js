function UEditor(){
var editor = UE.getEditor('container',{  
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
        toolbars:
        	['undo', 'redo','bold', 'italic', 'underline', 'fontborder', 'strikethrough',
        		['bold', 'italic', 'underline', 'fontborder',
        			'strikethrough', 'superscript', 
        			'subscript', 'removeformat', 
        			'simpleupload', //单图上传
        			'fontfamily', //字体
        			'fontsize', //字号
        			'inserttable', //插入表格
        			'justifyleft', //居左对齐
        			'justifyright', //居右对齐
        			'justifycenter', //居中对齐
        			'justifyjustify', //两端对齐
        			'attachment', //附件
        			'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        	],  
        	//focus时自动清空初始化时的内容  
        	autoClearinitialContent:true,  
        	//关闭字数统计  
        	wordCount:false,  
        	//关闭elementPath  
        	elementPathEnabled:false,  
        	initialFrameWidth: null,
        	//默认的编辑区域高度  
        	initialFrameHeight:300  
        	//更多其他参数，请参考ueditor.config.js中的配置项  
    });  
} 


function save() {
	if (!$('#form').validator("isFormValid"))
		return false;
	$.post("/information/doNoticeEdit.action", $("#form").serialize(),
			function(data) {
				if (data.state == true) {
					$("#message").text("操作成功");
				} else {
					$("#message").text("操作失败：" + data.message);
				}
				$("#my-alert").modal('open');
			});
}

function load() {
	var id = $("#id").val();
	$.getJSON("/information/doGetNotice.action", {
		id : id
	}, function(data) {
		if (data.state == false)
			return;
		$("#biaoTi").val(data.data.biaoTi);
		$("#container").val(data.data.neiRong);
		$("#fuJian").val(data.data.fuJian);
		if(data.data.fuJian&&data.data.fuJianName){
			var fuJians=data.data.fuJian.split(",");
			var fuJianNames=data.data.fuJianName.split(",");
			if(fuJians.length==fuJianNames.length){
				for(var i=0;i<fuJians.length;i++){
					var filename=fuJians[i];  
					var index1=filename.lastIndexOf(".");  
					var index2=filename.length; 
					var href=filename.substring(index1+1,index2);//后缀名  
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
					if(fuJians[i]){
						var row ="<div id="+fuJians[i]+"&&"+fuJianNames[i]+"><img src='"+img+"'><span>"+fuJianNames[i]+"</span>" +
								    "<span><a   href='"+"/file/down.action?realPath="+fuJians[i]+"&fileName="+fuJianNames[i]+"'>下载</a></span>" +
								  "</div>";
						$("#fuJian").append(row);
					}
				}
			}
		}
	});
}


function selectWJ(){
	var fuJianUrl=$("#fuJianUrl").val();
	var fuJianName=$("#fuJianName").val();
	layer.open({
		type : 2,
		title : '文件上传',
		area : [ '80%', '80%' ],
		shade : 0,
		maxmin : true,
		content : '/file/popup.action?upload_type=document',
		btn : [ '确定', '关闭' ],
		yes : function(index,layero) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			var selected=iframeWin.getSelectedJson();//获取选择的数据Json格式
			jQuery.each(selected,function(key, val) {
				var row = "<div><img src='"+val.img+"'><span>"+val.originalName+"</span><span><a   href="+"/file/down.action?realPath="+val.realPath+"&fileName="+val.originalName+">下载</a></span></div>"
					var fjpath = $("#fuJianPath").val();
					var fjmc = $("#fuJianMC").val();
					if(fjpath){
						$("#fuJianPath").val(fjpath+","+val.realPath);
					}else{
						$("#fuJianPath").val(val.realPath);
					}
					if(fjmc){
						$("#fuJianMC").val(fjmc+","+val.originalName);
					}else{
						$("#fuJianMC").val(val.originalName);
					}
					$("#fuJian").append(row);
					fuJianUrl += val.realPath+",";
					fuJianName += val.originalName+",";
					$("#fuJianUrl").val(fuJianUrl);
					$("#fuJianName").val(fuJianName);
				
			});
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
}

$(document).ready(function() {
	$("#btSave").click(function() {
		save();
	});
	$("#btBack").click(function() {
		location.href = "/information/noticeAdminPage.action";
	});
	$("#fj").click(function() {
		selectWJ();
	});
	$("#fujian").click(function() {
		$("#message").text("操作成功");

		$("#shangchuan").modal('open');
	});
	load();
	UEditor();
});