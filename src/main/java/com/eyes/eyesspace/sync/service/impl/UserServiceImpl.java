package com.eyes.eyesspace.sync.service.impl;

import com.eyes.eyesAuth.context.UserInfoHolder;
import com.eyes.eyesAuth.thrift.TTClientPool;
import com.eyes.eyesAuth.thrift.config.TTSocket;
import com.eyes.eyesAuth.thrift.generate.common.TTCustomException;
import com.eyes.eyesAuth.thrift.generate.user.UserInfoReturnee;
import com.eyes.eyesspace.sync.common.exception.CustomException;
import com.eyes.eyesspace.sync.common.result.ResultCode;
import com.eyes.eyesspace.sync.convert.UserConvert;
import com.eyes.eyesspace.sync.model.vo.UserInfoVO;
import com.eyes.eyesspace.sync.service.UserService;
import com.eyes.eyesspace.utils.SensitiveInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author eyesYeager
 * date 2023/2/9 15:48
 */
@Slf4j
@RefreshScope
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private TTClientPool ttClientPool;

	@Override
	public UserInfoVO getUserInfo() throws CustomException {
		Long uid = UserInfoHolder.getUid();
		TTSocket ttSocket = null;
		try {
			ttSocket = ttClientPool.getConnect();
			UserInfoReturnee userInfo = ttSocket.getUserClient().getUserInfo(uid);
			ttClientPool.returnConnection(ttSocket);
			UserInfoVO userInfoVO = UserConvert.INSTANCE.returnee2Dto(userInfo);
			userInfoVO.setEmail(SensitiveInfoUtils.email(userInfoVO.getEmail()));
			return userInfoVO;
		} catch (TTCustomException e) {
			throw new CustomException(e.getMsg());
		} catch (Exception e) {
			ttClientPool.invalidateObject(ttSocket);
			e.printStackTrace();
			throw new CustomException(ResultCode.FAILURE);
		}
	}
}
