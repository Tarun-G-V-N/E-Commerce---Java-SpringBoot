package com.simplestore.UserService.services;

import com.simplestore.UserService.dtos.UserDTO;
import com.simplestore.UserService.exceptions.UserNotFoundException;
import com.simplestore.UserService.mappers.UserMapper;
import com.simplestore.UserService.models.Role;
import com.simplestore.UserService.models.User;
import com.simplestore.UserService.repositories.RoleRepository;
import com.simplestore.UserService.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO getUserDetails(long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new UserNotFoundException(userId);
        return UserMapper.userToUserDTO(optionalUser.get());
    }

    public UserDTO setUserRoles(long userId, List<Long> roleIds) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new UserNotFoundException(userId);
        User user = optionalUser.get();

        Set<Role> roles = roleRepository.findAllByIdIn(roleIds);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        return UserMapper.userToUserDTO(savedUser);
    }
}
