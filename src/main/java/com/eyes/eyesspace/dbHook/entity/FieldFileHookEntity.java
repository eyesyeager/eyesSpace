package com.eyes.eyesspace.dbHook.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FieldFileHookEntity {
    private String category;

    private String url;

    private LocalDateTime createTime;
}
