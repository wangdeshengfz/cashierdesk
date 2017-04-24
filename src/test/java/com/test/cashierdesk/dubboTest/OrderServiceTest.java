package com.test.cashierdesk.dubboTest;

import java.io.UnsupportedEncodingException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.constant.Constant;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.BeanConvertUtil;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;
import com.bosssoft.itfinance.epay.v2.trade.common.api.OrderTradeOperation;
import com.bosssoft.itfinance.epay.v2.trade.common.api.PayTradeOperation;
import com.bosssoft.itfinance.epay.v2.trade.common.po.OrderTradeParam;
import com.bosssoft.itfinance.epay.v2.trade.common.po.OrderTradeResult;
import com.bosssoft.itfinance.epay.v2.trade.common.po.PayTradeParam;
import com.bosssoft.itfinance.epay.v2.trade.common.po.PayTradeResult;

public class OrderServiceTest {
	
    //@org.junit.Test
    public void createOrderTrade() throws BaseException, UnsupportedEncodingException {
		System.out.println("");
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo-consumer-test.xml" });
		context.start();
		OrderTradeOperation orderTradeOperation=(OrderTradeOperation)context.getBean("orderTradeOperation");
		
		//测试渠道支付请求
		System.out.println("=====================发起调用");
		OrderTradeParam orderTradeParam = new OrderTradeParam();
		orderTradeParam.setBussnessTradeNo("DD100001");
		orderTradeParam.setOrderDetail("");
		orderTradeParam.setOrderDate("20170421");
		orderTradeParam.setMerchantId("SH0001");
		orderTradeParam.setMerchantName("中文");
		orderTradeParam.setAmt(30000);
		orderTradeParam.setCurrencyCode(Constant.CURRENCY_CODE_RMB);
		orderTradeParam.setOrderTitle("注册会计师考试缴费");
		orderTradeParam.setOrderDesc("全国会计考试缴费-注册会计师");
		orderTradeParam.setAgent("博思软件代理缴费");
		orderTradeParam.setAgentIp("192.168.140.22");
/*		orderTradeParam.setMemberId("");// 发起该订单的会员id
		orderTradeParam.setMemberName("");//发起该订单的会员名称
*/		orderTradeParam.setCashierDeskReturnUrl(Constant.CASHIERDESK_MAIN_URL);//收银台的回调url(渠道服务调用收银台)
		//支付成功后回调的业务应用的url(银行为同步时-订单服务调用业务系统)
		orderTradeParam.setReturnUrl("http://wsbm.fjkj.gov.cn/myServlet?action=main&param=xxxx&sign=xxx");
		//支付成功后异步通知业务应用的url(银行为异步时-订单服务调用业务系统)
		orderTradeParam.setReturnUrl("http://wsbm.fjkj.gov.cn/myServlet?action=main&param=xxxx&sign=xxx");
		orderTradeParam.setAssignAccountId("");
		
        System.out.println("=====================入参" + BeanConvertUtil.Obj2String(orderTradeParam));
        
        OrderTradeResult resp = orderTradeOperation.createOrderTrade(orderTradeParam);
		System.out.println("=====================调用结果："+BeanConvertUtil.Obj2String(resp));
		System.out.println("=====================调用完成");
	}
    
    //@org.junit.Test
    public void bindMemberByPayOrderId() throws BaseException, UnsupportedEncodingException {
		System.out.println("");
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo-consumer-test.xml" });
		context.start();
		OrderTradeOperation orderTradeOperation=(OrderTradeOperation)context.getBean("orderTradeOperation");
		
		try{
        System.out.println("=====================入参");
        String memberId = "5003333";//从会话获取会员ID信息
        String payOrderId = "0996d9be5017404e188e0001";
        String orderTradeNo = "0996d9be5017404e188e0001";
        orderTradeOperation.bindMember(memberId, orderTradeNo);
		System.out.println("=====================调用结果：");
		System.out.println("=====================调用完成");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    @org.junit.Test
    public void createPayTrade() throws BaseException, UnsupportedEncodingException {
		System.out.println("=====");
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo-consumer-test.xml" });
		context.start();
		PayTradeOperation payTradeOperation=(PayTradeOperation)context.getBean("payTradeOperation");
		
		try{
        System.out.println("=====================入参");
        PayTradeParam ptp = new PayTradeParam();
		ptp.setOrderTradeNo("09975a028421744e188e0001");
		ptp.setPayChannelId("channel-bsbank_1.0.0");
		ptp.setPayChannelVersion(12);
		ptp.setAccountId("3");
		ptp.setAccountName("省会计中心");
		ptp.setAmt(2300);
		PayTradeResult rst = payTradeOperation.createPayTrade(ptp);
		System.out.println("=====================调用结果："+BeanConvertUtil.Obj2String(rst));
		System.out.println("=====================调用完成");
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    
}
