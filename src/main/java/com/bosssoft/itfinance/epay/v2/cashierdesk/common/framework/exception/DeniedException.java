package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.exception;

/**
 * 权限异常
 * 
 * @author wangml
 *
 */
public class DeniedException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public DeniedException()
	{
	}

	public DeniedException(String message)
	{
		super(message);
	}

	public DeniedException(Throwable cause)
	{
		super(cause);
	}

	public DeniedException(String message, Throwable cause)
	{
		super(message, cause);
	}

}