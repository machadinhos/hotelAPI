package com.machadinhos.hotelAPI.persistence.model;

public enum RoomType {

    STANDARD("Standard"),
    DELUXE("Deluxe"),
    SUITE("Suite");

    private final String roomType;


    RoomType (String roomType) {

        this.roomType = roomType;
    }


    public String getRoomType () {

        return roomType;
    }

}
