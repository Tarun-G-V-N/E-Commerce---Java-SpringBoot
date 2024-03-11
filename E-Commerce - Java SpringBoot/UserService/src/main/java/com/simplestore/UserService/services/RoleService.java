package com.simplestore.UserService.services;

import com.simplestore.UserService.models.Role;
import com.simplestore.UserService.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public Role createRole(String name) {

        Role role = new Role();
        role.setRoleName(name);

        return roleRepository.save(role);
    }
}
