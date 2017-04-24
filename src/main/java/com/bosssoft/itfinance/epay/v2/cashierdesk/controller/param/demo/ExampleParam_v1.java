package com.bosssoft.itfinance.epay.v2.cashierdesk.controller.param.demo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ExampleParam_v1
{
	private long id;

	@Length(min = 4, max = 10, message = "标题应大于4个字符，小于10个字符")
	@NotBlank(message = "标题不能为空")
	private String title;

	@NotBlank(message = "内容不能为空")
	@Length(max = 100, message = "内容不能超过100个字")
	private String content;

	@NotBlank(message = "图片地址不能为空")
	private String imageUrl;

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

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

}
