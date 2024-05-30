package com.rapidrepairbackend.service;

import com.rapidrepairbackend.entity.SimpleUser;
import com.rapidrepairbackend.entity.User;
import com.rapidrepairbackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = getFullUserByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.emptyList());
    }

    public SimpleUser getUserByUsername(String userName) {
        getFullUserByUsername(userName); // user exists?
        return new SimpleUser(userName);
    }

    private User getFullUserByUsername(String userName) {
        return userRepository.findFirstByUsername(userName);
    }
}
