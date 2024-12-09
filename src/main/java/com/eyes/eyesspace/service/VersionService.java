package com.eyes.eyesspace.service;

import com.eyes.eyesspace.common.exception.CustomException;
import com.eyes.eyesspace.common.result.PageBind;
import com.eyes.eyesspace.model.dto.VersionListDTO;
import com.eyes.eyesspace.model.vo.VersionInfoVO;

import java.util.List;

/**
 * @author eyesYeager
 * date 2023/5/20 16:30
 */

public interface VersionService {

	VersionInfoVO getVersionInfo() throws CustomException;

	PageBind<VersionListDTO> getVersionList(Integer page);

	List<VersionListDTO> getVersionListByIds(List<Integer> ids);
}