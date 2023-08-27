package com.machapipo.hotelAPI.command;

import com.machapipo.hotelAPI.model.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RoomDto {

    private Integer roomNumber;
    private Double price;
    private Boolean isAvailable;
    private String roomType;


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
