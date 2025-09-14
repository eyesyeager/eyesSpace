package com.eyes.eyesspace.script;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eyes.eyesspace.mapper.JokeMapper;
import com.eyes.eyesspace.model.entity.Joke;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
public class DealJokeUrlHttpScript {

    @Resource
    private JokeMapper jokeMapper;

    @Test
    public void execute() {
        List<Joke> jokes = jokeMapper.selectList(Wrappers.lambdaQuery());
        for (Joke joke : jokes) {
            String urlJson = joke.getUrlList();
            JSONArray jsonArray = JSONUtil.parseArray(urlJson);
            List<String> urlList = JSONUtil.toList(jsonArray, String.class);
            urlList = urlList.stream().map(v -> v.replace("http://", "https://")).toList();
            joke.setUrlList(JSONUtil.toJsonStr(urlList));
            jokeMapper.updateById(joke);
        }
    }
}
