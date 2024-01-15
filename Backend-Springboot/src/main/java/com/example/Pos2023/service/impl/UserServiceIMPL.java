package com.example.Pos2023.service.impl;


import com.example.Pos2023.dto.UserDTO;
import com.example.Pos2023.entity.User;
import com.example.Pos2023.repository.UserRepo;
import com.example.Pos2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public String saveUser(UserDTO userDTO) {

        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getUserPassword()
        );

        userRepo.save(user);
        return user.getUserName();
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int userId) {

        if(userRepo.existsById(userId)){

            User user = userRepo.getReferenceById(userId);

            user.setUserName(userDTO.getUserName());
            user.setUserPassword(userDTO.getUserPassword());

            userRepo.save(user);

            UserDTO userDTO1 = new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getUserPassword()
            );

            return userDTO1;
        }else {
            throw new RuntimeException("no data found that id");
        }


//        if (userRepo.existsByUserName( userDTO.getUserName() )){
//            User user = userRepo.getUserByUserName( userDTO.getUserName() );
//
//            //user.setUserName(userDTO.getUserName());
//            user.setUserPassword(userDTO.getUserPassword());
//
//            userRepo.save(user);
//
//            UserDTO userDTO1 = new UserDTO(
//                    user.getUserId(),
//                    user.getUserName(),
//                    user.getUserPassword()
//            );
//
//            return userDTO1;
//        }else{
//            throw new RuntimeException("no data found that id");
//        }
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) {
        if(userRepo.existsByUserName(userDTO.getUserName())){
            User user = userRepo.findByUserName(userDTO.getUserName());

            UserDTO userDTO1 = new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getUserPassword()
            );

            return userDTO1;

//            String requstedPassword = userDTO.getUserPassword();
//            String userPassword = user.getUserPassword();
//
//            boolean isEqual = userPassword.equals(requstedPassword);
//
//            if(isEqual == true){
//                return userDTO.getUserName() + " You can log in";
//            }else {
//                return userDTO.getUserName() + " Password wrong";
//            }
        }else {
            throw new RuntimeException("No user name found");
        }
    }
}
