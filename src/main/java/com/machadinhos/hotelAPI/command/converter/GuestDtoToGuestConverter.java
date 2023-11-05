package com.machadinhos.hotelAPI.command.converter;

import com.machadinhos.hotelAPI.service.GuestService;
import com.machadinhos.hotelAPI.service.RoomService;
import com.machadinhos.hotelAPI.command.GuestDto;
import com.machadinhos.hotelAPI.persistence.model.Guest;
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
    public Guest convert (GuestDto guestDto) {

        Guest guest;

        if (guestDto.getId() != null) {
            guest = guestService.getById(guestDto.getId());
            if (guest == null) {
                guest = new Guest();
            }
        } else {
            guest = new Guest();
        }

        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setEmail(guestDto.getEmail());
        guest.setPhoneNumber(guestDto.getPhoneNumber());
        guest.setCheckedIn(guestDto.getCheckedIn());
        if (guestDto.getRoomId() != null) {
            guest.setRoom(roomService.getById(guestDto.getRoomId()));
        } else {
            guest.setRoom(null);
        }

        return guest;
    }

}
