package com.rapidrepair.backend.repository;

import com.rapidrepair.backend.dto.AdDTO;
import com.rapidrepair.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    @Query("SELECT new com.rapidrepair.backend.dto.AdDTO(a.id, a.serviceName, a.description, a.price, null, a.img, u.id, u.name) " +
            "FROM User u JOIN u.ads a WHERE u.id = :userId")
    List<AdDTO> findUserWithAds(@Param("userId") Long userId);
}
