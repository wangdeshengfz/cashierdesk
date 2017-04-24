package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect;


public class DB2Dialect extends Dialect{

    public String getLimitSql(String sql, int offset, int limit){

        String sqlTrim = sql.trim();
        StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);

        pagingSelect
                .append("SELECT * FROM (SELECT PAGE_B.*, ROWNUMBER() OVER() AS RN FROM ( ");

        pagingSelect.append(sqlTrim);

        pagingSelect.append(" ) AS PAGE_B )AS PAGE_A WHERE PAGE_A.RN BETWEEN ").append(offset).append(" AND ").append(offset + limit - 1);

        return pagingSelect.toString();
    }

	
	public String getCountSql(String sql) {
		return null;
	}



}