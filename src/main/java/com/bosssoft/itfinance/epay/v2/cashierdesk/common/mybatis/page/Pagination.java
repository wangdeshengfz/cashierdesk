package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page;

import java.io.Serializable;
import java.util.List;

/**
 * 列表分页。包含list属性
 * @author Poppy Kwoks
 * @dateTime Apr 20, 2014  10:59:51 AM
 */
public class Pagination extends SimplePage implements java.io.Serializable,
		Paginable {

	private static final long serialVersionUID = 1L;
	/**
     * 当前页的数据
     */
    private List<?> list;

	public Pagination() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 */
	public Pagination(int pageNo, int pageSize) {
		super(pageNo, pageSize);
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param list
	 *            分页内容
	 */

	public Pagination(int pageNo, int pageSize, List<? extends Serializable> list) {
		super(pageNo, pageSize);
		this.list = list;
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(int pageNo, int pageSize, int totalCount, List<? extends Serializable> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * spageSize;
	}

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Pagination isGetTotalCount(boolean getTotalCount) {
		this.getTotalCount = getTotalCount;
		return this;
	}
}
