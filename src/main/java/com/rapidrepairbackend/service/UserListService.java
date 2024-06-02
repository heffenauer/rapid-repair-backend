package com.rapidrepairbackend.service;

import com.rapidrepairbackend.entity.User;
import com.rapidrepairbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserListService {

    private UserRepository userRepository;

    public UserListService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        if (this.userRepository.existsById(id)) {
            return this.userRepository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    public void deleteUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        }
    }

    public User createUser(User newUser) {
        if (this.userRepository.count() >= 50) {
            return null;
        }
        if (this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return this.userRepository.save(newUser);
    }

    public User updateUser(Long id, User user) {
        if (this.userRepository.existsById(id)) {
            if (this.userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
                throw new RuntimeException("Email already in use by another user");
            }
            return this.userRepository.save(user); // Save the updated user
        }
        return null;
    }
}
