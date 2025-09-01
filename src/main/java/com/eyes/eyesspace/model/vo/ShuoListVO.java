package com.eyes.eyesspace.model.vo;

import java.time.LocalDateTime;
import java.util.List;
import com.eyes.eyesspace.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ShuoListVO {
	private String content;

	private List<String> picList;

	private Boolean isProtected;

	@JsonFormat(pattern = DateUtils.TIME_FORMAT)
	private LocalDateTime createTime;
}
