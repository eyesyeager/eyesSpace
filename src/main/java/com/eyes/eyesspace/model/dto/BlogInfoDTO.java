package com.eyes.eyesspace.model.dto;

import com.eyes.eyesspace.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlogInfoDTO {
	private String title;

	private String summary;

	private String content;

	private String category;

	private List<String> labels;

	private Integer views;

	private Integer words;

	@JsonIgnore
	private Integer status;

	private Boolean isPrivate;

	@JsonFormat(pattern = DateUtils.DATE_FORMAT)
	private Date createTime;
}
