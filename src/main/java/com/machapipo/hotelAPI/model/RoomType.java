package com.machapipo.hotelAPI.model;

public enum RoomType {

    Standard("Standard"),
    Deluxe("Deluxe"),
    SuperDeluxe("Super Deluxe"),
    Suite("Suite");

    private final String roomType;

    RoomType(String roomType) {

        this.roomType = roomType;
    }

    public String getRoomType () {

        return roomType;
    }

}
