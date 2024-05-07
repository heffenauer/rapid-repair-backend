package com.rapidrepairbackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutdoorHelpOffersService {

    private long id =0;

    private List<OutdoorHelpOffers> outdoorHelpOffers = new ArrayList<>();

    public OutdoorHelpOffersService() {
        this.outdoorHelpOffers.add(new OutdoorHelpOffers(this.id++,"Yard Work", "Revamp your yard with our top-notch services, from lawn care to landscaping, for a stunning outdoor space.",
                "60 min", 100));

        this.outdoorHelpOffers.add(new OutdoorHelpOffers(this.id++,"Tree Care", "Tree trimming, removal, and maintenance services to ensure the health and safety of your trees.",
                "90 min", 200));

        this.outdoorHelpOffers.add(new OutdoorHelpOffers(this.id++,"Pest Control", "Providing solutions to control pests like mosquitoes, ticks, and other insects to make your outdoor space more enjoyable.",
                "120 min", 100));



    }
    public List<OutdoorHelpOffers> getOutdoorHelpList() {
        return this.outdoorHelpOffers;
    }

    public OutdoorHelpOffers getOutdoorHelpOffersById(long id){
        for (OutdoorHelpOffers outdoorHelpOffer : this.outdoorHelpOffers) {
            if(outdoorHelpOffer.getId() == id) {
                return outdoorHelpOffer;
            }
        }
        return null;
    }


    public void deleteOutdoorHelpOffers(long id){
        List<OutdoorHelpOffers> outdoorHelpOffersList = this.outdoorHelpOffers;
        for (int i = 0; i < outdoorHelpOffersList.size(); i++) {
            if(outdoorHelpOffersList.get(i).getId() == id) {
                this.outdoorHelpOffers.remove(i);
                return;

            }
        }

    }

    public OutdoorHelpOffers createOutdoorHelpOffers(OutdoorHelpOffers outdoorHelpOffer) {
        outdoorHelpOffer.setId(this.id++);
        this.outdoorHelpOffers.add(outdoorHelpOffer);
        return outdoorHelpOffer;
    }

    public OutdoorHelpOffers updateOutdoorHelpOffers(Long id, OutdoorHelpOffers outdoorHelpOffer) {
        OutdoorHelpOffers current = this.getOutdoorHelpOffersById(id);
        if(current == null){
            return null;
        }

        current.setName(outdoorHelpOffer.getName());
        current.setDescription(outdoorHelpOffer.getDescription());
        current.setDuration(outdoorHelpOffer.getDuration());
        current.setPrice(outdoorHelpOffer.getPrice());
        return current;


    }


}



