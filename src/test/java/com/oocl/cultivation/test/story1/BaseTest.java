package com.oocl.cultivation.test.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

public class BaseTest {
    protected final String CAR_NULL_ERROR_MESSAGE = ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE.getMessage();
    protected final String PARKING_CAR_HAVE_BEEN_PARKED = ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED.getMessage();
    protected final String PARKING_HAVE_NO_SPACE = ParkingFetchingEnums.PARKING_HAVE_NO_SPACE.getMessage();
    protected final String FETCHING_HAVE_NO_TICKET = ParkingFetchingEnums.FETCHING_HAVE_NO_TICKET.getMessage();
    protected final String FETCHING_ERROR_TICKET = ParkingFetchingEnums.FETCHING_ERROR_TICKET.getMessage();
}
