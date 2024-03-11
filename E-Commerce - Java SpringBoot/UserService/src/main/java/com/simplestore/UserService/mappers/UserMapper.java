package com.simplestore.UserService.mappers;

import com.simplestore.UserService.dtos.UserDTO;
import com.simplestore.UserService.models.User;

public class UserMapper {

    public static UserDTO userToUserDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
