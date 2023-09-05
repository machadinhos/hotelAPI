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
    public Room convert (RoomDto roomDto) {

        Room room;
        if (roomDto.getId() != null) {
            room = roomService.getById(roomDto.getId());
            if (room == null) {
                room = new Room();
            }
        } else {
            room = new Room();
        }

        room.setRoomNumber(roomDto.getRoomNumber());
        room.setPrice(roomDto.getPrice());
        room.setAvailable(roomDto.getAvailable());
        if (roomDto.getGuestId() != null) {
            room.setGuest(guestService.getById(roomDto.getGuestId()));
        }

        try {
            room.setRoomType(RoomType.valueOf(roomDto.getRoomType()));
        } catch (Exception e) {
            throw new InvalidRoom("Invalid room");
        }

        return room;
    }

}
