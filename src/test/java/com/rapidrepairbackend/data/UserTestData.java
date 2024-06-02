package com.rapidrepairbackend.data;

import com.rapidrepairbackend.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserTestData {

    private static long id1 = 1L;
    private static String firstName1 = "Adi";
    private static String lastName1 = "Okeric";
    private static String email1 = "adi.okeric@example.com";
    private static String password1 = "password123";
    private static String role1 = "USER";
    private static String country1 = "Bosnia";
    private static String timezone1 = "CET";
    private static String website1 = "https://adiokeric.com";
    private static String bio1 = "Software Developer";
    private static String username1 = "adiokeric";

    private static long id2 = 2L;
    private static String firstName2 = "Din";
    private static String lastName2 = "Becirbasic";
    private static String email2 = "din.becirbasic@example.com";
    private static String password2 = "securepassword";
    private static String role2 = "ADMIN";
    private static String country2 = "Bosnia";
    private static String timezone2 = "CET";
    private static String website2 = "https://dinbecirbasic.com";
    private static String bio2 = "Project Manager";
    private static String username2 = "dinbecirbasic";

    private static long id3 = 3L;
    private static String firstName3 = "Hadzera";
    private static String lastName3 = "Zukanovic";
    private static String email3 = "hadzera.zukanovic@example.com";
    private static String password3 = "mypassword";
    private static String role3 = "USER";
    private static String country3 = "Bosnia";
    private static String timezone3 = "CET";
    private static String website3 = "https://hadzerazukanovic.com";
    private static String bio3 = "Graphic Designer";
    private static String username3 = "hadzerazukanovic";

    public static User user1() {
        return User.builder()
                .id(id1)
                .firstName(firstName1)
                .lastName(lastName1)
                .email(email1)
                .password(password1)
                .role(role1)
                .country(country1)
                .timezone(timezone1)
                .website(website1)
                .bio(bio1)
                .username(username1)
                .build();
    }

    public static User user2() {
        return User.builder()
                .id(id2)
                .firstName(firstName2)
                .lastName(lastName2)
                .email(email2)
                .password(password2)
                .role(role2)
                .country(country2)
                .timezone(timezone2)
                .website(website2)
                .bio(bio2)
                .username(username2)
                .build();
    }

    public static User user3() {
        return User.builder()
                .id(id3)
                .firstName(firstName3)
                .lastName(lastName3)
                .email(email3)
                .password(password3)
                .role(role3)
                .country(country3)
                .timezone(timezone3)
                .website(website3)
                .bio(bio3)
                .username(username3)
                .build();
    }

    public static User userWithDifferentId(long id) {
        return User.builder()
                .id(id)
                .firstName(firstName1)
                .lastName(lastName1)
                .email(email1)
                .password(password1)
                .role(role1)
                .country(country1)
                .timezone(timezone1)
                .website(website1)
                .bio(bio1)
                .username(username1)
                .build();
    }
}
