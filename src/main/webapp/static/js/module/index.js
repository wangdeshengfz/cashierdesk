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
   var cashier = {};//收银台相关相关
   
   //初始化缴款订单信息到页面
   cashier.initOrderTradeInfo = function(){
	    var queryParam ={};
		var payOrderId = common.getUrlParameter("order_id");
		queryParam.payOrderId = payOrderId;
		common.common_ajax_request("/api/order/v1/queryOrderTrade", queryParam, false,function(data,code,msg){
			if(code == '200'){			
				$("#order_id").text(data.payOrderId);
				$("#amt").text(data.amt);
				$("#orderDate").text(data.orderDate);
				$("#merchantName").text(data.merchantName);
				$("#memberName").text(data.memberName);
				$("#orderTitle").text(data.orderTitle);
				$("#orderDesc").text(data.orderDesc);
				console.log(data);
			}else{
				layer.alert(msg.businessNote);
			}
		}, function(){alert("zdl");}, "post"); 
   };
   
   //绑定按钮事件
   cashier.btnListen = function(){
		//登录并去支付
		$('#btn_loginToPay').on('click',function(e){
			var username = $("#username").val();
			var password = $("#password").val();
			login.loginForInner(username, password, function(){
				alert("登录成功,开始绑定缴款订单。。");
				
				//绑定缴款订单
				var bindParam ={};
				var payOrderId = common.getUrlParameter("order_id");
				bindParam.payOrderId = payOrderId;
				common.common_ajax_request("/api/order/v1/bindMemberForOrderTrade", bindParam, false,function(data,code,msg){
					if(code == '200'){
						debugger;
						alert("缴款订单绑定成功");
						$("#div_login").hide();
						$("#div_channel").show();
						$("#div_payResult").hide();
					}else{
						layer.alert(msg.businessNote);
					}
				}, "", "post");
			});
		});
	   
		//选择渠道信息
		$('#btn_selectChannel').on('click',function(e){
			alert("选择渠道完成，创建支付订单");
			
			//创建支付订单
			var cpayParam ={};
			var payOrderId = common.getUrlParameter("order_id");
			cpayParam.payOrderId = payOrderId;
			cpayParam.payChannelId = "channel-bsbank_1.0.0";
			cpayParam.payChannelVersion = "12";
			cpayParam.accountId = "3";
			cpayParam.accountName = "省会计中心";
			cpayParam.amt = 2500;
			common.common_ajax_request("/api/order/v1/createPayOrder", cpayParam, false,function(data,code,msg){
				if(code == '200'){
					alert("支付订单创建成功，进入银行网关页面");
					$("#div_login").hide();
					$("#div_channel").hide();
					$("#div_payResult").show();
					common.formCommit(data.url,data.params);
					//window.open("https://www.cebbank.com/per/prePerlogin.do?ident=gr&_locale=zh_CN");
				}else{
					layer.alert(msg.businessNote);
				}
			}, "", "post");
		});
		
		//返回商户
		$('#btn_toMerchant').on('click',function(e){
			location.href="/cashierdesk/static/html/merchant_side.html"
		});

   }
	
   
   cashier.init = function() {
	    debugger;
	   	cashier.initOrderTradeInfo();
	   	debugger;
		$("#div_login").show();
		$("#div_channel").hide();
		$("#div_payResult").hide();
		cashier.btnListen();
		$("#order_id").html(common.getUrlParameter("order_id"));
	};
	
	cashier.init();
});
