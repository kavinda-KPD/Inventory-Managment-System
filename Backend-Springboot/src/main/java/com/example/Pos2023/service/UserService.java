package com.example.Pos2023.service;

import com.example.Pos2023.dto.UserDTO;

public interface UserService {
    String saveUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, int userId);

    UserDTO loginUser(UserDTO userDTO);
}
