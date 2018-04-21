function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/dictionary/doPage.action",{ 
			page: page,
			lei:$("#lei").val(),
		},function(response,status){
			console.log("1")
		showPage(response.data.total);
			console.log("1")
		jQuery.each(response.data.data, function(key, val) {
			console.log("1")
			var row = "<tr>"
				+ "<td>"+ (val.lei==null?"":val.lei) + "</td>"
				+ "<td>"+ (val.jian==null?"":val.jian) + "</td>"
				+ "<td>"+ (val.zhi==null?"":val.zhi) + "</td>"
				+"<td>"
				+"<button type=\"button\" onclick=\"Edit('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">编辑</button>&nbsp;"
				+"<button type=\"button\" onclick=\"Remove('"+val.id+"')\" class=\"am-btn am-btn-default am-btn-xs am-text-danger\">删除</button></td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function init() {
	var option = "<option value='-'>--选择类别--</option>";
	$("#lei").append(option);
	$.ajax({
		type : "get",
		url : "/system/dictionary/doList.action",
		async: false,
		success : function(data) {
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.lei + "'>"+ item.lei + "</option>";
					$("#lei").append(option);
				});
		}
	});
}

function Edit(uid){
	location.href="/system/dictionary/edit.action?id="+uid;
}
function Show(uid){
	location.href="/system/dictionary/show.action?id="+uid;
}
function Remove(uid){
    var onConfirm = function() {
		$.get("/system/dictionary/doRemove.action", {id:uid}, function(data,status) {
			if (data.state == true) {
				$("#message").text("操作成功");
				load(page);
			} else {
				$("#message").text("操作失败："+data.message);
			}
			$("#my-alert").modal('open');
		});
    };
    
    var $confirm = $('#my-confirm');
    var confirm = $confirm.data('amui.modal');
    if (confirm) {
      confirm.options.onConfirm =  onConfirm;
      confirm.toggle(this);
    } else {
      $confirm.modal({
        relatedElement: this,
        onConfirm: onConfirm,
      });
    }
}
$(document).ready(function () {
	$("#btAdd").click(function(){
		location.href="/system/dictionary/add.action";
	});	
	$("#btQuery").click(function(){
		load(1);
	});
	init();
	load();
});