package com.eyes.eyesspace.tool;

import com.alibaba.fastjson.JSON;
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
    private static final String JOKE_PIC_NAME_SPLIT = "-";

    @Value("${path.folder.joke}")
    private String jokePath;

    @Resource
    private MinioOssStorage minioOssStorage;

    @Resource
    private IJokeService jokeService;

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
        List<String> invalidPicNameList = new ArrayList<>();
        Map<Integer, JokePicEntity> jokePicEntityMap = new Hashtable<>();
        for (File f : files) {
            // 读取图片转换为流
            byte[] data;
            try {
                FileInputStream fis = new FileInputStream(f);
                data = IOUtils.inputStreamToBytes(fis);
            } catch (Exception e) {
                log.error("读取文件失败: {}", e.getMessage());
                continue;
            }

            // 处理文件名
            String fileName = f.getName();
            String name = fileName.substring(0, fileName.lastIndexOf("."));
            String[] split = name.split(JOKE_PIC_NAME_SPLIT);
            if (split.length != 2 && split.length != 3) {
                invalidPicNameList.add(fileName);
                continue;
            }

            // 上传文件
            String url;
            try {
                String objectName = RandomUtils.getUUid() + fileName.substring(fileName.lastIndexOf("."));
                ObjectUploadModel model = minioOssStorage.putObject(data, objectName, jokePath);
                url = minioOssStorage.getSimpleUrl(model.getObjectName(), jokePath);
            } catch (Exception e) {
                log.error("上传文件到 minio 失败! fileName: {}, err: {}", fileName, e.getMessage());
                continue;
            }

            // 拼接数据
            Integer id = Integer.parseInt(split[0]);
            String category = split[1];
            if (!jokePicEntityMap.containsKey(id)) {
                JokePicEntity entity = new JokePicEntity();
                entity.setId(id);
                entity.setCategory(category);
                jokePicEntityMap.put(id, entity);
            }
            jokePicEntityMap.get(id).getUrlList().add(url);
        }

        // 打印不规则命名文件
        if (!invalidPicNameList.isEmpty()) {
            StringBuilder builder = new StringBuilder("存在非法命名文件：");
            for (String invalidPicName : invalidPicNameList) {
                builder.append(invalidPicName).append(";");
            }
            log.error(builder.toString());
        }

        // 批量写入数据库
        List<Joke> jokeList = new ArrayList<>();
        for (Map.Entry<Integer, JokePicEntity> entry : jokePicEntityMap.entrySet()) {
            JokePicEntity entity = entry.getValue();
            Joke joke = new Joke();
            joke.setCategory(entity.getCategory());
            joke.setUrlList(JSON.toJSONString(entity.getUrlList()));
            jokeList.add(joke);
            log.info("即将插入数据库！文件id:{}，文件链接:{}", entry.getKey(), joke.getUrlList());
        }
        if (jokeService.saveBatch(jokeList)) {
            log.info("任务执行成功！共同步梗图记录数：{}", jokeList.size());
        } else {
            log.error("文件插入数据库失败！");
        }
    }

    @Data
    static class JokePicEntity {
        private Integer id;

        private String category;

        private List<String> urlList = new ArrayList<>();
    }
}
