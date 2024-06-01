package com.rapidrepairbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String country;
    private String timezone;
    private String website;
    private String bio;
    private String username;
}
