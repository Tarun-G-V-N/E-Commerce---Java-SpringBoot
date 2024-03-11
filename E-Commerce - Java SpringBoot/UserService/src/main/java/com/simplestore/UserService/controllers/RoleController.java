package com.simplestore.UserService.controllers;

import com.simplestore.UserService.models.Role;
import com.simplestore.UserService.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {

        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(String roleName) {

        Role role = roleService.createRole(roleName);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
