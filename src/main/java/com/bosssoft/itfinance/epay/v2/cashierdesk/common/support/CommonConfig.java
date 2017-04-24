package com.bosssoft.itfinance.epay.v2.cashierdesk.common.support;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @ClassName: CommonConfig
 * @Description: TODO(系统配置文件加载工具类)
 * @author yejuncai
 * @date 2016年3月15日 上午10:10:42
 *
 */
public class CommonConfig implements InitializingBean, DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(CommonConfig.class);
    
    private CommonConfig(){
        super();
    }
	
	// 静态内部类，优点：加载时不会初始化静态变量INSTANCE，因为没有主动使用，达到Lazy load
    private static class SingletonHolder {
        private static CommonConfig instance = new CommonConfig();
    }

    public static CommonConfig getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 配置属性对象
     */
    private Properties properties = new Properties();

    public Properties getProperties() {
        return CommonConfig.getInstance().properties;
    }

    public void setProperties(Properties properties) {
        CommonConfig.getInstance().properties = properties;
    }
	
	public static String getString(String propertiesKey){
		String value = "";
		try{
			value = new String(getInstance().properties.getProperty(propertiesKey, "").getBytes("ISO-8859-1"),"UTF-8"); 
		}catch(Exception e){
		    log.error(e.getMessage(), e);
		}
		return value;
	}
	
	public static boolean getBoolean(String propertiesKey){
		try{
			return Boolean.parseBoolean(getInstance().properties.getProperty(propertiesKey, "false"));
		}catch(Exception e){
		    log.error(e.getMessage(), e);
			return false;
		}
	}
	
	public static int getInt(String propertiesKey){
		try{
			return Integer.parseInt(getInstance().properties.getProperty(propertiesKey, "0"));
		}catch(Exception e){
		    log.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public static long getLong(String propertiesKey){
		try{
			return Long.parseLong(getInstance().properties.getProperty(propertiesKey, "-1"));
		}catch(Exception e){
		    log.error(e.getMessage(), e);
			return -1L;
		}
	}
	
	public static float getFloat(String propertiesKey){
		try{
			return Float.parseFloat(getInstance().properties.getProperty(propertiesKey, "0.0"));
		}catch(Exception e){
		    log.error(e.getMessage(), e);
			return 0F;
		}
	}
	
	public static double getDouble(String propertiesKey){
		try{
			return Double.parseDouble(getInstance().properties.getProperty(propertiesKey, "0.0"));
		}catch(Exception e){
		    log.error(e.getMessage(), e);
			return 0L;
		}
	}

	/**
	 * ***********************************Spring-bean***************************
	 * @Description: TODO(使用spring的bean来初始化程序内的一些配置)
	 * 通过实现InitializingBean/DisposableBean 接口来定制初始化之后/销毁之前的操作方法；
	 * 通过<bean> 元素的 init-method/destroy-method属性指定初始化之后 /销毁之前调用的操作方法；
	 * 在指定方法上加上@PostConstruct或@PreDestroy注解来制定该方法是在初始化之后还是销毁之前调用
	 * @author xiangqi
	 * @date 2016-06-06 下午 05:34
	 */
	@PostConstruct
	public void postConstruct() {//bean初始化之后
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

/*	public void initMethod() {
		//初始化redis连接池
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(CommonConfig.getInt("codis.MaxActive"));
		config.setMaxIdle(CommonConfig.getInt("codis.MaxIdle"));
		config.setMaxWaitMillis(CommonConfig.getInt("codis.maxWaitMillis"));
		config.setMinIdle(CommonConfig.getInt("codis.MinIdle"));
		config.setTestOnBorrow(CommonConfig.getBoolean("codis.TestOnBorrow"));
		RedisImpl.initJedisPool(new JedisPool(config, CommonConfig.getString("codis.host"),
				CommonConfig.getInt("codis.port"), CommonConfig.getInt("codis.TimeOut"),
				StringUtils.defaultIfBlank(CommonConfig.getString("codis.password"), null)));
		log.info("=============================服务器成功启动！=============================");
	}*/

	@PreDestroy
	public void preDestroy() {
	}

	@Override
	public void destroy() throws Exception {
		//log.info("执行InitAndDestroyBean: destroy");
	}

/*	public void destroyMethod() {
		ProtocolConfig.destroyAll();//关闭dubbo服务端口
		log.info("=============================服务器成功关闭！=============================");
	}*/

	public static void main(String[] args) {//测试
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		context.close();
	}
}