package com.machapipo.hotelAPI.controller.rest;

import com.machapipo.hotelAPI.command.RoomDto;
import com.machapipo.hotelAPI.converter.RoomDtoToRoomConverter;
import com.machapipo.hotelAPI.converter.RoomToRoomDtoConverter;
import com.machapipo.hotelAPI.model.Room;
import com.machapipo.hotelAPI.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/hotel/api/rooms", "/hotel/api/room"})
public class RoomController {

    private RoomRepo roomRepo;

    private RoomToRoomDtoConverter roomToRoomDtoConverter;
    private RoomDtoToRoomConverter roomDtoToRoomConverter;


    @Autowired
    public void setRoomRepo (RoomRepo roomRepo) {

        this.roomRepo = roomRepo;
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
    public ResponseEntity<List<RoomDto>> getRooms () {

        List<Room> rooms = roomRepo.findAll();

        return ResponseEntity.ok(roomToRoomDtoConverter.convert(rooms));
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoom (@PathVariable Long id) {

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(roomToRoomDtoConverter.convert(room));
    }


    @PostMapping({"/", ""})
    public ResponseEntity<RoomDto> createRoom (@RequestBody RoomDto roomDto) {

        try {
            Room room = roomRepo.save(Objects.requireNonNull(roomDtoToRoomConverter.convert(roomDto)));

            return ResponseEntity.ok(roomToRoomDtoConverter.convert(room));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> updateRoom (@PathVariable Long id, @RequestBody RoomDto roomDto) {

        try {
            Room room = roomRepo.findById(id).orElse(null);

            if (room == null) {
                return ResponseEntity.notFound().build();
            }

            room = Objects.requireNonNull(roomDtoToRoomConverter.convert(roomDto));

            room = roomRepo.save(room);

            return ResponseEntity.ok(roomToRoomDtoConverter.convert(room));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RoomDto> deleteRoom (@PathVariable Long id) {

        try {
            Room room = roomRepo.findById(id).orElse(null);

            if (room == null) {
                return ResponseEntity.notFound().build();
            }

            roomRepo.delete(room);

            return ResponseEntity.ok(roomToRoomDtoConverter.convert(room));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
