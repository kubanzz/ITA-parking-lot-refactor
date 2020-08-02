package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;
import com.oocl.cultivation.story1.exceptions.NotEnoughPositionException;

import java.util.*;

public class PackingBoy extends AbstractPackingBoy {

    public PackingBoy() {
    }

    public PackingBoy(PackingLot parkingLot) {
        super(parkingLot);
    }

    public PackingBoy(List<PackingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public PackingLot findRightParkingLot() {
        return getPackingLots().stream()
                .filter(packingLot -> !packingLot.isParkingLogFull())
                .findFirst()
                .orElseThrow(() -> new NotEnoughPositionException(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE));
    }

}
