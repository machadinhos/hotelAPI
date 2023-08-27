package com.machapipo.hotelAPI.repo;

import com.example.demo.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo extends JpaRepository<Guest, Long> {

}
