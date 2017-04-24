package com.bosssoft.itfinance.epay.v2.cashierdesk.common.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.constant.SystemInfoConstant;
import com.bosssoft.itfinance.epay.v2.teamcommon.BaseException;

/**
 * @ClassName: LogAop
 * @Description: TODO(AOP全局异常日志输出)
 * @author wangml
 * @date 2016年4月5日 上午10:40:27
 */
@Aspect
@Component
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class.getName());

    // 只监控接口层代码调用
    @Pointcut("execution(* com.bosssoft.itfinance.epay.v2.cashierdesk.controller..*.*(..))")
    public void anymethod() {}

    @Around("anymethod()")
    // 声明环绕通知
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
	    Object result = null;
	    long start = System.currentTimeMillis();
	    boolean is_debug = false;
	    try {
            is_debug = SystemInfoConstant.isDebug();
		    if (is_debug) {
			    Object[] args = pjp.getArgs();
			    // 获取方法名称
			    String methodName = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName(); //
			    logger.info("调用方法：{}", methodName);
			    logger.info("输入参数：{}", obj2String(args));
		    }
		    result = pjp.proceed();
		    if (is_debug) {
			    logger.info("输出参数：{}", obj2String(result));
		    }
	    } catch (BaseException se) {
		    logger.info(pjp.toShortString() + " :  " + se.getMessage());
		    throw se;
	    } catch (Throwable e) {
		    logger.warn(e.getMessage(), e);
		    throw e;
	    } finally {
		    long end = System.currentTimeMillis();
		    long count = end - start;
		    boolean is_count_time = false;
		    if (is_debug) {// 非生产环境
			    is_count_time = true;
		    } else {
			    if (logger.isDebugEnabled()) {
				    is_count_time = true;
			    }
		    }
		    if (is_count_time && count > 1) {
			    logger.info(pjp.toString() + " 用时：" + count + " ms");
		    }
	    }
	    return result;
    }
    
    private String obj2String(Object object) throws BaseException {
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
	    String object_str = null;
        try{
	        byte[] bytes = JSON.toJSONBytes(object, feature);
		    object_str = new String(bytes, "UTF-8");
	    }catch (Exception e){
	    	//throw new BaseException(ErrorCode.SYS_SERIALIZE_BUG,e);
	    	logger.info("序列化入参失败",e);
	    }
        return object_str;
    } 

}
