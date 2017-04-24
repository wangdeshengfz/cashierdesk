package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.result.order;


public class OrderTradeInfoResult
{
    /**
     * 标识id，作为一笔订单的页面定位标识。本身不具备业务含义
     */
    private String            payOrderId;
    
	/**
     * 商户的下单日期，格式为yyyyMMdd
     */
    private String            orderDate;
	
    /**
     * 商户名称
     */
    private String            merchantName;
	
    /**
     * 支付金额，以分作为单位
     */
    private int               amt;
	
    /**
     * 交易币种
     */
    private int               currencyCode;
	
    /**
     * 交易标题
     */
    private String            orderTitle;
    
    /**
     * 交易描述
     */
    private String            orderDesc;
    
    /**
     * 发起该订单的会员名称
     */
    private String            memberName;

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	public int getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

}
