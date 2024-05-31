package com.rapidrepairbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @CrossOrigin(origins = "https://rapid-repair.netlify.app")
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate() {
        return ResponseEntity.ok("Authenticated successfully");
    }
}
