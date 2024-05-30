package com.rapidrepairbackend.service;


import com.rapidrepairbackend.entity.Cleaners;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CleanersService {

    private long id =0;

    private List<Cleaners> cleaners = new ArrayList<>();

    public CleanersService() {
        this.cleaners.add(new Cleaners(this.id++,"Adam", "Harry",
                "adamharry@gmail.com", "123456789",
                "3 years of experience in this field", 4.8));

        this.cleaners.add(new Cleaners(this.id++,"Brody", "Styles",
                "brodystyles@gmail.com", "223456789",
                "4 years of experience in this field", 4.6));

        this.cleaners.add(new Cleaners(this.id++,"Cici", "Long",
                "cicilong@gmail.com", "323456789",
                "5 years of experience in this field", 4.0));

        this.cleaners.add(new Cleaners(this.id++,"Debrah", "Mccan",
                "debrahmccan@gmail.com", "423456789",
                "1.5 years of experience in this field", 4.2));


    }

    public List<Cleaners> getCleanersList(){
        return this.cleaners;
    }


    public Cleaners getCleanerById(long id){
        for (Cleaners cleaner : this.cleaners) {
            if(cleaner.getId() == id) {
                return cleaner;
            }
        }
        return null;
    }

    public void deleteCleaner(long id){
        List<Cleaners> cleanersList = this.cleaners;
        for (int i = 0; i < cleanersList.size(); i++) {
            if(cleanersList.get(i).getId() == id) {
                this.cleaners.remove(i);
                return;

            }
        }

    }


    public Cleaners createCleaner(Cleaners cleaner) {
        cleaner.setId(this.id++);
        this.cleaners.add(cleaner);
        return cleaner;
    }

    public Cleaners updateCleaner(Long id, Cleaners cleaner) {
        Cleaners current = this.getCleanerById(id);
        if(current == null){
            return null;
        }

        current.setFirstName(cleaner.getFirstName());
        current.setLastName(cleaner.getLastName());
        current.setPhoneNumber(cleaner.getPhoneNumber());
        current.setRating(cleaner.getRating());
        current.setDescription(cleaner.getDescription());
        current.setEmail(cleaner.getEmail());
        return current;


    }
}
