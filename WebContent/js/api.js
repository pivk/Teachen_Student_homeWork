function getUnitList() {
	var option = "<option value='-'>--选择单位--</option>";
	$("#unitId").append(option);
	$.ajax({
		type : "get",
		url : "/system/unit/doList.action",
		async: false,
		success : function(data) {
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.id + "'>"+ item.mingCheng + "</option>";
					$("#unitId").append(option);
				});
		}
	});
}

function getSheng(){
	var option = "<option value=''>--选择省份--</option>";
	$("#changSheng").append(option);
	$("#huSheng").append(option);
	var sheng = "省";
	$.ajax({
		type : "post",
		async: false,
		url : "/system/dictionary/getSheng.action",
		data:{lei:sheng},
		success : function(data) {
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
					$("#changSheng").append(option);
					$("#huSheng").append(option);
					$("#changSheng").find("option[value='370000']").attr("selected",true);
					$("#huSheng").find("option[value='370000']").attr("selected",true);
				});
		}
	});
	var option1 = "<option value=''>--选择城市--</option>";
	$("#changShi").append(option1);
	$("#huShi").append(option1);
	$.ajax({
		type:"post",
		async: false,
		url:"/system/dictionary/getSheng.action",
		data:{lei:"市",jian:"37"},
		success:function(data){
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
					$("#changShi").append(option);
					$("#huShi").append(option);
					$("#changShi").find("option[value='370200']").attr("selected",true);
					$("#huShi").find("option[value='370200']").attr("selected",true);
				});
		}
	});
	var option2 = "<option value=''>--请选择区--</option>";
	$("#changQu").append(option2);
	$("#huQu").append(option2);
	$.ajax({
		type:"post",
		async: false,
		url:"/system/dictionary/getSheng.action",
		data:{lei:"区",jian:"3702"},
		success:function(data){
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
					$("#changQu").append(option);
					$("#huQu").append(option);
					$("#changQu").find("option[value='370282']").attr("selected",true);
					$("#huQu").find("option[value='370282']").attr("selected",true);
				});
		}
	});
	var option3 = "<option value=''>--请选择街道--</option>";
	$("#changJie").append(option3);
	$("#huJie").append(option3);
	$.ajax({
		type:"post",
		url:"/system/dictionary/getSheng.action",
		data:{lei:"街道",jian:"370282"},
		success:function(data){
			if(data.state==false) return;
			$(data.data).each(
				function(key, item) {
					var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
					$("#changJie").append(option);
					$("#huJie").append(option);
				});
		}
	});
}
function getChange(){
	$("#changSheng").change(function(){
		$("#changShi").empty();
		$("#changQu").empty();
		$("#changJie").empty();
		var option = "<option value=''>--选择城市--</option>";
		$("#changShi").append(option);
		var option1 = "<option value=''>--请选择区--</option>";
		$("#changQu").append(option1);
		var option2 = "<option value=''>--选择街道--</option>";
		$("#changJie").append(option2);
			$.ajax({
				type:"post",
				url:"/system/dictionary/getSheng.action",
				dataType:"json",
				data:{lei:"市",jian:$(this).val().substring(0,2)},
				success:function(data){
					if(data.state==false) return;
					$(data.data).each(
						function(key, item) {
							var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
							$("#changShi").append(option);
						});
				}
			});
	});
	$("#huSheng").change(function(){
		$("#huShi").empty();
		$("#huQu").empty();
		$("#huJie").empty();
		var option = "<option value=''>--选择城市--</option>";
		$("#huShi").append(option);
		var option1 = "<option value=''>--请选择区--</option>";
		$("#huQu").append(option1);
		var option2 = "<option value=''>--选择街道--</option>";
		$("#huJie").append(option2);
			$.ajax({
				type:"post",
				url:"/system/dictionary/getSheng.action",
				dataType:"json",
				data:{lei:"市",jian:$(this).val().substring(0,2)},
				success:function(data){
					if(data.state==false) return;
					$(data.data).each(
						function(key, item) {
							var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
							$("#huShi").append(option);
						});
				}
			});
	});
}
function getChangeShi(){
	$("#changShi").change(function(){
		$("#changQu").empty();
		$("#changJie").empty();
		var option = "<option value=''>--请选择区--</option>";
		$("#changQu").append(option);
		var option2 = "<option value=''>--选择街道--</option>";
		$("#changJie").append(option2);
			$.ajax({
				type:"post",
				url:"/system/dictionary/getSheng.action",
				dataType:"json",
				data:{lei:"区",jian:$(this).val().substring(0,4)},
				success:function(data){
					if(data.state==false) return;
					$(data.data).each(
						function(key, item) {
							var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
							$("#changQu").append(option);
						});
				}
			});
	});
	$("#huShi").change(function(){
		$("#huQu").empty();
		$("#huJie").empty();
		var option = "<option value=''>--请选择区--</option>";
		$("#huQu").append(option);
		var option2 = "<option value=''>--选择街道--</option>";
		$("#huJie").append(option2);
			$.ajax({
				type:"post",
				url:"/system/dictionary/getSheng.action",
				dataType:"json",
				data:{lei:"区",jian:$(this).val().substring(0,4)},
				success:function(data){
					if(data.state==false) return;
					$(data.data).each(
						function(key, item) {
							var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
							$("#huQu").append(option);
						});
				}
			});
	});
}

function getChangeQu(){
	$("#changQu").change(function(){
		$("#changJie").empty();
		var option2 = "<option value=''>--选择街道--</option>";
		$("#changJie").append(option2);
			$.ajax({
				type:"post",
				url:"/system/dictionary/getSheng.action",
				dataType:"json",
				data:{lei:"街道",jian:$(this).val().substring(0,6)},
				success:function(data){
					if(data.state==false) return;
					$(data.data).each(
						function(key, item) {
							var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
							$("#changJie").append(option);
						});
				}
			});
	});
	$("#huQu").change(function(){
		$("#huJie").empty();
		var option2 = "<option value=''>--选择街道--</option>";
		$("#huJie").append(option2);
			$.ajax({
				type:"post",
				url:"/system/dictionary/getSheng.action",
				dataType:"json",
				data:{lei:"街道",jian:$(this).val().substring(0,6)},
				success:function(data){
					if(data.state==false) return;
					$(data.data).each(
						function(key, item) {
							var option = "<option value='" + item.jian + "'>"+ item.zhi + "</option>";
							$("#huJie").append(option);
						});
				}
			});
	});
}




function getJob() {
	var JobLei=[
		{key:'散居儿童',value:'散居儿童'},
		{key:'托幼儿童',value:'托幼儿童'},
		{key:'学生',value:'学生'},
		{key:'教师',value:'教师'},
		{key:'餐饮食品业',value:'餐饮食品业'},
		{key:'商业服务',value:'商业服务'},
		{key:'医务人员',value:'医务人员'},
		{key:'工人',value:'工人'},
		{key:'农民',value:'农民'},
		{key:'民工',value:'民工'},
		{key:'牧民',value:'牧民'},
		{key:'渔民',value:'渔民'},
		{key:'干部职员',value:'干部职员'},
		{key:'离退人员',value:'离退人员'},
		{key:'家务及待业',value:'家务及待业'},
		{key:'其他',value:'其他'},
		{key:'不详',value:'不详'},
		];
	$("#zhiYe").empty();
	var option = "<option value='-'>--选择职业--</option>";
	$("#zhiYe").append(option);
	jQuery.each(JobLei, function(key,item) {
		var option = "<option value='"+item.key+"'>"+item.value+"</option>";
		$("#zhiYe").append(option);
	});
}

function getStatus() {
	var JobLei=[
		{key:'草稿',value:'草稿'},
		{key:'已提审',value:'已提审'},
		{key:'驳回',value:'驳回'},
		{key:'通过',value:'通过'},
		{key:'已上报',value:'已上报'},
		];
	$("#status").empty();
	var option = "<option value='-'>-选择状态-</option>";
	$("#status").append(option);
	jQuery.each(JobLei, function(key,item) {
		var option = "<option value='"+item.key+"'>"+item.value+"</option>";
		$("#status").append(option);
	});
}
