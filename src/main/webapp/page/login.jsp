<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="http://java.sun.com/jstl/core"%>
    <s:set var="path" value="<%String path = request.getContextPath(); out.print(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"); %>>"></s:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>login</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${ path }css/bootstrap.min.css">
	<script type="text/javascript" src="${ path }js/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="${ path }js/jquery/jQuery.md5.js"></script>
	<script type="text/javascript" src="${ path }js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ path }js/my/showinfo.js"></script>
	<link rel="stylesheet" href="${ path }css/my.css">
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	    <div class="container-fluid">
		    <div class="navbar-header">
		        <a class="navbar-brand" href="#">blobfish</a>
		    </div>
	    </div>
	</nav>

	<div class="container">
		<div class="col-sm-12 text-center">
			<h3>Login</h3>
		</div>
		<div class="col-sm-6 col-sm-offset-3">
			<form class="login_form form-horizontal" role="form">
				<div class="username form-group">
					<label class="col-sm-2 control-label">用户名：</label>
					<div class="col-sm-9">
						<input class="input_name form-control" type="text" name="username">
					</div>
				</div>
				<div class="passwd form-group">
					<label class="col-sm-2 control-label">密&nbsp;&nbsp;码：</label>
					<div class="col-sm-9">
						<input class="input_passwd form-control" type="password" autocomplete="new-password" name="passwd">
					</div>
				</div>
				<div class="form-group text-center">
					<input class="post btn btn-primary col-sm-3 col-sm-offset-3" type="submit" value="登录">
					<a class="btn col-sm-3" href="regist.jsp">注册</a>
				</div>
			</form>
		</div>
	</div>
	<div class="show-info col-sm-4"></div>
</script>
</body>
<script type="text/javascript">
$(".login_form").submit(function(e){
	if(!check())
		return false;
	$.ajax({
		cache:true,
		type:"POST",
		url:"login/in",
		data:$(".login_form").serialize(),
		async:false,
		error:function(req){
			$(".passwd").val(window.passwd);
			$(".username").addClass(".has-error");
			$(".passwd").addClass(".has-error");
		},
		success:function(req){
			$(".input_passwd").val(window.passwd);
			//alert(req.responseText + " ： " + req);
			$(".show-info").html($(".show-info").html()+showInfoEnabCancel(req.state,req.info));
			
			setTimeout(function(){
				$(".alert").addClass("fade-out");
			},2000);
		}
	})
	return false;
})
function check(){
	var callb = $.ajax({
		type:"POST",
		"url":"login/salt",
		data:{
			start_date:new Date().getTime()
		},
		async:false
	}).responseText;
	var calljson = JSON.parse(callb);
	console.log(calljson.salt);
	if(calljson.salt){
		var passwd = $(".input_passwd").val();
		window.passwd = passwd;
		//alert($.md5(passwd) + calljson.salt);
		$(".input_passwd").val($.md5($.md5(passwd).toUpperCase() + calljson.salt).toUpperCase());
		//$(".login_form").submit();
		return true;
	}else{
		alert("login error");
		return false;
	}
}
</script>
</html>