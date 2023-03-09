package com.example.jwtdemo.repo;

import com.example.jwtdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
