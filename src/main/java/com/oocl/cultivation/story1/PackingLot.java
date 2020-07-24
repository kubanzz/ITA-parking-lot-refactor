package com.oocl.cultivation.story1;

import java.util.ArrayList;
import java.util.List;

public class PackingLot {
    private int parkingSpace;
    private List<Car> packingCarList;

    PackingLot() {
        this.parkingSpace = 10;
        this.packingCarList = new ArrayList<>();
    }

    public PackingLot(int parkingSpace) {
        this.parkingSpace = parkingSpace;
        this.packingCarList = new ArrayList<>();
    }

    int getParkingSpace() {
        return parkingSpace;
    }

    void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    List<Car> getPackingCarList() {
        return packingCarList;
    }
}
