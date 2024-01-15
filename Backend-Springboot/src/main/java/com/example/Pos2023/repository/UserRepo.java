package com.example.Pos2023.repository;


import com.example.Pos2023.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByUserName(String userName);

    User getUserByUserName(String userName);

    User findByUserName(String userName);
}
