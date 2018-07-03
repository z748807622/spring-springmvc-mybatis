
	var w_width = $(window).width();
	var music = {"list":null,"page":1,"index":null,"audio":$("#audio")[0],"size":null};
	$(".shoucang").click(function(){
		music.page = 1;
		getTop500(1,"collect/all?");
		$(this).addClass("active");
	});

	$(".search_m").submit(function(){
		$(".advice").removeClass("active");
		$(".submit").html("正在查找");
		$(".submit").attr("disabled",true);
		var songname = $("#search_name").val();
		if(songname == ""){
			showInfoEnabCancel("info","搜索内容为空");
			$(".submit").attr("disabled",false);
			$(".submit").html("查找");
		}else{
			var res = getSource("music/search?songName="+songname,"&page",1,function(json){
				$(".page ul").attr("date_url","music/search?songName="+songname+"&");
				music.page = 1;
				showList(json);
				$(".submit").attr("disabled",false);
				$(".submit").html("查找");
			});
		}
		return false;
	});
	$(window).resize(function() {
		  reform();
	});
	//添加boostrap手机浏览状态，按下任意位置收起导航栏
	$(".navbar-toggle").find("div").hide();
	$(".navbar-toggle").click(function(){
		//console.log($(this).attr("aria-expanded"));
		if($(this).attr("aria-expanded") == 'true'){
			//console.log("hide");
			$(this).find("div").hide();
		}
		else{
			//console.log("show");
			$(this).find("div").show();
		}
	});
	
	//登陆
	$(".login").click(function(){
		setTimeout(function(){
			$(".login-page").addClass("login-page-show");
		},10);
		$(".login-page").show();
	});
	$(".login-click").click(function(){
		$(".login-page").removeClass("login-page-show");
		setTimeout(function(){
			$(".login-page").hide();
		},500);
	});
	$(".login-close").click(function(){
		$(".login-page").removeClass("login-page-show");
		setTimeout(function(){
			$(".login-page").hide();
		},500);
	});

	getTop500(1,"music/recommendation/2?");




/**
 * 歌曲列表模板
 * @param songname
 * @param singer
 * @param filehash
 * @param img
 * @param alt
 * @returns {String}
 */	
function getTemp(songname,singer,filehash,img,alt,hascollect){
	var collect = hascollect?"hadcollect":"";
	return '<li class="music"><span class="glyphicon glyphicon-heart collect '
	+ collect +
	'"></span><div class="cover" date_src="'
	+ filehash +
	'"><div class="song_img"><img class="img-circle" alt="'
	+ alt +
	'" src="'
	+ img +
	'"><span class="play_hidden play glyphicon glyphicon-play"></span></div></div><div class="singer">'
	+ singer +
	'</div><div class="song">'
	+ songname +
	'</div></li>';
}
/**
 * 给列表上色
 */
function reform(){
	var its = $(".music");
	if($(window).width() >= 1200){
		//console.log(8);
		ff(8);
	}else if($(window).width() >= 922){
		//console.log(6);
		ff(6);
	}else if($(window).width() >= 768){
		//console.log(4);
		ff(4);
	}else{
		//console.log(2);
		ff(2);
	}
	for(var i=0; i<6; i++){
		$($(".page_index")[i]).html(music.page+i);
	}
	//传入一行的个数，然后对其上色
	function ff(num){
		var arr = new Array();
		arr[0] = true;
		for(var i=1; i<its.length; i++){
			if(i % num == 0)
				arr[i] = arr[i-1];
			else
				arr[i] = !arr[i-1];
		}
		//console.log(arr);
		for(var i=0; i<its.length; i++){
			$(its[i]).addClass(arr[i]?($(its[i]).removeClass("gray"),"white"):($(its[i]).removeClass("white"),"gray"));
		}
	}
}
/**
 * 获取top500
 * @param page
 */
function getTop500(page,url){
	
	//$(".page ul").attr("date_url",url);
	music.page = parseInt(page);
	getSource(url,"page",page,function(json){
		console.log(json.state);
		if(json.state != "login")
			$(".page ul").attr("date_url",url);
		showList(json);
	});
}

/**
 * 用list渲染页面
 * @param ret
 */
function showList(ret){
	music.list = ret.data;
	$("#music").html("");
	for(var i=0; i<ret.data.length; i++){
		var song = ret.data[i].song;
		$("#music").html($("#music").html()+getTemp(song.songName,song.singerName,song.fileHash,song.img,song.songName,ret.data[i].hascollect));
	}
	addClickToList();
	addCollectClick();
	reform();
}

/**
 * 给页数添加点击事件
 */
addClickToPage();
function addClickToPage(){
	//向前
	$(".pre").unbind();
	$(".pre").click(function(){
		var url = $(".page ul").attr("date_url");
		if(music.page <= 1)
			return;
		getSource(url,"page",music.page - 1,function(json){
			music.page-=1;
			showList(json);
		});
	});
	//向后
	$(".next").unbind();
	$(".next").click(function(){
		var url = $(".page ul").attr("date_url");
		getSource(url,"page",music.page+1,function(json){
			music.page+=1;
			showList(json);
		});
	});
	//中间
	$(".page_index").unbind();
	$(".page_index").click(function(){
		var url = $(".page ul").attr("date_url");
		var p = $(this).html();
		music.page = parseInt(p);
		getSource(url,"page",p,function(json){
			showList(json);
		});
	});
}

/**
 * 一个参数的get请求
 * @param url
 * @param info
 * @param value
 * @returns 一个json
 */
function getSource(url,info,value,cb){
	showLoading();
	$.ajax({"url":url+info+"="+value,"success":function(res){
		if(res.success != "false")
			cb(res);
		showInfoEnabCancel("info", res.info);
		hideLoading();
	}});
}
function netGet(url,cb,that){
	showLoading();
	$.ajax({"url":url,"success":function(res){
		cb(res,that);
		hideLoading();
	}});
}

function showLoading(){
	logging_lock ++;
	$(".loading").show();
	$(".loading").addClass("loading_show");
	//console.log("showLoading:"+logging_lock);
}
function hideLoading(){
	if(--logging_lock <= 0){
		$(".loading").removeClass("loading_show");
		delayRun(function(){
			$(".loading").hide();
		}, 0.5);
	}
	//console.log("hideLoading:"+logging_lock);
}
/**
 * 给列表添加点击事件
 * @param that
 */
function addClickToList(){
	$(".cover").click(function(){
		var now = music.index;
		var audio = music.audio;
		var that = $(this);
		getSource("music/geturl?","fileHash",$(this).attr("date_src"),function(json){
			audio.src = json.data.url;
			if(now){
				if(now.attr("date_src") == that.attr("date_src")){
					if(that.find(".play").hasClass("glyphicon-play")){
						play(that);
					}else{
						pause(that);
					}
				}else{
					pause(now);
					play(that);
				}
			}else{
				play(that);
			}
		});
		
		music.index = that;
		function play(it){
			it.find(".play").removeClass("play_hidden");
			it.find(".play").addClass("play_show");
			it.find(".play").removeClass("glyphicon-play");
			it.find(".play").addClass("glyphicon-stop");
			music.audio.play();
		}
		function pause(it){
			it.find(".play").removeClass("play_show");
			it.find(".play").addClass("play_hidden");
			it.find(".play").removeClass("glyphicon-stop");
			it.find(".play").addClass("glyphicon-play");
			music.audio.pause();
		}
	});
}
/**
 * 给收藏按钮添加点击事件
 */
function addCollectClick(){
	$(".collect").click(function(){
		var filehash = $(this).parent(".music").find(".cover").attr("date_src");
		netGet("collect//m/" + filehash,function(res,that){
			if(res.state == "login"){
				$(".login").click();
				showInfoEnabCancel("info", "请登录");
			}
			if(res.state == "collect"){
				$(that).addClass("hadcollect");
			}else if(res.state == "cancel"){
				$(that).removeClass("hadcollect");
			}
			showInfoEnabCancel("info", res.info);
		},this);
	});
}