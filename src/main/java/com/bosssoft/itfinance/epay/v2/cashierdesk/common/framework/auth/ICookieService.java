package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICookieService {

	/**
	 * 登录秒杀系统，写入cookie到客户端。
	 * 
	 * @param response HttpServletResponse
	 * @param token 令牌
	 */
	public void saveCookie(HttpServletResponse response, String token);
	
	
	public void clearCookie(HttpServletRequest request,HttpServletResponse response);

}
