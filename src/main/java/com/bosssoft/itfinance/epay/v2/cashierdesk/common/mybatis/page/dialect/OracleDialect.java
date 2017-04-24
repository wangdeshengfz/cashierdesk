package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect;




/**
 * 
 * @author tony.li
 *
 */

public class OracleDialect extends Dialect {

	public String getLimitString(String sql, int offset, int limit) {

	    String sqlTrim = sql.trim();
		boolean isForUpdate = false;
		if (sqlTrim.toLowerCase().endsWith(" for update")) {
		    sqlTrim = sqlTrim.substring(0, sqlTrim.length() - 11);
			isForUpdate = true;
		}

		StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);
		
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		
		pagingSelect.append(sqlTrim);
		
		pagingSelect.append(" ) row_ ) where rownum_ > "+offset+" and rownum_ <= "+(offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		
		return pagingSelect.toString();
	}

	@Override
	public String getCountSql(String sql) {
		return "select count(1)  from ("+sql+") t";
	}

	@Override
	public String getLimitSql(String sql, int offset, int limit) {
	    String sqlTrim = sql.trim();
		boolean isForUpdate = false;
		if (sqlTrim.toLowerCase().endsWith(" for update")) {
		    sqlTrim = sqlTrim.substring(0, sqlTrim.length() - 11);
			isForUpdate = true;
		}

		StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);
		
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		
		pagingSelect.append(sqlTrim);
		
		pagingSelect.append(" ) row_ ) where rownum_ > "+offset+" and rownum_ <= "+(offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		
		return pagingSelect.toString();
	}
}
