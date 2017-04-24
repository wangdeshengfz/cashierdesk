package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.exception.DeniedException;

public class Session {

	private final static Logger logger = LoggerFactory.getLogger(Session.class.getName());

	private SessionData sessionData = new SessionData();

	protected Map<String, String[]> params = new HashMap<>();

	public Session(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handler2 = (HandlerMethod) handler;
			FireAuthority fireAuthority = handler2.getMethodAnnotation(FireAuthority.class);
			if (fireAuthority != null) {
				if (fireAuthority.loginStatus().equals(LoginStatus.LOGIN)) {
					loginAuth(request, response);
				} else if (fireAuthority.loginStatus().equals(LoginStatus.NO_LOGIN)) {
					logger.info("认证通过！");
				} else {
					throw new DeniedException("权限验证失败！");
				}
			}
		}
	}
	/**
	 * 登录验证
	 * 
	 * @param request
	 * @throws IOException
	 */
	private void loginAuth(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Cookie> cookieMap = saveCookieToMap(request);
		//TokenUtil.clientValidator(request);
	    String token = request.getParameter("token");
	    if(token == null || "".equals(token) || "null".equals(token) || "NULL".equals(token)){
    		Cookie cookie = getCookieByName(cookieMap, "token");
    		if (cookie != null) {
    			token = cookie.getValue();
    		}
	    }
	    
		String channel = request.getParameter("channel");
		if (token == null || token.equals("")) {
			throw new DeniedException("对不起，请先登录！");
		} else {
			SessionData sessionData = TokenUtil.validateToken(token,channel);
			this.sessionData = sessionData;
		}
	}

	public SessionData getUser() {
		return sessionData;
	}

	/**
	 * 从cookies中获取指定名字的cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	private Cookie getCookieByName(Map<String, Cookie> cookieMap, String name) {
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	private Map<String, Cookie> saveCookieToMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

}
