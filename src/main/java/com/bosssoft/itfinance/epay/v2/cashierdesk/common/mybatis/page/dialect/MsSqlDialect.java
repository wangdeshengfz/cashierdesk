package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect;



public class MsSqlDialect extends Dialect{
	
	public String getCountSql(String sql) {

		Integer offset=0;
		Integer limit=2147483647;
		String sqlTrim = sql.trim();
		String order = ""; 
		StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);
		pagingSelect.append("select count(1) from (select row_number()over(order by tempcolumn)temprownumber,* ")
				.append(" from (select top ").append(offset + limit).append(" tempcolumn=0,* from ( ");
		
		sqlTrim=sqlTrim.replace('\r', ' ').replace('\n', ' ').replaceAll("\\s{2,}", " ");
		// 暂不支持多排序结果合并查询 
		// 判断是否order....结尾, 分页中排序需要拆分sql
		if (sqlTrim.toLowerCase().matches(".+ order [^)]+$")) {
			order = sqlTrim.substring(sqlTrim.lastIndexOf(" order "));
			sqlTrim = sqlTrim.substring(0, sqlTrim.lastIndexOf(" order "));
		}

		pagingSelect.append(sqlTrim);
		pagingSelect.append(" ) d_page ").append(order).append(
				" ) t_page )tt_page where temprownumber>").append(offset);


		return pagingSelect.toString();
	}

	public String getLimitSql(String sql, int offset, int limit) {
	    String sqlTrim = sql.trim();
		String order = "";
		StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);
		pagingSelect.append("select * from (select row_number()over(order by tempcolumn)temprownumber,* ")
				.append(" from (select top ").append(offset + limit).append(" tempcolumn=0,* from ( ");

		
		sqlTrim=sqlTrim.replace('\r', ' ').replace('\n', ' ').replaceAll("\\s{2,}", " ");
		// 暂不支持多排序结果合并查询
		// 判断是否order....结尾, 分页中排序需要拆分sql
		if (sqlTrim.toLowerCase().matches(".+ order [^)]+$")) {
			order = sqlTrim.substring(sqlTrim.lastIndexOf(" order "));
			sqlTrim = sqlTrim.substring(0, sqlTrim.lastIndexOf(" order "));
		}

		pagingSelect.append(sqlTrim);
		pagingSelect.append(" ) d_page ").append(order).append(
				" ) t_page )tt_page where temprownumber>").append(offset);

		return pagingSelect.toString();
	}

	
	public String getLimitString(String sql, int skipResults, int maxResults) {
		return null;
	}
	
	

}
