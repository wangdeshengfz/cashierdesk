requirejs.config({
    baseUrl: '../js/lib',
    paths: {
        jquery: 'jquery.min',
        login: '../common/login',
        config: "../common/config",
        layer: '../plugins/layer/layer'
    },
    shim: { //引入没有使用requirejs模块写法的类库。backbone依赖underscore
        'jquery': {
            exports: '$'
        },
    	'layer': ['jquery']
    }
});
define(['jquery','config','login','layer'], function($,config,login,layer) {
	
	Array.prototype.indexOf = function(val) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) return i;
        }
        return -1;
    };
    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    }
	
	//加载扩展模块
	layer.config({
		extend: '../plugins/layer/extend/layer.ext.js'
	});
	
	var _common_request = function(action, params, care, callback,$jump401,$gtype,$complete,$async){
		//如果参数为空设置为空对象
		if(typeof params === 'undefined' || typeof params != 'object' ){
			params={}
		}
		if($gtype == undefined || $gtype==''){
			$gtype = "get";
		}
		var _async=true;
		if(typeof $async ==='boolean'){
			_async=$async;
		}
		var ajaxurl = config.apiRoot() + action;
		$.ajax({
			url: ajaxurl,
			type: $gtype,
			dataType: 'json',
			async:_async,
			cache: 'false',
			data:  setFullParameter(params),
			success: function(jsonstr) {
				if (callback != null) {
					if (jsonstr.code == "200") //业务异常
					{
						callback(jsonstr.data, jsonstr.code, jsonstr.businessMsg);
					} else if (jsonstr.code == "400") {
						//是否关心异常码
						if (care) {
							callback(jsonstr.data, jsonstr.code, jsonstr.businessMsg);
						} else {
							layer.closeAll('loading'); //关闭加载层
							layer.msg(jsonstr.businessMsg.businessNote);
						}
					} else if (jsonstr.code == '401') {//未登录
						//loginJs.loadHtml();
						if(typeof $jump401 === 'function'){
							$jump401($resultdata.code,$resultdata.businessMsg);
						}else{
							login.toLogin();
						}
					} else {
						if(jsonstr.businessMsg.businessNote != null&&jsonstr.businessMsg.businessNote != '') {
							layer.closeAll('loading'); //关闭加载层
							layer.msg(jsonstr.businessMsg.businessNote);
						} else {
							layer.closeAll('loading'); //关闭加载层
							layer.msg(JSON.stringify(jsonstr.businessMsg));
						}
						
					}
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//TODO:隐藏弹出框
				//TODO:提示消息
				if(jqXHR.readyState === 0 && jqXHR.status === 0){}else{
					//_log(JSON.stringify(jqXHR) + "," + textStatus + "," + errorThrown);
					_log("系统异常");
					layer.closeAll('loading'); //关闭加载层
				}
				//						alert(jqXHR.responseText);
				//						alert("error");
			},
			complete:function(){
				if(typeof $complete==='function'){
					$complete();
				}
			}
		});
	}
	//注册到window
	window.common_ajax_request=_common_request;
	window.layer = layer;
	window.loading = _loading;
	//弹出框带遮罩,2s后自动关闭
	var _alert=function(msg,callback){
		layer.msg(msg, {
			  time: 2000, //2s后自动关闭
			  shade:0.3//遮罩
			  },function(){
				  callback(); 
			  }
		);
	};
	
	var _loading = function(){
		return layer.load(0, {shade: 0.3});
	};
	var _request=function($gtype,$url,$params,$callback,$businesscallback400,$jump401){
		//判空
		if($gtype == null || typeof $gtype === 'undefined' || $url == null ||typeof $url === 'undefined'){
			_log('参数错误: gtype , url 是非空字段');
			return;
		}
		//如果参数为空设置为空对象
		if($params == null ||typeof $params === 'undefined' || typeof $params != 'object' ){
			$params={}
		}
		
		$.ajax({
			url: config.apiRoot()+$url,
			type: $gtype,
			data: setFullParameter($params),
			dataType: 'json',
//			jsonp: "callback",
//			contentType: "application/json;charset=UTF-8",
			cache: 'false',
			success: function($resultdata) {
				if($resultdata!=null){
					if($resultdata.code=='200'){
						//业务正常
						if($callback !=null && typeof $callback === 'function'){
							//data回调
							$callback($resultdata.data,$resultdata.code,$resultdata.businessMsg);
						}
					}else if($resultdata.code=='401'){//未登录
						if(typeof $jump401 === 'function'){
							$jump401($resultdata.code,$resultdata.businessMsg);
						}else{
							login.toLogin();
						}
					}else if($resultdata.code != '400'){//业务错误
						//业务异常回调
						if($businesscallback400 !=null && typeof $businesscallback400 === 'function'){
							$businesscallback400($resultdata.code,$resultdata.businessMsg);
						}else if($businesscallback400 !=null && typeof $businesscallback400 === 'string' && $businesscallback400 === 'sys'){
							//未定义业务接收 方法
							layer.msg($resultdata.businessMsg.businessNote);
						}
					}else{//未定义业务接收 方法
						layer.msg($resultdata.businessMsg.businessNote);
					}
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				//TODO:隐藏弹出框
				//TODO:提示消息
				if(jqXHR.readyState === 0 && jqXHR.status === 0){}else{
					//_log("请求错误:"+JSON.stringify(jqXHR) + "," + textStatus + "," + errorThrown);
					_log("系统异常");
				}
			}
		});
	};
	
	/**
	 * 从url中获取参数
	 * name 参数名,isDecode 是否进行解码
	 */
	var _getUrlParameter = function(name, isDecode) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var search_str = window.location.search.substr(1);
		if(isDecode){
			search_str = decodeURI(window.location.search).substr(1).replace("%3A",":");
		}
		var r = search_str.match(reg);
		if (r != null)
			return isDecode?r[2]:unescape(r[2]);
		return null;
	};
	
	/**
	 * 采用form表单提交数据,param为json格式
	 * url 提交目的接口，用绝对地址
	 * param 入参，json格式数据
	 * target 提交目的页面 默认新开一个页面 
	 */
	var _formCommit = function(url,param,target){
		   //创建from表单及其子元素  
		   var form=$("<form></form>");
		   //设置form的属性值  
		   form.attr("action", url);
		   form.attr("method", "post");
		   if(target == null){
			   form.attr("target", "_blank");
		   }else{
			   form.attr("target", target);
		   }
		   form.hide();
		   if(param != null){
			   $.each(param,function(name,value) {
				      //创建一个文本框  
				      var input_text = $("<input type='text'/>"); 
				      input_text.attr("name", name);  
				      input_text.attr("value", value);    
				      //附加到表单  
				      form.append(input_text);  
			   });
		   }
		   //提交表单  *注意此处的写法，要先将创建的form渲染到body之中才可触发submit()事件，否则是不能触发的  
		   form.appendTo(document.body).submit();
		   return false;  
	   }
	
	return {
		/**
		 * ajax 请求
		 * 例子 : common.ajax_request('get',config.apiRoot()+'/api/seller/v1/menu_config',{},function(data){},function(data){});
		 * 
		 * @param $gtype 请求类型 string  (GET | POST)
		 * @param $url  请求地址 string  (http://xxx.xxx.xxx/)
		 * @param $params  请求参数 object  ($('xxx').serializeArray())
		 * @param $callback  请求回调 function(data,code,businessMsg);
		 * @param $businesscallback400  未处理业务回调 function(code,businessMsg); | sys(string 系统弹出)
		 * @param $jump401  未设置自动跳转 function(code,businessMsg);
		 * @returns
		 */
		ajax_request:function($gtype,$url,$params,$callback,$businesscallback400,$jump401){
			return _request($gtype,$url,$params,$callback,$businesscallback400,$jump401);
		},
		common_ajax_request:function(action, params, care, callback,$jump401,$gtype,$complete){
			return _common_request(action, params, care, callback,$jump401,$gtype,$complete);
		},
		loading:function(){
			return _loading();
		},
		getUrlParameter: function(name, isDecode) {
			return _getUrlParameter(name, isDecode);
		},
		formCommit: function(url,param,target){
			return _formCommit(url,param,target);
		}
	};
});
