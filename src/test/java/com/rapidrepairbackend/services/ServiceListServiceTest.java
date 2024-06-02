// /src/test/java/com/rapidrepairbackend/service/ServiceListServiceTest.java
package com.rapidrepairbackend.services;

import com.rapidrepairbackend.entity.ServiceEntity;
import com.rapidrepairbackend.repositories.ServiceRepository;
import com.rapidrepairbackend.service.ServiceListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceListServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceListService serviceListService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetServices() {
        ServiceEntity service1 = new ServiceEntity();
        ServiceEntity service2 = new ServiceEntity();
        List<ServiceEntity> services = Arrays.asList(service1, service2);

        when(serviceRepository.findAll()).thenReturn(services);

        List<ServiceEntity> result = serviceListService.getServices();

        assertEquals(2, result.size());
        verify(serviceRepository, times(1)).findAll();
    }

    @Test
    public void testGetServiceById_Found() {
        ServiceEntity service = new ServiceEntity();
        service.setId(1L);

        when(serviceRepository.existsById(1L)).thenReturn(true);
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));

        ServiceEntity result = serviceListService.getServiceById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(serviceRepository, times(1)).existsById(1L);
        verify(serviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetServiceById_NotFound() {
        when(serviceRepository.existsById(1L)).thenReturn(false);

        ServiceEntity result = serviceListService.getServiceById(1L);

        assertNull(result);
        verify(serviceRepository, times(1)).existsById(1L);
        verify(serviceRepository, times(0)).findById(1L);
    }

    @Test
    public void testDeleteServiceById_Found() {
        when(serviceRepository.existsById(1L)).thenReturn(true);

        serviceListService.deleteServiceById(1L);

        verify(serviceRepository, times(1)).existsById(1L);
        verify(serviceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteServiceById_NotFound() {
        when(serviceRepository.existsById(1L)).thenReturn(false);

        serviceListService.deleteServiceById(1L);

        verify(serviceRepository, times(1)).existsById(1L);
        verify(serviceRepository, times(0)).deleteById(1L);
    }

    @Test
    public void testCreateService() {
        ServiceEntity service = new ServiceEntity();
        when(serviceRepository.save(any(ServiceEntity.class))).thenReturn(service);

        ServiceEntity result = serviceListService.createService(service);

        assertNotNull(result);
        verify(serviceRepository, times(1)).save(service);
    }

    @Test
    public void testUpdateService_Found() {
        ServiceEntity service = new ServiceEntity();
        service.setId(1L);

        when(serviceRepository.existsById(1L)).thenReturn(true);
        when(serviceRepository.save(any(ServiceEntity.class))).thenReturn(service);

        ServiceEntity updatedService = new ServiceEntity();
        ServiceEntity result = serviceListService.updateService(1L, updatedService);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(serviceRepository, times(1)).existsById(1L);
        verify(serviceRepository, times(1)).save(updatedService);
    }

    @Test
    public void testUpdateService_NotFound() {
        when(serviceRepository.existsById(1L)).thenReturn(false);

        ServiceEntity updatedService = new ServiceEntity();
        ServiceEntity result = serviceListService.updateService(1L, updatedService);

        assertNull(result);
        verify(serviceRepository, times(1)).existsById(1L);
        verify(serviceRepository, times(0)).save(updatedService);
    }
}
