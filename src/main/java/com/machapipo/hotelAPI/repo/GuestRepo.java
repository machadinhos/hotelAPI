package com.machapipo.hotelAPI.repo;

import com.machapipo.hotelAPI.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuestRepo extends JpaRepository<Guest, Long> {

    @Query("select g from Guest g where g.isCheckedIn = false")
    List<Guest> findNotCheckedIn ();

}
