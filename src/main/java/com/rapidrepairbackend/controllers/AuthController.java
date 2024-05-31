package com.rapidrepairbackend.controllers;

import com.rapidrepairbackend.ApiResponse;
import com.rapidrepairbackend.entity.AuthenticationRequestPayload;
import com.rapidrepairbackend.entity.AuthenticationResponsePayload;
import com.rapidrepairbackend.service.CustomUserDetailsService;
import com.rapidrepairbackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    @ResponseBody
    public ApiResponse<AuthenticationResponsePayload> createAuthenticationToken(
            @RequestBody AuthenticationRequestPayload payload
    ) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword())
            );
            customUserDetailsService.loadUserByUsername(payload.getEmail());
            final String jwt = jwtTokenUtil.generateToken(payload.getEmail());
            return ApiResponse.success(new AuthenticationResponsePayload(jwt));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ApiResponse.error("Error authenticating! " + e.getMessage());
        }
    }
}
