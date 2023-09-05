package com.machapipo.hotelAPI.command.converter;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.persistence.model.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestToGuesDtoConverter extends AbstractConverter<Guest, GuestDto> {

    @Override
    public GuestDto convert (Guest guest) {

        GuestDto guestDto = new GuestDto();

        guestDto.setId(guest.getId());
        guestDto.setFirstName(guest.getFirstName());
        guestDto.setLastName(guest.getLastName());
        guestDto.setEmail(guest.getEmail());
        guestDto.setPhoneNumber(guest.getPhoneNumber());
        guestDto.setCheckedIn(guest.getCheckedIn());
        if (guest.getRoom() != null) {
            guestDto.setRoomId(guest.getRoom().getId());
        }

        return guestDto;
    }

}
