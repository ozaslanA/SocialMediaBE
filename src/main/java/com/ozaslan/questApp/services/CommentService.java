package com.ozaslan.questApp.services;

import com.ozaslan.questApp.entities.Comment;
import com.ozaslan.questApp.entities.Post;
import com.ozaslan.questApp.entities.User;
import com.ozaslan.questApp.repositories.CommentRepository;
import com.ozaslan.questApp.request.CommentCreateRequest;
import com.ozaslan.questApp.request.CommentUpdateRequest;
import com.ozaslan.questApp.request.PostUpdateRequest;
import com.ozaslan.questApp.responses.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;


    @Autowired
    public CommentService(CommentRepository commentRepository,UserService userService,PostService postService) {
        this.commentRepository = commentRepository;
        this.userService=userService;
        this.postService=postService;
    }


    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments = commentRepository.findAll();
        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment getOneCommentById(Long commentId) {
        return  commentRepository.findById(commentId).orElse(null);

    }

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user=userService.getOneUser(newCommentRequest.getUserId());
        Post post=postService.getOnePostById(newCommentRequest.getPostId());
        if(user !=null && post !=null){

        Comment toSave=new Comment();
        toSave.setId(newCommentRequest.getId());
        toSave.setPost(post);
        toSave.setUser(user);
        toSave.setText(newCommentRequest.getText());
        return commentRepository.save(toSave);

        }else
        return null;
    }


    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment =commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentToUpdate=comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.deleteById(commentId);
    }



}
