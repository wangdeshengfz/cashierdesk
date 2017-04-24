package com.bosssoft.itfinance.epay.v2.cashierdesk.common.support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.ICookieService;

@Component
public class CookieService implements ICookieService {

	@Override
	public void saveCookie(HttpServletResponse response, String token) {
		Cookie cookie = new Cookie("token", token);
		//cookie.setMaxAge(TokenUtil.expireTime);
		cookie.setDomain("bosssoft.com");
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@Override
	public void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setValue("");
			response.addCookie(cookie);
		}
	}
}
