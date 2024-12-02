package com.eyes.eyesspace.sync.model.vo;

import com.eyes.eyesspace.sync.model.dto.FootprintDTO;
import lombok.Data;

import java.util.List;

@Data
public class ContextVO {
	private String spaceName;

	private String ownerName;

	private String ownerAvatar;

	private String ownerMotto;

	private List<FootprintDTO> footprint;

	private String buildTime;

	private String announce;
}