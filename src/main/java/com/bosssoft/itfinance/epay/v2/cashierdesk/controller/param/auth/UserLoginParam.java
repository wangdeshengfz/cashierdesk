package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.auth;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class UserLoginParam {
	
	/**
	 * 用戶名
	 */
	@NotBlank(message = "用户名不能为空！")
	@Length(min = 5, max = 25, message = "用户名必须大于5位，小于25位！")
	private String username;

	/**
	 * 密碼
	 */
	@NotBlank(message = "密码不能为空！")
	@Length(min = 5, max = 32, message = "密码必须大于5位，小于32位！")
	private String password;

	public String getUsername() {
		if(username!=null){
			username = username.trim();
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if(password!=null){
			password = password.trim();
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
