package com.eyes.eyesspace.model.vo;

import com.eyes.eyesspace.model.dto.AnimeListDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author eyesYeager
 * date 2023/5/22 17:54
 */

@Data
@AllArgsConstructor
public class AnimeListVO {
	private Integer total;

	private List<AnimeListDTO> data;
}