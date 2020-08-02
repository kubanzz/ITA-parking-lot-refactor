package com.oocl.cultivation.story1;

import com.oocl.cultivation.story1.entity.Car;
import com.oocl.cultivation.story1.entity.Ticket;
import com.oocl.cultivation.story1.enums.ParkingFetchingEnums;
import com.oocl.cultivation.story1.exceptions.CarHaveBeenParkedException;
import com.oocl.cultivation.story1.exceptions.CarIllegalException;
import com.oocl.cultivation.story1.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.story1.exceptions.TicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractPackingBoy {
    private List<PackingLot> packingLots = new ArrayList<>();

    public AbstractPackingBoy() {
        packingLots.add(new PackingLot());
    }

    public AbstractPackingBoy(PackingLot parkingLot) {
        this.packingLots.add(parkingLot);
    }

    public AbstractPackingBoy(List<PackingLot> parkingLotList) {
        this.packingLots = new ArrayList<>(parkingLotList);
    }

    public List<PackingLot> getPackingLots() {
        return packingLots;
    }

    public Car fetchCar(Ticket ticket) {
        if (ticket == null) {
            throw new TicketException(ParkingFetchingEnums.FETCHING_HAVE_NO_TICKET);
        }

        return packingLots.stream()
                .map(packingLot -> packingLot.fetchCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new TicketException(ParkingFetchingEnums.FETCHING_ERROR_TICKET));
    }

    protected boolean isCarHaveBenParked(Car car){
        return packingLots.stream()
                .anyMatch(packingLot -> packingLot.isCarHaveBeenParked(car));
    }

    public Ticket parkCar(Car car){
        if (car == null) {
            throw new CarIllegalException(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE);
        }

        if (isCarHaveBenParked(car)) {
            throw new CarHaveBeenParkedException(ParkingFetchingEnums.PARKING_CAR_HAVE_BEEN_PARKED);
        }

        if (isParkingLotsFull()) {
            throw new NotEnoughPositionException(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE);
        }

        PackingLot packingLot = findRightParkingLot();
        return packingLot.parkCar(car);
    }

    public List<Ticket> parkCar(List<Car> carList){
        if (carList == null) {
            throw new CarIllegalException(ParkingFetchingEnums.CAR_NULL_ERROR_MESSAGE);
        }

        List<Ticket> ticketList = new ArrayList<>();
        AtomicInteger parkedCarNums = new AtomicInteger();
        carList.stream()
                .map(car -> parkCar(car))
                .filter(Objects::nonNull)
                .forEach(ticket -> {
                    parkedCarNums.getAndIncrement();
                    ticketList.add(ticket);
                });

        if (parkedCarNums.get() != carList.size()) {
            throw new NotEnoughPositionException(ParkingFetchingEnums.PARKING_HAVE_NO_SPACE);
        }
        return ticketList;
    }

    public abstract PackingLot findRightParkingLot();

    protected boolean isParkingLotsFull() {
        return packingLots.stream()
                .allMatch(PackingLot::isParkingLogFull);
    }
}
