package com.machapipo.hotelAPI.service;

import com.machapipo.hotelAPI.exception.InvalidGuest;
import com.machapipo.hotelAPI.model.Guest;
import com.machapipo.hotelAPI.repo.GuestRepo;
import com.machapipo.hotelAPI.repo.RoomRepo;
import com.machapipo.hotelAPI.utils.ModelValidator;
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

        return guestRepo.findNotCheckedIn();
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

        if (!ModelValidator.validateGuest(guest)) {

            throw new InvalidGuest();
        }

        return guestRepo.save(guest);
    }


    @Override
    public Guest update (Guest guest) throws InvalidGuest {

        if (!ModelValidator.validateGuest(guest)) {
            throw new InvalidGuest();
        }

        if (guest.getRoom() != null) {
            guest.getRoom().setAvailable(false);
            guest.getRoom().setGuest(guest);

            roomRepo.save(guest.getRoom());
        }

        return guestRepo.save(guest);
    }


    @Override
    public void delete (Long id) {

        guestRepo.deleteById(id);
    }

}
