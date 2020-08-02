package com.oocl.cultivation.story1.exceptions;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

public class BaseException extends RuntimeException {
    private String message;

    public BaseException(ParkingFetchingEnums enumMess) {
        this.message = enumMess.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
