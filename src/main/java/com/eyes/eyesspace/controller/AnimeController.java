package com.eyes.eyesspace.controller;

import com.eyes.eyesAuth.limiter.Limiter;
import com.eyes.eyesAuth.permission.Permission;
import com.eyes.eyesAuth.permission.PermissionEnum;
import com.eyes.eyesspace.common.exception.CustomException;
import com.eyes.eyesspace.common.result.Result;
import com.eyes.eyesspace.model.vo.AnimeInfoVO;
import com.eyes.eyesspace.model.vo.AnimeListInfoVO;
import com.eyes.eyesspace.model.vo.AnimeListVO;
import com.eyes.eyesspace.model.vo.AnimeNoticeVO;
import com.eyes.eyesspace.model.vo.FileUploadVO;
import com.eyes.eyesspace.service.AnimeService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author eyesYeager
 * date 2023/5/21 19:44
 */

@Validated
@RestController
@RequestMapping("/anime")
public class AnimeController {
	@Resource
	private AnimeService animeService;

	@Permission(PermissionEnum.ADMIN)
	@PostMapping("/uploadAnimePic")
	public Result<FileUploadVO> uploadAnimePic(@RequestPart("file") MultipartFile multipartFile) throws CustomException {
		return Result.success(animeService.uploadAnimePic(multipartFile));
	}

	@Permission
	@Limiter
	@GetMapping("/getAnimeNotice")
	public Result<AnimeNoticeVO> getAnimeNotice() {
		return Result.success(animeService.getAnimeNotice());
	}

	@Permission
	@Limiter
	@GetMapping("/getAnimeListInfo")
	public Result<AnimeListInfoVO> getAnimeListInfo() {
		return Result.success(animeService.getAnimeListInfo());
	}

	@Permission
	@Limiter
	@GetMapping("/getAnimeList")
	public Result<AnimeListVO> getAnimeList(Integer page) {
		return Result.success(animeService.getAnimeList(page));
	}

	@Permission
	@Limiter
	@GetMapping("/getAnimeInfo/{id}")
	public Result<AnimeInfoVO> getAnimeInfo(@PathVariable Integer id) throws CustomException {
		return Result.success(animeService.getAnimeInfo(id));
	}
}