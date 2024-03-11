package com.simplestore.UserService.controllers;

import com.simplestore.UserService.dtos.SetUserRolesDTO;
import com.simplestore.UserService.dtos.UserDTO;
import com.simplestore.UserService.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") long userId) {

        UserDTO userDTO = userService.getUserDetails(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(@PathVariable("id") long userId, @RequestBody SetUserRolesDTO setUserRolesDTO) {

        UserDTO userDTO = userService.setUserRoles(userId, setUserRolesDTO.getRoleIds());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
