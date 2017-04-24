package com.bosssoft.itfinance.epay.v2.cashierdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.core.MsgResult;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.BeanConvertUtil;
import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.channel.ChannelPayReqParam;
import com.bosssoft.itfinance.epay.v2.channel.common.api.ChannelTradeOperation;
import com.bosssoft.itfinance.epay.v2.channel.common.entity.ChannelPayReq;
import com.bosssoft.itfinance.epay.v2.channel.common.entity.ChannelPayResp;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;

@Controller
@RequestMapping("/channel")
public class ChannelController{
	@Autowired
	private ChannelTradeOperation channelTradeOperation;
	
	/**
	 * 请求渠道服务，获取银行网关地址和参数
	 * @param channelPayReqParam
	 * @return
	 * @throws BaseServiceException 
	 */
	@RequestMapping("/v1/queryReqChannelPayInfo")
	@ResponseBody
	public MsgResult queryReqChannelPayInfo(@Valid ChannelPayReqParam channelPayReqParam) 
			throws BaseException
	{
		ChannelPayReq channelPayReq = BeanConvertUtil.beanConvert(channelPayReqParam, ChannelPayReq.class);
		ChannelPayResp channelPayResp = channelTradeOperation.createPayReq(channelPayReq);
		return new MsgResult(channelPayResp);
	}
}
