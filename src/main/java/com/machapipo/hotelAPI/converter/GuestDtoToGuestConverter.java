package com.machapipo.hotelAPI.converter;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.persistence.model.Guest;
import com.machapipo.hotelAPI.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuestDtoToGuestConverter extends AbstractConverter<GuestDto, Guest> {

    private GuestService guestService;


    @Autowired
    public void setGuestService (GuestService guestService) {

        this.guestService = guestService;
    }


    @Override
    public Guest convert (GuestDto source) {

        Guest guest;

        if (source.getId() != null) {
            guest = guestService.getById(source.getId());
            if (guest == null) {
                guest = new Guest();
            }
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
