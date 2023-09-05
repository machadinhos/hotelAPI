package com.machapipo.hotelAPI.command.converter;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.persistence.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomDtoConverter extends AbstractConverter<Room, RoomDto> {

    @Override
    public RoomDto convert (Room room) {

        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setPrice(room.getPrice());
        roomDto.setAvailable(room.getAvailable());
        roomDto.setRoomType(room.getRoomType().getRoomType());
        if (room.getGuest() != null) {
            roomDto.setGuestId(room.getGuest().getId());
        }

        return roomDto;
    }

}
