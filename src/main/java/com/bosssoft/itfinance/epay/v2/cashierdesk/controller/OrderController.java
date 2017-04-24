package com.bosssoft.itfinance.epay.v2.cashierdesk.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.constant.Constant;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.enums.ErrorCode;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.exception.CashierdeskException;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.FireAuthority;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.LoginStatus;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core.MsgResult;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.BeanConvertUtil;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.JedisUtil;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.SignUtils;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order.BindMemberForOrderTradeParam;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order.CreateOrderTradeParam;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order.CreatePayOrderParam;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.result.order.OrderTradeInfoResult;
import com.bosssoft.itfinance.epay.v2.channel.common.api.ChannelTradeOperation;
import com.bosssoft.itfinance.epay.v2.channel.common.entity.ChannelPayReq;
import com.bosssoft.itfinance.epay.v2.channel.common.entity.ChannelPayResp;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;
import com.bosssoft.itfinance.epay.v2.trade.common.api.OrderTradeOperation;
import com.bosssoft.itfinance.epay.v2.trade.common.api.PayTradeOperation;
import com.bosssoft.itfinance.epay.v2.trade.common.po.OrderTradeParam;
import com.bosssoft.itfinance.epay.v2.trade.common.po.OrderTradeResult;
import com.bosssoft.itfinance.epay.v2.trade.common.po.PayTradeParam;
import com.bosssoft.itfinance.epay.v2.trade.common.po.PayTradeResult;

@Controller
@RequestMapping("/order")
public class OrderController
{
	@Autowired
	private OrderTradeOperation orderTradeOperation;
	@Autowired
	private PayTradeOperation payTradeOperation;
	@Autowired
	private ChannelTradeOperation channelTradeOperation;
	
	private static final String CACHE_PAYORDERID_PREFIX = "PAYORDERID_";
	private static final int CACHE_PAYORDERID_TIMEOUT_SECOND = 300;
	
	
	/**
	 * 接收商户缴费申请，生成缴费订单(调用成功跳转到收银台)
	 * @param preateOrderTradeParam
	 * @return
	 * @throws IOException 
	 * @throws BaseServiceException 
	 */
	@RequestMapping("/v1/createOrderTrade")
	@ResponseBody
	public void createOrderTrade(HttpServletResponse response,
			@Valid CreateOrderTradeParam preateOrderTradeParam) throws BaseException, IOException
	{
		//1 入参验证
		//1.1 商户签名验签
		//String MD5KEY_TEST = "testMD5Key";
		//validMerchantSign(preateOrderTradeParam,Constant.MERCHANT_SIGN_MD5,MD5KEY_TEST);
		
		//System.out.println("======"+BeanConvertUtil.Obj2String(preateOrderTradeParam));
		
		//2 调用订单服务，创建缴款订单 
		OrderTradeParam orderTradeParam = BeanConvertUtil.beanConvert(preateOrderTradeParam,OrderTradeParam.class);
		orderTradeParam.setCashierDeskReturnUrl(Constant.CASHIERDESK_RETURN_URL);
		OrderTradeResult rst = orderTradeOperation.createOrderTrade(orderTradeParam);
		
		System.out.println("出参======"+BeanConvertUtil.Obj2String(rst));
		
		//3  跳转到收银台
		String urlParamStr = "?order_id=" + rst.getPayOrderId();
		String url = Constant.CASHIERDESK_MAIN_URL + urlParamStr;
		response.sendRedirect(url);
	}
	
	/**
	 * 接收商户缴费申请，生成缴费订单(返回执行结果串)
	 * @param preateOrderTradeParam
	 * @return
	 * @throws BaseServiceException 
	 */
	@RequestMapping("/v1/createOrderTradeForJson")
	@ResponseBody
	public MsgResult createOrderTradeForJson(@Valid CreateOrderTradeParam preateOrderTradeParam) 
			throws BaseException
	{
		//1 入参验证
		//1.1 商户签名验签
		//String MD5KEY_TEST = "testMD5Key";
		//validMerchantSign(preateOrderTradeParam,Constant.MERCHANT_SIGN_MD5,MD5KEY_TEST);
		
		//2 调用订单服务，创建缴款订单 
		OrderTradeParam orderTradeParam = BeanConvertUtil.beanConvert(preateOrderTradeParam,OrderTradeParam.class);
		orderTradeParam.setCashierDeskReturnUrl(Constant.CASHIERDESK_RETURN_URL);
		OrderTradeResult rst = orderTradeOperation.createOrderTrade(orderTradeParam);
		
		//3 返回缴款订单ID
		String payOrderId = rst.getPayOrderId();
		return new MsgResult(payOrderId);
	}
	
	/**
	 * 查询缴款订单信息
	 * @param preateOrderTradeParam
	 * @return
	 * @throws IOException 
	 * @throws BaseServiceException 
	 */
	@RequestMapping("/v1/queryOrderTrade")
	@ResponseBody
	public MsgResult queryOrderTrade(String payOrderId) throws BaseException{
		OrderTradeResult orderTradeResult = orderTradeOperation.queryByPayOrderId(payOrderId);
		OrderTradeInfoResult otir = BeanConvertUtil.beanConvert(orderTradeResult, OrderTradeInfoResult.class);
		return new MsgResult(otir);
	}
	
	/**
	 * 缴款订单绑定会员
	 * @param bindMemberForOrderTradeParam
	 * @return
	 * @throws BaseServiceException 
	 */
	@RequestMapping("/v1/bindMemberForOrderTrade")
 	@FireAuthority(loginStatus=LoginStatus.LOGIN)
	@ResponseBody
	public MsgResult bindMemberForOrderTrade(@Valid BindMemberForOrderTradeParam bindMemberForOrderTradeParam) 
			throws BaseException
	{
		String memberId = "5003333";//从会话获取会员ID信息
		
		String orderTradeNo = getOrderTradeNo(bindMemberForOrderTradeParam.getPayOrderId());
		if(orderTradeNo == null){
			throw new CashierdeskException(ErrorCode.ORDER_ORDERTRADENO_NOTEXISTS);
		}
		orderTradeOperation.bindMember(memberId, orderTradeNo);
		return new MsgResult();
	}
	
	/**
	 * 创建支付订单并调用渠道服务，获取银行网关连接和参数
	 * @param createPayOrderParam
	 * @return
	 * @throws BaseServiceException 
	 */
	@RequestMapping("/v1/createPayOrder")
 	@FireAuthority(loginStatus=LoginStatus.LOGIN)
	@ResponseBody
	public MsgResult createPayOrder(@Valid CreatePayOrderParam createPayOrderParam) 
			throws BaseException
	{
		//1 通过缴款订单标识ID获取缴款订单信息
		OrderTradeResult orderTradeResult = orderTradeOperation.queryByPayOrderId(createPayOrderParam.getPayOrderId());
		
		//2 创建支付订单
		PayTradeParam payTradeParam = BeanConvertUtil.beanConvert(createPayOrderParam,PayTradeParam.class);
		payTradeParam.setAmt(orderTradeResult.getAmt());
		payTradeParam.setOrderTradeNo(orderTradeResult.getOrderTradeNo());
		payTradeParam.setMerchantId(orderTradeResult.getMemberId());
		payTradeParam.setMerchantName(orderTradeResult.getMemberName());
		PayTradeResult ptr = payTradeOperation.createPayTrade(payTradeParam);
		try {
			System.out.println("订单服务创建支付订单返回="+BeanConvertUtil.Obj2String(ptr));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//3 调用渠道服务获取银行网关元素
	    ChannelPayReq channelPayReq = new ChannelPayReq();
	    channelPayReq.setPayReqNo(ptr.getPayReqNo());
	    channelPayReq.setPayChannelId(ptr.getPayChannelId());
	    channelPayReq.setAccountId(ptr.getAccountId());
	    channelPayReq.setCreateDate(ptr.getCreateDate());
	    channelPayReq.setCreateTime(ptr.getCreateTime());
	    channelPayReq.setOrderTitle(orderTradeResult.getOrderTitle());
	    channelPayReq.setAmt(ptr.getAmt());
		ChannelPayResp channelPayResp = channelTradeOperation.createPayReq(channelPayReq);
		
		try {
			System.out.println("渠道服务返回="+BeanConvertUtil.Obj2String(channelPayResp));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new MsgResult(channelPayResp);
	}
	
	
	/**
	 * 验证商户支付申请签名是否正确
	 * @param preateOrderTradeParam
	 * @param signType 签名类型 1:MD5 2:RSA
	 * @param key 秘钥
	 * @throws CashierdeskException
	 */
	private void validMerchantSign(CreateOrderTradeParam preateOrderTradeParam,int signType,String key) throws BaseException{
		String sign = preateOrderTradeParam.getSign();//签名串
		Map params = BeanConvertUtil.beanConvert(preateOrderTradeParam, HashMap.class);
		//MD5加密
		if(Constant.MERCHANT_SIGN_MD5 == signType){
			if(SignUtils.verifyByMD5(params, sign, key) != true){
				throw new CashierdeskException(ErrorCode.ORDER_MERCHANT_SIGNERROR);
			}
		}else if(Constant.MERCHANT_SIGN_RSA == signType){
			if(SignUtils.verifyByRSA(params, sign, key) != true){
				throw new CashierdeskException(ErrorCode.ORDER_MERCHANT_SIGNERROR);
			}
		}
		throw new CashierdeskException(ErrorCode.ORDER_MERCHANT_SIGNERROR);
	}
	
	/**
	 * 获取缴款订单编号（通过缴款订单标识ID）
	 * 从缓存中取订单编号，取不到从接口取，缓存5分钟超时
	 * @param payOrderId
	 * @return
	 * @throws BaseException
	 */
	private String getOrderTradeNo(String payOrderId) throws BaseException{
		String orderTradeNo = JedisUtil.get(CACHE_PAYORDERID_PREFIX + payOrderId);
		if(orderTradeNo == null){
			OrderTradeResult otr = orderTradeOperation.queryByPayOrderId(payOrderId);
			if(otr != null && otr.getPayOrderId() != null){
				JedisUtil.set(CACHE_PAYORDERID_PREFIX + payOrderId, otr.getOrderTradeNo(), CACHE_PAYORDERID_TIMEOUT_SECOND);
				orderTradeNo = otr.getOrderTradeNo();
			}
		}
		return orderTradeNo;
	}
	
}
