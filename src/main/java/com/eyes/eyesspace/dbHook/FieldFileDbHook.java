//package com.eyes.eyesspace.dbHook;
//
//import com.eyes.eyesspace.dbHook.entity.FieldFileHookEntity;
//import io.github.eyesyeager.dbHookStarter.AbstractHook;
//import io.github.eyesyeager.eyesStorageStarter.entity.ObjectUploadModel;
//import io.github.eyesyeager.eyesStorageStarter.exception.EyesStorageException;
//import io.github.eyesyeager.eyesStorageStarter.service.storage.QiniuOssStorage;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Scope("prototype")
//@Component
//public class FieldFileDbHook extends AbstractHook {
//    @Resource
//    private QiniuOssStorage storage;
//
//    /**
//     * 程序执行入口
//     * @param result 变化的数据库数据
//     */
//    @Override
//    public void execute(List<Object> result) {
//        String executorName = executorEntity.getName();
//        log.info("------ start executing FieldFileDbHook({}) task ------", executorName);
//        Map<String, String> paramMap = (Map) params;
//        String appName = paramMap.get("appName");
//        for (Object o : result) {
//            log.info("deal result: {}", o.toString());
//            FieldFileHookEntity entity = (FieldFileHookEntity) o;
//            try {
//                ObjectUploadModel model = putObject(entity, appName);
//                log.info("successfully uploaded object: {}", model.toString());
//            } catch (Exception e) {
//                log.error("failed to uploaded object: {}", e.toString());
//            }
//        }
//        log.info("------ FieldFileDbHook({}) task execution completed ------", executorName);
//    }
//
//    private ObjectUploadModel putObject(FieldFileHookEntity entity, String appName) throws EyesStorageException {
//        if (StringUtils.isBlank(entity.getUrl())) {
//            throw new IllegalArgumentException("url is empty");
//        }
//        String[] split = entity.getUrl().split("/");
//        String fileName = split[split.length - 1];
//        return storage.putObjectByNetUrl(entity.getUrl(), fileName, appName + "/" + entity.getCategory());
//    }
//}
