package com.oocl.cultivation.story1.exceptions;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

public class CarHaveBeenParkedException extends BaseException{

    public CarHaveBeenParkedException(ParkingFetchingEnums enumMess) {
        super(enumMess);
    }
}
