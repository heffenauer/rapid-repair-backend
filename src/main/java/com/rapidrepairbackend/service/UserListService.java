package com.rapidrepairbackend.service;


import com.rapidrepairbackend.entity.User;
import com.rapidrepairbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

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
        if (this.userRepository.existsById(id)) {
            return this.userRepository.findById(id).get();
        } else
            return null;
    }

    public void deleteUserById(Long id) {
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        }
    }

    public User createUser(User newUser) {
        if (this.userRepository.count() < 50) {
            return this.userRepository.save(newUser);
        }
        return null;
    }

    public User updateUser(Long id, User user) {
        if (this.userRepository.existsById(id)) {
            return this.userRepository.save(user); // Save the updated user
        }
        return null;
    }
}
