package com.machapipo.hotelAPI.command.converter;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.persistence.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomDtoConverter extends AbstractConverter<Room, RoomDto> {

    @Override
    public RoomDto convert (Room source) {

        RoomDto roomDto = new RoomDto();

        roomDto.setId(source.getId());
        roomDto.setRoomNumber(source.getRoomNumber());
        roomDto.setPrice(source.getPrice());
        roomDto.setAvailable(source.getAvailable());
        roomDto.setRoomType(source.getRoomType().getRoomType());
        if (source.getGuest() != null) {
            roomDto.setGuestId(source.getGuest().getId());
        }

        return roomDto;
    }

}
