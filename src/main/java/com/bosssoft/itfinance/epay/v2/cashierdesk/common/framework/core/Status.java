package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core;

public enum Status
{

	/**
	 * 成功
	 */
	OK("200"),
	/**
	 * 业务异常
	 */
	BusinessException("400"),

	/**
	 * 权限异常
	 */
	DeniedException("401"),

	/**
	 * 服务端异常
	 */
	ServerException("500");

	private String code;

	private Status(String code)
	{
		this.code = code;
	}

	@Override
	public String toString()
	{
		return code;
	}

}
