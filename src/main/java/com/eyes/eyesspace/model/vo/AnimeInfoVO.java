package com.eyes.eyesspace.model.vo;

import com.eyes.eyesspace.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author eyesYeager
 * date 2023/5/22 20:02
 */

@Data
public class AnimeInfoVO {
	private String title;

	private String type;

	private String period;

	private String introduce;

	private String word;

	private String cover;

	private Integer view;

	@JsonIgnore
	private Integer status;

	private Boolean isProtected;

	@JsonFormat(pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime createTime;
}
