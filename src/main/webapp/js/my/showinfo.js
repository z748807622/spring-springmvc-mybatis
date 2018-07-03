//showLogging计数器
var logging_lock = 0;

/**
 * 
 * @param type 返回消息的类型，success、info、warning、danger
 * @param message 要显示的信息
 * @returns  返回一个div,没有取消按钮
 */
function showInfo(type,message){
	var typeStr = '<div class="alert alert-'+ type +'">'+ message +'</div>';
	clearDiv();
	$(".show-info").html($(".show-info").html()+typeStr);
	setTimeout(function(){
		$(".alert").addClass("fade-out");
	},2000);
	//return typeStr;
}

function clearDiv(){
	setTimeout(function(){
		while($(".alert").length > 1)
			$(".alert")[0].remove();
	},2000);
}

/**
 * 
 * @param type 返回消息的类型，success、info、warning、danger
 * @param message 要显示的信息
 * @returns {String}返回一个div,有取消按钮
 */
function showInfoEnabCancel(type,message){
	var typeStr = '<div class="alert alert-'+type+' alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"> &times;</button>'+message+'</div>';
	$(".show-info").html($(".show-info").html()+typeStr);
	setTimeout(function(){
		$(".alert").addClass("fade-out");
		delayRun(function(){clearDiv()},3);
	},2000);
	//return typeStr;
}


/**
 * 
 * @param fun 要执行的函数
 * @param time 要延迟的时间，单位秒
 */
function delayRun(fun,time){
	setTimeout(function(){
		fun();
	},time*1000);
}