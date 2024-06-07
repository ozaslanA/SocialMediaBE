package com.ozaslan.questApp.services;

import com.ozaslan.questApp.entities.Post;
import com.ozaslan.questApp.entities.User;
import com.ozaslan.questApp.repositories.PostRepository;
import com.ozaslan.questApp.request.PostCreateRequest;
import com.ozaslan.questApp.request.PostUpdateRequest;
import com.ozaslan.questApp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    @Autowired

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService=userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        } else {
            list = postRepository.findAll();
        }
        return list.stream().map(PostResponse::new).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post crateOnePost(PostCreateRequest newPostRequest) {
        User user=userService.getOneUser(newPostRequest.getUserId());
        if( user==null)
            return null;
        Post toSave=new Post();
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post>  post = postRepository.findById(postId);

        if(post.isPresent()){
            Post toUpdate=post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;

    }

    public void deleteOnePostById(Long postId) {
        Post post=postRepository.findById(postId).orElseThrow();
        postRepository.deleteById(postId);

    }
}
