package com.bosssoft.itfinance.epay.v2.cashierdesk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.FireAuthority;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.ICookieService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.ITokenService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.LoginStatus;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core.MsgResult;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.demo.ExampleParam_v1;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.demo.ExampleParam_v2;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;

@Controller
public class ExampleController
{
	@Autowired
	private ITokenService tokenService;
	@Autowired
	private ICookieService cookieService;
	
	/**
	 * 无需登录，无入参验证方法
	 * @param msg
	 * @return
	 */
	@RequestMapping("/v1/obtainExamples1")
	@ResponseBody
	public MsgResult obtainExamples_v1(String msg)
	{
		return new MsgResult("服务端接收到此入参，入参 msg="+msg);
	}
	
	/**
	 * 无需登录有入参验证方法
	 * @param example
	 * @return
	 */
	@RequestMapping("/v1/obtainExamples2")
	@ResponseBody
	public MsgResult obtainExamples_v2(@Valid ExampleParam_v1 example)
	{
		List<ExampleParam_v1> list = new ArrayList<>();
		example.setId(1);
		example.setTitle("测试");
		example.setContent("想怎么玩怎么忘！");
		example.setImageUrl("http://img1.imgtn.bdimg.com/it/u=441809964,2415329917&fm=21&gp=0.jpg");
		list.add(example);
		return new MsgResult(list);
	}
	
	/**
	 * 需要登录且有验证方法
	 * @param example
	 * @return
	 * @throws BaseServiceException
	 */
 	@RequestMapping("/v1/obtainExamples3")
 	@FireAuthority(loginStatus=LoginStatus.LOGIN)
	@ResponseBody
	public MsgResult obtainExamples_v3(@Valid ExampleParam_v2 example)
			throws BaseException {
		return new MsgResult(example);
	}
 	
	/**
	 * 无需登录，无入参验证方法
	 * @param msg
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/v1/obtainExamples4")
	@ResponseBody
	public void obtainExamples_v4(HttpServletRequest request, HttpServletResponse response,@Valid ExampleParam_v2 example) throws IOException
	{
		String url = "http://www.baidu.com";
		response.sendRedirect(url); 
	}
	
	
/*	@RequestMapping("/v2/obtainExamples")
	@FireAuthority(loginStatus=LoginStatus.LOGIN)
	@ResponseBody*/
/*	public MsgResult obtainExamples_v2(@Valid ExampleParam_v2 example)
	{
		SessionData session = AppContext.getSession().getUser();
		System.out.println(session.getUsername());
		System.out.println(session.getPassword());
		List<ExampleParam_v2> list = new ArrayList<>();
		example.setId(1);
		list.add(example);
		return new MsgResult(list);
	}*/
	
   /* @RequestMapping("/v1/obtainExamples_v3")
    @ResponseBody
    public MsgResult obtainExamples_v3(@Valid ExampleParam_v3 example)
    {
        ExampleParam_v3 rst = new ExampleParam_v3();
        rst.setTitle("service ok " + example.getTitle());
        return new MsgResult(rst);
    }
    
    @RequestMapping("/v1/testRedis")
    @ResponseBody
    public MsgResult obtainExamples_v4(@Valid ExampleParam_v3 example)
    {
        JedisUtil.set("wang", "zhangsan");
        String s = String.valueOf(JedisUtil.get("wang"));
        System.out.println("sssssssssssss="+s);
        
        
        Long n = JedisUtil.incr("TT");
        JedisUtil.expire("TT", 10);
        Long sysj = JedisUtil.pttl("TT");
        
        return new MsgResult(sysj);
    }
    *//**
	 * 图片上传
	 * @param file
	 * @throws Exception
	 * 接收参数file值的一个基本格式"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAdiUlEQVR"
	 * Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryUJ22CrdVuZd8Ye2s
	 *//*
	@RequestMapping({"/seller/fexUpload.htm"})
	@FireAuthority(loginStatus=LoginStatus.ACCESSAUTH)
	@ResponseBody
	public MsgResult fexUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(file!=null){			
//			FastDFSFile fastDSFile = FastDFSFileUtil.saveFileToServer(file);
			FastDFSFile fastDSFile = ImageSimpleUploadUtils.saveFileToServer(
					file.getBytes()
					,new ImageSimpleScale(ImageSimpleScale.Scale._small)
					,new ImageSimpleScale(ImageSimpleScale.Scale._middle)
					,new ImageSimpleScale(ImageSimpleScale.Scale._big)
					);
			return new MsgResult(fastDSFile);
		}else{
			return new MsgResult();
		}
	}*/
	
}
