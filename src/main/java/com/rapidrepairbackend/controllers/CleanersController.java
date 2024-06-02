package com.rapidrepairbackend.controllers;

import com.rapidrepairbackend.service.CleanersService;
import com.rapidrepairbackend.entity.Cleaners;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("cleaners")
@RestController
public class CleanersController {

    private final CleanersService cleanersService;

    public CleanersController(CleanersService cleanersService) {
        this.cleanersService = cleanersService;
    }

    @GetMapping("list")
    public List<Cleaners> getCleaners() {
        return this.cleanersService.getCleanersList();
    }

    @PostMapping()
    public Cleaners createCleaners(@RequestBody Cleaners cleaner) {
        return this.cleanersService.createCleaner(cleaner);
    }



/*

    @GetMapping("{id}")
    public Cleaners getCleanersById(@PathVariable Integer id) {
        return this.cleanersService.getCleanerById(id);
    }

    @DeleteMapping("{id}")
    public void deleteCleaners(@PathVariable Long id) {
        this.cleanersService.deleteCleaner(id);
    }



    @PutMapping("{id}")
    public Cleaners updateCleaners(@PathVariable Long id,@RequestBody Cleaners cleaner) {
        return this.cleanersService.updateCleaner(id,cleaner);
    }


*/

}

