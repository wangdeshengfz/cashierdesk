package com.test.cashierdesk.controllerTest;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.test.cashierdesk.base.ControllerBaseTest;

public class OrderControllerTest extends ControllerBaseTest{
    /**
     * 接收商户缴费申请，生成缴费订单(返回执行结果串)
     * @throws Exception
     */
	@Test
    public void createOrderTrade() throws Exception
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("bussnessTradeNo","0");
        param.put("orderDetail","0");
        param.put("orderDate","0");
        param.put("orderTime","0");
        param.put("merchantId","0");
        param.put("merchantName","0");
        param.put("amt","0");
        param.put("currencyCode","0");
        param.put("orderTitle","0");
        param.put("orderDesc","0");
        param.put("agent","0");
        param.put("agentIp","0");
        param.put("memberId","0");
        param.put("memberName","0");
        param.put("returnUrl","0");
        param.put("notifyUrl","0");
        param.put("assignAccountId","0");
        testRequest("/v1/createOrderTradeForJson", param);
    }
	
}
