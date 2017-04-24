package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect;


public class Mssql12Dialect extends Dialect{

	public String getCountSql(String sql) {
		return "select count(1) from ("+sql+") t";
	}

	public String getLimitSql(String sql, int offset, int limit) {
	    String sqlTrim = sql.trim();
        
        StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);

        pagingSelect.append(sqlTrim);
        pagingSelect.append(" OFFSET " ).append(offset).append(" ROW FETCH NEXT ").append(limit).append(" ROWS ONLY ");

        return pagingSelect.toString();
	}

}