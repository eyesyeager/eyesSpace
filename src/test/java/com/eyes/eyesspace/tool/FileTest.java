package com.eyes.eyesspace.tool;

import io.github.eyesyeager.eyesStorageStarter.entity.ObjectUploadModel;
import io.github.eyesyeager.eyesStorageStarter.exception.EyesStorageException;
import io.github.eyesyeager.eyesStorageStarter.service.storage.QiniuOssStorage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class FileTest {

    @Resource
    private QiniuOssStorage ossStorage;

    @Test
    public void test() throws EyesStorageException {
        String url = "";
        ObjectUploadModel objectUploadModel = ossStorage.putObjectByNetUrl(url, "test.mp4", "test");
        System.out.println(objectUploadModel);
    }
}
