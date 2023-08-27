package com.machapipo.hotelAPI.converter;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.model.Room;

public class RoomToRoomDtoConverter extends AbstractConverter<Room, RoomDto> {

    @Override
    public RoomDto convert (Room source) {

        RoomDto roomDto = new RoomDto();

        roomDto.setId(source.getId());
        roomDto.setRoomNumber(source.getRoomNumber());
        roomDto.setPrice(source.getPrice());
        roomDto.setAvailable(source.getAvailable());
            roomDto.setRoomType(source.getRoomType().getRoomType());

        return roomDto;
    }

}
