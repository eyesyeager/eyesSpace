package com.eyes.eyesspace.tool;

import com.alibaba.fastjson.JSONArray;
import com.eyes.eyesspace.model.entity.Video;
import com.eyes.eyesspace.service.IVideoService;
import com.eyes.eyesspace.utils.DateUtils;
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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@SpringBootTest
public class UploadBiliVideoTest {

    @Value("${path.folder.video}")
    private String videoPath;

    @Value("${path.folder.video-cover}")
    private String videoCoverPath;

    @Resource
    private MinioOssStorage minioOssStorage;

    @Resource
    private IVideoService videoService;

    @Test
    public void execute() {
        // 初始化参数
        String videoLocalPath = "H:\\temp\\video\\video";
        String infoLocalPath = "H:\\temp\\video\\data.json";

        // 读取 info json 文件
        FileReader fileReader = null;
        Reader reader = null;
        StringBuilder jsonInfoBuilder = new StringBuilder();
        try {
            File file = new File(infoLocalPath);
            fileReader = new FileReader(file);
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            int ch = 0;
            while ((ch = reader.read()) != -1) {
                jsonInfoBuilder.append((char) ch);
            }
        } catch (Exception e) {
            log.error("failed to read info json file! error: {}", e.getMessage());
            return;
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                log.error("failed to close stream!", e);
            }
        }
        List<BiliEntity> biliEntityList = JSONArray.parseArray(jsonInfoBuilder.toString(), BiliEntity.class);

        // 读取 video 文件夹
        File file = new File(videoLocalPath);
        File[] files = file.listFiles();
        if (Objects.isNull(files)) {
            log.error("videoLocalPath {} doesn't exist", videoLocalPath);
            return;
        }
        for (File item : files) {
            String fileName = item.getName();
            BiliEntity entity = null;
            for (BiliEntity biliEntity : biliEntityList) {
                if (!fileName.startsWith(biliEntity.getTitle())) {
                    continue;
                }
                entity = biliEntity;
            }
            if (entity == null) {
                log.error("{} does not exist in json.", fileName);
                continue;
            }
            handleVideo(entity, item);
        }
    }

    private void handleVideo(BiliEntity entity, File item) {
        // 上传封面
        String coverUrl;
        try {
            String coverName = RandomUtils.getUUid() + ".png";
            ObjectUploadModel model = minioOssStorage.putObjectByNetUrl(entity.getCover(), coverName, videoCoverPath);
            coverUrl = minioOssStorage.getSimpleUrl(model.getObjectName(), videoCoverPath);
        } catch (Exception e) {
            log.error("fail to upload video cover!", e);
            return;
        }
        // 上传视频源文件
        String videoUrl;
        try {
            FileInputStream fis = new FileInputStream(item);
            byte[] data = IOUtils.inputStreamToBytes(fis);
            String videoName = RandomUtils.getUUid() + ".mp4";
            ObjectUploadModel model = minioOssStorage.putObject(data, videoName, videoPath);
            videoUrl = minioOssStorage.getSimpleUrl(model.getObjectName(), videoPath);
        } catch (Exception e) {
            log.error("fail to read file!", e);
            return;
        }
        // 写入数据库
        Video video = new Video();
        video.setTitle(entity.title);
        video.setAuthor(entity.getUpper().getName());
        video.setVideoUrl(videoUrl);
        video.setCoverUrl(coverUrl);
        video.setOriginalUrl("https://www.bilibili.com/video/" + entity.getBvid());
        video.setComment("暂无");
        LocalDateTime localDateTime = DateUtils.timestampToDatetime(entity.fav_time * 1000);
        video.setCreateTime(localDateTime);
        video.setUpdateTime(localDateTime);
        if (videoService.save(video)) {
            log.info("video {} upload success!", entity.getTitle());
        } else {
            log.error("failed to save db: {}", entity);
        }
    }

    @Data
    static class BiliEntity {
        private String title;

        private String cover;

        private String bvid;

        private UpperEntity upper;

        private Long fav_time;
    }

    @Data
    static class UpperEntity {
        private String name;
    }
}
