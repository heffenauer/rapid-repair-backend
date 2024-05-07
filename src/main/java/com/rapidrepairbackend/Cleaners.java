package com.rapidrepairbackend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cleaners {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String description;
    private double rating;

}
