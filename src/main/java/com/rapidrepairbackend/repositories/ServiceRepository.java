package com.rapidrepairbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rapidrepairbackend.ServiceEntity;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
  List<ServiceEntity> findByName(String name);
  boolean existsByName(String name);
}
