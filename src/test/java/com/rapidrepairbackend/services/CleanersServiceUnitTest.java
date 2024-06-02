package com.rapidrepairbackend.services;


import com.rapidrepairbackend.data.CleanersTestData;
import com.rapidrepairbackend.entity.Cleaners;
import com.rapidrepairbackend.repositories.CleanersRepository;
import com.rapidrepairbackend.service.CleanersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class CleanersServiceUnitTest {
    @MockBean
    private CleanersRepository cleanersRepository;

    @TestConfiguration
    static class CleanersServiceTestConfiguration {
        @Bean
        @Primary
        public CleanersService cleanersService(CleanersRepository cleanersRepository) {
            return new CleanersService(cleanersRepository);
        }
    }

    @Autowired
    private CleanersService cleanersService;

    @Test
    public void givenCleaners_whenGetCleanersList_thenReturnListOfCleaners() {

        Mockito.when(cleanersRepository.findAll())
                .thenReturn(List.of(CleanersTestData.cleaner1(), CleanersTestData.cleaner2()));

        List<Cleaners> result = cleanersService.getCleanersList();

        assertThat(result).hasSize(2);
    }

    @Test
    public void givenNoCleaners_whenGetCleanersList_thenReturnEmptyList() {

        Mockito.when(cleanersRepository.findAll())
                .thenReturn(Collections.EMPTY_LIST);

        List<Cleaners> result = cleanersService.getCleanersList();

        assertThat(result).isEmpty();
    }


    @Test
    public void givenCleaner_whenCreateCleaner_thenCleanerIsCreated() {
        Cleaners newCleaner = CleanersTestData.cleaner1();
        Mockito.when(cleanersRepository.save(newCleaner))
                .thenReturn(newCleaner);

        Cleaners result = cleanersService.createCleaner(newCleaner);

        assertThat(result).isEqualTo(newCleaner);
    }

    @Test
    public void givenCleanerId_whenGetCleanerById_thenReturnCleaner() {
        Cleaners cleaner = CleanersTestData.cleaner1();
        Mockito.when(cleanersRepository.findById(cleaner.getId()))
                .thenReturn(java.util.Optional.of(cleaner));

        Cleaners result = cleanersService.getCleanerById(cleaner.getId());

        assertThat(result).isEqualTo(cleaner);
    }


    @Test
    public void givenCleaner_whenUpdateCleaner_thenCleanerIsUpdated() {
        Cleaners existingCleaner = CleanersTestData.cleaner1();
        Cleaners updatedCleaner = CleanersTestData.cleaner2();

        Mockito.when(cleanersRepository.findById(existingCleaner.getId()))
                .thenReturn(Optional.of(existingCleaner));
        Mockito.when(cleanersRepository.save(existingCleaner))
                .thenReturn(existingCleaner);

        Cleaners result = cleanersService.updateCleaner(existingCleaner.getId(), updatedCleaner);

        assertThat(result).isEqualTo(existingCleaner);
        assertThat(result.getFirstName()).isEqualTo(updatedCleaner.getFirstName());
        assertThat(result.getLastName()).isEqualTo(updatedCleaner.getLastName());
        assertThat(result.getPhoneNumber()).isEqualTo(updatedCleaner.getPhoneNumber());
        assertThat(result.getRating()).isEqualTo(updatedCleaner.getRating());
        assertThat(result.getDescription()).isEqualTo(updatedCleaner.getDescription());
        assertThat(result.getEmail()).isEqualTo(updatedCleaner.getEmail());
    }

    @Test
    public void givenInvalidCleanerId_whenUpdateCleaner_thenReturnNullAndNoUpdateHappens() {
        long invalidId = 999L;
        Cleaners updatedCleaner = CleanersTestData.cleaner2();


        Mockito.when(cleanersRepository.existsById(invalidId)).thenReturn(false);

        Cleaners result = cleanersService.updateCleaner(invalidId, updatedCleaner);


        assertThat(result).isNull();
        Mockito.verify(cleanersRepository, Mockito.never()).save(Mockito.any(Cleaners.class));
    }


    @Test
    public void givenValidCleanerId_whenDeleteCleaner_thenRepositoryCalled() {
        // Arrange
        long validId = 1L;  // Example valid ID
        Mockito.when(cleanersRepository.existsById(validId)).thenReturn(true);

        // Act
        cleanersService.deleteCleaner(validId);

        // Assert
        verify(cleanersRepository, times(1)).deleteById(validId);
    }

    @Test
    public void givenInvalidCleanerId_whenDeleteCleaner_thenThrowException() {

        long invalidId = 999L;
        Mockito.when(cleanersRepository.existsById(invalidId)).thenReturn(false);

        try {
            cleanersService.deleteCleaner(invalidId);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).contains("Cleaner with ID " + invalidId + " not found.");
        }
    }


}

