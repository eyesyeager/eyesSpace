package com.eyes.eyesspace.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentEnum {
	BLOG(0, "博客评论"),

	SHUOSHUO(1, "说说评论"),

	MUSIC(2, "音乐评论"),

	VIDEO(3, "视频评论"),

	MESSAGE(4, "全站留言"),

	ANIME(5, "动漫评论"),

	BOOK(6, "小说评论"),

	TRAVEL(7, "旅行评论");

	private final Integer type;

	private final String description;
}
