package com.rapidrepairbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rapidrepairbackend.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User findFirstByUsername(String username);
}
