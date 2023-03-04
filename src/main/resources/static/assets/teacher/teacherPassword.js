$(document).ready(function() {
	$("[type='submit']").attr("disabled",false);
	$.formValidator.initConfig({
		//与form的id有关
		formID : "passwordForm",
		//主题切换，内容和js的themes里面的文件夹名相同即可
		theme : "Bull",
		onSuccess : function() {// 该组校验通过后的回调函数
			$("[type='submit']").attr("disabled",true);
			update();
		},
		onError : function() {
			//alert("校验没有通过，具体错误请看错误提示")
			var html=[];
			$('#modalFoot').empty();
			$('#modalBody').html('验证不通过请看错误');
			html.push('<button type="button" data-dismiss="modal" class="btn btn-primary">确认</button>');
			$('#modalFoot').html(html.join(""));
			$('#myModal').modal("show");
		}
	});
	
	// 对原密码验证
	$("#oldpassword").formValidator({
		onShow : "请输入原密码",// 显示时候的提示
		onFocus : "请输入原密码",// 获得焦点的提示
		onCorrect : "输入合法"// 输入正确后的提示
	}).inputValidator({
		min : 1,// 最小长度为1
		empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空格"},
		onError : "原密码不能为空"// 发生错误的提示
	});
	
	// 对新密码验证
	$("#password").formValidator({
		onShow : "请输入新密码",// 显示时候的提示
		onFocus : "请输入新密码",// 获得焦点的提示
		onCorrect : "输入合法"// 输入正确后的提示
	}).inputValidator({
		min : 5,// 最小长度为5
		empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空格"},
		onError : "密码不得少于5位"// 发生错误的提示
	});

	// 对重复密码验证
	$("#repassword").formValidator({
		onShow : "请再次输入密码",// 显示时候的提示
		onFocus : "请再次输入密码",// 获得焦点的提示
		onCorrect : "密码一致"// 输入正确后的提示
	}).inputValidator({
		min : 5,// 最小长度为5
		empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空格"},
		onError : "请输入5位及以上密码"// 发生错误的提示
	}).compareValidator({
		desID : "password",// 被比较的id
		operateor : "=",// 比较与password是否相等
		onError : "2次密码不一致"// 发生错误的提示
	});
	
});

function update(){
	$.ajax({
		type : "post",// 请求方式
		url : "../UpdateBasic.adub",// 发送请求地址
		dataType : "json",
		async : false,
		data : {// 发送给数据库的数据
			flag : 'teacher_update_password',
			oldpassword : $("#oldpassword").val(),
			password : $("#password").val()
		},
		// 请求成功后的回调函数有两个参数
		success : function(data) {
			if(data.num==1){
				var html=[];
				$('#modalFoot').empty();
				$('#modalHeader').html('提示信息');
				$('#modalBody').html('密码修改成功');
				html.push('<button type="button" data-dismiss="modal" class="btn btn-primary" onclick="window.location.reload()">确认</button>');
				$('#modalFoot').html(html.join(""));
				$('#myModal').modal("show");
			}else{
				var html=[];
				$('#modalFoot').empty();
				$('#modalHeader').html('提示信息');
				$('#modalBody').html('您输入的密码有误，请重新输入');
				html.push('<button type="button" data-dismiss="modal" class="btn btn-primary" onclick="window.location.reload()">确认</button>');
				$('#modalFoot').html(html.join(""));
				$('#myModal').modal("show");
			}
		},
		error : function(){
			window.parent.location.href="../error.html";
		}
	});
}