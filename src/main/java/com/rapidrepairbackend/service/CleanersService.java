package com.rapidrepairbackend.service;

import com.rapidrepairbackend.entity.Cleaners;
import com.rapidrepairbackend.repositories.CleanersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleanersService {

    private final CleanersRepository cleanersRepository;

    public CleanersService(CleanersRepository cleanersRepository) {
        this.cleanersRepository = cleanersRepository;
    }

    public List<Cleaners> getCleanersList() {
        return this.cleanersRepository.findAll();
    }


    public Cleaners createCleaner(Cleaners cleaner) {
        return this.cleanersRepository.save(cleaner);
    }

    public Cleaners getCleanerById(long id) {
        return this.cleanersRepository.findById(id).orElse(null);
    }

    public Cleaners updateCleaner(Long id, Cleaners cleaner) {
        Cleaners existingCleaner = this.cleanersRepository.findById(id).orElse(null);
        if (existingCleaner != null) {
            existingCleaner.setFirstName(cleaner.getFirstName());
            existingCleaner.setLastName(cleaner.getLastName());
            existingCleaner.setPhoneNumber(cleaner.getPhoneNumber());
            existingCleaner.setRating(cleaner.getRating());
            existingCleaner.setDescription(cleaner.getDescription());
            existingCleaner.setEmail(cleaner.getEmail());
            return this.cleanersRepository.save(existingCleaner);
        }
        return null;
    }

    public void deleteCleaner(long id) {
        if (this.cleanersRepository.existsById(id)) {
            this.cleanersRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cleaner with ID " + id + " not found.");
        }
    }

}

