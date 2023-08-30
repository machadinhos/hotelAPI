package com.machapipo.hotelAPI.converter;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.persistence.model.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestToGuesDtoConverter extends AbstractConverter<Guest, GuestDto> {

    @Override
    public GuestDto convert (Guest source) {

        GuestDto guestDto = new GuestDto();

        guestDto.setId(source.getId());
        guestDto.setFirstName(source.getFirstName());
        guestDto.setLastName(source.getLastName());
        guestDto.setEmail(source.getEmail());
        guestDto.setPhoneNumber(source.getPhoneNumber());

        return guestDto;
    }

}
