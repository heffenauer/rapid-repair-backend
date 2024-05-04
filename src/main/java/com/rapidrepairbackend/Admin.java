package com.rapidrepairbackend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class  Admin{
    private long id;
    private String username;
    private String password;
    private String email;

}
