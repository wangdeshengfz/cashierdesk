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
   var merchant = {};//商户端相关
   
   //绑定按钮事件
   merchant.btnListen = function(){
	 //去缴费
		$('#btn-toCashierdesk').on('click',function(e){
			var payParam = {};
			payParam.bussnessTradeNo = $("#bussnessTradeNo").val();
			payParam.amt = $("#amt").val();
			payParam.orderDate = $("#orderDate").val();
			payParam.orderTime = $("#orderTime").val();
			payParam.merchantName = $("#merchantName").val();
			payParam.orderTitle = $("#orderTitle").val();
			payParam.orderDesc = $("#orderDesc").val();
			payParam.orderDetail = $("#orderDetail").val();
			payParam.merchantId = $("#merchantId").val();
			payParam.currencyCode = $("#currencyCode").val();
			payParam.agent = $("#agent").val();
			payParam.agentIp = $("#agentIp").val();
			payParam.returnUrl = $("#returnUrl").val();
			
			payParam.notifyUrl = $("#notifyUrl").val();
			payParam.assignAccountId = $("#assignAccountId").val();
			payParam.sign = $("#sign").val();
			
			var url = "http://localhost:9091/cashierdesk/api/order/v1/createOrderTrade";
			var method = "post";
			var target = "_blank";
			common.formCommit(url,payParam);
			/*common.common_ajax_request("/api/order/v1/createOrderTradeForJson", payParam, false,function(data,code,msg){
				if(code == '200'){
					alert("缴款订单生成成功");
					location.href="/cashierdesk/static/html/index.html?order_id="+data;
					console.log(data,"返回数据");
				}else{
					layer.alert(msg.businessNote);
				}
			}, "", "post");*/
		});
	   
	   //获取数据
/*		$('#btn-topay').on('click',function(){
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
		});*/
		
   }
			
   merchant.init = function() {
	   merchant.btnListen();
	};
	
	merchant.init();
});
