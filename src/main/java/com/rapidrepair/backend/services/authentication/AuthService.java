package com.rapidrepair.backend.services.authentication;

import com.rapidrepair.backend.dto.SignupRequestDTO;
import com.rapidrepair.backend.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDTO signupRequestDTO);

    UserDto signupCompany(SignupRequestDTO signupRequestDTO);

    boolean presentByEmail(String email);
}
