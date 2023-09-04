package com.machapipo.hotelAPI.controller.rest;

import com.machapipo.hotelAPI.command.GuestDto;
import com.machapipo.hotelAPI.command.converter.GuestDtoToGuestConverter;
import com.machapipo.hotelAPI.command.converter.GuestToGuesDtoConverter;
import com.machapipo.hotelAPI.exception.InvalidModel;
import com.machapipo.hotelAPI.persistence.model.Guest;
import com.machapipo.hotelAPI.service.GuestService;
import com.machapipo.hotelAPI.utils.APIResponse;
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
    public ResponseEntity<APIResponse<List<GuestDto>>> getGuests () {

        APIResponse<List<GuestDto>> response = new APIResponse<>();

        List<Guest> guests = guestService.getAll();

        response.setSuccess(true);
        response.setMessage("Guests found");
        response.setData(guestToGuesDtoConverter.convert(guests));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<GuestDto>> getGuest (@PathVariable Long id) {

        APIResponse<GuestDto> response = new APIResponse<>();

        Guest guest = guestService.getById(id);

        if (guest == null) {
            response.setSuccess(false);
            response.setMessage("Guest not found");

            return ResponseEntity.ok(response);
        }

        response.setSuccess(true);
        response.setMessage("Guest found");
        response.setData(guestToGuesDtoConverter.convert(guest));

        return ResponseEntity.ok(response);
    }


    @PostMapping({"/", ""})
    public ResponseEntity<APIResponse<GuestDto>> createGuest (@RequestBody GuestDto guestDto) {

        APIResponse<GuestDto> response = new APIResponse<>();

        try {
            guestDto.setId(null);

            Guest guest = guestService.create(Objects.requireNonNull(guestDtoToGuestConverter.convert(guestDto)));

            response.setSuccess(true);
            response.setMessage("Guest created successfully");
            response.setData(guestToGuesDtoConverter.convert(guest));

            return ResponseEntity.ok(response);
        } catch (InvalidModel e) {

            response.setMessage(e.getMessage());
            response.setSuccess(false);

            return ResponseEntity.badRequest().body(response);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<GuestDto>> updateGuest (@PathVariable Long id, @RequestBody GuestDto guestDto) {

        APIResponse<GuestDto> response = new APIResponse<>();

        try {
            Guest guest = guestService.getById(id);

            if (guest == null) {
                return ResponseEntity.notFound().build();
            }

            guestDto.setId(id);

            guest = Objects.requireNonNull(guestDtoToGuestConverter.convert(guestDto));

            guest = guestService.update(guest);

            response.setSuccess(true);
            response.setMessage("Guest updated successfully");
            response.setData(guestToGuesDtoConverter.convert(guest));

            return ResponseEntity.ok(response);
        } catch (InvalidModel e) {

            response.setMessage(e.getMessage());
            response.setSuccess(false);

            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteGuest (@PathVariable Long id) {

        APIResponse<Void> response = new APIResponse<>();

        try {
            Guest guest = guestService.getById(id);

            if (guest == null) {
                return ResponseEntity.notFound().build();
            }

            guestService.delete(guest);

            response.setSuccess(true);
            response.setMessage("Guest deleted successfully");

            return ResponseEntity.ok(response);
        } catch (InvalidModel e) {

            response.setMessage(e.getMessage());
            response.setSuccess(false);

            return ResponseEntity.badRequest().body(response);
        }
    }

}
