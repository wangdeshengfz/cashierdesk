package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao;

import java.util.LinkedList;
import java.util.List;

/**
 * 查询结果排序类
 */
public class OrderClause {

	/**
	 * 排序语句列表
	 */
	private List<Clause> clauses = new LinkedList<Clause>();
	
	/**
	 * 添加顺序排序
	 * @param propertyName 属性名
	 * @return 当前类对象
	 */
	public OrderClause addAsc(String propertyName) {
		clauses.add(new Clause(propertyName, "asc"));
		return this;
	}

	/**
	 * 添加倒序排序
	 * @param propertyName 属性名
	 * @return 当前类对象
	 */
	public OrderClause addDesc(String propertyName) {
		clauses.add(new Clause(propertyName, "desc"));
		return this;
	}

	/**
	 * 获取排序语句列表
	 * @return 排序语句列表
	 */
	public List<Clause> getClauses() {
		return clauses;
	}

	/**
	 * 排序语句类
	 */
	class Clause {
		String propertyName;
		String orderType;
		
		public Clause(String propertyName, String orderType) {
			this.propertyName = propertyName;
			this.orderType = orderType;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public String getOrderType() {
			return orderType;
		}

		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
	}
	
}
