package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;

import java.util.ArrayList;
import java.util.List;

public class SmallParkingBoy extends AbstractPackingBoy{

    public SmallParkingBoy(){
        super();
    }

    public SmallParkingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    public SmallParkingBoy(List<PackingLot> packingLotList) {
        super(packingLotList);
    }

    @Override
    public PackingLot findRightParkingLot() {
        List<PackingLot> packingLotList = getPackingLots();
        PackingLot surplusMaxSpacParkingLot = null;
        for (PackingLot packingLot : packingLotList) {
            if (surplusMaxSpacParkingLot == null || surplusMaxSpacParkingLot.getRemainingSpace() < packingLot.getRemainingSpace()) {
                surplusMaxSpacParkingLot = packingLot;
            }
        }

        return surplusMaxSpacParkingLot;
    }

}
