package com.pique.pique.domain.dtos.user;

import com.pique.pique.domain.entities.TransactionEntity;
import com.pique.pique.domain.entities.UserEntity;

public record UserDTOResponse(
        String firstName,
        String lastName,
        String document,
        String email
){
    public static UserDTOResponse fromUserDTO(UserDTO userDTO) {
        return new UserDTOResponse(userDTO.firstName(), userDTO.lastName(), userDTO.document(), userDTO.email());
    }

    public static UserDTOResponse fromUserEntity(UserEntity userEntity){
        return new UserDTOResponse(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDocument(),
                userEntity.getEmail()
        );
    }
}
