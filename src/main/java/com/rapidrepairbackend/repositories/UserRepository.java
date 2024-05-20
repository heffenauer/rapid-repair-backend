package com.rapidrepairbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rapidrepairbackend.User;


import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findByEmail(String email);

    List<User> existsByEmail(String email);
}
