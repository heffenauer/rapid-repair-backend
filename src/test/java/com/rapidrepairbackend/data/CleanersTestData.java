package com.rapidrepairbackend.data;

import com.rapidrepairbackend.entity.Cleaners;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CleanersTestData {

    public static Cleaners cleaner1() {
        Cleaners cleaner = new Cleaners();
        cleaner.setId(1L);
        cleaner.setFirstName("Ana");
        cleaner.setLastName("Dan");
        cleaner.setEmail("ana@gmail.com");
        cleaner.setPhoneNumber("1234567890");
        cleaner.setDescription("bla bla bla");
        cleaner.setRating(3.9);
        return cleaner;

    }

    public static Cleaners cleaner2() {
        Cleaners cleaner = new Cleaners();
        cleaner.setId(2L);
        cleaner.setFirstName("Adam");
        cleaner.setLastName("Brody");
        cleaner.setEmail("adam@gmail.com");
        cleaner.setPhoneNumber("4567890");
        cleaner.setDescription("hihi bla bla");
        cleaner.setRating(4.9);
        return cleaner;

    }
}
