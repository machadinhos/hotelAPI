package com.machapipo.hotelAPI.converter;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.exception.InvalidRoom;
import com.machapipo.hotelAPI.persistence.model.Room;
import com.machapipo.hotelAPI.persistence.model.RoomType;
import com.machapipo.hotelAPI.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoToRoomConverter extends AbstractConverter<RoomDto, Room> {

    private RoomService roomService;


    @Autowired
    public void setRoomService (RoomService roomService) {

        this.roomService = roomService;
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

        try {
            room.setRoomType(RoomType.valueOf(source.getRoomType()));
        } catch (IllegalArgumentException ignored) {
            throw new InvalidRoom();
        }

        return room;
    }

}
