package com.ozaslan.questApp.request;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String text;
    private String title;
    private Long userId;
}
