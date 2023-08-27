package com.machapipo.hotelAPI.repo;

import com.machapipo.hotelAPI.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
