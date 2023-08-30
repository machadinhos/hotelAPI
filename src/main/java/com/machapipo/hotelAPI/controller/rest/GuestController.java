package com.machapipo.hotelAPI.controller.rest;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.converter.GuestDtoToGuestConverter;
import com.machapipo.hotelAPI.converter.GuestToGuesDtoConverter;
import com.machapipo.hotelAPI.persistence.model.Guest;
import com.machapipo.hotelAPI.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
@RequestMapping({"/hotel/api/guests", "/hotel/api/guest"})
public class GuestController {

    private GuestService guestService;

    private GuestToGuesDtoConverter guestToGuesDtoConverter;
    private GuestDtoToGuestConverter guestDtoToGuestConverter;


    @Autowired
    public void setGuestRepo (GuestService guestService) {

        this.guestService = guestService;
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

        List<Guest> guests = guestService.getAll();

        return ResponseEntity.ok(guestToGuesDtoConverter.convert(guests));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getGuest (@PathVariable Long id) {

        Guest guest = guestService.getById(id);

        if (guest == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(guestToGuesDtoConverter.convert(guest));
    }


    @PostMapping({"/", ""})
    public ResponseEntity<GuestDto> createGuest (@RequestBody GuestDto guestDto) {

        try {
            Guest guest = guestService.create(Objects.requireNonNull(guestDtoToGuestConverter.convert(guestDto)));

            return ResponseEntity.ok(guestToGuesDtoConverter.convert(guest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest (@PathVariable Long id, @RequestBody GuestDto guestDto) {

        try {
            Guest guest = guestService.getById(id);

            if (guest == null) {
                return ResponseEntity.notFound().build();
            }

            guestDto.setId(id);

            guest = Objects.requireNonNull(guestDtoToGuestConverter.convert(guestDto));

            guest = guestService.update(guest);

            return ResponseEntity.ok(guestToGuesDtoConverter.convert(guest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest (@PathVariable Long id) {

        try {
            Guest guest = guestService.getById(id);

            if (guest == null) {
                return ResponseEntity.notFound().build();
            }

            guestService.delete(guest);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
