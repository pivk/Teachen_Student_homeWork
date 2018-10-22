function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/user/doTeacherPage.action",{ 
			page: page,
			xingMing:$("#mingCheng").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick=\"check2(this)\" value="+val.id +','+val.xingMing +" name='check' /></td>" 
				+ "<td>"+ (val.xingMing==null?"":val.xingMing) + "</td>"
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
function getSelected(){
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
	var json='{"id":"'+id+'"}';
	return json;
}
function getSelectedJson(){	
	var json=getSelected();
	return JSON.parse(json);
}

$(document).ready(function () {

	load(1);
});