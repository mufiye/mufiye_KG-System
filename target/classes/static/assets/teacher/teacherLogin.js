function loginCheck() {
//	var type="teacher";
	var code = document.getElementById("rand").value;
	if ($('#username').val() == "" || $('#password').val() == "") {
		/*alert("用户名或密码不能为空！");*/
		var html=[];
		/*$('#modalFoot').empty();*/
		$('#modalHeader').html('提示信息');
		$('#modalBody').html('用户名和密码不能为空');
		//html.push('<button type="button" data-dismiss="modal" class="btn green">取消</button>');
		html.push('<button type="button" data-dismiss="modal" class="btn green" style="width:54px;height:30px;margin-top:0px;">确认</button>');
		$('#modalFoot').html(html.join(""));
		$('#myModal').modal("show");
	}else if ($('#rand').val()== "") {
		/*alert("验证码不能为空！");*/
		var html=[];
		/*$('#modalFoot').empty();*/
		$('#modalHeader').html('提示信息');
		$('#modalBody').html('验证码不能为空');
		//html.push('<button type="button" data-dismiss="modal" class="btn green">取消</button>');
		html.push('<button type="button" data-dismiss="modal" class="btn green" style="width:54px;height:30px;margin-top:0px;">确认</button>');
		$('#modalFoot').html(html.join(""));
		$('#myModal').modal("show");
	}else if (code.toString() !== valicode.toString()) {
		var html = [];
		$('#modalFoot').empty();
		$('#modalHeader').html('提示信息');
		$('#modalBody').html('验证码错误');
		//html.push('<button type="button" data-dismiss="modal" class="btn green">取消</button>');
		html.push('<button type="button" data-dismiss="modal" class="btn green" style="width:54px;height:30px;margin-top:0px;">确认</button>');
		$('#modalFoot').html(html.join(""));
		$('#myModal').modal("show");
	}
	else {
		var username = $("#username").val();
		var password = $("#password").val();
		if(username==="admin"&&password==="123456")
			setTimeout("window.location.href='index.html'",1000);
		/*$.ajax({
			type : "post",// 请求方式
			url : "QueryLogin.adub",// 发送请求地址
			dataType : "json",
			async : false,
			data : {// 发送给数据库的数据
				flag : "teacher_login",
				loginType :"teacher",//登录类型
				username : $("#username").val(),
				password : $("#password").val(),
				rand: $("#rand").val()
			},
			// 请求成功后的回调函数有两个参数
			success : function(data) {
				if (data.num == 1) {
					setTimeout("window.location.href='index.html'",1000);
				}
				if (data.num == 0) {
					var html=[];
					$('#modalFoot').empty();
					$('#modalHeader').html('提示信息');
					$('#modalBody').html('用户名或密码错误');
					//html.push('<button type="button" data-dismiss="modal" class="btn green">取消</button>');
					html.push('<button type="button" data-dismiss="modal" class="btn green" style="width:54px;height:30px;margin-top:0px;">确认</button>');
					$('#modalFoot').html(html.join(""));
					$('#myModal').modal("show");
					// setTimeout("window.location.href='queryAdmin.html'",2000);
				}
			},
			error : function(){
				window.location.href="error.html";
			}
		});*/
	}
}
$("body").keydown(function(event) {
    if (event.keyCode == "13") {//keyCode=13是回车键
        $("#login").click();
    }
});