package com.machapipo.hotelAPI.command.converter;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.persistence.model.Guest;
import com.machapipo.hotelAPI.service.GuestService;
import com.machapipo.hotelAPI.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuestDtoToGuestConverter extends AbstractConverter<GuestDto, Guest> {

    private GuestService guestService;
    private RoomService roomService;


    @Autowired
    public void setGuestService (GuestService guestService) {

        this.guestService = guestService;
    }


    @Autowired
    public void setRoomService (RoomService roomService) {

        this.roomService = roomService;
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
        guest.setCheckedIn(source.getCheckedIn());
        if (source.getRoomId() != null) {
            guest.setRoom(roomService.getById(source.getRoomId()));
        }

        return guest;
    }

}
