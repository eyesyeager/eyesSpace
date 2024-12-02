package com.eyes.eyesspace.sync.controller;

import com.eyes.eyesAuth.limiter.Limiter;
import com.eyes.eyesAuth.permission.Permission;
import com.eyes.eyesAuth.permission.PermissionEnum;
import com.eyes.eyesspace.sync.common.exception.CustomException;
import com.eyes.eyesspace.sync.common.result.Result;
import com.eyes.eyesspace.sync.model.vo.UserInfoVO;
import com.eyes.eyesspace.sync.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author eyesYeager
 * date 2023/2/3 13:24
 */

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@Limiter
	@Permission(PermissionEnum.USER)
	@GetMapping("/getUserInfo")
	public Result<UserInfoVO> getUserInfo() throws CustomException {
		return Result.success(userService.getUserInfo());
	}
}
