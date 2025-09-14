package com.eyes.eyesspace.tool;

import com.alibaba.fastjson.JSON;
import com.eyes.eyesspace.mapper.JokeMapper;
import com.eyes.eyesspace.model.entity.Joke;
import com.eyes.eyesspace.service.IJokeService;
import com.eyes.eyesspace.utils.IOUtils;
import com.eyes.eyesspace.utils.RandomUtils;
import io.github.eyesyeager.eyesStorageStarter.entity.ObjectUploadModel;
import io.github.eyesyeager.eyesStorageStarter.service.storage.MinioOssStorage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Slf4j
@SpringBootTest
public class UploadJokePicTest {
    @Value("${path.folder.joke}")
    private String jokePath;

    @Resource
    private MinioOssStorage minioOssStorage;

    @Resource
    private JokeMapper jokeMapper;

    @Test
    public void execute() {
        // 初始化参数
        String localPath = "";

        // 读取文件夹，得到文件列表
        File file = new File(localPath);
        File[] files = file.listFiles();
        if (Objects.isNull(files)) {
            log.error("localPath {} doesn't exist", localPath);
            return;
        }

        // 循环处理图片
        for (File f : files) {
            log.info("--------- 开始处理文件: {}", f.getName());

            // 读取图片转换为流
            byte[] data;
            try {
                FileInputStream fis = new FileInputStream(f);
                data = IOUtils.inputStreamToBytes(fis);
            } catch (Exception e) {
                log.error("读取文件失败: {}", e.getMessage());
                continue;
            }

            // 上传文件
            String url;
            String fileName = f.getName();
            try {
                String objectName = RandomUtils.getUUid() + fileName.substring(fileName.lastIndexOf("."));
                ObjectUploadModel model = minioOssStorage.putObject(data, objectName, jokePath);
                url = minioOssStorage.getSimpleUrl(model.getObjectName(), jokePath);
                log.info("上传文件成功！ fileName:{}, url: {}", fileName, url);
            } catch (Exception e) {
                log.error("上传文件失败! fileName: {}", fileName, e);
                continue;
            }

            // 写入数据库
            Joke joke = new Joke();
            joke.setUrlList(JSON.toJSONString(Collections.singletonList(url)));
            if (jokeMapper.insert(joke) == 1) {
                log.info("成功插入数据库！ fileName:{}, url: {}", fileName, url);
            } else {
                log.error("插入数据库与失败！ fileName:{}, url: {}", fileName, url);
            }

            log.info("--------- 成功处理文件: {}", f.getName());
        }
    }
}
