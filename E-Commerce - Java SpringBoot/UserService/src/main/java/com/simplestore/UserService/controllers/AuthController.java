package com.simplestore.UserService.controllers;

import com.simplestore.UserService.dtos.*;
import com.simplestore.UserService.models.SessionStatus;
import com.simplestore.UserService.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

        UserDTO userDTO = authService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        return new ResponseEntity<>(loginResponseDTO.getUserDTO(), loginResponseDTO.getHeaders(), HttpStatus.OK);
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable("id") Long id, @RequestHeader("token") String token) {

        return authService.logOut(id, token);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDTO validateTokenRequestDTO) {

        SessionStatus sessionStatus = authService.validate(validateTokenRequestDTO.getToken(), validateTokenRequestDTO.getUserID());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }
}
