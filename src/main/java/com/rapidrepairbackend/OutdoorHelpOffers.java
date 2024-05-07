package com.rapidrepairbackend;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutdoorHelpOffers {

    private long id;
    private String name;
    private String description;
    private String duration;
    private int price;

}
