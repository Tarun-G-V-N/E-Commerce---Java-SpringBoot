package com.simplestore.UserService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDTO {

    private String token;
    private long userID;
}
