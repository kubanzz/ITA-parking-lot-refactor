package com.oocl.cultivation.story1.exceptions;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

public class TicketException extends BaseException{

    public TicketException(ParkingFetchingEnums enumMess) {
        super(enumMess);
    }
}
