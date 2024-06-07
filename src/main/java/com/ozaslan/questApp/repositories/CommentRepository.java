package com.ozaslan.questApp.repositories;

import com.ozaslan.questApp.entities.Comment;
import com.ozaslan.questApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserId(Long userId);

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByPostId(Long postId);
}
