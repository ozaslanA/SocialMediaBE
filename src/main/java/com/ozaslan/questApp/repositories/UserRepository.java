package com.ozaslan.questApp.repositories;

import com.ozaslan.questApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
