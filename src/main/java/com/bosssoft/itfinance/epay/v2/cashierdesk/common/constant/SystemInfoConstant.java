package com.bosssoft.itfinance.epay.v2.cashierdesk.common.constant;

/**
 * 系统信息常量
 * @author wangml
 *
 */
public class SystemInfoConstant {
    /**
     * 系统版本号
     */
    private static String appVersion = "0.0.1";
    /**
     * 是否debug模式
     */
    private static boolean debug = false;
    
    public static String getAppVersion() {
        return SystemInfoConstant.appVersion;
    }

    public void setAppVersion(String appVersion) {
    	SystemInfoConstant.appVersion = appVersion;
    }
    
    public static boolean isDebug() {
        return SystemInfoConstant.debug;
    }

    public void setDebug(boolean debug) {
    	SystemInfoConstant.debug = debug;
    }
}
