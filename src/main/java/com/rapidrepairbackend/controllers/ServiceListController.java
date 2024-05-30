// /src/main/java/com/rapidrepairbackend/ServiceListController.java
package com.rapidrepairbackend.controllers;

import com.rapidrepairbackend.service.ServiceListService;
import com.rapidrepairbackend.entity.ServiceEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceListController {

  private final ServiceListService serviceService;

  public ServiceListController(ServiceListService serviceService) {
    this.serviceService = serviceService;
  }

  @GetMapping("list")
  public List<ServiceEntity> getServices() {
    return this.serviceService.getServices();
  }

  @GetMapping("/{id}")
  public ServiceEntity getServiceById(@PathVariable Long id) {
    return this.serviceService.getServiceById(id);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteServiceById(@PathVariable Long id) {
    this.serviceService.deleteServiceById(id);
  }

  @PostMapping("/create")
  public ServiceEntity addService(@RequestBody ServiceEntity service) {
    return this.serviceService.createService(service);
  }

  @PutMapping("/update/{id}")
  public ServiceEntity updateService(@PathVariable Long id, @RequestBody ServiceEntity service) {
    return this.serviceService.updateService(id, service);
  }
}
