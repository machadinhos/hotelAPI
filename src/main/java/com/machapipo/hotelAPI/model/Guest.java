package com.machapipo.hotelAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "guest")
public class Guest extends AbstractModel {

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @OneToOne
    private com.example.demo.model.Room room;


    public String getFirstName () {

        return firstName;
    }


    public void setFirstName (String firstName) {

        this.firstName = firstName;
    }


    public String getLastName () {

        return lastName;
    }


    public void setLastName (String lastName) {

        this.lastName = lastName;
    }


    public String getEmail () {

        return email;
    }


    public void setEmail (String email) {

        this.email = email;
    }


    public String getPhoneNumber () {

        return phoneNumber;
    }


    public void setPhoneNumber (String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }


    public com.example.demo.model.Room getRoom () {

        return room;
    }


    public void setRoom (com.example.demo.model.Room room) {

        this.room = room;
    }

}
