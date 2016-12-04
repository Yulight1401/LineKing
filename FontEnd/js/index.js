
	function loginAregister(){
		function showmask(mask,window){/**mask与window均为jquery对象*/
			mask.fadeIn(400, function() {
			});
		}
		function hidemask(mask,window){
			mask.fadeOut(400, function() {
			});
		}
		$("#loginwindow").on('click', function(event) {
			event.preventDefault();
			event.stopPropagation();
		});
		$("#registerwindow").on('click', function(event) {
			event.preventDefault();
			event.stopPropagation();
		});
		$("#forgrtpasswindow").on('click', function(event) {
			event.preventDefault();
			event.stopPropagation();
		});
		$("#editwindow").on('click', function(event) {
			event.preventDefault();
			event.stopPropagation();
		});
		/**注册登陆**/
		$(".nav  #login").on("click",function(){
			showmask($("#loginmask"),$("#loginwindow"));
		});
		$(".nav  #register").on("click",function(){
			showmask($("#registermask"),$("#registerwindow"));
		});
		/**忘记密码**/
		$("#forgetpass").on("click",function(){
			$("#loginmask").fadeOut();
			showmask($("#forgetpassmask"),$("#forgetpasswindow"));
		});
		$(".startedit").on("click",function(){
			showmask($("#editmask"),$("#editwindow"));
		});


		$("i.loginclose").on("click",function(){
			hidemask($("#loginmask"),$("#loginwindow"));
		});
		$("i.registerclose").on("click",function(){
			hidemask($("#registermask"),$("#registerwindow"));
		});
		$("i.forgetpassclose").on("click",function(){
			hidemask($("#forgetpassmask"),$("#forgetpasswindow"));
		});
		$("i.editclose").on("click",function(){
			hidemask($("#editmask"),$("#editwindow"));
		});

		$("#loginmask").on("click",function(){
			hidemask($("#loginmask"),$("#loginwindow"));
		});
		$("#registermask").on("click",function(){
			hidemask($("#registermask"),$("#registerwindow"));
		});
		$("#forgetpassmask").on("click",function(){
			hidemask($("#forgetpassmask"),$("#forgetpasswindow"));
		});
		$("#editmask").on("click",function(){
			hidemask($("#editmask"),$("#editwindow"));
		});

		$("#hashao").on("click",function(){
			$("#registermask").fadeOut();
			showmask($("#loginmask"),$("#loginwindow"));
		});
		$("#quickreg").on("click",function(){
			$("#loginmask").fadeOut();
			showmask($("#registermask"),$("#registerwindow"));
		});
		$("#postTopic").on('click',function(){
			if(window.localStorage.name.length==0){
				showError("请先注册或者登录！");
			}else{
				http.postTopic(window.localStorage.name,$("#edit").val(),function(data,status){
					if(status=='success'){
						showError("添加成功！");
						$("#editmask").hide();
						setTimeout(function(){
							location.href="index.html";
						},1000)
					}
					else{
						showError("添加失败");
					}
				})
			}

		})
	}
	loginAregister();
	/**********************/
	/**去掉首尾的空格**/
	function trim(str){
		if(str==" "){
			return "";
		}
    	return str.replace(/^\s+|\s+$/g,"");
	}
	function checkEmail(email){
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if(!myreg.test(email.val())){
            return false;
        }
        return true;
	}
	function checkName(name){//参数为jquery对象
		if(trim(name.val())==""){
			return false;
		}
		return true;
	}
	function checkPass(pass1,pass2){
		if(trim(pass1.val())!=trim(pass2.val())){
			return false;
		}
		return true;
	}
	function checkNull(obj){
		if(trim(obj.val())==""){
			return false;
		}
		return true;
	}
	/******************/
	var isAnimated=false;
	function showError(msg){
		if(!isAnimated){
			isAnimated=true;
			$("p.errormsg").text(msg);
			$(".errorTip").animate({
				top: 0},
				400, function() {
				setTimeout(function(){
					$(".errorTip").animate({top:-100},
						400,function(){
							isAnimated=false;
						});
				}, 1500);
			});
		}
	}
	$(".login-button").on("click",function(){
		if(!checkName($("#login-username"))){
			showError("用户名不能为空");
			return ;
		}
		if(!checkNull($("#login-password"))){
			showError("密码好像忘了?");
			return ;
		}
		else{
			http.login($("#login-username").val(),$("#login-password").val(),function(data,status){
				if(status=='success'){
					var template='<a href="user-center.html">用户中心</a>';
					$("#loginmask").hide();
					showError('登录成功！');
					$('#user').html(template);
					setCookie('name',$("#login-username").val());
					window.localStorage.name = $("#login-username").val();
				}else{
					showError('登录错误！用户名与密码不匹配！');
				}
			})
		}
	});
	$(".register-button").on("click",function(){
		if(!checkName($("#register-username"))){
			showError("用户名不能为空哦。。。");
			return ;
		}
		if(!checkNull($("#register-password"))){
			showError("密码好像忘了。。。");
			return ;
		}
		if(!checkPass($("#register-password"),$("#s-register-password"))){
			showError("我没看错的话，两次密码好像不一样吧。。。");
			return ;
		}else{
			http.logon($("#register-username").val(),$("#register-password").val(),function(data,status){
				if(status=='success'){
					$("#registermask").hide();
					showError('注册成功');
					setCookie('name',$("#register-username").val());
					window.localStorage.name = $("#register-username").val();
					var template='<a href="user-center.html">用户中心</a>';
					$('#user').html(template);
				}else{
					showError("注册失败，用户名重复。")
				}
			})
		}
	});

	/**************评论框的变化************/
	var isSlide=false;
	$("body").on("click",".comment",function(event){
		event.stopPropagation();
		if(!isSlide){
			isSlide=true;
			$(this).animate({"height":"80px"},300,function(){
				isSlide=false;
			});
		}else{
			return ;
		}
	});
	$("body").on("focus",".comment",function(event){
		event.stopPropagation();
		if(!isSlide){
			isSlide=true;
			$(this).animate({"height":"80px"},300,function(){
				isSlide=false;
			});
		}else{
			return ;
		}
	});
	$("body").on("click",function(event){
		event.stopPropagation();
		if(!isSlide){
			isSlide=true;
			$(".comment").animate({"height":"30px"},300,function(){
				isSlide=false;
			});
		}else{
			return ;
		}
	});
	/******************评论框的变化*****************/

	/***************选择发帖的类型***********/
	var card={};
	card.typevalue="1";
	var leftword="";
	$(".type").on("click",function(){
		$(".type").removeClass('active');
		$(this).addClass('active');
		card.typevalue=$(this).attr('type-value');
	})
	/***************选择发帖的类型***********/
	/**********发帖的字数限制***********/
	$("#edit").on("keyup",function(){
		var word=100-$(this).val().length;
		if(word>20){
			leftword="剩"+word+"字";
		}
		if((word>0)&&(word<=20)){
			leftword="只剩"+word+"字";
		}
		if(word==0){
			leftword="无法输入";
		}
		$("#word").text(leftword);
	});
	$("#edit").on("keydown",function(){
		var word=100-$(this).val().length;
		if(word>20){
			leftword="剩"+word+"字";
		}
		if((word>0)&&(word<=20)){
			leftword="只剩"+word+"字";
		}
		if(word==0){
			leftword="无法输入";
		}
		$("#word").text(leftword);
	});
	$("#edit").on("change",function(){
		var word=100-$(this).val().length;
		if(word>20){
			leftword="剩"+word+"字";
		}
		if((word>0)&&(word<=20)){
			leftword="只剩"+word+"字";
		}
		if(word==0){
			leftword="无法输入";
		}
		$("#word").text(leftword);
	});
	/**********发帖的字数限制***********/

	/***点赞，战队，收藏，举报的效果***/
	$(".main-left").on("click",".card-line",function(){
		var target=$(this).parent().parent().next().next().find($("#comment"));
		target.focus();
	});

if(window.localStorage.name.length!=0){
	var template='<a href="user-center.html">用户中心</a>';
	$('#user').html(template);
}

function postComment(id){
	if(window.localStorage.name.length==0){
		showError('请先登录或者注册');
	}else{
		var qy="comment"+id;
		console.log(qy);
		http.postComment(window.localStorage.name,document.getElementById("comment1").value,id,function(data,status){
			if(status=='success'){
				showError("评论成功哦！");
				setTimeout(function(){
					location.href="index.html";
				},1000)
				}else{
				showError("评论失败哦！");
			}
		});
	}
}
getTopics();
function getTopics(){
	http.getTopics(function(result){
		for(var i in result){
			insertTopic(result[i]);
			getComments(result[i].id);
		}
	})
}
function getComments(id){
	http.getComments(id,function(result){
		for(var i in result){
			insertComment(id,result[i]);
		}
	})
}
function insertTopic(json){
	var commetid="comment"+json.id;
		var template='<div class="card-wrapper">'+
'					<div class="card">'+
'						<div class="card-user">'+
'							<img src="images/you.jpg" alt="">'+
'							<span>'+json.poster+'</span>'+
'						</div>'+
'						<div class="card-theme">'+
'							<a href="line.html"><span><i class="fa fa-tags"></i>队形贴</span></a>'+
'						</div>'+
'						<div class="card-title">'+
'							<span class="card-look"><i class="fa fa-eye"></i>浏览(500)</span>'+
'							<span class="card-time"><i class="fa-clock-o fa fa-1x"></i>'+json.time+'</span>'+
'						</div>'+
'						<div class="card-content">'+
'							<p>'+json.content+'</p>'+
'						</div>'+
'						<div class="card-bottom">'+
'							<span class="card-line"><i class="fa fa-pencil"></i>站队</span>'+
'							<span class="card-zan"><i class="fa fa-thumbs-o-up"></i>赞(96)</span>'+
'							<span class="card-love"><i class="fa fa-heart-o"></i>收藏(96)</span>'+
'							<span class="card-danger"><i class="fa fa-hand-o-down"></i>举报</span>'+
'						</div>'+
'					</div>'+
'					<div class="feedback" id="'+json.id+'">'+
'					</div>'+
'					<div class="comment-input">'+
'						<textarea name="comment" class="comment" id="'+commetid+'" rows="6"></textarea>'+
'						<button onclick="postComment('+json.id+')">评论</button>'+
'					</div>'+
'				</div>';
$("#topics").append(template);
}
function insertComment(id,json){
	var template='<div class="card-feedback">'+
'							<img src="images/you.jpg" alt="">'+
'							<div class="feedback-title">'+
'								<span class="feedback-user">'+json.commenter+'</span>'+
'								<span class="feedback-time"><i class="fa-clock-o fa fa-1x"></i>'+json.time+'</span>'+
'								<span><i class="fa fa-thumbs-o-up"></i>赞(96)</span>'+
'							</div>'+
'							<div class="feedback-content">'+
'								<span>'+json.content+'</span>'+
'							</div>'+
'						</div>';
var qy="#"+id;
$(qy).append(template);
}
