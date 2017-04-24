package com.bosssoft.itfinance.epay.v2.cashierdesk.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.IUserVoService;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.auth.UserVo;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.framework.exception.DeniedException;

/**
 * 用户信息获取模块
 * 该方法登录时候会被调用. 
 * 
 */
@Service
public class UserVoService implements IUserVoService {
	
	private Logger logger = LoggerFactory.getLogger(IUserVoService.class);

    @Override
    public UserVo getUserVoByUsername(String username) {  
    	UserVo user = new UserVo();
    	//测试代码，后期要对接到会员中心接口
    	if("wangml".equals(username)){
    		user.setUsername("wangml");
    		user.setPasword("e10adc3949ba59abbe56e057f20f883e");
    	}
    	logger.info("模拟账户 获取");
		return user;
    }
    @Override
    public void loginValid(String username, String password) {
        UserVo user = getUserVoByUsername(username);
        if (user != null && user.getPasword() != null && user.getPasword().equals(password)) {
            //验证通过
        } else {
            throw new DeniedException("用户名或者密码不正确！");
        }
    }

    @Override
    public UserVo getUserVoByUserId(long userId) {
    	UserVo user = new UserVo();
    	user.setUsername("wangml");
    	user.setPasword("123456");
        return user;
    }
    
/*    @Override
    public void loginValid(String username, String password, Map<String, String> data) {
        if(data == null){
            loginValid(username,password);
            return;
        }
        
        UserVo user = getUserVoByUsername(username);
        if (user == null || user.getPasword() == null) {
            throw new DeniedException("您的用户名或者密码不正确！");
        }
        
        String mvalue = data.get("mvalue");
        String newPassword = Md5Encrypt.md5(user.getPasword()+LoginSaltUtil.getSaltCode(username,Long.valueOf(mvalue)));
        if(!password.equals(newPassword)){
            throw new DeniedException("用户名或者密码不正确！");
        }
    }*/
}
