function load(newpage){
	calPage(newpage);
	$("#table > tbody").empty();
	$.get("/system/course/doUSerPage.action",{ 
			page: page,
			mingCheng:$("#mingCheng").val(),
		},function(response,status){
		showPage(response.data.total);
		jQuery.each(response.data.data, function(key, val) {
			var row = "<tr>"
				+"<td><input type='checkbox' onclick=\"check2(this)\" value="+val.id +','+val.mingCheng +" name='check' /></td>" 
				+ "<td>"+ (val.mingCheng==null?"":val.mingCheng) + "</td>"
				+ "<td>"+ (val.code==null?"":val.code) + "</td>"
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
	/*var json='[';
	$(selected).each(function(index,item){
		if(index>0){
			json+=",";
		}
		var id=item.id;
		var name=item.text;
		json+='{"id":"'+id+'","name":"'+name+'"}';
	});
	json+=']';*/
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