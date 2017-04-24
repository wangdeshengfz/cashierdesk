package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page;

/**
 * 分页接口
 * @author Poppy Kwoks
 * @dateTime Apr 20, 2014  10:37:40 AM
 */
public interface Paginable {
	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public int getTotalCount();
	/**
	 * 是否获取总记录数
	 * 
	 * @return
	 */
	public boolean isGetTotalCount();

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public int getTotalPage();

	/**
	 * 每页记录数
	 * 
	 * @return
	 */
	public int getPageSize();
	/**
	 * 起始位置
	 * 
	 * @return
	 */
	public int getOffset();
	/**
	 * 读取记录数
	 * 
	 * @return
	 */
	public int getLimit();
	/**
	 * 当前页号
	 * 
	 * @return
	 */
	public int getPageNo();

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage();

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage();

	/**
	 * 返回下页的页号
	 */
	public int getNextPage();

	/**
	 * 返回上页的页号
	 */
	public int getPrePage();
	/**
	 * 设置记录总数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount);
}
