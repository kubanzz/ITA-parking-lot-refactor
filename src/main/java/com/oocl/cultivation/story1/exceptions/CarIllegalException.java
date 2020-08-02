package com.oocl.cultivation.story1.exceptions;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

public class CarIllegalException extends BaseException{
    public CarIllegalException(ParkingFetchingEnums enumMess) {
        super(enumMess);
    }
}
