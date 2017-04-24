package com.test.cashierdesk.dailyTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.order.CreateOrderTradeParam;
import com.bosssoft.itfinance.epay.v2.trade.common.po.OrderTradeParam;

/**
 * bean 解析测试类，用于生成一些测试代码
 * @author wangml
 *
 */
public class BeanAnalyse {
	
	/**
	 * 打印传入bean对于的json put语句
	 * @param cla
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void printBeanPutJsonString(Class cla) 
		throws InstantiationException, IllegalAccessException {
		Object otp = cla.newInstance();
		
		//1 获取实例属性列表
		Field fields[] = otp.getClass().getDeclaredFields(); 
		
		//2 获取有set方法的属性列表
		List<String> setProps = new ArrayList<String>();
		Method[] ms = OrderTradeParam.class.getMethods();
		for(int i=0;i<ms.length;i++){
			String setMethod = ms[i].getName();
			if(setMethod.startsWith("set")){
				setMethod = setMethod.replaceFirst("set", "");
				String prop = toLowerCaseFirstOne(setMethod);
				setProps.add(prop);
			}
		}
		
		//3 生成目标串,并按照类中属性顺序
		for(int i=0;i<fields.length;i++){
			String prop = fields[i].getName();
			if(setProps.contains(prop)){
				System.out.println("param.put(\""+prop+"\",\"0\");");
			}
		}
	}

	//首字母转小写
	private static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	
	
	public static void main(String[] args) throws Exception {
		printBeanPutJsonString(CreateOrderTradeParam.class);
	}

}
