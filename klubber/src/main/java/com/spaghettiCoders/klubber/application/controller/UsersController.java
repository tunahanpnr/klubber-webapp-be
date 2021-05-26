package com.spaghettiCoders.klubber.application.controller;

import com.spaghettiCoders.klubber.application.dto.UserDTO;
import com.spaghettiCoders.klubber.application.entity.Users;
import com.spaghettiCoders.klubber.application.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/fetchusers")
    @PreAuthorize("permitAll()")
    public List<UserDTO> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/getuser/{username}")
    @PreAuthorize("permitAll()")
    public UserDTO getUser(@PathVariable String username) {
        return usersService.getUser(username);
    }
}
