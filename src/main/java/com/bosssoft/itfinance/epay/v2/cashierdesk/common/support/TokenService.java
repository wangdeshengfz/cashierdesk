package com.bosssoft.itfinance.epay.v2.cashierdesk.common.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.ITokenService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.IUserVoService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.SessionData;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.TokenUtil;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.UserVo;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.exception.DeniedException;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.util.JedisPoolUtil;

@Service
public class TokenService implements ITokenService {

	@Autowired
	private JedisPoolUtil jedisPoolUtil;

	@Autowired
	private IUserVoService userVoService;

	private final String TOKENKEY = "AUTH:USERCENTER:";


	@Override
	public SessionData createSessionData(String username, String channel) {
		/* Expires in one hour */
		long expires = System.currentTimeMillis() + 1000L * TokenUtil.expireTime;
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(username);
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtil.computeSignature(username, expires));
		SessionData sessionData = getUserInfoByDB(username);
		sessionData.setAccessToken(tokenBuilder.toString());
		try (Jedis jedis = jedisPoolUtil.getJedis()) {
			String redisKey = TOKENKEY + username + ":" + channel;
			//jedis.set(redisKey, new Gson().toJson(sessionData).toString());
			jedis.set(redisKey, JSON.toJSONString(sessionData));
			jedis.expire(redisKey, TokenUtil.expireTime);
		}
		return sessionData;
	}

	@Override
	public void login(String username, String password) {
		userVoService.loginValid(username, password);
	}

	
	@Override
	public SessionData getUserInfoByDB(String username) {
		SessionData user = new SessionData();
		UserVo userVo = userVoService.getUserVoByUsername(username);
		if (userVo.getUsername() == null || userVo.getUsername().equals("")) {
			throw new DeniedException("用户名不存在！");
		}
		user.setUsername(username);
		user.setUserid(userVo.getUserid());
		user.setPassword(userVo.getPasword());
		user.setNickName(userVo.getNickname());
		user.setEmail(userVo.getEmail());
		user.setMobile(userVo.getMobile());
		return user;
	}


	@Override
	public SessionData getSessionData(String username, String channel) {
		try (Jedis jedis = jedisPoolUtil.getJedis()) {
			//return new Gson().fromJson(jedis.get(TOKENKEY + username + ":" + channel), SessionData.class);
			return JSON.parseObject(jedis.get(TOKENKEY + username + ":" + channel), SessionData.class);
		}
	}

}
