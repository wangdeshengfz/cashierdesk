package com.bosssoft.itfinance.epay.v2.cashierdesk.common.support;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @ClassName: BaseBizService
 * @Description: TODO(内部公共服务基类)
 * @author xiangqi
 * @date 2016-05-13 下午 01:49
 */
public class BaseBizService implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(BaseBizService.class);
	private static final long serialVersionUID = 1L;
	
	/**
	 * @Title: listHasData
	 * @author xiangqi
	 * @date 2016年4月23日 下午8:38:37
	 * @Description: TODO(判断ArrayList是否有数据)
	 * @param list
	 * @return boolean    返回类型
	 */
	protected boolean listHasData(List<?> list){
		boolean ret = false;
		if(list!=null && !list.isEmpty()){
			ret = true;
		}
		return ret;
	}
	
	/**
	 * @Title: removeRepeatList
	 * @author xiangqi
	 * @date 2016年4月23日 下午8:38:59
	 * @Description: TODO(删除ArrayList重复的对象)
	 * @param targetList
	 * @return List<T>    返回类型
	 */
	public static <T extends Serializable> List<T> removeRepeatList(List<T> targetList){
		List<T> returnList = new ArrayList<T>();
		for(T o:targetList){
			if(!returnList.contains(o)){
				returnList.add(o);
			}
		}
		return returnList;
	}

	/**
	 * @Title: Obj2String
	 * @author xiangqi
	 * @date 2016-05-25 下午 05:57
	 * @Description: TODO(对象序列化成字符串)
	 * @param object
	 * @return java.lang.String 返回值类型
	 * @throws UnsupportedEncodingException
	 */
	public String Obj2String(Object object) {
	    String result = null;
		SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
		byte[] bytes = JSON.toJSONBytes(object, feature);
		
		try {
		    result = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
		return result;
	}
	
}
