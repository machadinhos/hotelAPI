package com.machapipo.hotelAPI.converter;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.model.Room;
import com.machapipo.hotelAPI.model.RoomType;
import com.machapipo.hotelAPI.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoToRoomConverter extends AbstractConverter<RoomDto, Room> {

    private RoomRepo roomRepo;


    @Autowired
    public void setRoomRepo (RoomRepo roomRepo) {

        this.roomRepo = roomRepo;
    }


    @Override
    public Room convert (RoomDto source) {

        Room room;
        if (source.getId() != null) {
            room = roomRepo.findById(source.getId()).orElse(new Room());
        } else {
            room = new Room();
        }

        room.setRoomNumber(source.getRoomNumber());
        room.setPrice(source.getPrice());
        room.setAvailable(source.getAvailable());

        try {
            room.setRoomType(RoomType.valueOf(source.getRoomType()));
        } catch (IllegalArgumentException ignored) {
        }

        return room;
    }

}
