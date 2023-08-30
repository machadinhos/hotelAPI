package com.machapipo.hotelAPI.controller.rest;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.converter.GuestDtoToGuestConverter;
import com.machapipo.hotelAPI.converter.GuestToGuesDtoConverter;
import com.machapipo.hotelAPI.model.Guest;
import com.machapipo.hotelAPI.repo.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
@RequestMapping({"/hotel/api/guests", "/hotel/api/guest"})
public class GuestController {

    private GuestRepo guestRepo;

    private GuestToGuesDtoConverter guestToGuesDtoConverter;
    private GuestDtoToGuestConverter guestDtoToGuestConverter;


    @Autowired
    public void setGuestRepo (GuestRepo guestRepo) {

        this.guestRepo = guestRepo;
    }


    @Autowired
    public void setGuestToGuesDtoConverter (GuestToGuesDtoConverter guestToGuesDtoConverter) {

        this.guestToGuesDtoConverter = guestToGuesDtoConverter;
    }


    @Autowired
    public void setGuestDtoToGuestConverter (GuestDtoToGuestConverter guestDtoToGuestConverter) {

        this.guestDtoToGuestConverter = guestDtoToGuestConverter;
    }


    @GetMapping({"/", "", "/list"})
    public ResponseEntity<List<GuestDto>> getGuests () {

        List<Guest> guests = guestRepo.findAll();

        return ResponseEntity.ok(guestToGuesDtoConverter.convert(guests));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getGuest (@PathVariable Long id) {

        Guest guest = guestRepo.findById(id).orElse(null);

        if (guest == null) {
            System.out.println("GuestController.getGuest: guest == null");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(guestToGuesDtoConverter.convert(guest));
    }


    @PostMapping({"/", ""})
    public ResponseEntity<GuestDto> createGuest (@RequestBody GuestDto guestDto) {

        try {
            Guest guest = guestRepo.save(Objects.requireNonNull(guestDtoToGuestConverter.convert(guestDto)));

            return ResponseEntity.ok(guestToGuesDtoConverter.convert(guest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest (@PathVariable Long id, @RequestBody GuestDto guestDto) {

        try {
            Guest guest = guestRepo.findById(id).orElse(null);

            if (guest == null) {
                return ResponseEntity.notFound().build();
            }

            guestDto.setId(id);

            guest = Objects.requireNonNull(guestDtoToGuestConverter.convert(guestDto));

            guest = guestRepo.save(guest);

            return ResponseEntity.ok(guestToGuesDtoConverter.convert(guest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest (@PathVariable Long id) {

        try {
            Guest guest = guestRepo.findById(id).orElse(null);

            if (guest == null) {
                return ResponseEntity.notFound().build();
            }

            guestRepo.delete(guest);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
