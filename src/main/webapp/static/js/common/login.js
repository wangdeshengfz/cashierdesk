requirejs.config({
    baseUrl: '../js/lib',
    paths: {
        jquery: 'jquery.min',
        jquerycookie: 'jquery.cookie',
        md5: 'md5.min',
        config: "../common/config"
    },
    shim: {
    	
    }
});
define(['jquery','config','md5','jquerycookie'], function($,config,md5) {
	var _href=location.href;
	var _dhref=decodeURIComponent(_href);
	if(_dhref.indexOf('\'')!=-1||_dhref.indexOf('\"')!=-1||_dhref.indexOf('>')!=-1||_dhref.indexOf('<')!=-1){
		alert("URL地址存在非法字符 ( '  \"  >  < ) :  \r\n"+_href);
		return;
	}
	
	$(function(){
		$("body").keydown(function(){
			_keyLogin(event);
		})
	})

	//按enter键登录
	var  _keyLogin=function(event){  
	  if (event.keyCode==13)                       
		//按Enter键的键值为13  
	     $("#btn-login").click(); 
		//调用登录按钮的登录事件  
	}
	
	// 重置密码
	var _changepwd = function($frm,$callback){
		/*$.each($frm,function(_index,_data){
			_data['value']=md5(_data['value']);
		});
		//console.log($frm);
		$.ajax({
			url: config.apiRoot()+'/api/v1/changepwd',
			type: 'GET',
			data: setFullParameter($frm),
			dataType: 'jsonp',
			cache: 'false',
            timeout: 30000,
			success: function($resultdata) {
				if(typeof $callback === 'function'){
					$callback($resultdata);
				}
			}
		});*/
	};
	window.changepwd=_changepwd;
	
	
	var _checkLogin = function($jump){
		//检测用户是否登录没有登录跳转到登录
		$.ajax({
			url: config.apiRoot()+'/api/auth/asscessCheck',
			type: 'GET',
			data: setFullParameter({}),
			dataType: 'json',
			cache: 'false',
            timeout: 30000,
			success: function($resultdata) {
				if($resultdata!=null){
					if($resultdata.code=='401'){
						_toLogin();
					}
				}
			}
		});	
	};
	window.checkLogin=_checkLogin;
	
	var _loginOut = function(){
		//检测用户是否登录没有登录跳转到登录
		$.cookie("token",null);
		document.cookie = 'token=';
		$.ajax({
			url: config.apiRoot()+'/api/auth/logout',
			type: 'GET',
			data: setFullParameter({}),
			dataType: 'json',
			cache: 'false'
		});	
		_toLogin();
	};
	window.loginOut=_loginOut;
	
	//跳转到登录
	var _toLogin = function(){
		if(window.location.href.indexOf(config.webRoot()+"/html/login.html")<0){
			window.location.href = config.webRoot()+"/html/login.html?url="+encodeURIComponent(window.location.href);
		}
	};
	window.toLogin=_toLogin;

	//获取url参数
	var _param = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    };
    
	//登录
	var _login = function(){
		var params = {};
		params.username=$.trim($("#username").val());
		params.password = md5($.trim($("#password").val()));
		params._r=Math.random();
		
		$.ajax({
			url: config.apiRoot()+'/api/auth/asscessToken',
			type: 'POST',
			data: setFullParameter(params),
			dataType: 'json',
			success: function($resultdata) {
				if($resultdata!=null){
					if($resultdata.code=='200'){
						//登录成功
						$.cookie("token", $resultdata.data);
						//console.info($resultdata.data,"token");
						// 登录成功 返回登录前url
						var _rtourl = _param('url');
						//只重定向本站地址
						if (_rtourl != null&&decodeURIComponent(_rtourl).indexOf(location.protocol+'//'+location.hostname)==0) {
							window.location.href = _rtourl;
						}else{
							window.location.href = "index.html";
						}
					}else{//未定义业务接收 方法
						_log($resultdata.businessMsg.businessNote);
					}
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//TODO:提示消息
				_log("登录失败!");
				//_log("请求错误:"+JSON.stringify(jqXHR) + "," + textStatus + "," + errorThrown);
			}
		});		
	};
	
	//嵌入登录方法
	var _loginForInner = function(username,password,callback){
		var params = {};
		params.username=$.trim($("#username").val());
		params.password = md5($.trim($("#password").val()));
		params._r=Math.random();
		
		$.ajax({
			url: config.apiRoot()+'/api/auth/asscessToken',
			type: 'POST',
			data: setFullParameter(params),
			dataType: 'json',
			success: function($resultdata) {
				if($resultdata!=null){
					if($resultdata.code=='200'){
						//登录成功
						$.cookie("token", $resultdata.data);
						if(callback != null){
							callback();
						}else{
							alert("登录成功！");
						}
					}else{//未定义业务接收 方法
						_log($resultdata.businessMsg.businessNote);
					}
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//TODO:提示消息
				_log("登录失败!");
				//_log("请求错误:"+JSON.stringify(jqXHR) + "," + textStatus + "," + errorThrown);
			}
		});		
	};
	
	//注册到window
	window.login=_login;

    //  弹出消息
    var _log = function(msg){
    	alert(msg);
    };
    
	return {
		/**
		 * 检测登录状态
		 * @param $jump boolean (false 不跳转 其余跳转登录)
		 * @returns
		 */
		checkLogin:function($jump){return _checkLogin($jump);},
		toLogin:function(){return _toLogin();},
		loginOut:function(){return _loginOut();},
		changepwd:function($frm,$callback){return _changepwd($frm,$callback);},
		loginForInner:function(username,password,callback){return _loginForInner(username,password,callback)}
	}
});