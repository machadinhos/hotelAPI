package com.machadinhos.hotelAPI.exception;

public abstract class InvalidModel extends RuntimeException {

    public InvalidModel (String message) {

        super(message);
    }


    public InvalidModel () {

    }

}
