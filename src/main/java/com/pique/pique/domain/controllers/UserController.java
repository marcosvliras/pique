package com.pique.pique.domain.controllers;

import com.pique.pique.domain.dtos.user.UserDTO;
import com.pique.pique.domain.dtos.user.UserDTOResponse;
import com.pique.pique.domain.exceptions.ErrorResponse;
import com.pique.pique.domain.exceptions.UserAlreadyExistsException;
import com.pique.pique.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserDTOResponse createUser(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO user = userService.createUser(userDTO);
        return UserDTOResponse.fromUserDTO(user);
    }
}
