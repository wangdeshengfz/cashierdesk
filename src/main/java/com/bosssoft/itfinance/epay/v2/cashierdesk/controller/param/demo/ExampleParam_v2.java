package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.demo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ExampleParam_v2
{
	private long id;

	@Length(min = 4, max = 20, message = "标题应大于4个字符，小于10个字符")
	@NotBlank(message = "标题不能为空")
	private String title;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}


}
