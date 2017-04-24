package com.bosssoft.itfinance.epay.v2.cashierdesk.common.enums;

import java.io.Serializable;

import com.bosssoft.itfinance.epay.v2.teamcommon.EnumVal;

public enum ErrorCode  implements EnumVal, Serializable{
    
	/**错误编码 0200 1-999 全局使用,业务异常从0201开始**/
	/** 1 入参验证失败 **/
	PARAM_VALIDATE_FAIL("0200001", "入参验证失败"),
	/** 2:ID不能为空 **/
	PARAM_VALD_IDNULL("0200002", "ID不能为空"),
	/** 3:数据转换异常 **/
	DATA_CONVERT_ERROR("0200003", "数据转换异常"),
	/** 4：数据库查询失败 **/
	SQL_SELECT_ERROR("0200004", "数据库查询失败"),
	/** 5：数据库新增失败 **/
	SQL_SAVE_ERROR("0200005", "数据库新增失败"),
	/** 6：数据库更新失败 **/
	SQL_UPDATE_ERROR("0200006", "数据库更新失败"),
	/** 7：数据库更新失败 **/
	SQL_DELETE_ERROR("0200007", "数据库删除失败"),
	/** 8:生成MD5线程重复KEY失败 **/
	THREAD_MD5_ERROR("0200008", "线程锁KEY MD5加密失败"),
	/** 9:多个线程同时重复请求异常 **/
	THREAD_REPEAT_REQUEST("0200009", "线程锁拦截成功，此处出现并发请求"),
	/** 10:其他异常 **/
	SYS_OTHER_BUG("0200010", "其他异常"),
	/** 11:服务忙，请稍后重试 **/
	SYS_SERVICE_BUSY("0200011", "服务忙，请稍后重试"),
	/** 12:序列号异常 **/
	SYS_SERIALIZE_BUG("0200012", "序列号异常"),
    
	
    /** 0201段为订单异常 范围0201001-0201999 */
	/** 0201001：商户签名信息错误 **/
	ORDER_MERCHANT_SIGNERROR("0201001", "商户签名信息错误"),
	/** 0201002：缴款订单标识ID对于订单编号不存在 **/
	ORDER_ORDERTRADENO_NOTEXISTS("0201002", "缴款订单标识ID对于订单编号不存在"),
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	/** 99999:未知异常**/
	SYS_UNKNOW_BUG("99999", "未知异常");
    private String code;
    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
	@Override
    public String errCode() {
        return code;
    }

	@Override
    public String errMsg() {
        return msg;
    }
}
