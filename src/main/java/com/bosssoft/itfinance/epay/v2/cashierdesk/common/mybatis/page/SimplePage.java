package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page;

import org.apache.ibatis.session.RowBounds;

/**
 * 简单分页类
 * @author Poppy Kwoks
 * @dateTime Apr 20, 2014  10:30:03 AM
 */
public class SimplePage extends RowBounds implements Paginable {

	/**
	 * 默认记录数---支持全部查询
	 */
	public static final int DEF_COUNT = Integer.MAX_VALUE;


	protected Integer totalCount = 0;
	protected Integer spageSize = 20;
	protected Integer pageNo = 1;
	private Integer poffset;
	
	boolean getTotalCount=true;

	/**
	 * 手动设置起始位置
	 * @param pageoffset
	 */
	public void setOffset(Integer offset) {
		this.poffset=offset;
	}
	/**
	 * 设置读取总数
	 * @param limit
	 */
	public void setLimit(Integer limit) {
		this.spageSize=limit;
	}
	
	/**
	 * 起始位置
	 */
	public int getOffset() {
		return poffset!=null?poffset:(pageNo-1)*spageSize;
	}
	/**
	 * 读取记录数
	 */
	public int getLimit() {
		return spageSize;
	}
	
	/**
	 * 检查页码 checkPageNo
	 * 
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int cpn(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}

	public SimplePage() {
		
	}

	/**
	 * @param pageNo 页码
	 * @param pageSize 每页几条数据
	 */
	public SimplePage(int pageNo, int pageSize) {
		setPageSize(pageSize);
		setPageNo(pageNo);
	}
	
	/**
	 * @param pageNo 页码
	 * @param pageSize 每页几条数据
	 * @param totalCount 总共几条数据
	 */
	public SimplePage(int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	/**
	 * 调整页码，使不超过最大页数
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}

	/**
	 * 获得页码
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 每页几条数据
	 */
	public int getPageSize() {
		return spageSize;
	}

	/**
	 * 总共几条数据
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 总共几页
	 */
	public int getTotalPage() {
		int totalPage = this.totalCount / spageSize;
		if (this.totalCount % spageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	/**
	 * 是否第一页
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * 是否最后一页
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	/**
	 * 下一页页码
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 上一页页码
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * if totalCount<0 then totalCount=0
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	/**
	 * if pageSize< 1 then pageSize=DEF_COUNT
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.spageSize = DEF_COUNT;
		} else {
			this.spageSize = pageSize;
		}
	}

	/**
	 * if pageNo < 1 then pageNo=1
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}
	
	public boolean isGetTotalCount() {
		return getTotalCount;
	}
	public SimplePage isGetTotalCount(boolean getTotalCount) {
		this.getTotalCount = getTotalCount;
		return this;
	}

}
