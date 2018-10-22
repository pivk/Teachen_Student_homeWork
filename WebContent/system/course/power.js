function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/course/doPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick=\"check2(this)\" value="+val.id+" name='check' /></td>" 
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+ "<td>"+ (val.code==null?"":val.code) + "</td>"
				+ "<td>"+ (val.userName==null?"":val.userName) + "</td>"
				+"</tr>";
			$("#table > tbody").append(row);
		});
	  });
}
function check(obj) {
	var o1 = $("[name='check']");
		for(var i=0;i<o1.length;i++){
		o1[i].checked=obj.checked;
		}
		
}

function check2(obj) {
	var allobj = document.getElementById("main");
	if(obj.checked==false){
		allobj.checked=false;
		} else {
			var flag=true;
			var o1 = document.getElementsByName("check");
			for(var i=0;i<o1.length;i++){
				if(o1[i].checked==false){
					flag=false;
					break;
					}
				}
				if(flag==true){
				allobj.checked=true;
				}
			}
	}
function save() {
	var id = "";
	$("[name='check']").each(function(index,element){
		if($(this).prop("checked")==true){
			id+= $(this).val()+",";
		} 
	});	
	if(id == "") {
		$("#message").text("请至少选择一个操作");
		$("#my-alert").modal('open');
		return false;
	}
	$.post("/system/course/addUsers.action", {id:id,xueqi:$("#xueqi").val()}, function(data) {
		if (data.state == true) {
			$("#message").text("操作成功");
			load(page);
		} else {
			$("#message").text("操作失败：" + data.message);
		}
		$("#my-alert").modal('open');
	});
}

$(document).ready(function () {
	$("#btAdd").click(function(){
		parent.openTab("1","添加课程","/system/course/add.action");
	});	
	$("#btQuery").click(function(){
		load(1);
	});
	
	$("#btSave").click(function() {
		save();
	});
	$("#main").click(function(){
		check(this);
	});
	
	load(1);
});