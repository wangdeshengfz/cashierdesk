package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CreatePayOrderParam {
	
    /**
     * 缴款订单标识id，作为一笔订单的页面定位标识。本身不具备业务含义
     */
	@NotBlank(message = "缴款订单标识id,不能为空")
    private String            payOrderId;
	
    /**
     * 支付渠道id
     */
	@NotBlank(message = "支付渠道id,不能为空")
    private String            payChannelId;
   
	/**
     * 支付渠道版本
     */
	@NotNull(message = "支付渠道版本,不能为空")
    private int               payChannelVersion;
   
	/**
     * 收款账户id
     */
	@NotBlank(message = "收款账户id,不能为空")
    private String            accountId;
    
	/**
     * 收款账户名称
     */
	@NotBlank(message = "收款账户名称,不能为空")
    private String            accountName;

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getPayChannelId() {
		return payChannelId;
	}

	public void setPayChannelId(String payChannelId) {
		this.payChannelId = payChannelId;
	}

	public int getPayChannelVersion() {
		return payChannelVersion;
	}

	public void setPayChannelVersion(int payChannelVersion) {
		this.payChannelVersion = payChannelVersion;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}
