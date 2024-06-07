package com.ozaslan.questApp.request;

import lombok.Data;

@Data

public class CommentCreateRequest {
    Long id;
    private String text;
    private Long postId;
    private Long userId;
}
