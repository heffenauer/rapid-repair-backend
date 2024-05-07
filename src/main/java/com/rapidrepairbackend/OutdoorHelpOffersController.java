package com.rapidrepairbackend;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("outdoorhelpoffers")
@RestController
public class OutdoorHelpOffersController {

    private final OutdoorHelpOffersService outdoorHelpOffersService;

    public OutdoorHelpOffersController(OutdoorHelpOffersService outdoorHelpOffersService) {
        this.outdoorHelpOffersService = outdoorHelpOffersService;

    }

    @GetMapping("list")
    public List<OutdoorHelpOffers> getOutdoorHelpOffers() {
        return this.outdoorHelpOffersService.getOutdoorHelpList();
    }

    @GetMapping("{id}")
    public OutdoorHelpOffers getOutdoorHelpOffersById(@PathVariable Integer id) {
        return this.outdoorHelpOffersService.getOutdoorHelpOffersById(id);
    }

    @DeleteMapping("{id}")
    public void deleteOutdoorHelpOffers(@PathVariable Long id) {
        this.outdoorHelpOffersService.deleteOutdoorHelpOffers(id);
    }

    @PostMapping()
    public OutdoorHelpOffers createOutdoorHelpOffers(@RequestBody OutdoorHelpOffers outdoorHelpOffers) {
        return this.outdoorHelpOffersService.createOutdoorHelpOffers(outdoorHelpOffers);
    }

    @PutMapping("{id}")
    public OutdoorHelpOffers updateOutdoorHelpOffers(@PathVariable Long id, @RequestBody OutdoorHelpOffers outdoorHelpOffers) {
        return this.outdoorHelpOffersService.updateOutdoorHelpOffers(id, outdoorHelpOffers);
    }
}



