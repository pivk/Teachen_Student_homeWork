$(document).ready(function() {
var uploader = WebUploader.create({
	swf: '/js/webupload/Uploader.swf',
    server: '/file/upload.action',
    pick: '#filePicker',
    auto:true,
    duplicate:true,
    resize: false,
    //fileNumLimit:1,
	dnd : '#uploader .queueList',
	disableGlobalDnd : true,
	formData:{upload_type:$("#upload_type").val()},

});
//当有文件被添加进队列的时候
uploader.on('fileQueued', function( file ) {
	$('#fileList').append( '<div id="' + file.id + '" class="item">' +
        '<h4 class="info">' + file.name + '</h4>' +
        
    '</div>' );
});
//文件上传过程中创建进度条实时显示。
uploader.on('uploadProgress', function( file, percentage ) {
    var $li = $( '#'+file.id ),
        $percent = $li.find('.progress .progress-bar');

    // 避免重复创建
    if ( !$percent.length ) {
        $percent = $('<div class="progress progress-striped active">' +
          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
          '</div>' +
        '</div>').appendTo( $li ).find('.progress-bar');
    }

    $li.find('p.state').text('上传中');

    $percent.css('width', percentage * 100 + '%' );
});
uploader.on('uploadSuccess', function( file,response ) {
	if(response.state==false){
		alert("上传失败");
		return;
	}
	$('#fileListjl').append( '<input type="hidden" class="filelist" name="'+response.data.originalName+'" id="'+response.data.realPath+'" href="'+response.data.format+'">' );
	$("#realPath").val(response.data.realPath);
	$("#originalName").val(response.data.originalName);
	$("#fileSize").val(response.data.size);
	$("#fileFormat").val(response.data.format);
});
uploader.on('uploadError', function( file,code) {
	
});
uploader.on('uploadComplete', function( file ) {
    $( '#'+file.id ).find('.progress').fadeOut();
});
$("#ctlBtn").click(function(){
	uploader.upload();
});
});