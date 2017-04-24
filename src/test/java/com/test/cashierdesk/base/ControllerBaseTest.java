package com.test.cashierdesk.base;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Map;
import javax.servlet.http.Cookie;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 控制器层测试
 * @author wangml
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-servlet.xml"})
@WebAppConfiguration
public abstract class ControllerBaseTest
{
	@Autowired
	private WebApplicationContext wac;

	public MockMvc mockMvc;

	@Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	public void testLoginRequest(String request, Map<String, String> params) throws Exception
	{
		MockHttpServletRequestBuilder tokenRequest = get("/auth/asscessToken");
		tokenRequest.param("channel", "pc");
		tokenRequest.param("version", "v1.0");
		if (params != null)
		{
			for (String param : params.keySet())
			{
				tokenRequest.param(param, params.get(param));
			}
		}
		tokenRequest.param("username", "wangml");
		tokenRequest.param("password", DigestUtils.md5Hex("123456"));
		
		MvcResult mvcResult = this.mockMvc.perform(tokenRequest).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String json = response.getContentAsString();
		JSONObject jsonObject = JSON.parseObject(json);
		String tokenStr = jsonObject.getString("data");
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get(request);
		mockHttpServletRequestBuilder.param("channel", "pc");
		mockHttpServletRequestBuilder.param("version", "v1.0");
		Cookie token = new Cookie("token",tokenStr);
		mockHttpServletRequestBuilder.cookie(token);
		if (params != null)
		{
			for (String param : params.keySet())
			{
				mockHttpServletRequestBuilder.param(param, params.get(param));
			}
		}
		mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
		System.out.println("==================结果=======================");
		response = mvcResult.getResponse();
		json = response.getContentAsString();
		System.out.println(json);
		jsonObject = JSON.parseObject(json);
		int code = jsonObject.getIntValue("code");
		System.out.println("=============================================");
		org.junit.Assert.assertNotEquals(500, code);

	}

	public void testTokenRequest(String request, Map<String, String> params) throws Exception
	{
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get(request);
		mockHttpServletRequestBuilder.param("channel", "pc");
		mockHttpServletRequestBuilder.param("version", "v1.0");
		if (params != null)
		{
			for (String param : params.keySet())
			{
				mockHttpServletRequestBuilder.param(param, params.get(param));
			}
		}
		MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
		System.out.println("==================结果=======================");
		MockHttpServletResponse response = mvcResult.getResponse();
		String json = response.getContentAsString();
		System.out.println(json);
		JSONObject jsonObject = JSON.parseObject(json);
		int code = jsonObject.getIntValue("code");
		System.out.println("=============================================");
		org.junit.Assert.assertNotEquals(500, code);
	}

	public void testRequest(String request, Map<String, String> params) throws Exception
	{
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get(request);

		if (params != null)
		{
			for (String param : params.keySet())
			{
				mockHttpServletRequestBuilder.param(param, params.get(param));
			}
		}
		MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
		System.out.println("==================结果=======================");
		MockHttpServletResponse response = mvcResult.getResponse();
		String json = response.getContentAsString();
		System.out.println(json);
		JSONObject jsonObject = JSON.parseObject(json);
		int code = jsonObject.getIntValue("code");
		System.out.println("=============================================");
		org.junit.Assert.assertNotEquals(500, code);
	}

	/**
	 * 返回状态码
	 * @param request
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String testRequestReturnResult(String request, Map<String, String> params) throws Exception
	{
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get(request);
		if (params != null)
		{
			for (String param : params.keySet())
			{
				mockHttpServletRequestBuilder.param(param, params.get(param));
			}
		}
		MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
		System.out.println("=================结果========================");
		MockHttpServletResponse response = mvcResult.getResponse();
		String json = response.getContentAsString();
		System.out.println(json);
		System.out.println("=========================================");
		return json;
	}

}
