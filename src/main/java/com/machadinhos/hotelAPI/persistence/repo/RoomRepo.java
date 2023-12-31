package com.machadinhos.hotelAPI.persistence.repo;

import com.machadinhos.hotelAPI.persistence.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {

    List<Room> findByAvailableTrue ();

    Room findByAvailableFalseAndGuestId (Long id);

}
