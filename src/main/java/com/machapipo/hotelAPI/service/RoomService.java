package com.machapipo.hotelAPI.service;

import com.machapipo.hotelAPI.exception.InvalidRoom;
import com.machapipo.hotelAPI.persistence.model.Room;
import com.machapipo.hotelAPI.persistence.repo.GuestRepo;
import com.machapipo.hotelAPI.persistence.repo.RoomRepo;
import com.machapipo.hotelAPI.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomService implements GenericService<Room> {

    private RoomRepo roomRepo;
    private GuestRepo guestRepo;


    @Autowired
    public void setRoomRepo (RoomRepo roomRepo) {

        this.roomRepo = roomRepo;
    }


    @Autowired
    public void setGuestRepo (GuestRepo guestRepo) {

        this.guestRepo = guestRepo;
    }


    @Transactional(readOnly = true)
    public List<Room> getAvailable () {

        return roomRepo.findAvailable();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Room> getAll () {

        return roomRepo.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Room getById (Long id) {

        return roomRepo.findById(id).orElse(null);
    }


    @Override
    public Room create (Room room) throws InvalidRoom {

        if (!ModelValidator.validateRoom(room)) {
            throw new InvalidRoom();
        }

        return roomRepo.save(room);
    }


    @Override
    public Room update (Room room) throws InvalidRoom {

        if (!ModelValidator.validateRoom(room)) {
            throw new InvalidRoom();
        }

        if (room.getGuest() != null) {
            room.getGuest().setCheckedIn(true);
            room.getGuest().setRoom(room);

            guestRepo.save(room.getGuest());
        }

        return roomRepo.save(room);
    }


    @Override
    public void delete (Room room) {

        roomRepo.delete(room);
    }

}
