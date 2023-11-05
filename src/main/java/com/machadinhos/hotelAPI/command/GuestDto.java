package com.machadinhos.hotelAPI.command;

public class GuestDto implements DtoModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean checkedIn;
    private Long roomId;


    public Long getId () {

        return id;
    }


    public void setId (Long id) {

        this.id = id;
    }


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


    public Boolean getCheckedIn () {

        return checkedIn;
    }


    public void setCheckedIn (Boolean checkedIn) {

        this.checkedIn = checkedIn;
    }


    public Long getRoomId () {

        return roomId;
    }


    public void setRoomId (Long roomId) {

        this.roomId = roomId;
    }

}
