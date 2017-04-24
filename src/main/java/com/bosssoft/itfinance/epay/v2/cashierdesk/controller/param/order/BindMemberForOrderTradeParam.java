package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order;

import org.hibernate.validator.constraints.NotBlank;

public class BindMemberForOrderTradeParam {
	
    /**
     * 缴款订单标识id，作为一笔订单的页面定位标识。本身不具备业务含义
     */
	@NotBlank(message = "缴款订单标识id,不能为空")
    private String            payOrderId;

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
    
    /**
     * 会员ID
     */
/*	@NotBlank(message = "会员ID")
    private String            memberId;*/
	
	
    
}
