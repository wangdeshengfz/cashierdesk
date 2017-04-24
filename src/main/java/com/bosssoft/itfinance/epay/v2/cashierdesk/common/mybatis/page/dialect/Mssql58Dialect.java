package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect;


public class Mssql58Dialect extends Dialect{
	
	public String getCountSql(String sql) {
		int offset=0;
		int limit=Integer.MAX_VALUE;
        String sqlTrim = sql.trim();
        String order = "";
       
        StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);

        pagingSelect
                .append("select count(1) from (select row_number()over(order by tempcolumn)temprownumber,* ")
                .append(" from (select top ").append(offset + limit - 1).append(" tempcolumn=0,* from ( ");
        
        // 暂不支持多排序结果合并查询
        // 判断是否order....结尾, 分页中排序需要拆分sql
        if (sqlTrim.toLowerCase().matches(".+order[^)]+$")){
            order = sqlTrim.substring(sqlTrim.lastIndexOf("order"));
            sqlTrim = sqlTrim.substring(0, sqlTrim.lastIndexOf("order"));
        }
        pagingSelect.append(sqlTrim);
        pagingSelect.append(" ) d " ).append(order).append(" ) t )tt where temprownumber>=").append(offset);

        return pagingSelect.toString();
	}

	
	public String getLimitSql(String sql, int offset, int limit) {
	    String sqlTrim = sql.trim();
        String order = "";
       
        StringBuilder pagingSelect = new StringBuilder(sqlTrim.length() + 100);

        pagingSelect
                .append("select * from (select row_number()over(order by tempcolumn)temprownumber,* ")
                .append(" from (select top ").append(offset + limit - 1).append(" tempcolumn=0,* from ( ");
        
        // 暂不支持多排序结果合并查询
        // 判断是否order....结尾, 分页中排序需要拆分sql
        if (sqlTrim.toLowerCase().matches(".+order[^)]+$")){
            order = sqlTrim.substring(sqlTrim.lastIndexOf("order"));
            sqlTrim = sqlTrim.substring(0, sqlTrim.lastIndexOf("order"));
        }
        pagingSelect.append(sqlTrim);
        pagingSelect.append(" ) d " ).append(order).append(" ) t )tt where temprownumber>=").append(offset);

        return pagingSelect.toString();
	}

	
	public String getLimitString(String sql, int skipResults, int maxResults) {
		return null;
	}

}