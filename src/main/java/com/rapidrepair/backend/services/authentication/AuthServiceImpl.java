package com.rapidrepair.backend.services.authentication;

import com.rapidrepair.backend.dto.SignupRequestDTO;
import com.rapidrepair.backend.dto.UserDto;
import com.rapidrepair.backend.entity.User;
import com.rapidrepair.backend.enums.UserRole;
import com.rapidrepair.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signupClient(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setPhone(signupRequestDTO.getPhone());
        user.setRole(UserRole.CLIENT);  // Set role appropriately

        User savedUser = userRepository.save(user);
        return savedUser.getDto();
    }

    @Override
    public UserDto signupCompany(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setPhone(signupRequestDTO.getPhone());
        user.setRole(UserRole.COMPANY);  // Set role appropriately

        User savedUser = userRepository.save(user);
        return savedUser.getDto();
    }

    @Override
    public boolean presentByEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }
}
