package com.simplestore.UserService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private int errorCode;
    private String errorMessage;
}
