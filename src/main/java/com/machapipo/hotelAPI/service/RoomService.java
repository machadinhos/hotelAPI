package com.machapipo.hotelAPI.service;

import com.machapipo.hotelAPI.exception.InvalidRoom;
import com.machapipo.hotelAPI.persistence.model.Guest;
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

        return roomRepo.findByAvailableTrue();
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

        validateRoom(room);

        if (room.getGuest() != null) {
            if (room.getGuest().getCheckedIn() && room.getGuest().getRoom() != room) {
                throw new InvalidRoom("Guest already checked in");
            }

            room.getGuest().setCheckedIn(true);
            room.getGuest().setRoom(room);

            guestRepo.save(room.getGuest());
        }

        return roomRepo.save(room);
    }


    @Override
    public Room update (Room room) throws InvalidRoom {

        validateRoom(room);

        if (room.getGuest() != null) {
            if (room.getGuest().getCheckedIn() && room.getGuest().getRoom() != room) {
                throw new InvalidRoom("Guest already checked in");
            }

            room.getGuest().setCheckedIn(true);
            room.getGuest().setRoom(room);

            guestRepo.save(room.getGuest());
        } else {
            Guest guest = guestRepo.findByCheckedInTrueAndRoomId(room.getId());

            if (guest != null) {
                guest.setCheckedIn(false);
                guest.setRoom(null);

                guestRepo.save(guest);
            }
        }

        return roomRepo.save(room);
    }


    @Override
    public void delete (Room room) throws InvalidRoom {

        if (roomRepo.findById(room.getId()).orElse(null) == null) {
            throw new InvalidRoom("Room not found");
        }

        if (room.getGuest() != null) {
            room.getGuest().setCheckedIn(false);
            room.getGuest().setRoom(null);

            guestRepo.save(room.getGuest());
        }

        roomRepo.delete(room);
    }


    private void validateRoom (Room room) {

        if (!ModelValidator.validateRoom(room)) {
            throw new InvalidRoom("Invalid room");
        }
    }

}
