package com.pique.pique.domain.dtos;

public record UserDTOResponse(
        String firstName,
        String lastName,
        String document,
        String email
){
    public static UserDTOResponse fromUserDTO(UserDTO userDTO) {
        return new UserDTOResponse(userDTO.firstName(), userDTO.lastName(), userDTO.document(), userDTO.email());
    }
}
