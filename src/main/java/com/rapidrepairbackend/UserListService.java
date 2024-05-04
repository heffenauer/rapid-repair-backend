package com.rapidrepairbackend;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserListService {

    private long id = 0;
    private List<User> users = new ArrayList<>();

    public UserListService(List<User> users) {
        User adi = new User(this.id++, "Adi", "Okeric", 22, "adi.okeric@stu.ssst.edu.ba", "adi123");
        User hadzera = new User(this.id++, "Hadzera", "Zukanovic", 21, "hadzera.zukanovic@stu.ssst.edu.ba", "hadzera123");
        User din = new User(this.id++, "Din", "Becirbasic", 22, "din.becirbasic@stu.ssst.edu.ba", "din123");
        this.users.add(adi);
        this.users.add(hadzera);
        this.users.add(din);

    }

    public List<User> getUsers() {
        return this.users;
    }


    public User getUserById(Long id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        System.out.println("The user does not exist with id " + id + "." + " Try again.");
        return null;
    }

    public String getUserByEmail(String email) {
        for (User user : this.users) {
            if (user.getEmail().equals(email)) {
                return user.getName();
            }
        }
        System.out.println("There is no users with such email: " + email);
        return null;
    }

    public void deleteUserById(Long id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                this.users.remove(user);
            }
        }
        System.out.println("The user does not exist with id " + id + "." + " Try again.");
    }

    public User createUser(User newUser) {
        if (newUser.getName() == "" || newUser.getSurname() == "" || newUser.getAge() == 0 || newUser.getEmail() == "" || newUser.getPassword() == "") {
            System.out.println("Please enter all details");
        } else {

            newUser.setId(this.id++);
            this.users.add(newUser);
            return newUser;
        }
        return null;
    }

    public User updateUser(Long id, User user) {
        User current = this.getUserById(id);
        if (current == null) {
            System.out.println("The user does not exist with id " + id + "." + " Try again.");
            return null;
        }
        current.setName(user.getName());
        current.setSurname(user.getSurname());
        current.setAge(user.getAge());
        current.setEmail(user.getEmail());
        current.setPassword(user.getPassword());
        return current;

    }
}
