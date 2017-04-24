package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.channel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ChannelPayReqParam {
	@NotBlank(message = "支付请求号,不能为空")
    private String payReqNo;

	@NotBlank(message = "支付渠道ID,不能为空")
    private String payChannelId;

	@NotBlank(message = "收款账户ID,不能为空")
    private String accountId;

	@NotBlank(message = "下单时间,不能为空")
    private String createDate;

	@NotBlank(message = "下单日期,不能为空")
    private String createTime;

	@NotBlank(message = "订单名称,不能为空")
    private String orderName;

	@NotNull(message = "支付金额,不能为空")
    private Integer amt;

    public String getPayReqNo() {
        return payReqNo;
    }

    public void setPayReqNo(String payReqNo) {
        this.payReqNo = payReqNo;
    }

    public String getPayChannelId() {
        return payChannelId;
    }

    public void setPayChannelId(String payChannelId) {
        this.payChannelId = payChannelId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

	public Integer getAmt() {
		return amt;
	}

	public void setAmt(Integer amt) {
		this.amt = amt;
	}

}
