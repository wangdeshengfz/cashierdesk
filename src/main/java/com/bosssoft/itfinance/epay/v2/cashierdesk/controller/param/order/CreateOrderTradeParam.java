package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CreateOrderTradeParam {
	/**
     * 业务订单号，在一个商户id下唯一，由业务系统传入
     */
	@NotBlank(message = "业务订单号不能为空")
    private String            bussnessTradeNo;
    
	/**
     * 订单详情
     */
	@NotBlank(message = "订单详情不能为空")
    private String            orderDetail;
    
	/**
     * 商户的下单日期，格式为yyyyMMdd
     */
	@NotBlank(message = "下单日期不能为空")
    private String            orderDate;
    
	/**
     * 商户的下单时间，格式为HHmmss
     */
	@NotBlank(message = "下单时间不能为空")
    private String            orderTime;
    
	/**
     * 商户id
     */
	@NotBlank(message = "商户id不能为空")
    private String            merchantId;
    
	/**
     * 商户名称
     */
	@NotBlank(message = "商户名称不能为空")
    private String            merchantName;
   
	/**
     * 支付金额，以分作为单位
     */
	@NotNull(message = "支付金额不能为空")
    private Integer               amt;
    
	/**
     * 交易币种
     */
	@NotNull(message = "交易币种不能为空")
    private Integer               currencyCode;
    
	/**
     * 交易标题
     */
	@NotBlank(message = "交易标题不能为空")
    private String            orderTitle;
    
	/**
     * 交易描述
     */
	@NotBlank(message = "交易描述不能为空")
    private String            orderDesc;
    
	/**
     * 下单的用户代理信息
     */
	@NotBlank(message = "下单的用户代理信息不能为空")
    private String            agent;
    
	/**
     * 下单的用户代理ip
     */
	@NotBlank(message = "下单的用户代理ip不能为空")
    private String            agentIp;
    
/*	*//**
     * 发起该订单的会员id
     *//*
	@NotBlank(message = "发起该订单的会员id不能为空")
    private String            memberId;
    
	*//**
     * 发起该订单的会员名称
     *//*
	@NotBlank(message = "发起该订单的会员名称不能为空")
    private String            memberName;*/
       
	/**
     * 支付成功后回调的业务应用的url
     */
	@NotBlank(message = "支付成功后回调的业务应用的url不能为空")
    private String            returnUrl;
    
	/**
     * 支付成功后异步通知业务应用的url
     */
	@NotBlank(message = "支付成功后异步通知业务应用的url 不能为空")
    private String            notifyUrl;
   
	/**
     * 指定的收款账户id，不指定时为空
     */
    private String            assignAccountId;
    
    /**
     * 商户签名数据
     */
    @NotBlank(message = "商户签名数据  不能为空")
    private String            sign;
    
	public String getBussnessTradeNo() {
		return bussnessTradeNo;
	}
	public void setBussnessTradeNo(String bussnessTradeNo) {
		this.bussnessTradeNo = bussnessTradeNo;
	}
	public String getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getAgentIp() {
		return agentIp;
	}
	public void setAgentIp(String agentIp) {
		this.agentIp = agentIp;
	}
	/*public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}*/
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getAssignAccountId() {
		return assignAccountId;
	}
	public void setAssignAccountId(String assignAccountId) {
		this.assignAccountId = assignAccountId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
    
}
