package com.pique.pique.domain.dtos;

import com.pique.pique.domain.entities.enums.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record UserDTO(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull @Valid String document,
        @NotNull String email,
        @NotNull String password,
        @NotNull UserType userType
     ){
     public UserDTO withoutPassword() {
          return new UserDTO(firstName, lastName, document, email, null, userType);
     }
}

