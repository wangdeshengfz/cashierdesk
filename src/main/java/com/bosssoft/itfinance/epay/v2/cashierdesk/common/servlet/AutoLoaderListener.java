package com.bosssoft.itfinance.epay.v2.cashierdesk.common.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.constant.SystemInfoConstant;

public class AutoLoaderListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(AutoLoaderListener.class); 

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        logger.info("E缴通收银台启动初始化完成(版本:"+SystemInfoConstant.getAppVersion()+")");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.info("E缴通收银台关闭完成(版本:"+SystemInfoConstant.getAppVersion()+")");
    }

}
