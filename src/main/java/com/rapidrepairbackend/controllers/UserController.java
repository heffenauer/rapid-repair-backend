package com.rapidrepairbackend.controllers;

import com.rapidrepairbackend.dto.UserRegistrationDto;
import com.rapidrepairbackend.entity.User;
import com.rapidrepairbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping()
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        User registeredUser = userService.registerUser(registrationDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }


}
