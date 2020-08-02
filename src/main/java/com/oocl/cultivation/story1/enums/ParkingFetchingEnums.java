package com.oocl.cultivation.story1.enums;

public enum  ParkingFetchingEnums {

    CAR_NULL_ERROR_MESSAGE("the car cannot be null"),
    PARKING_CAR_HAVE_BEEN_PARKED("the car has been packed"),
    PARKING_HAVE_NO_SPACE("Not enough position."),
    FETCHING_HAVE_NO_TICKET("Please provide your parking ticket."),
    FETCHING_ERROR_TICKET("Unrecognized parking ticket.")
    ;

    private String message;

    ParkingFetchingEnums(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
