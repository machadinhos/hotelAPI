package com.machapipo.hotelAPI.persistence.repo;

import com.machapipo.hotelAPI.persistence.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepo extends JpaRepository<Guest, Long> {

    List<Guest> findByCheckedInFalse ();

    Guest findByCheckedInTrueAndRoomId (Long roomId);

}
