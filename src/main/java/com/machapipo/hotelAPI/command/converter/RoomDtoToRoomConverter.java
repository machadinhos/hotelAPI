package com.machapipo.hotelAPI.command.converter;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.exception.InvalidRoom;
import com.machapipo.hotelAPI.persistence.model.Room;
import com.machapipo.hotelAPI.persistence.model.RoomType;
import com.machapipo.hotelAPI.service.GuestService;
import com.machapipo.hotelAPI.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoToRoomConverter extends AbstractConverter<RoomDto, Room> {

    private RoomService roomService;
    private GuestService guestService;


    @Autowired
    public void setRoomService (RoomService roomService) {

        this.roomService = roomService;
    }


    @Autowired
    public void setGuestService (GuestService guestService) {

        this.guestService = guestService;
    }


    @Override
    public Room convert (RoomDto source) {

        Room room;
        if (source.getId() != null) {
            room = roomService.getById(source.getId());
            if (room == null) {
                room = new Room();
            }
        } else {
            room = new Room();
        }

        room.setRoomNumber(source.getRoomNumber());
        room.setPrice(source.getPrice());
        room.setAvailable(source.getAvailable());
        if (source.getGuestId() != null) {
            room.setGuest(guestService.getById(source.getGuestId()));
        }

        try {
            room.setRoomType(RoomType.valueOf(source.getRoomType()));
        } catch (Exception e) {
            throw new InvalidRoom("Invalid room");
        }

        return room;
    }

}
