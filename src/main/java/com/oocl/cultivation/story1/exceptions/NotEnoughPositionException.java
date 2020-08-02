package com.oocl.cultivation.story1.exceptions;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

public class NotEnoughPositionException extends BaseException{

    public NotEnoughPositionException(ParkingFetchingEnums carNullErrorMessage) {
        super(carNullErrorMessage);
    }
}
