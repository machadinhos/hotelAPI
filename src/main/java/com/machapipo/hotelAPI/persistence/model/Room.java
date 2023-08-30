package com.machapipo.hotelAPI.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room extends AbstractModel {

    @Column
    private Integer roomNumber;
    @Column
    private Double price;
    @Column
    private Boolean isAvailable;
    @Column
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @OneToOne
    private Guest guest;


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


    public RoomType getRoomType () {

        return roomType;
    }


    public void setRoomType (RoomType roomType) {

        this.roomType = roomType;
    }


    public Guest getGuest () {

        return guest;
    }


    public void setGuest (Guest guest) {

        this.guest = guest;
    }

}
