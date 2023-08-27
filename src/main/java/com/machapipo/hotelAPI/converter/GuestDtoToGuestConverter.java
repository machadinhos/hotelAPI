package com.machapipo.hotelAPI.converter;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.model.Guest;
import com.machapipo.hotelAPI.repo.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuestDtoToGuestConverter extends AbstractConverter<GuestDto, Guest> {

    private GuestRepo guestRepo;


    @Autowired
    public void setGuestRepo (GuestRepo guestRepo) {

        this.guestRepo = guestRepo;
    }


    @Override
    public Guest convert (GuestDto source) {

        Guest guest;
        if (source.getId() != null) {
            guest = guestRepo.findById(source.getId()).orElse(new Guest());
        } else {
            guest = new Guest();
        }

        guest.setFirstName(source.getFirstName());
        guest.setLastName(source.getLastName());
        guest.setEmail(source.getEmail());
        guest.setPhoneNumber(source.getPhoneNumber());

        return guest;
    }

}
