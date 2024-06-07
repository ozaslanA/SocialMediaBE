package com.ozaslan.questApp.repositories;

import com.ozaslan.questApp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
