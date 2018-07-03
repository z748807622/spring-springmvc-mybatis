login();
$(".login_form").submit(function(e){
	$(".post").val("正在登陆");
	$(".post").attr("disabled",true);
	check(function(){
		login();
	});
	
	return false;
});
function login(){
	showLoading();
	$.ajax({
		cache:true,
		type:"POST",
		url:"login/in",
		data:$(".login_form").serialize(),
		async:true,
		error:function(req){
			$(".passwd").val(window.passwd);
			$(".username").addClass(".has-error");
			$(".passwd").addClass(".has-error");
			$(".post").val("登陆");
			$(".post").attr("disabled",false);
			hideLoading();
		},
		success:function(req){
			$(".input_passwd").val(window.passwd);
			//alert(req.responseText + " ： " + req);
			showInfoEnabCancel(req.state,req.info);
			$(".post").val("登陆");
			$(".post").attr("disabled",false);
			hideLoading();
			if(!req.name)
				return;
			$(".login-operete").hide();
			$("#login-success").show();
			$(".login-page").removeClass("login-page-show");
			setTimeout(function(){
				$(".login-page").hide();
			},500);
			$("#login-success .username").html(req.name);
		}
	});
}
function check(cb){
	$.ajax({
		type:"POST",
		"url":"login/salt",
		success:function(res){
			//console.log(res);
			if(res.salt){
				var passwd = $(".input_passwd").val();
				window.passwd = passwd;
				$(".input_passwd").val($.md5($.md5(passwd).toUpperCase() + res.salt).toUpperCase());
				cb();
				return true;
			}else{
				alert("login error");
				return false;
			}
		}
	});
	
}
