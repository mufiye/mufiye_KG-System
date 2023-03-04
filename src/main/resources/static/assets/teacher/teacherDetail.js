$(document).ready(function() {
	var s = GetQueryString("id");
	$.ajax({
		type : "post",// 请求方式
		url : "../QueryParam.adub",// 发送请求地址
		dataType : "json",
		async : false,
		data : {// 发送给数据库的数据
			flag : 'teacher_browse_own_Information_detail',
			id : s
		},
		// 请求成功后的回调函数有两个参数
		success : function(data) {
			var objs = eval(data); // 解析json对象
			var ob = objs.output;
			$('#id').html(ob.id);
			$('#name').html(ob.name);
			$('#sex').html(ob.sex);
			$('#college').html(ob.college);
			$('#arrive_time').html(ob.arrive_time);
			$('#note').html(ob.note);
			
		},
		error : function(){
			//window.parent.location.href="../error.html";
		}
	});
});


