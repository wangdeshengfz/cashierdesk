package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
/**
 * @ClassName: BaseEntity
 * @Description: TODO(数据库实体基类)
 * @author wangml
 * @date 2016-12-14 下午 08:06
 */
public class BaseEntity implements Serializable{

	@TableField(exist = false)//必须要加上不然会被子类当作字段继承去
    private static final long serialVersionUID = 1L;

    /**
     * @Title: getDateToString
     * @author xiangqi
     * @date 2016-05-13 22:42
     * @Description: TODO(用一句简要说明该方法作用)
     * @param date 当前时间对象Date类型
     * @param ymd  格式  yyyy-MM-dd    yyyy-MM-dd HH:mm:ss
     * @throws Exception
     * @return java.lang.String 返回值类型
     */
	public String getDateToString(Date date,String ymd) {
		   SimpleDateFormat formatter = new SimpleDateFormat(ymd);
		   String dateString = formatter.format(date);
		   return dateString;
	}
}
