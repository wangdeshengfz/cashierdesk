package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth;


public interface IUserVoService {

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username 用户名
	 * @return UserVo
	 */
	public UserVo getUserVoByUsername(String username);

	/**
	 * 验证用户账号信息是否正确
	 * 
	 * @param username 用户名
	 * @param password 密码
	 */
	public void loginValid(String username, String password);

	/**
	 * 通过用户名获取用户信息
	 * @param userId  用户id
	 * @return UserVo
	 */
	public UserVo getUserVoByUserId(long userId);
}
