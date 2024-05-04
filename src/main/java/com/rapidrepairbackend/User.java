package com.rapidrepairbackend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String password;
}
