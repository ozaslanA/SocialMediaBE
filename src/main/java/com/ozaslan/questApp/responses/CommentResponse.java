package com.ozaslan.questApp.responses;

import com.ozaslan.questApp.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    private Long id;
    private Long userId;
    private String userName;
    private String text;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.text = entity.getText();
    }
}
