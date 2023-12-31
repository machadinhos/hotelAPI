package com.machadinhos.hotelAPI.service;

import com.machadinhos.hotelAPI.exception.InvalidGuest;
import com.machadinhos.hotelAPI.persistence.model.Guest;
import com.machadinhos.hotelAPI.persistence.model.Room;
import com.machadinhos.hotelAPI.persistence.repo.GuestRepo;
import com.machadinhos.hotelAPI.persistence.repo.RoomRepo;
import com.machadinhos.hotelAPI.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GuestService implements GenericService<Guest> {

    private GuestRepo guestRepo;
    private RoomRepo roomRepo;


    @Autowired
    public void setGuestRepo (GuestRepo guestRepo) {

        this.guestRepo = guestRepo;
    }


    @Autowired
    public void setRoomRepo (RoomRepo roomRepo) {

        this.roomRepo = roomRepo;
    }


    @Transactional(readOnly = true)
    public List<Guest> getNotCheckedIn () {

        return guestRepo.findByCheckedInFalse();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Guest> getAll () {

        return guestRepo.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Guest getById (Long id) {

        return guestRepo.findById(id).orElse(null);
    }


    @Override
    public Guest create (Guest guest) throws InvalidGuest {

        validateGuest(guest);

        if (guest.getRoom() != null) {
            if (!guest.getRoom().getAvailable() && guest.getRoom().getGuest() != guest) {
                throw new InvalidGuest("Room not available");
            }

            guest.getRoom().setAvailable(false);
            guest.getRoom().setGuest(guest);

            roomRepo.save(guest.getRoom());
        }

        return guestRepo.save(guest);
    }


    @Override
    public Guest update (Guest guest) throws InvalidGuest {

        validateGuest(guest);

        if (guest.getRoom() != null) {
            if (!guest.getRoom().getAvailable() && guest.getRoom().getGuest() != guest) {
                throw new InvalidGuest("Room not available");
            }

            guest.getRoom().setAvailable(false);
            guest.getRoom().setGuest(guest);

            roomRepo.save(guest.getRoom());
        } else {
            Room room = roomRepo.findByAvailableFalseAndGuestId(guest.getId());

            if (room != null) {
                room.setAvailable(true);
                room.setGuest(null);

                roomRepo.save(room);
            }
        }

        return guestRepo.save(guest);
    }


    @Override
    public void delete (Guest guest) throws InvalidGuest {

        if (guestRepo.findById(guest.getId()).orElse(null) == null) {
            throw new InvalidGuest("Guest not found");
        }

        if (guest.getRoom() != null) {
            guest.getRoom().setAvailable(true);
            guest.getRoom().setGuest(null);

            roomRepo.save(guest.getRoom());
        }

        guestRepo.delete(guest);
    }


    private void validateGuest (Guest guest) {

        if (!ModelValidator.validateGuest(guest)) {
            throw new InvalidGuest("Invalid guest");
        }
    }

}
