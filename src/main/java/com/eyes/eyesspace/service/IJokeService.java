package com.eyes.eyesspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eyes.eyesspace.result.PageBind;
import com.eyes.eyesspace.model.entity.Joke;

import java.util.List;

/**
 * @author eyesYeager
 * date 2023/9/25 8:50
 */
public interface IJokeService extends IService<Joke> {

	PageBind<List<String>> getJokeList(Integer pageIndex);
}
