package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth;


public interface ITokenService {

	/**
	 * 创建会话数据
	 * 
	 * @param username 用户名
	 * @param channel 渠道
	 * @return SessionData
	 */

	SessionData createSessionData(String username, String channel);
	/**
	 * 获取会话数据
	 * 
	 * @param username 用户名
	 * @param channel 渠道
	 * @return SessionData
	 */
	SessionData getSessionData(String username, String channel);

	/**
	 * 从数据库中获取用户信息
	 * @param username 用户名
	 * @return SessionData 
	 */
	SessionData getUserInfoByDB(String username);

	
	/**
	 * 登录认证
	 * @param username 用户名
	 * @param password 密码
	 */
	void login(String username, String password);



}
