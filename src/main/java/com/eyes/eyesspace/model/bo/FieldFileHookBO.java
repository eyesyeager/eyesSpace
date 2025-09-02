package com.eyes.eyesspace.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FieldFileHookBO {
    private String category;

    private String url;

    private LocalDateTime createTime;
}
