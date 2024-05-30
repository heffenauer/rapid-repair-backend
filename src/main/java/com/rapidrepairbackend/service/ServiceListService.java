// /src/main/java/com/rapidrepairbackend/ServiceListService.java
package com.rapidrepairbackend.service;

import com.rapidrepairbackend.entity.ServiceEntity;
import com.rapidrepairbackend.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceListService {

  private final ServiceRepository serviceRepository;

  public ServiceListService(ServiceRepository serviceRepository) {
    this.serviceRepository = serviceRepository;
  }

  public List<ServiceEntity> getServices() {
    return this.serviceRepository.findAll();
  }

  public ServiceEntity getServiceById(Long id) {
    if (this.serviceRepository.existsById(id)) {
      return this.serviceRepository.findById(id).orElse(null);
    }
    return null;
  }

  public void deleteServiceById(Long id) {
    if (this.serviceRepository.existsById(id)) {
      this.serviceRepository.deleteById(id);
    }
  }

  public ServiceEntity createService(ServiceEntity newService) {
    return this.serviceRepository.save(newService);
  }

  public ServiceEntity updateService(Long id, ServiceEntity service) {
    if (this.serviceRepository.existsById(id)) {
      service.setId(id); // Ensure the ID is set correctly
      return this.serviceRepository.save(service);
    }
    return null;
  }
}
