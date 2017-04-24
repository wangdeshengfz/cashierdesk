requirejs.config({
    baseUrl: '../js/lib',
    paths: {
        jquery: 'jquery.min',
        common:'../common/common',
        login:'../common/login'
    },
    waitSeconds : 15,
	map : {
		'*' : {
			'css':'css'
		}
	}
});
requirejs(['jquery','common','login'], function($,common,login) {
   var demo = {};//用户相关
   
   //采用form表单提交数据,param为json格式
   demo.formSubmit = function(url,param,method,target){
	   //创建from表单及其子元素  
	   var form=$("<form></form>");   
	   //设置form的属性值  
	   form.attr("action", url);    
	   form.attr("method", method);
	   form.hide();
	   if(param.length > 0){
		   $.each(anObject,function(name,value) {
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
   
   //绑定按钮事件
   demo.BtnListen = function(){
	   //获取数据
		$('#btn-getdata').on('click',function(){
			var param = {};
			param.title = $("#title").val();
			common.common_ajax_request("/api/v1/obtainExamples3", param, false,function(data,code,msg){
				if(code == '200'){
					layer.alert("获取数据成功");
					console.log(data,"返回数据");
				}else{
					layer.alert(msg.businessNote);
				}
			}, "", "post");
		});
		
		//退出登录
		$('#btn-loginout').on('click',function(e){
			e.preventDefault();
			login.loginOut();
		});
   }
			
   demo.init = function() {
		demo.BtnListen();
	};
	
	demo.init();
});
