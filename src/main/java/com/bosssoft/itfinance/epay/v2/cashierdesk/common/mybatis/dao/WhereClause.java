package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao;

import java.util.List;
import java.util.LinkedList;
import java.util.Collection;

/**
 * 高级查询条件类，可在此类对象中添加多条不同类型的查询条件语句
 */
public class WhereClause {

	/**
	 * 条件语句列表
	 */
	private List<Clause> clauses = new LinkedList<Clause>();
	
	/**
	 * 添加等于条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addEq(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "eq"));
		return this;
	}

	/**
	 * 添加不等于条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addNe(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "ne"));
		return this;
	}

	/**
	 * 添加小于条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addLt(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "lt"));
		return this;
	}

	/**
	 * 添加小于等于条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addLe(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "le"));
		return this;
	}

	/**
	 * 添加大于条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addGt(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "gt"));
		return this;
	}

	/**
	 * 添加大于等于条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addGe(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "ge"));
		return this;
	}

	/**
	 * 添加 like 条件（即like '%关键字%'）
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addLike(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "like"));
		return this;
	}

	/**
	 * 添加左 like 条件（即like '关键字%'）
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addLlike(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "llike"));
		return this;
	}

	/**
	 * 添加右 like 条件（即like '%关键字'）
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addRlike(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "rlike"));
		return this;
	}

	/**
	 * 添加 not like 条件
	 * @param propertyName 属性名
	 * @param propertyValue 属性值
	 * @return 当前类对象
	 */
	public WhereClause addNotLike(String propertyName, Object propertyValue) {
		clauses.add(new Clause(propertyName, propertyValue, "nlike"));
		return this;
	}

	/**
	 * 添加 between 条件
	 * @param propertyName 属性名
	 * @param minPropertyValue 最小属性值
	 * @param maxPropertyValue 最大属性值
	 * @return 当前类对象
	 */
	public WhereClause addBetween(String propertyName, Object minPropertyValue, Object maxPropertyValue) {
		Object[] propertyValues = {minPropertyValue, maxPropertyValue};
		clauses.add(new Clause(propertyName, propertyValues, "between"));
		return this;
	}
	
	/**
	 * 添加 in 条件
	 * @param propertyName 属性名
	 * @param propertyValues 属性值列表
	 * @return 当前类对象
	 */
	public WhereClause addIn(String propertyName, Object[] propertyValues) {
		if (propertyValues == null || propertyValues.length == 0) {
		    return this;
		} 
		clauses.add(new Clause(propertyName, propertyValues, "in"));
		return this;
	}

	/**
	 * 添加 in 条件
	 * @param propertyName 属性名
	 * @param propertyValues 属性值列表
	 * @return 当前类对象
	 */
	public WhereClause addIn(String propertyName, Collection<?> propertyValues) {
		if (propertyValues == null || propertyValues.isEmpty()) {
            return this;
        } 
		clauses.add(new Clause(propertyName, propertyValues.toArray(), "in"));
		return this;
	}

	/**
	 * 添加 not in 条件
	 * @param propertyName 属性名
	 * @param propertyValues 属性值列表
	 * @return 当前类对象
	 */
	public WhereClause addNotIn(String propertyName, Object[] propertyValues) {
		if (propertyValues == null || propertyValues.length == 0) {
            return this;
        } 
		clauses.add(new Clause(propertyName, propertyValues, "nin"));
		return this;
	}

	/**
	 * 添加 not in 条件
	 * @param propertyName 属性名
	 * @param propertyValues 属性值列表
	 * @return 当前类对象
	 */
	public WhereClause addNotIn(String propertyName, Collection<?> propertyValues) {
		if (propertyValues == null || propertyValues.isEmpty()) {
            return this;
        } 
		clauses.add(new Clause(propertyName, propertyValues.toArray(), "nin"));
		return this;
	}

	/**
	 * 获取条件语句列表
	 * @return 条件语句列表
	 */
	public List<Clause> getClauses() {
		return clauses;
	}

	/**
	 * 条件语句类
	 */
	class Clause {
		String propertyName;
		Object propertyValue;
		String operator;
		
		public Clause(String propertyName, Object propertyValue, String operator) {
			this.propertyName = propertyName;
			this.propertyValue = propertyValue;
			this.operator = operator;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public Object getPropertyValue() {
			return propertyValue;
		}

		public void setPropertyValue(Object propertyValue) {
			this.propertyValue = propertyValue;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}
	}
	
}

