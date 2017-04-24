package com.test.cashierdesk.controllerTest;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.test.cashierdesk.base.ControllerBaseTest;

public class ExampleControllerTest extends ControllerBaseTest{
    /**
     * obtainExamples_v1 方法测试
     * @throws Exception
     */
	//@Test
    public void obtainExamples_v1() throws Exception
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("msg","测试消息22222");
        testRequest("/v1/obtainExamples1", param);
    }
	
	/**
	 * obtainExamples_v3 方法测试
	 * @throws Exception
	 */
	@Test
    public void obtainExamples_v3() throws Exception
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("title","传入值");
        testLoginRequest("/v1/obtainExamples3", param);
    }
	
}
