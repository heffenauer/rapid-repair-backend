package com.rapidrepairbackend.service;

import com.rapidrepairbackend.dto.UserRegistrationDto;
import com.rapidrepairbackend.entity.User;
import com.rapidrepairbackend.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(registrationDto.getRole());
        user.setCountry(registrationDto.getCountry());
        user.setTimezone(registrationDto.getTimezone());
        user.setWebsite(registrationDto.getWebsite());
        user.setBio(registrationDto.getBio());
        user.setUsername(registrationDto.getUsername());
        return userRepository.save(user);
    }
}
