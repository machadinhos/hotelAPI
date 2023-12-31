package com.machadinhos.hotelAPI.utils;

import com.machadinhos.hotelAPI.persistence.model.Guest;
import com.machadinhos.hotelAPI.persistence.model.Room;

import java.util.regex.Pattern;

public class ModelValidator {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    private static boolean validateEmail (String email) {

        return EMAIL_PATTERN.matcher(email).matches();
    }


    public static boolean validateGuest (Guest guest) {

        if (guest.getFirstName() == null || guest.getFirstName().isEmpty()) {
            return false;
        }
        if (guest.getLastName() == null || guest.getLastName().isEmpty()) {
            return false;
        }
        if (guest.getEmail() == null || guest.getEmail().isEmpty() || !validateEmail(guest.getEmail())) {
            return false;
        }
        if (guest.getPhoneNumber() == null || guest.getPhoneNumber().isEmpty()) {
            return false;
        }
        if (guest.getCheckedIn() == null) {
            return false;
        }
        if (guest.getCheckedIn() && guest.getRoom() == null) {
            return false;
        }

        return guest.getCheckedIn() || guest.getRoom() == null;
    }


    public static boolean validateRoom (Room room) {

        if (room.getRoomNumber() == null || room.getRoomNumber() <= 0) {
            return false;
        }
        if (room.getPrice() == null || room.getPrice() <= 0) {
            return false;
        }
        if (room.getRoomType() == null) {
            return false;
        }
        if (room.getAvailable() == null) {
            return false;
        }
        if (room.getGuest() != null && room.getAvailable()) {
            return false;
        }

        return room.getGuest() != null || room.getAvailable();
    }

}
