package com.eyes.eyesspace.script;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eyes.eyesspace.constant.StatusEnum;
import com.eyes.eyesspace.mapper.ShuoMapper;
import com.eyes.eyesspace.model.entity.Shuoshuo;
import com.eyes.eyesspace.utils.CryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class EncryptPrivateShuoScript {

    private static final String key = "";

    @Resource
    private ShuoMapper shuoMapper;

    @Test
    public void execute() {
        List<Shuoshuo> shuoshuos = shuoMapper.selectList(Wrappers.<Shuoshuo>lambdaQuery()
                .eq(Shuoshuo::getStatus, StatusEnum.PRIVATE.getStatus())
                .notIn(Shuoshuo::getId, Arrays.asList(676, 678)));
        log.info("共有{}条数据", shuoshuos.size());
        int successNum = 0;
        for (Shuoshuo shuoshuo : shuoshuos) {
            try {
                String content = CryptUtils.aesEncrypt(shuoshuo.getContent(), key);
                shuoshuo.setContent(content);
                int i = shuoMapper.updateById(shuoshuo);
                if (i < 1) {
                    log.error("更新数据库失败！{}", shuoshuo);
                }
                successNum++;
                log.info("更新成功！{}", shuoshuo);
            } catch (Exception e) {
                log.error("数据处理失败！{}", shuoshuo, e);
            }
        }
        log.info("共有{}条数据，成功更新{}条数据！", shuoshuos.size(), successNum);
    }
}
