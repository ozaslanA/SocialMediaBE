package com.ozaslan.questApp.controllers;


import com.ozaslan.questApp.entities.Comment;
import com.ozaslan.questApp.entities.User;
import com.ozaslan.questApp.request.CommentCreateRequest;
import com.ozaslan.questApp.request.CommentUpdateRequest;
import com.ozaslan.questApp.request.PostUpdateRequest;
import com.ozaslan.questApp.responses.CommentResponse;
import com.ozaslan.questApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long>postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOnePost(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }



    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneCommentById(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
      commentService.deleteOneComment(commentId);
    }
}
