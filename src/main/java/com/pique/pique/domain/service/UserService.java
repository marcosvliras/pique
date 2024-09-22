package com.pique.pique.domain.service;

import com.pique.pique.domain.dtos.UserDTO;
import com.pique.pique.domain.entities.UserEntity;
import com.pique.pique.domain.exceptions.UserAlreadyExistsException;
import com.pique.pique.domain.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) throws Exception {
        Optional<UserEntity> user = userRepository.findByDocumentAndEmail(userDTO.document(), userDTO.email());
        if (user.isPresent()) {
              throw new UserAlreadyExistsException("User already exists");
        }

        var newUser = new UserEntity(
                userDTO.firstName(),
                userDTO.lastName(),
                userDTO.document(),
                userDTO.email(),
                userDTO.password(),
                userDTO.userType()
        );

        userRepository.save(newUser);
        return userDTO;
    }
}
