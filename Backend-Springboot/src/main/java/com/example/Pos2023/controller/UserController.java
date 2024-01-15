package com.example.Pos2023.controller;

import com.example.Pos2023.dto.UserDTO;
import com.example.Pos2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/users")
public class UserController
{

	@Autowired
	private UserService userService;

	@PostMapping(path = "/save")
	public ResponseEntity<Object> saveUser( @RequestBody UserDTO userDTO )
	{
		String userSavedMessage = userService.saveUser( userDTO );

		Map<String, String> response = new HashMap<>();
		response.put( "message", userSavedMessage );
		return ResponseEntity.ok( response );
	}

	@PutMapping(path = "/update/{userId}")
	public ResponseEntity<Object> updateUser( @RequestBody UserDTO userDTO, @PathVariable(value = "userId") int userId )
	{
		UserDTO updatedUser = userService.updateUser( userDTO,userId );

//		Map<String, String> response = new HashMap<>();
//		response.put( "message", message );
		return ResponseEntity.ok( updatedUser );
	}

	@PostMapping(path = "/login")
	public ResponseEntity<Object> loginUser( @RequestBody UserDTO userDTO )
	{
		UserDTO user = userService.loginUser( userDTO );

		return ResponseEntity.ok( user );
	}
}

//    @GetMapping(path = "/login")
//    public String loginUser(@RequestBody UserDTO userDTO){
//        String message = userService.loginUser(userDTO);
//        return message;
//    }

//    @PutMapping(path = "/update")
//    public String updateUser(@RequestBody UserDTO userDTO){
//        String message = userService.updateUser(userDTO);
//        return message;
//    }

//    @PostMapping(path = "/save")
//    public String saveUser(@RequestBody UserDTO userDTO){
//
//        String userSavedMessage = userService.saveUser(userDTO);
//        return userSavedMessage;
//    }
