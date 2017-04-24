define(['jquery'],function ($) {
	//截取异常数字
	var _rep_businessNo=function(txt){
		return new RegExp('^\\d{1,10}:(.*?)$').test(txt)?(txt.substring(txt.indexOf(':')+1)):txt;
	};
	window.rep_businessNo=_rep_businessNo;
	
	var Config={
			//添加必要的请求参数
			setFullParameter:function(params){
				if(typeof $.cookie("token") === 'undefined' || $.cookie("token") ==''){
					if(typeof toLogin ==='function'){
						toLogin();
					}
				}
				if($.isArray(params)){
					//设置请求随机数
					params.push({"name":"_r","value":Math.random()});
					params.push({"name":"token","value":$.cookie("token")});
					params.push({"name":"channel","value":"pc"});
					params.push({"name":"version","value":"v1.0"});
					params.push({"name":"request_hash","value":location.hash});
				}else{
					//设置请求随机数
					params._r=Math.random();
					params.token = $.cookie("token");
					params.channel = "pc";
					params.version = "v1.0";
					params.request_hash = location.hash;
				}
				return params;
			}
	};

	//web 服务器地址获取
	Config.webRoot = function(){
		var js=document.scripts;
		var jsroot='';
		for(var i=js.length;i>0;i--){
			jsroot=js[i-1].src;
			if(jsroot.indexOf("js/lib/require.js")>-1){
				jsroot=jsroot.substring(0,jsroot.indexOf("/js/lib/require.js"));
				break;
			}
		}
		return jsroot;
	};
	 // 设置_apiRoot地址
	Config.apiRoot = function(){
		 // 设置_apiRoot地址
		 var _apiRoot=Config.webRoot();
		 return _apiRoot.substring(0,_apiRoot.lastIndexOf('/'));
	};
	

	//注册到window
	window.setFullParameter=Config.setFullParameter;

	//初始化定义系统配置
	Config.sysConfig={};
	
	//获取系统配置
	/*$.ajax({
		//地址格式
		url: Config.apiRoot()+'/api/v1/sysconfig',
		type: 'GET',
		dataType: 'jsonp',
		cache: 'false',
		async:false,
		data:  {r:Math.random()},
		success: function(data) {
			if(data.code=='200' && data.businessMsg.businessCode=='0' && typeof data.data === 'object'){
				Config.sysConfig=data.data;
			}
		}
	});*/
	
	//服务器图片
	Config.imgRoot=function(){
		 var imgrs=[];
		 if(typeof Config.sysConfig.imgRoot === 'string'){
			 imgrs=Config.sysConfig.imgRoot.split(',');
		 }
		 return imgrs[Math.floor(Math.random()*imgrs.length)];
	};
	
	return{
		 //图片服务器地址
		 imgRoot:Config.imgRoot,
		 //api后台项目地址
		 apiRoot:Config.apiRoot,
		 //web前端项目地址
		 webRoot:Config.webRoot,
		 //请求附加参数方法
		 setFullParameterArr:Config.setFullParameter,
		 //请求附加参数方法
		 setFullParameter:Config.setFullParameter,
		 //系统配置
		 sysconfig:function(){
			 return Config.sysConfig;
		 }
	};
});