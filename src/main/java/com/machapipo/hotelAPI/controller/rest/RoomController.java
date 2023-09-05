package com.machapipo.hotelAPI.controller.rest;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.command.converter.RoomDtoToRoomConverter;
import com.machapipo.hotelAPI.command.converter.RoomToRoomDtoConverter;
import com.machapipo.hotelAPI.exception.InvalidModel;
import com.machapipo.hotelAPI.exception.InvalidRoom;
import com.machapipo.hotelAPI.persistence.model.Room;
import com.machapipo.hotelAPI.service.RoomService;
import com.machapipo.hotelAPI.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
@RequestMapping({"/hotel/api/rooms", "/hotel/api/room"})
public class RoomController {

    private RoomService roomService;

    private RoomToRoomDtoConverter roomToRoomDtoConverter;
    private RoomDtoToRoomConverter roomDtoToRoomConverter;


    @Autowired
    public void setRoomService (RoomService roomService) {

        this.roomService = roomService;
    }


    @Autowired
    public void setRoomToRoomDtoConverter (RoomToRoomDtoConverter roomToRoomDtoConverter) {

        this.roomToRoomDtoConverter = roomToRoomDtoConverter;
    }


    @Autowired
    public void setRoomDtoToRoomConverter (RoomDtoToRoomConverter roomDtoToRoomConverter) {

        this.roomDtoToRoomConverter = roomDtoToRoomConverter;
    }


    @GetMapping({"/", "", "/list"})
    public ResponseEntity<APIResponse<List<RoomDto>>> getRooms () {

        APIResponse<List<RoomDto>> response = new APIResponse<>();

        List<Room> rooms = roomService.getAll();

        response.setSuccess(true);
        response.setMessage("Rooms found");
        response.setData(roomToRoomDtoConverter.convert(rooms));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<RoomDto>> getRoom (@PathVariable Long id) {

        APIResponse<RoomDto> response = new APIResponse<>();

        Room room = roomService.getById(id);

        if (room == null) {
            throw new InvalidRoom("Room not found");
        }

        response.setSuccess(true);
        response.setMessage("Room found");
        response.setData(roomToRoomDtoConverter.convert(room));

        return ResponseEntity.ok(response);
    }


    @PostMapping({"/", ""})
    public ResponseEntity<APIResponse<RoomDto>> createRoom (@RequestBody RoomDto roomDto) {

        APIResponse<RoomDto> response = new APIResponse<>();

        try {
            roomDto.setId(null);

            Room room = roomService.create(roomDtoToRoomConverter.convert(roomDto));

            response.setSuccess(true);
            response.setMessage("Room created successfully");
            response.setData(roomToRoomDtoConverter.convert(room));

            return ResponseEntity.ok(response);
        } catch (InvalidModel e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

            return ResponseEntity.ok(response);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<RoomDto>> updateRoom (@PathVariable Long id, @RequestBody RoomDto roomDto) {

        APIResponse<RoomDto> response = new APIResponse<>();

        try {
            Room room = roomService.getById(id);

            if (room == null) {
                throw new InvalidRoom("Room not found");
            }

            room = roomDtoToRoomConverter.convert(roomDto);

            room = roomService.update(room);

            response.setSuccess(true);
            response.setMessage("Room updated successfully");
            response.setData(roomToRoomDtoConverter.convert(room));

            return ResponseEntity.ok(response);
        } catch (InvalidModel e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

            return ResponseEntity.ok(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<RoomDto>> deleteRoom (@PathVariable Long id) {

        APIResponse<RoomDto> response = new APIResponse<>();

        try {
            Room room = roomService.getById(id);

            if (room == null) {
                throw new InvalidRoom("Room not found");
            }

            roomService.delete(room);

            response.setSuccess(true);
            response.setMessage("Room deleted successfully");
            response.setData(roomToRoomDtoConverter.convert(room));

            return ResponseEntity.ok(response);
        } catch (InvalidModel e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());

            return ResponseEntity.ok(response);
        }

    }

}
