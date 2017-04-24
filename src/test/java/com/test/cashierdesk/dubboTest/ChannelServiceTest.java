package com.test.cashierdesk.dubboTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bosssoft.itfinance.epay.v2.channel.common.api.ChannelTradeOperation;
import com.bosssoft.itfinance.epay.v2.channel.common.entity.ChannelPayReq;
import com.bosssoft.itfinance.epay.v2.channel.common.entity.ChannelPayResp;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;

public class ChannelServiceTest {
	
    @org.junit.Test
    public void test() throws BaseException {
		System.out.println("");
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo-consumer-test.xml" });
		context.start();
		ChannelTradeOperation channelTradeOperation=(ChannelTradeOperation)context.getBean("channelTradeOperation");
		
		//测试渠道支付请求
		System.out.println("发起调用");
		ChannelPayReq channelPayReq = new ChannelPayReq();
        channelPayReq.setPayReqNo("X2017041700001");
        channelPayReq.setCreateDate("20170417");
        channelPayReq.setCreateTime("205557");
        channelPayReq.setOrderTitle("56aP6Lev6YCa5rWL6K+V6K6i5Y2V");
        channelPayReq.setAmt(1000);
        channelPayReq.setPayChannelId("channel-bsbank_1.0.0");
        channelPayReq.setAccountId("3");
        System.out.println("====================="+channelPayReq);
        
		ChannelPayResp resp = channelTradeOperation.createPayReq(channelPayReq);
		System.out.println("调用结果："+resp);
		System.out.println("调用完成");
	}
	
	
}
