package com.eyes.eyesspace.model.vo;

import com.eyes.eyesspace.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author eyesYeager
 * data 2024/12/14 17:13
 */

@Data
public class BookInfoVO {
	private String title;

	private String author;

	private String type;

	private String period;

	private String introduce;

	private String word;

	private Integer view;

	@JsonIgnore
	private Integer status;

	private Boolean isPrivate;

	@JsonFormat(pattern = DateUtils.DATE_FORMAT)
	private String createTime;
}