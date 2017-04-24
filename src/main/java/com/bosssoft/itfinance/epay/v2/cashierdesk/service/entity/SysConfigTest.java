package com.bosssoft.itfinance.epay.v2.cashierdesk.service.entity;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.entity.BaseEntity;

/**
 *
 * 配置测试表
 *
 */
@TableName("sys_config_test")
public class SysConfigTest extends BaseEntity {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId(type = IdType.INPUT)
	private String id;

	/** 配置代码 */
	private String code;

	/** 配置值 */
	private String value;

	/** 备注 */
	private String note;

	/** 创建时间 */
	private String createtime;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
