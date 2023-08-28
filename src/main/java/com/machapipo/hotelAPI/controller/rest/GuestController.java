package com.machapipo.hotelAPI.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/hotel/api/guests", "/hotel/api/guest"})
public class GuestController {

    @GetMapping("/hello")
    public String hello () {

        return "Hello from GuestController";
    }

}
