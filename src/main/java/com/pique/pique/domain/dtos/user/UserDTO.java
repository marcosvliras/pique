package com.pique.pique.domain.dtos.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pique.pique.domain.entities.enums.UserType;
import com.pique.pique.domain.exceptions.InvalidUserException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record UserDTO(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull @Valid String document,
        @NotNull String email,
        @NotNull String password,
        BigDecimal balance,
        @NotNull UserType userType
     ){

     @JsonCreator
     public UserDTO(
             @JsonProperty("firstName") String firstName,
             @JsonProperty("lastName") String lastName,
             @JsonProperty("document") String document,
             @JsonProperty("email") String email,
             @JsonProperty("password") String password,
             @JsonProperty("balance") BigDecimal balance,
             @JsonProperty("userType") UserType userType
     ){
          this.firstName = firstName;
          this.lastName = lastName;
          this.document = document;
          this.email = email;
          this.password = password;
          this.userType = userType;

          if (balance == null) {
                 this.balance = BigDecimal.ZERO;
          } else {
                 this.balance = balance;
          }

          if (this.balance.compareTo(BigDecimal.ZERO) < 0) {
               throw new InvalidUserException("Balance must be greater than or equal to zero");
          }

     }

     public UserDTO withoutPassword() {
          return new UserDTO(firstName, lastName, document, email, null, balance,userType);
     }
}

