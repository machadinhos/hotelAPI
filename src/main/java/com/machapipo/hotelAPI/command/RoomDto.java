package com.machapipo.hotelAPI.command;

public class RoomDto {

    private Long id;
    private Integer roomNumber;
    private Double price;
    private Boolean isAvailable;
    private String roomType;


    public Long getId () {

        return id;
    }


    public void setId (Long id) {

        this.id = id;
    }


    public Integer getRoomNumber () {

        return roomNumber;
    }


    public void setRoomNumber (Integer roomNumber) {

        this.roomNumber = roomNumber;
    }


    public Double getPrice () {

        return price;
    }


    public void setPrice (Double price) {

        this.price = price;
    }


    public Boolean getAvailable () {

        return isAvailable;
    }


    public void setAvailable (Boolean available) {

        isAvailable = available;
    }


    public String getRoomType () {

        return roomType;
    }


    public void setRoomType (String roomType) {

        this.roomType = roomType;
    }

}