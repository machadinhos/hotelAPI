package com.machapipo.hotelAPI.exception;

public abstract class InvalidModel extends RuntimeException {

    public InvalidModel (String message) {

        super(message);
    }


    public InvalidModel () {

    }

}
