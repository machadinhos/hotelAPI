package com.machadinhos.hotelAPI.persistence.model;

public enum RoomType {

    Standard("Standard"),
    Deluxe("Deluxe"),
    Suite("Suite");

    private final String roomType;


    RoomType (String roomType) {

        this.roomType = roomType;
    }


    public String getRoomType () {

        return roomType;
    }

}
