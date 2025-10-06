package com.example.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.usermicroservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
