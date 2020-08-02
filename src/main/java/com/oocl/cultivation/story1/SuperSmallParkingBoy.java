package com.oocl.cultivation.story1;

import java.util.List;

public class SuperSmallParkingBoy extends AbstractPackingBoy {


    public SuperSmallParkingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    public SuperSmallParkingBoy(List<PackingLot> packingLotList) {
        super(packingLotList);
    }

    public SuperSmallParkingBoy() {
        super();
    }

    @Override
    public PackingLot findRightParkingLot() {
        List<PackingLot> packingLotList = getPackingLots();
        PackingLot maxSiteRateParkingLot = null;
        double maxSiteRate = 0;

        // TODO stream
        for (PackingLot packingLot : packingLotList) {
            if (maxSiteRateParkingLot == null || maxSiteRate < calculateSiteRate(packingLot)) {
                maxSiteRateParkingLot = packingLot;
                maxSiteRate = calculateSiteRate(packingLot);
            }
        }

        return maxSiteRateParkingLot;
    }

    private double calculateSiteRate(PackingLot parkingLot) {
        if (parkingLot.getParkingSpace() == 0) {
            return 0;
        }
        return (parkingLot.getRemainingSpace() + 0.0) / parkingLot.getParkingSpace();
    }

}
