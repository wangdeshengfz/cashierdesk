package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.dialect.Dialect;

/**
 * 物理分页设置
 * 
 * @author Poppy Kwoks
 * @dateTime Apr 20, 2014 1:51:25 PM
 */
public class PaginationInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

    Dialect dialect;

    public Object plugin(Object target) {
        if (dialect != null) {
            if (target != null && target instanceof org.apache.ibatis.executor.statement.RoutingStatementHandler) {
	            RoutingStatementHandler statementHandler = (RoutingStatementHandler) target;
                BoundSql boundSql = statementHandler.getBoundSql();
                MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(),
		                new DefaultObjectWrapperFactory(),new DefaultReflectorFactory());
                RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
                if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
                    return target;
                }
                Paginable paginable = (Paginable) rowBounds;
                // 是否获取记录总数
                if (paginable.isGetTotalCount()) {
                    Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
                    try {
	                    String countSql = dialect.getCountSql(boundSql.getSql());
                        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
                        BoundSql countBoundSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(),boundSql.getParameterObject());
	                    //复制BoundSql内的AdditionalParameter对象
	                    for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
	                    	if(parameterMapping.getMode() != ParameterMode.OUT) {//非输出模式参数
			                    String propertyName = parameterMapping.getProperty();
			                    if (boundSql.hasAdditionalParameter(propertyName)) {
				                    countBoundSql.setAdditionalParameter(propertyName, boundSql.getAdditionalParameter(propertyName));
			                    }
		                    }
	                    }
	                    ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
                        // 通过connection建立一个countSql对应的PreparedStatement对象。
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        if (logger.isDebugEnabled()) {
                            logger.debug(countSql.replaceAll("\n", ""));
                        }
                        Connection conn = configuration.getEnvironment().getDataSource().getConnection();
                        try {
                            pstmt = conn.prepareStatement(countSql);
                            // 通过parameterHandler给PreparedStatement对象设置参数
                            parameterHandler.setParameters(pstmt);
                            // 之后就是执行获取总记录数的Sql语句和获取结果了。
                            rs = pstmt.executeQuery();
                            if (rs.next()) {
                                paginable.setTotalCount(rs.getInt(1));
                            }
                        } catch (SQLException e) {
                            logger.error("PaginationInterceptor->执行sql异常：", e);
                        } finally {
                            try {
                                if (rs != null)
                                    rs.close();
                                if (pstmt != null)
                                    pstmt.close();
                                if (conn != null)
                                    conn.close();
                            } catch (SQLException e) {
                                logger.error("PaginationInterceptor->关闭连接异常：", e);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("PaginationInterceptor->plugin()", e);
                    }
                }
                // 设置分页
                metaStatementHandler.setValue("delegate.boundSql.sql",dialect.getLimitSql(boundSql.getSql(), paginable.getOffset(), paginable.getLimit()));
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", paginable.getLimit());
            }
        }
        return target;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }


    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }


    public void setProperties(Properties properties) {

    }

}
