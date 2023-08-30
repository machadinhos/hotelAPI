package com.machapipo.hotelAPI.persistence.repo;

import com.machapipo.hotelAPI.persistence.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.isAvailable = true")
    List<Room> findAvailable ();

}
