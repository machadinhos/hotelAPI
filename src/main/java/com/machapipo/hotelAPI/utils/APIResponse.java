package com.machapipo.hotelAPI.utils;

public class APIResponse<T> {

    private final Boolean success;
    private final String message;
    private final T data;


    public APIResponse (Boolean success, String message, T data) {

        this.success = success;
        this.message = message;
        this.data = data;
    }


    public Boolean getSuccess () {

        return success;
    }


    public String getMessage () {

        return message;
    }


    public T getData () {

        return data;
    }

}
