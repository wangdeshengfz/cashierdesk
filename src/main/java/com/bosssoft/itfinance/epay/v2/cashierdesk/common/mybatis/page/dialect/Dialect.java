package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect;


public abstract class Dialect {

	public static enum Type{
		MYSQL,
		ORACLE
	}


	public abstract String getLimitSql(String sql, int offset, int limit);
	public abstract String getCountSql(String sql);
	
}
