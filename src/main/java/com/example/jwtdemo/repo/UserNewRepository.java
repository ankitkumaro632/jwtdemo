package com.example.jwtdemo.repo;

import com.example.jwtdemo.entity.UserNew;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserNewRepository extends JpaRepository<UserNew,Integer> {
    UserNew findByUserName(String username);
}
