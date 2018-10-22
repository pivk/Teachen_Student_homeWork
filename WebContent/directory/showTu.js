var myChart2 = echarts.init(document.getElementById('main2'));
  $.get('/system/course/dogetStatisticsV.action',
		 {courseId:$("#courseId").val(),}
		).done(function (response,status) {
	  var keys=[];    //类别数组（实际用来盛放X轴坐标值）  
      var values=[];//销量数组（实际用来盛放Y坐标值
  	jQuery.each(response.data, function(key, val) {
  		values.push(val.num);
  	})
option2 = {
  	  title: {
          text: '论文成绩人数柱形图'
      },
      tooltip: {},
      legend: {
          data:['人数']
      },
    xAxis: {
        type: 'category',
        data: ['100~90', '90~80', '80~70', '70~60', '60以下']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
    	 name: '人数',
        data: values,
        type: 'bar'
    }]
};

myChart2.setOption(option2);

})