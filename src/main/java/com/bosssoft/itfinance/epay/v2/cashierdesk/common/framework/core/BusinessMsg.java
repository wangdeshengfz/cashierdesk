package com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core;

public class BusinessMsg {

	public BusinessMsg() {

	}

	public BusinessMsg(String businessCode, String businessNote) {
		this.businessCode = businessCode;
		this.businessNote = businessNote;
	}

	private String businessCode = "0";

	private String businessNote = "Success";

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessNote() {
		return businessNote;
	}

	public void setBusinessNote(String businessNote) {
		this.businessNote = businessNote;
	}

}
