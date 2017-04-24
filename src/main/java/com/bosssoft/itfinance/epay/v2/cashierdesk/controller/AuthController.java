package com.bosssoft.itfinance.epay.v2.cashierdesk.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.FireAuthority;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.ICookieService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.ITokenService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.LoginStatus;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.TokenUtil;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core.MsgResult;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.auth.UserLoginParam;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;

@RestController
@Description("授权认证控制器")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	

	@Autowired
	private ITokenService tokenService;
	@Autowired
	private ICookieService cookieService;
    
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 * @throws BaseServiceException
	 */
    @RequestMapping("/auth/asscessToken")
	@ResponseBody
	public MsgResult asscessToken(HttpServletRequest request,
			HttpServletResponse response, @Valid UserLoginParam param)
			throws BaseException {
    	TokenUtil.clientValidator(request);
 		tokenService.login(param.getUsername(), param.getPassword());
		String channel = request.getParameter("channel");
		String token = null;
/*		if(FrameworkConfig.isDebug()){
			SessionData data=tokenService.getSessionData(param.getUserName(),channel);
			if(data!=null){
			    token = data.getAccessToken();
			    try {
					if(token!=null&&Long.parseLong(token.split(":")[1])<System.currentTimeMillis()){
						token=null;
					}
				} catch (NumberFormatException e) {
					logger.error("登录时候token时间戳格式化错误!");
					token=null;
				}
			}
		}*/
		if(token == null || "".equals(token.trim())){
		    token = tokenService.createSessionData(param.getUsername(),channel).getAccessToken();
		}
		cookieService.saveCookie(response, token);
		
		logger.info("登录成功="+token);
		return new MsgResult(token);
	}
	
    /**
     * 登出
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
	@RequestMapping("/auth/logout")
	@FireAuthority(loginStatus = LoginStatus.LOGIN)
	@ResponseBody
	public MsgResult logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		cookieService.clearCookie(request, response);
		return new MsgResult();
	}
	
	/**
	 * 验证是否登录
	 * @param response
	 * @throws IOException
	 */
    @RequestMapping("/auth/asscessCheck")
    @FireAuthority(loginStatus = LoginStatus.LOGIN)
    @ResponseBody
    public void asscessCheck(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().append("").flush();
    }
	
}
