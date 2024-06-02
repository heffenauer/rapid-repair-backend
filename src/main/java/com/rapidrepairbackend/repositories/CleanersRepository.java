package com.rapidrepairbackend.repositories;

import com.rapidrepairbackend.entity.Cleaners;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CleanersRepository extends JpaRepository<Cleaners, Long> {
    List<Cleaners> findCleanerByFirstName(String firstName);
    boolean existsCleanerByFirstName(String firstName); // Correct method name: existsCleanerByFirstName
}
