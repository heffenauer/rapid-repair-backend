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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), Collections.emptyList());
    }

    public SimpleUser getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new SimpleUser(user.getEmail());
    }
}
