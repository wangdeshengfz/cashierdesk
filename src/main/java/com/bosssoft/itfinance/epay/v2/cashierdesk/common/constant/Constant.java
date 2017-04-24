package com.bosssoft.itfinance.epay.v2.cashierdesk.common.constant;

public class Constant {

	/**
	 * 银行支付完成后，渠道服务回调收银台url,订单服务添加入参 ?payOrderId=xxxx
	 */
	public static final String CASHIERDESK_RETURN_URL = "http://www.bosssoft.com/epay/v2/cashierdesk/paystatus.html";
	
	/**
	 * 生成缴款订单后，跳转到收银台url,代码中动态添加 ?order_id=123456789
	 */
	public static final String CASHIERDESK_MAIN_URL = "http://localhost:9091/cashierdesk/static/html/index.html";

	/**
	 *	币种-人民币
	 */
	public static final int CURRENCY_CODE_RMB = 1;
	
	/**
	 *	商户缴费签名类型-MD5 
	 */
	public static final int MERCHANT_SIGN_MD5 = 1;
	/**
	 *	商户缴费签名类型-RSA 
	 */
	public static final int MERCHANT_SIGN_RSA = 2;
}
