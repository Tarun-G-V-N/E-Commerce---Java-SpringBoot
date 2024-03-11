package com.simplestore.UserService.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMapAdapter;

@Getter
@Setter
public class LoginResponseDTO {

    private UserDTO userDTO;
    private MultiValueMapAdapter<String, String> headers;
}
