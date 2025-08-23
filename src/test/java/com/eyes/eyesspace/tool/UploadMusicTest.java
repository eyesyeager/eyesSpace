package com.eyes.eyesspace.tool;

import com.eyes.eyesspace.service.IMusicService;
import io.github.eyesyeager.eyesStorageStarter.service.storage.MinioOssStorage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@Slf4j
@SpringBootTest
public class UploadMusicTest {
    @Value("${path.folder.music}")
    private String musicPath;

    @Value("${path.folder.music-cover}")
    private String musicCoverPath;

    @Resource
    private MinioOssStorage minioOssStorage;

    @Resource
    private IMusicService musicService;

    @Test
    public void execute() {
    }
}
